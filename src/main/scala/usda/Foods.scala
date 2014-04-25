package usda

import scala.slick.jdbc.JdbcBackend.Database

trait Foods extends Tables with DAO with Usda {
  import profile.simple._

  val nutritionUsed = Seq(
    "203", // Protein
    "204", // Total lipid (fat)
    "205", // Carbohydrate, by difference
    "208", // Energy
    "269", // Sugars, total
    "291", // Fiber, total dietary
    "301", // Calcium, Ca
    "303", // Iron, Fe
    "304", // Magnesium, Mg
    "306", // Potassium, K
    "307", // Sodium, Na
    "309", // Zinc, Zn
    "320", // Vitamin A, RAE
    "323", // Vitamin E (alpha-tocopherol)
    "324", // Vitamin D
    "401", // Vitamin C, total ascorbic acid
    "415", // Vitamin B-6
    "417", // Folate, total
    "418", // Vitamin B-12
    "432", // Folate, food
    "601", // Cholesterol
    "605", // Fatty acids, total trans
    "606", // Fatty acids, total saturated
    "645", // Fatty acids, total monounsaturated
    "646" // Fatty acids, total polyunsaturated
  )

  def forNdbNo(ndbNo: String): Option[Food] =
    db.withSession { implicit session =>
      val q = for {
        (((f, d), n), w) <- FoodDes.filter(_.ndbNo is ndbNo).sortBy(_.ndbNo) leftJoin
          NutData.filter(_.nutrNo inSetBind nutritionUsed).sortBy(_.nutrNo) on (_.ndbNo === _.ndbNo) leftJoin
          NutrDef on (_._2.nutrNo === _.nutrNo) leftJoin
          Weight.sortBy(_.seq) on (_._1._1.ndbNo === _.ndbNo)
      } yield (f.ndbNo, f.longDesc, f.manufacname,
        w.seq, w.amount, w.msreDesc, w.gmWgt,
        d.nutrNo, n.nutrdesc, d.nutrVal, n.units
      )
      q.list.groupBy(_._1).values.map(food => food.head match {
        case (ndbNo, longDesc, manufacname, _, _, _, _, _, _, _, _) =>
          Food(ndbNo, longDesc, manufacname,
            unitSize :: food.map(_ match {
              case (_, _, _, seq, amount, msreDesc, gmWgt, _, _, _, _) =>
                Size(seq, amount, msreDesc, gmWgt)
            }).toSet.toList,
            food.map(_ match {
              case (_, _, _, _, _, _, _, nutrNo, nutrdesc, nutrVal, units) =>
                Nutrient(nutrNo, nutrdesc, nutrVal, units)
            }).toSet.toList)
      }).headOption
    }

  def detail(offset: Int, limit: Int): List[Food] =
    db.withSession { implicit session =>
      val q = for {
        ((((f0, f1), d), n), w) <- FoodDes.drop(offset).take(limit) leftJoin
          FoodDes on (_.ndbNo === _.ndbNo) leftJoin
          NutData.filter(_.nutrNo inSetBind nutritionUsed) on (_._2.ndbNo === _.ndbNo) leftJoin
          NutrDef on (_._2.nutrNo === _.nutrNo) leftJoin
          Weight.sortBy(_.seq) on (_._1._1._2.ndbNo === _.ndbNo)
      } yield (f1.ndbNo, f1.longDesc, f1.manufacname,
        w.seq, w.amount, w.msreDesc, w.gmWgt,
        d.nutrNo, n.nutrdesc, d.nutrVal, n.units
      )
      q.list.groupBy(_._1).values.map(food => food.head match {
        case (ndbNo, longDesc, manufacname, _, _, _, _, _, _, _, _) =>
          Food(ndbNo, longDesc, manufacname,
            unitSize :: food.map(_ match {
              case (_, _, _, seq, amount, msreDesc, gmWgt, _, _, _, _) =>
                Size(seq, amount, msreDesc, gmWgt)
            }).toSet.toList,
            food.map(_ match {
              case (_, _, _, _, _, _, _, nutrNo, nutrdesc, nutrVal, units) =>
                Nutrient(nutrNo, nutrdesc, nutrVal, units)
            }).toSet.toList)
      }).toList
    }

  def summary(offset: Int, limit: Int): List[FoodResult] =
    db.withSession { implicit session =>
      val q = for {
        ((f0, f1), w) <- FoodDes.drop(offset).take(limit) leftJoin
          FoodDes on (_.ndbNo === _.ndbNo) leftJoin
          Weight.sortBy(_.seq) on (_._2.ndbNo === _.ndbNo)
      } yield (f1.ndbNo, f1.longDesc, f1.manufacname,
        w.seq, w.amount, w.msreDesc, w.gmWgt
      )
      q.list.map(_ match {
        case (ndbNo, longDesc, manufacname, seq, amount, msreDesc, gmWgt) =>
          FoodResult(ndbNo, longDesc, manufacname, seq, amount, msreDesc, gmWgt)
      })
    }

  def ndbNo(longDesc: String): Option[String] =
    db.withSession { implicit session =>
      FoodDes.filter(_.longDesc is longDesc).
        map(_.ndbNo).firstOption
    }

  def startsWith(q: String, limit: Int): List[String] =
    db.withSession { implicit session =>
      FoodDes.filter(_.longDesc.startsWith(q)).
        map(_.longDesc).take(limit).list
    }

  def code(ndbNo: String): Option[String] =
    db.withSession { implicit session =>
      FoodDes.filter(_.ndbNo is ndbNo).
        map(_.fdgrpCd).firstOption
    }

  def group(ndbNo: String): Option[String] =
    db.withSession { implicit session =>
      FdGroup.leftJoin(FoodDes.filter(_.ndbNo is ndbNo)).
        on(_.fdgrpCd === _.fdgrpCd).
        map(_._1.fdgrpDesc).firstOption
    }

  def groupName(fdgrpCd: String): Option[String] =
    db.withSession { implicit session =>
      FdGroup.filter(_.fdgrpCd is fdgrpCd).
        map(_.fdgrpDesc).firstOption
    }
}
