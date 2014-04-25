package usda

trait DAO {
  val db: scala.slick.jdbc.JdbcBackend.DatabaseDef
}
