import javax.inject.Inject
import scala.concurrent.Future
import scala.concurrent.duration._

import play.api.mvc._
import play.api.libs.ws._
import play.api.http.HttpEntity

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl._
import akka.util.ByteString

import scala.concurrent.ExecutionContext

class Application @Inject() (ws: WSClient) extends Controller {

    def sendHttpRequest(): Unit = {
        val request: WSRequest = ws.url(url).addHttpHeaders("Accept" -> "application/json")
        val futureResponse: Future[WSResponse] = request.get()
    }
}
