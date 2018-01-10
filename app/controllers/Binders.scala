package controllers

import business._
import play.api.mvc.PathBindable

object Binders {
  implicit def bookIDPathBinder = new PathBindable[BookID] {
      override def bind(key: String, value: String): Either[String, BookID] = {
         try {
            Right(BookID(value.toInt))
         } catch {
            case e: java.lang.NumberFormatException => Left("Id must be a valid BookID")
         }
      }
      override def unbind(key: String, value: BookID): String = value.toString
   }
  
  implicit def bookSearchSortBinder = new PathBindable[BookSearchSort] {
      override def bind(key: String, value: String): Either[String, BookSearchSort] =
        BookSearchSort.nameMap.mapValues(Right(_)).getOrElse(value.trim, Left("Must be a valid sort option."))
      override def unbind(key: String, value: BookSearchSort): String = value.toString
   }
}