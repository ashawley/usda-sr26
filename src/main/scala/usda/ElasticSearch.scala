package usda

object ElasticSearch extends MySqlResource with UsdaJson {

  import org.json4s.native.Serialization.write
  import org.json4s.native.Serialization.read

  import com.sksamuel.elastic4s.ElasticClient
  import com.sksamuel.elastic4s.ElasticDsl._
  import com.sksamuel.elastic4s.source._

  import scala.concurrent._
  import ExecutionContext.Implicits.global
  import scala.util.{ Try, Success, Failure }

  val client = ElasticClient.remote("localhost", 9200)

  def createIndex = client.execute { create index "usda" }

  def searchButter = client.execute { search in "usda/sr26" query "Butter" }

  def flush = client.execute { delete index "usda" }

  def close = client.close

  def printFuture[T](f: Try[T]) = {
    f match {
      case Success(f) => println(f)
      case Failure(e) => println(e.getMessage)
    }
    f
  }

  def printResponse(f: Try[org.elasticsearch.action.index.IndexResponse]) =
    f match {
      case Success(resp) =>
        println(Map(
          "index" -> resp.getIndex,
          "id" -> resp.getId,
          "type" -> resp.getType,
          "version" -> resp.getVersion
        ))
      case _ => printFuture(f)
    }

  def load = {
    val foods = new Foods with MySQL {}
    val food = foods.detail(0, 1).head
    client.execute { index into "usda/sr26" id food.ndbNo doc ObjectSource(food) }
  }

  def main(args: Array[String]) = {
    createIndex andThen { case f => printFuture(f) } map { _ =>
      load andThen { case f => printResponse(f) } map { _ =>
        searchButter andThen { case f => printFuture(f) } map {
          _ => close
        }
      }
    }
  }
}
