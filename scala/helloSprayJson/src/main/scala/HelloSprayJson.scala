import spray.json._
import spray.json.ParserInput.StringBasedParserInput
import DefaultJsonProtocol._

case class Color(name: String, red: Int, green: Int, blue: Int)

/*
// long form of JsonReader
object Protocol {
    implicit object JsonColorReader extends JsonReader[Color] {
        def read(jsValue: JsValue): Color = {
            val jsObject: JsObject = jsValue.asJsObject()
            val fields: Map[String, JsValue] = jsObject.fields
            val name: String = fields.get("name").get.convertTo[String]
            val red: Int = fields.getOrElse("red", JsNumber(0)).convertTo[Int]
            val green: Int = fields.getOrElse("green", JsNumber(0)).convertTo[Int]
            val blue: Int = fields.getOrElse("blue", JsNumber(0)).convertTo[Int]
            Color(name,red,green,blue)
        }
    }
}
import Protocol._
*/

// short form of JsonReader/JsonWriter
object MyJsonProtocol extends DefaultJsonProtocol {
    implicit val colorFormat = jsonFormat4(Color)
}

import MyJsonProtocol._

object Hello extends Greeting with App {
  println(greeting)
  val source: String = """{ "name": "magenta", "red": 255, "green":55, "blue": 5 }"""
  val jsonAst: JsValue = source.parseJson // or JsonParser(source)
  println("jsonAst:", jsonAst)
  println("jsonAst type:", jsonAst.getClass)

  val sourceArrayStr: String = """[ {"red": 255}, {"green":55}, {"blue": 5} ]"""
  val parserInput: StringBasedParserInput = new StringBasedParserInput(sourceArrayStr)
  val jsonArrayAst: JsValue = JsonParser(parserInput)
  println("jsonArrayAst:", jsonArrayAst, "jsonArrayAst type:", jsonArrayAst.getClass)

  //val color:Color = jsonAst.convertTo[Color](JsonColorReader)
  //val color:Color = jsonAst.convertTo[Color](MyJsonProtocol.colorFormat)
  val color:Color = jsonAst.convertTo[Color]
  println("case class color:", color)
}

trait Greeting {
  lazy val greeting: String = "helloSprayJson"
}
