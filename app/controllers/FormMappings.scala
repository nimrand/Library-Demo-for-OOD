package controllers

import java.time.LocalDate
import java.time.ZoneId
import java.util.Date
import play.api.data.Forms._
import scala.collection.immutable.Seq
import play.api.data.format.Formatter
import business._
import play.api.data.Forms._
import play.api.data._

object FormMappings {
  def tokens(maxLength : Int) = text(maxLength = maxLength).transform[Seq[String]](_.split(" ").to[Seq], _.mkString(" "))
  
  implicit val isbnFormat = new Formatter[ISBN] {
    def bind(key : String, data : Map[String, String]) = {
      ISBN.tryParseAndConvert(data.getOrElse(key, "")) match {
        case Success(isbn) => Right(isbn)
        case Error(message) => Left(List(FormError(key, message)))
      }
    }
    
    def unbind(key : String, isbn : ISBN) = Map(key -> isbn.toString)
  }
  
  implicit val callNumberFormat = new Formatter[CallNumber] {
    def bind(key : String, data : Map[String, String]) = {
      CallNumber.tryParse(data.getOrElse(key, "")) match {
        case Success(callNumber) => Right(callNumber)
        case Error(message) => Left(List(FormError(key, message)))
      }
    }
    
    def unbind(key : String, isbn : CallNumber) = Map(key -> isbn.toString)
  }
}