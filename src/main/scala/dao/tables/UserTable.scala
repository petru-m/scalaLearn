package dao.data.tables

import dao.data.User
import services.DatabaseService
import slick.lifted.ProvenShape

import scala.concurrent.Future

/**
  * Created by Petru Miftode on 18.05.2017.
  */
trait UserTable {

  protected val databaseService: DatabaseService
  import databaseService._
  import databaseService.driver.api._

  class Users(tag:Tag) extends Table[User](tag,"user"){
    def id: Rep[Option[Int]] = column[Option[Int]]("id",O.PrimaryKey, O.AutoInc)
    def firstName: Rep[String] = column[String]("firstName")
    def lastName: Rep[String] = column[String]("lastName")
    def telephone: Rep[Long] = column[Long]("telephone")

    def * : ProvenShape[User] = (id,firstName,lastName,telephone) <> ((User.mapperTo _).tupled, User.unapply)
  }

  val userTable = TableQuery[Users]

  def insert(user:User): Future[User] = db.run(userTable returning userTable += user)

  def selectAll: Future[Seq[User]] = db.run(userTable.result)
}
