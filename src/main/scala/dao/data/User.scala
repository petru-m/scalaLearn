package dao.data

import dao.base.BaseEntity
import messaging.serialized.RequestUser

/**
  * Created by Petru Miftode on 06.05.2017.
  */

case class User
(
  id:Option[Int],
  firstName:String,
  lastName:String,
  telephone:Long
) extends BaseEntity

object User{
  def apply(requestUser: RequestUser): User = new User(None, requestUser.firstName, requestUser.lastName, requestUser.telephone)

  def mapperTo(id:Option[Int], firstName:String, lastName:String,telephone:Long): User=
    User(id:Option[Int], firstName:String, lastName:String,telephone:Long)
}
