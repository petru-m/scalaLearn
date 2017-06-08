package dao.base

import slick.jdbc.PostgresProfile.api._
import scala.reflect._

/**
  * Created by Petru Miftode on 27.05.2017.
  */

trait BaseEntity {
  val id:Option[Int]
}

abstract class BaseTable[E: ClassTag](tag: Tag, schemaName: Option[String], tableName: String)extends Table[E](tag, schemaName, tableName) {
  val classOfEntity = classTag[E].runtimeClass
  val id: Rep[Option[Int]] = column[Option[Int]]("Id", O.PrimaryKey, O.AutoInc)
}
