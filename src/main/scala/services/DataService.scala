package services

import com.zaxxer.hikari.{HikariConfig, HikariDataSource}
import config.ServerConfig

import scala.util.Try

/**
  * Created by Petru Miftode on 18.05.2017.
  */

class DatabaseService(dbUrl: String, dbUser: String, dbPassword: String) {

  private val hikariConfig = new HikariConfig()
  hikariConfig.setJdbcUrl(dbUrl)
  hikariConfig.setUsername(dbUser)
  hikariConfig.setPassword(dbPassword)

  private val dataSource = new HikariDataSource(hikariConfig)
  val driver = slick.jdbc.PostgresProfile
  import driver.api._
  val db: driver.backend.DatabaseDef = Database.forDataSource(dataSource)

  db.createSession()
}

object DatabaseService {
  def apply(config: ServerConfig): Try[DatabaseService] = {
    Try(new DatabaseService(config.dbUrl, config.dbUser, config.dbPassword))
  }
}