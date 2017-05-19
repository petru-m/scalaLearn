package services

import dao.data.User
import dao.data.tables.UserTable
import messaging.serialized.{RequestUser, ResponseUser}

import scala.concurrent.{ExecutionContext, Future}

/**
  * Created by Petru Miftode on 19.05.2017.
  */
class UserService( override val databaseService: DatabaseService)(implicit executionContext: ExecutionContext) extends UserTable{

  def addUser(reqUser:RequestUser):Future[ResponseUser] = {
    insert(User(reqUser)).map(user => ResponseUser(user.id.get))
  }

  def getUsers:Future[Seq[User]] = selectAll
}
