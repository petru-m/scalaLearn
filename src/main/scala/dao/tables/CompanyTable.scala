package dao.data.tables

import dao.data.Company
import services.DatabaseService
import slick.lifted.ProvenShape

import scala.concurrent.Future

/**
  * Created by Petru Miftode on 19.05.2017.
  */
trait CompanyTable {

  protected val databaseService: DatabaseService
  import databaseService._
  import driver.api._

  class Companies(tag:Tag) extends Table[Company](tag,"company"){
    def id : Rep[Option[Int]] = column[Option[Int]]("id",O.PrimaryKey, O.AutoInc)
    def name : Rep[String] = column[String]("name")
    def street : Rep[String] = column[String]("street")
    def number : Rep[Int] = column[Int] ("number")

    def * : ProvenShape[Company] = (id,name,street,number) <> ((Company.mapperTo _).tupled, Company.unapply _)

  }
  val companyTable = TableQuery[Companies]

  def insert(company: Company): Future[Company] = db.run(companyTable returning companyTable += company)

  def selectAll:Future[Seq[Company]] = db.run(companyTable.result)
}
