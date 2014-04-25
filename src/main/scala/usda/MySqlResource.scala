package usda

import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory

import scala.slick.jdbc.JdbcBackend.Database

trait MySqlResource {
  val config = ConfigFactory.load("db/mysql")

  val url = "jdbc:mysql://" +
    config.getString("db.host") +
    // ":" + config.getString("db.port") +
    "/" + config.getString("db.database")

  val driver = "com.mysql.jdbc.Driver"

  trait MySQL {
    val profile = scala.slick.driver.MySQLDriver

    val db = Database.forURL(
      url,
      user = config.getString("db.user"),
      password = config.getString("db.password"),
      driver = "com.mysql.jdbc.Driver"
    )
  }
}
