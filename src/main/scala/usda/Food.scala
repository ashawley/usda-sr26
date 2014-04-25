package usda

case class Food(
  ndbNo: String,
  name: String,
  brand: String,
  sizes: Seq[Size],
  nutrients: Seq[Nutrient])

case class FoodResult(
  ndbNo: String,
  name: String,
  brand: String,
  seq: String,
  amount: BigDecimal,
  description: String,
  grams: BigDecimal)
