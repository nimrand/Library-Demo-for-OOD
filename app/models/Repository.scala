package models

trait Repository {
  def createBook(info : BookInfo) : Book
}