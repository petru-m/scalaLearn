import akka.actor.ActorSystem
import akka.event.{Logging, LoggingAdapter}
import akka.http.scaladsl.Http
import akka.stream.{ActorMaterializer, Materializer}
import config.ServerConfig
import services.Route

import scala.concurrent.ExecutionContextExecutor
import scala.util.{Failure, Success}

/**
  * Created by Petru Miftode on 06.05.2017.
  */


object Boot extends App with Route{

  override implicit val actorSystem = ActorSystem("system")
  override implicit val executionContext: ExecutionContextExecutor = actorSystem.dispatcher
  override implicit val materializer: Materializer = ActorMaterializer()
  override val config: ServerConfig = new ServerConfig()
  val logger: LoggingAdapter = Logging(actorSystem, getClass)

  Http().bindAndHandle(routes, config.host, config.port)
    .onComplete {
      case Success(_) => logger.debug("Http Server started successfully")
      case Failure(error) =>
        logger.debug(s"Error at starting Http Server: ${error.getMessage}")
        System.exit(-1)
    }
}
