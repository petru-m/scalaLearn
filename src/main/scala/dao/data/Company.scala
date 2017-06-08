package dao.data

import dao.base.BaseEntity
import messaging.serialized.RequestCompany

/**
  * Created by Petru Miftode on 08.05.2017.
  */

case class Company
(
  id: Option[Int],
  name: String,
  street: String,
  number: Int
) extends BaseEntity

object Company{
  def apply(requestCompany: RequestCompany): Company = new Company(None, requestCompany.name, requestCompany.street, requestCompany.number)

  def mapperTo(id:Option[Int], name:String,street:String,number:Int): Company =
    Company(id:Option[Int], name:String, street: String, number : Int)
}