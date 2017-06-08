package services

import dao.base.BaseRepository
import dao.data.Company
import dao.tables.Tables.CompanyTable
import messaging.serialized.{RequestCompany, ResponseCompany}
import slick.lifted.TableQuery
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent. Future

/**
  * Created by Petru Miftode on 19.05.2017.
  */

class CompanyService extends BaseRepository[CompanyTable,Company](TableQuery[CompanyTable]) {

  def addCompany(reqComp:RequestCompany):Future[ResponseCompany] = {
    save(Company(reqComp)).map(company => ResponseCompany(company.id.get))
  }

}

