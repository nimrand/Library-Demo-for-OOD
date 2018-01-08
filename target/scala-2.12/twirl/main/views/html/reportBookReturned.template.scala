
package views.html

import _root_.play.twirl.api.TwirlFeatureImports._
import _root_.play.twirl.api.TwirlHelperImports._
import _root_.play.twirl.api.Html
import _root_.play.twirl.api.JavaScript
import _root_.play.twirl.api.Txt
import _root_.play.twirl.api.Xml
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._
/*1.2*/import b3.vertical.fieldConstructor
/*2.2*/import helper._
/*3.2*/import business.LoanBookDTO
/*4.2*/import business.LibraryMember
/*5.2*/import business.Publisher
/*6.2*/import business.Book
/*7.2*/import scala.collection.immutable.Seq
/*8.2*/import java.time.LocalDate

object reportBookReturned extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Form[LocalDate],Book,Messages,RequestHeader,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*10.2*/(bookReturnedForm : Form[LocalDate], book : Book)(implicit messages: Messages, req: RequestHeader):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*10.100*/("""

"""),_display_(/*12.2*/main("Loan Book")/*12.19*/ {_display_(Seq[Any](format.raw/*12.21*/("""
	"""),format.raw/*13.2*/("""<h1>Report Book Returned - """),_display_(/*13.30*/book/*13.34*/.title),format.raw/*13.40*/("""</h1>
	"""),_display_(/*14.3*/form(action = routes.LibraryAppController.reportBookReturnedPost(book.id), 'id -> "bookReturnedForm")/*14.104*/ {_display_(Seq[Any](format.raw/*14.106*/("""
      """),_display_(/*15.8*/CSRF/*15.12*/.formField),format.raw/*15.22*/("""
      """),_display_(/*16.8*/b3/*16.10*/.date(bookReturnedForm("returnedDate"), '_label -> "Returned Date")),format.raw/*16.77*/("""
      """),_display_(/*17.8*/b3/*17.10*/.submit('class -> "btn btn-primary")/*17.46*/{_display_(Seq[Any](format.raw/*17.47*/(""" """),format.raw/*17.48*/("""Report Book Returned """)))}),format.raw/*17.70*/("""
    """)))}),format.raw/*18.6*/("""
""")))}))
      }
    }
  }

  def render(bookReturnedForm:Form[LocalDate],book:Book,messages:Messages,req:RequestHeader): play.twirl.api.HtmlFormat.Appendable = apply(bookReturnedForm,book)(messages,req)

  def f:((Form[LocalDate],Book) => (Messages,RequestHeader) => play.twirl.api.HtmlFormat.Appendable) = (bookReturnedForm,book) => (messages,req) => apply(bookReturnedForm,book)(messages,req)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Mon Jan 08 22:00:56 KST 2018
                  SOURCE: /Users/kpyancey/Projects/Library-Demo-for-OOD/app/views/reportBookReturned.scala.html
                  HASH: c0381001e0e673a291d150c95ba819adda28455f
                  MATRIX: 432->1|475->39|498->56|533->85|570->116|603->143|631->165|676->204|1058->233|1253->331|1282->334|1308->351|1348->353|1377->355|1432->383|1445->387|1472->393|1506->401|1617->502|1658->504|1692->512|1705->516|1736->526|1770->534|1781->536|1869->603|1903->611|1914->613|1959->649|1998->650|2027->651|2080->673|2116->679
                  LINES: 17->1|18->2|19->3|20->4|21->5|22->6|23->7|24->8|29->10|34->10|36->12|36->12|36->12|37->13|37->13|37->13|37->13|38->14|38->14|38->14|39->15|39->15|39->15|40->16|40->16|40->16|41->17|41->17|41->17|41->17|41->17|41->17|42->18
                  -- GENERATED --
              */
          