package services

import dao.data.Company
import dao.data.tables.CompanyTable
import messaging.serialized.{RequestCompany, ResponseCompany}

import scala.concurrent.{ExecutionContext, Future}

/**
  * Created by Petru Miftode on 19.05.2017.
  */
class CompanyService ( override val databaseService: DatabaseService)(implicit executionContext: ExecutionContext) extends CompanyTable{

  def addCompany(reqComp:RequestCompany):Future[ResponseCompany] = {
    insert(Company(reqComp)).map(company => ResponseCompany(company.id.get))
  }

  def getCompanies:Future[Seq[Company]] = selectAll
}

