package actor

import java.net.URL

import akka.actor.{Actor, Props}
import org.jsoup.Jsoup
import scala.collection.JavaConverters._

/**
  * Created by Petru Miftode on 12.06.2017.
  */

case class ActorFinder() extends Actor{
  val actorRequest = context.actorOf(Props[ActorRequest])
  val actorResponse = context.actorOf(Props[ActorResponse])

  var host = ""

  override def receive: Receive = {
    case url:URL =>
      sender() ! s"$url"
      host = url.getHost

    case query:String =>
      if(!host.isEmpty) {
        host = "https://"+host +"/search?q="+query

        val response = Jsoup.connect(host).ignoreContentType(true)
          .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:40.0) Gecko/20100101 Firefox/40.1")
          .execute()

        val document = response.parse()
        val titles = document.getElementsByTag("h3").asScala.map(e => e.text()).toList
        actorResponse ! titles.take(3)
      }
  }
}
