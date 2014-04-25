package usda

import java.io.PrintWriter

object Json extends MySqlResource with UsdaJson {

  import org.json4s.native.Serialization.write

  def main(args: Array[String]) {
    val foods = new Foods with MySQL {}
    (foods.detail(0, 20) ++
      foods.detail(21, 20) ++
      foods.detail(41, 20) ++
      foods.detail(61, 20) ++
      foods.detail(81, 20)).foreach { f =>
        val json = write(f)
        val output = new PrintWriter("data/" + f.ndbNo + ".json")
        output.write(json)
        output.close
      }

  }
}
