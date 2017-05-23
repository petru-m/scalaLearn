package services

import akka.actor.ActorSystem
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.stream.Materializer
import akka.http.scaladsl.server.Directives._
import config.ServerConfig
import messaging.serialized.{RequestCompany, RequestUser}
import io.circe.generic.auto._
import de.heikoseeberger.akkahttpcirce.CirceSupport

import scala.concurrent.ExecutionContextExecutor


/**
  * Created by Petru Miftode on 08.05.2017.
  */


trait Route extends CirceSupport  {

  implicit val actorSystem: ActorSystem
  implicit val executionContext: ExecutionContextExecutor
  implicit val materializer: Materializer

  implicit val userService : UserService
  implicit val companyService : CompanyService

  def config:ServerConfig

  val routes =
    path("hello"){
      get {
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "Well...its working...apparently"))
      }
    }~
    path("user"){
      post{
        entity(as[RequestUser]){
          request => complete(userService.addUser(request))
        }
      }
    }~
    path("company"){
      post{
        entity(as[RequestCompany]){
          request => complete(companyService.addCompany(request))
        }
      }
    }~
    pathPrefix("user"){
      pathEnd {
        complete(userService.selectAllUsers)
      }~
        pathSuffix("company"){
          complete(userService.getUsersWithCompany)
      }
    }~
    path("company"){
      get{
        complete(companyService.selectAllCompanies)
      }
    }
}
