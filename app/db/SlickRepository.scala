package db

import business._
import javax.inject.{ Inject, Singleton }
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import scala.concurrent.{ Future, ExecutionContext }

@Singleton
class SlickRepository @Inject() (dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) extends Repository {

  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._
  
  private class PublisherTable(tag: Tag) extends Table[Publisher](tag, "Publisher") {
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

    def name = column[String]("name")
    
    def * = (id, name) <> ((fields : (Int, String)) => new Publisher(PublisherID(fields._1), fields._2), (publisher: Publisher) => Some((publisher.id.asInt, publisher.name)))
  }
  
  private val publishers = TableQuery[PublisherTable]
  
  def createPublisher(name : String) : Future[Publisher] = db.run {
    publishers.map(p => p.name) returning publishers.map(_.id) into ((name, id) => new Publisher(PublisherID(id), name)) += (name)
  }
}