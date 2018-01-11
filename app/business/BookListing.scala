package business

import scala.collection.immutable.Seq

case class BookListing(bookID : BookID, title : String, author : Author, keywords : Seq[String], description : String, isbn : ISBN, callNumber : CallNumber, status : BookStatus) {
  def this(bookID : BookID, properties : EditBookDTO, authorName : PersonName, status : BookStatus) =
    this(bookID, properties.title, new Author(properties.authorID, authorName), properties.keywords, properties.description, properties.isbn, properties.callNumber, status)
}