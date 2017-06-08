package dao.base

import services.DataService
import slick.jdbc.PostgresProfile
import slick.jdbc.PostgresProfile.api._
import slick.lifted.TableQuery

import scala.concurrent.Future
import scala.reflect._

/**
  * Created by Petru Miftode on 27.05.2017.
  */

trait BaseRepositoryComponent[T <: BaseTable[E], E <: BaseEntity]{
  def getAll : Future[Seq[E]]
  def save(request: E) : Future[E]
}

trait BaseRepositoryQuery[T <: BaseTable[E], E <: BaseEntity] {
  val query : PostgresProfile.api.type#TableQuery[T]

  def getAllQuery = {
    query.result
  }

  def saveQuery(request :E) = {
    query returning query += request
  }
}

abstract class BaseRepository[T <: BaseTable[E], E <: BaseEntity : ClassTag](clazz:TableQuery[T]) extends BaseRepositoryQuery[T,E] with BaseRepositoryComponent[T,E] {
  val clazzTable: TableQuery[T] = clazz
  lazy val clazzEntity = classTag[E].runtimeClass
  val query: PostgresProfile.api.type#TableQuery[T] = clazz
  val db: PostgresProfile.backend.DatabaseDef = DataService.db

  def getAll: Future[Seq[E]] = {
    db.run(getAllQuery)
  }

  def save(row: E) = {
    db.run(saveQuery(row))
  }
}