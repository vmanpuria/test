import akka.actor.{ Actor, ActorSystem, ActorRef, ActorLogging, Props }
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.stream.{ ActorMaterializer, ActorMaterializerSettings }
import akka.util.ByteString
import scala.io.StdIn

class Myself extends Actor with ActorLogging {

  import akka.pattern.pipe
  import context.dispatcher

  final implicit val materializer: ActorMaterializer = ActorMaterializer(ActorMaterializerSettings(context.system))

  val http = Http(context.system)

  override def preStart() = {
    http.singleRequest(HttpRequest(uri = "http://localhost:8080/hello"))
      .pipeTo(self)
  }

  def receive = {
    case HttpResponse(StatusCodes.OK, headers, entity, _) =>
      entity.dataBytes.runFold(ByteString(""))(_ ++ _).foreach { body =>
        log.info("Got response, body: " + body.utf8String)
      }
    case resp @ HttpResponse(code, _, _, _) =>
      log.info("Request failed, response code: " + code)
      resp.discardEntityBytes()
  }

}

object WebClient {
    def main(args: Array[String]) {
        val system: ActorSystem = ActorSystem("webClientSystem")

        try {
            //create actor
            val client: ActorRef = system.actorOf(Props[Myself], "myself")  
            println("Press ENTER to exit")
            StdIn.readLine()
        } finally {
            system.terminate()
        }
    }
}
