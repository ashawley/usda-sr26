package usda.test

import usda.Tables

import org.specs2.mutable.Specification

class TablesSpec extends Specification with DatabaseHarness {

  "MySQL driver" should {
    "be connected" in {
      new MySQL {
        db.createConnection
      }
      success
    }
  }

  "H2 driver" should {
    "be connected" in {
      new H2 {
        db.createConnection
      }
      success
    }
  }

  "H2 database" should {
    "be able to add tables" in {
      new Tables with H2 {
        import profile.simple._
        db.withSession { implicit session =>
          ddl.create
        }
      }
      success
    }

    "be able to insert records" in {
      new Tables with H2 {
        import profile.simple._
        db.withSession { implicit session =>
          ddl.create
          FdGroup += FdGroupRow("0100", "Dairy and Egg Products")
          FoodDes += FoodDesRow("01256", "0100", "Yogurt, Greek, plain, nonfat", "YOGURT,GREEK,PLN,NONFAT", "", "", "Y", "", 0, "", 0.00, 0.00, 0.00, 0.00)
          Weight += WeightRow("01256", "1", 1.000, "container", 170.00, 1, 0.000)
          NutrDef += NutrDefRow("203", "g", "PROCNT", "Protein", "2", 600)
          NutrDef += NutrDefRow("204", "g", "FAT", "Total lipid (fat)", "2", 800)
          NutrDef += NutrDefRow("300", "kcal", "ENERC_KCAL", "Energy", "0", 300)
          NutData += NutDataRow("01256", "203", 10.120, 16, 0.179, "1", "A", "", "", 1, 8.870, 11.740, 5, 9.740, 10.643, "2, 3", "", "0")
          NutData += NutDataRow("01256", "204", 0.390, 18, 0.032, "1", "A", "", "", 1, 0.230, 0.610, 7, 0.314, 0.466, "2, 3", "", "0")
          // NutData += NutDataRow("15209", "203", 25.440, 0, 0.000, "1", "", "", "", 0, 0.000, 0.000, 0, 0.000, 0.000, "", "", "0")
          FoodDes.length.run === 1
          val q = for {
            (((f, d), n), w) <- FoodDes leftJoin
              NutData on (_.ndbNo === _.ndbNo) leftJoin
              NutrDef on (_._2.nutrNo === _.nutrNo) leftJoin
              Weight.sortBy(_.seq) on (_._1._2.ndbNo === _.ndbNo)
          } yield (f.ndbNo, f.longDesc, f.manufacname, w.seq, w.amount, w.msreDesc, w.gmWgt, d.nutrNo, n.nutrdesc, d.nutrVal, n.units)
          q.take(1).list.groupBy(_._1).values.map(food => food.head match {
            case (ndbNo, longDesc, manufacname, _, _, _, _, _, _, _, _) =>
              Seq(ndbNo, longDesc, manufacname,
                food.map(_ match {
                  case (_, _, _, seq, amount, msreDesc, gmWgt, _, _, _, _) => Seq(seq, amount, msreDesc, gmWgt)
                }).toSet.toList,
                food.map(_ match {
                  case (_, _, _, _, _, _, _, nutrNo, nutrdesc, nutrVal, units) => Seq(nutrNo, nutrdesc, nutrVal, units)
                }).toSet.toList)
          }) === List(List("01256", "Yogurt, Greek, plain, nonfat", "", List(List("1", BigDecimal("1.00"), "container", BigDecimal("170.00"))), List(List("203", "Protein", BigDecimal("10.12"), "g"))))
        }
      }
      success
    }
    "be able to drop tables" in {
      new Tables with H2 {
        import profile.simple._
        db.withSession { implicit session =>
          ddl.create
          ddl.drop
        }
      }
      success
    }

  }

  "MySQL database" should {
    "be able to get a record" in {
      new Tables with MySQL {
        import profile.simple._
        db.withSession { implicit session =>
          FoodDes.filter(_.ndbNo === "01256").first.longDesc === "Yogurt, Greek, plain, nonfat"
        }
      }
      success
    }

    "find a key with an index" in {
      new Tables with MySQL {
        import profile.simple._
        db.withSession { implicit session =>
          FoodDes.filter(_.longDesc === "Chicken, broilers or fryers, leg, meat only, cooked, roasted").map(_.ndbNo).firstOption must beSome("05082")
        }
      }
      success
    }

    "get joined records" in {
      new Tables with MySQL {
        import profile.simple._
        db.withSession { implicit session =>
          val q = for {
            (((f, n), d), w) <- FoodDes.filter(_.ndbNo is "05082") leftJoin
              NutData on (_.ndbNo === _.ndbNo) leftJoin
              NutrDef on (_._2.nutrNo === _.nutrNo) leftJoin
              Weight.sortBy(_.seq) on (_._1._2.ndbNo === _.ndbNo)

          } yield (n.ndbNo, f.longDesc, d.nutrdesc, n.nutrVal,
            w.amount, w.msreDesc, w.gmWgt
          )
          q.take(1).list.head === Tuple7("05082", "Chicken, broilers or fryers, leg, meat only, cooked, roasted", "Protein", BigDecimal("24.220"), BigDecimal("3.0"), "oz", BigDecimal("85.0"))
        }
      }
      success
    }

    "get grouped records" in {
      new Tables with MySQL {
        import profile.simple._
        db.withSession { implicit session =>
          val q2 = for {
            n <- NutrDef if (n.nutrNo.inSetBind(Seq("203", "204")))
          } yield (n.nutrNo, n.nutrdesc, n.units)
          q2.list.groupBy(_._1) === Map(
            "203" -> List(("203", "Protein", "g")),
            "204" -> List(("204", "Total lipid (fat)", "g")))
        }
      }
      success
    }
  }
}
