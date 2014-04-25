package usda

trait UsdaJson {
  import org.json4s._
  import org.json4s.jackson.JsonMethods._
  import org.json4s.{ DefaultFormats, Formats }

  implicit val jsonFormats: Formats = DefaultFormats.withBigDecimal +
    FieldSerializer[Food]() +
    FieldSerializer[Size]() +
    FieldSerializer[Nutrition]()
}
