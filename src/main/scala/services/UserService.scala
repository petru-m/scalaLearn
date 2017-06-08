package services

import dao.base.BaseRepository
import dao.data.User
import dao.tables.Tables.UserTable
import messaging.serialized.{RequestUser, ResponseUser}
import slick.lifted.TableQuery
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by Petru Miftode on 19.05.2017.
  */

class UserService extends BaseRepository[UserTable,User](TableQuery[UserTable]) {

  def addUser(reqUser:RequestUser):Future[ResponseUser] = {
    save(User(reqUser)).map(user => ResponseUser(user.id.get))
  }

  def selectUsersWithCompanies = {
    //ToDo
  }
}
