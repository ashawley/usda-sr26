package usda

object App extends MySqlResource {

  def main(args: Array[String]) {
    val foods = new Foods with MySQL {}
    val nutrition = new Nutrition with MySQL {}
    val weight = new Weight with MySQL {}
    foods.ndbNo("Apples, raw, with skin")
    weight.sizes("01123")
    nutrition.forFood("05082")
    foods.detail(0, 2)
  }
}

