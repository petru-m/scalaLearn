package messaging.serialized

/**
  * Created by Petru Miftode on 06.05.2017.
  */

case class RequestCompany
(
  name: String,
  street: String,
  number: Int
)

case class ResponseCompany
(
  idCompany:Int
)
