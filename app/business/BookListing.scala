package business

import scala.collection.immutable.Seq

case class BookListing(bookID : BookID, title : String, author : Author, keywords : Seq[String], description : String, isbn : ISBN, callNumber : String, status : BookStatus) {
  
}