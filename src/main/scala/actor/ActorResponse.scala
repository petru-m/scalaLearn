package actor

import akka.actor.Actor

/**
  * Created by Petru Miftode on 12.06.2017.
  */

case class ActorResponse()  extends Actor{

  override def receive: Receive = {
    case response:List[_] =>
      response.map(data => println(data))
  }

}
