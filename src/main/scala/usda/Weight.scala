package usda

import scala.slick.jdbc.JdbcBackend.Database

trait Weight extends Tables with DAO with Usda {
  import profile.simple._

  def foodSizes(ndbNo: String): List[(String, String)] =
    db.withSession { implicit session =>
      Weight.filter(_.ndbNo is ndbNo).
        sortBy(w => (w.ndbNo, w.seq)).
        map(w => (w.seq, w.msreDesc)).list
    }

  def sizes(ndbNo: String): List[(String, String)] =
    db.withSession { implicit session =>
      Weight.filter(_.ndbNo is ndbNo).
        sortBy(w => (w.ndbNo, w.seq)).
        map(w => (w.seq, w.msreDesc)).list
    }

  def size(ndbNo: String, seq: String): Option[Size] =
    db.withSession { implicit session =>
      Weight.filter(w => (w.ndbNo is ndbNo) && (w.seq is seq)).
        map(w => (w.seq, w.amount, w.msreDesc, w.gmWgt)).
        firstOption.map {
          case (seq, amount, msreDesc, gmWgt) =>
            Size(seq, amount, msreDesc, gmWgt)
          case _ if (seq == "0") => unitSize
        }
    }

  def weightId(ndbNo: String, size: String): Option[String] =
    db.withSession { implicit session =>
      Weight.filter(w => (w.ndbNo is ndbNo) && (w.msreDesc is size)).
        map(_.seq).firstOption
    }

  def grams(ndbNo: String, seq: String): Option[BigDecimal] =
    db.withSession { implicit session =>
      Weight.filter(w => (w.ndbNo is ndbNo) && (w.seq is seq)).
        map(_.gmWgt).firstOption
    }
}
