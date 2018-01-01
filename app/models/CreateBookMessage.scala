package models
import java.time.LocalDate
import scala.collection.immutable.Seq

case class BookInfo(title : String, description : String, isdn : ISBN, publicationDate : LocalDate, authors : Seq[AuthorID], publisher : PublisherID) {
}