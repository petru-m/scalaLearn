package services

import com.zaxxer.hikari.{HikariConfig, HikariDataSource}
import config.ServerConfig

/**
  * Created by Petru Miftode on 18.05.2017.
  */

object DataService{

  val serverConfig = new ServerConfig()

  private val hikariConfig = new HikariConfig()
  hikariConfig.setJdbcUrl(serverConfig.dbUrl)
  hikariConfig.setUsername(serverConfig.dbUser)
  hikariConfig.setPassword(serverConfig.dbPassword)

  private var dataSource = new HikariDataSource(hikariConfig)
  val driver = slick.jdbc.PostgresProfile
  import driver.api._
  val db: driver.backend.DatabaseDef = Database.forDataSource(dataSource)

}