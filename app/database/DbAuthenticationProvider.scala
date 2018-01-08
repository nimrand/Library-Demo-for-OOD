package database

import business._
import javax.inject.{ Inject, Singleton }
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import scala.concurrent.{ Future, ExecutionContext }
import scala.collection.immutable._
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Singleton
class DbAuthenticationProvider @Inject() (dbSchema: DbSchema)(implicit ec: ExecutionContext) {
  import dbSchema.dbConfig._
  import profile.api._
  import dbSchema._
  
  def authenticate(username : String, password : String) : Future[Boolean] =
    db.run(users.filter(u => u.username === username && u.password === password).exists.result)
}