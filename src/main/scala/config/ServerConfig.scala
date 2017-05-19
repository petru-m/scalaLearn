package config

import com.typesafe.config.{Config, ConfigFactory}

/**
  * Created by Petru Miftode on 06.05.2017.
  */

class ServerConfig {

  val conf: Config = ConfigFactory.load()
  val host: String = conf.getString("http.host")
  val port: Int = conf.getInt("http.port")

  private val dbConfig = conf.getConfig("database")
  val dbUrl: String = dbConfig.getString("url")
  val dbUser: String = dbConfig.getString("user")
  val dbPassword: String = dbConfig.getString("password")


}
