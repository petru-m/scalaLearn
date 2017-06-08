package dao.tables

import dao.data.{Company, User}
import slick.jdbc.PostgresProfile
import dao.base._
import slick.lifted.ProvenShape

/**
  * Created by Petru Miftode on 07.06.2017.
  */

object Tables extends {
  val profile = PostgresProfile
} with Tables

trait Tables {
  val profile: PostgresProfile

  import profile.api._

  class UserTable(tag: Tag) extends BaseTable[User](tag, Some("public"), "user") {
    override val id: Rep[Option[Int]] = column[Option[Int]]("id", O.PrimaryKey, O.AutoInc)
    val firstName: Rep[String] = column[String]("firstName")
    val lastName: Rep[String] = column[String]("lastName")
    val telephone: Rep[Long] = column[Long]("telephone")
    val companyId: Rep[Int] = column[Int]("id_company")

    def * : ProvenShape[User] = (id, firstName, lastName, telephone) <> ((User.mapperTo _).tupled, User.unapply)

    def ? = (Rep.Some(id), firstName, lastName,telephone).shaped.<>({ r => import r._; _1.map(_ => (User.mapperTo _).tupled((_1.get, _2, _3, _4))) }, (_: Any) => throw new Exception("Inserting into ? projection not supported."))

    def companiesFK = foreignKey("companies_users_FK", id, companyTable)(_.id)
  }
  lazy val userTable = new TableQuery(tag => new UserTable(tag))

  class CompanyTable(tag: Tag) extends BaseTable[Company](tag, Some("public"), "company") {
    override val id: Rep[Option[Int]] = column[Option[Int]]("id", O.PrimaryKey, O.AutoInc)
    val name: Rep[String] = column[String]("name")
    val street: Rep[String] = column[String]("street")
    val number: Rep[Int] = column[Int]("number")

    def * : ProvenShape[Company] = (id, name, street, number) <> ((Company.mapperTo _).tupled, Company.unapply)

    def ? = (Rep.Some(id), name, street, number).shaped.<>({ r => import r._; _1.map(_ => (Company.mapperTo _).tupled((_1.get, _2, _3, _4))) }, (_: Any) => throw new Exception("Inserting into ? projection not supported."))

  }
  lazy val companyTable = new TableQuery(tag => new CompanyTable(tag))

}