package messaging.serialized

/**
  * Created by Petru Miftode on 15.05.2017.
  */
case class RequestUser
(
  firstName:String,
  lastName:String,
  telephone:Long
)

case class ResponseUser
(
  id:Int
)
