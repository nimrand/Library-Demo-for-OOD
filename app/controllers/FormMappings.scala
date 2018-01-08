package controllers

import java.time.LocalDate
import java.time.ZoneId
import java.util.Date
import play.api.data.Forms._
import scala.collection.immutable.Seq

object FormMappings {
  def tokens(maxLength : Int) = text(maxLength = maxLength).transform[Seq[String]](_.split(" ").to[Seq], _.mkString(" "))
}