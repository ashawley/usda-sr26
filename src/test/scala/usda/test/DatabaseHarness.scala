package usda.test

import scala.slick.jdbc.JdbcBackend.Database

import scala.util.Random

import usda.DAO

trait DatabaseHarness {
  trait MySQL extends DAO {
    val profile = scala.slick.driver.MySQLDriver
    val db = Database.forURL(
      "jdbc:mysql://127.0.0.1/usda",
      user = "root", password = "",
      driver = "com.mysql.jdbc.Driver"
    )
  }

  trait H2 extends DAO {
    val profile = scala.slick.driver.H2Driver
    val db = Database.forURL(
      "jdbc:h2:mem:test" + Random.alphanumeric.take(128),
      driver = "org.h2.Driver"
    )
  }
}
