package usda

import scala.slick.jdbc.JdbcBackend.Database

trait Nutrition extends Tables with DAO {
  import profile.simple._

  def forFood(ndbNo: String) =
    db.withSession { implicit session =>
      val q = for {
        (n, d) <- NutData.filter(_.ndbNo is ndbNo) leftJoin
          NutrDef on (_.nutrNo === _.nutrNo)
      } yield (n.nutrNo, d.nutrdesc, n.nutrVal, d.units)

      q.list.map(_ match {
        case (nutrNo, nutrdesc, nutrVal, unit) => Nutrient(nutrNo, nutrdesc, nutrVal, unit)
      })
    }

  def nutrients(ndbNo: String): List[String] =
    db.withSession { implicit session =>
      NutData.filter(_.ndbNo is ndbNo).map(_.nutrNo).list
    }

  def name(nutrNo: String): Option[String] =
    db.withSession { implicit session =>
      NutrDef.filter(_.nutrNo is nutrNo).map(_.nutrdesc).firstOption
    }

  def units(nutrNo: String): Option[String] =
    db.withSession { implicit session =>
      NutrDef.filter(_.nutrNo is nutrNo).map(_.units).firstOption
    }
}
