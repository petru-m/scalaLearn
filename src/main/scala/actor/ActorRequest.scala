package actor

import java.net.URL

import akka.actor.{Actor, Props}
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

/**
  * Created by Petru Miftode on 12.06.2017.
  */

case class ActorRequest()  extends Actor{

  implicit val timeout = Timeout(1 minutes)
  val actorFinder = context.actorOf(Props[ActorFinder])
  override def receive: Receive = {
    case url:URL =>
      val result = actorFinder ? url
      result.map { value =>
        println(value)
      }.recover {
        case e: Exception =>
          println(s"$e")
      }
    case query: String =>
        actorFinder ! query
    case _ => println("Wrong data received")
   }
}
