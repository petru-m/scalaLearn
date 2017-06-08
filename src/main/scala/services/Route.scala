package services

import akka.actor.ActorSystem
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._
import akka.stream.Materializer
import config.ServerConfig
import de.heikoseeberger.akkahttpcirce.CirceSupport
import io.circe.generic.auto._
import messaging.serialized.{RequestCompany, RequestUser}
import scala.concurrent.ExecutionContextExecutor


/**
  * Created by Petru Miftode on 08.05.2017.
  */


trait Route extends CirceSupport {

  implicit val actorSystem: ActorSystem
  implicit val executionContext: ExecutionContextExecutor
  implicit val materializer: Materializer

  implicit var userService = new UserService
  implicit var companyService = new CompanyService

  def config: ServerConfig

  val routes =
    path("hello") {
      get {
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "Well...its working...apparently"))
      }
    }~
    pathPrefix("user") {
      pathEnd {
        complete(userService.getAll)
      } ~
        pathSuffix("company") {
          complete(userService.selectUsersWithCompanies)
        }
    } ~
    path("user") {
      post {
        entity(as[RequestUser]) {
          request => complete(userService.addUser(request))
        }
      }
    } ~
    path("company") {
      post {
        entity(as[RequestCompany]) {
          request => complete(companyService.addCompany(request))
        }
      }
    } ~
    path("company") {
      get {
        complete(companyService.getAll)
      }
    }
}
