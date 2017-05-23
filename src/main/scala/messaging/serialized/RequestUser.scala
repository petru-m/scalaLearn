package messaging.serialized

import dao.data.{Company, User}

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

case class ResponseUserWithCompany
(
  idUser:Int,
  firstName: String,
  lastName: String,
  companyId: Int,
  companyName: String
)

object ResponseUserWithCompany{
  def  apply(user:User,company:Company):ResponseUserWithCompany = {
    ResponseUserWithCompany(user.id.get, user.firstName, user.lastName, company.id.get, company.name)
  }
}