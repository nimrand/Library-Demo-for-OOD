
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

object loanBook extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Form[LoanBookDTO],Book,Seq[LibraryMember],Messages,RequestHeader,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*9.2*/(loanBookForm : Form[LoanBookDTO], book : Book, libraryMembers : Seq[LibraryMember])(implicit messages: Messages, req: RequestHeader):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*9.135*/("""

"""),_display_(/*11.2*/main("Loan Book")/*11.19*/ {_display_(Seq[Any](format.raw/*11.21*/("""
	"""),format.raw/*12.2*/("""<h1>Loan Book - """),_display_(/*12.19*/book/*12.23*/.title),format.raw/*12.29*/("""</h1>
	"""),_display_(/*13.3*/b3/*13.5*/.form(action = routes.LibraryAppController.loanBookPost(book.id))/*13.70*/ {_display_(Seq[Any](format.raw/*13.72*/("""
	  """),_display_(/*14.5*/CSRF/*14.9*/.formField),format.raw/*14.19*/("""
	  """),_display_(/*15.5*/b3/*15.7*/.select(loanBookForm("memberID"), libraryMembers.map(m => (m.id.asInt.toString, m.name.toString)), '_label -> "Loaned To")),format.raw/*15.129*/("""
	  """),format.raw/*16.4*/("""<a href=""""),_display_(/*16.14*/routes/*16.20*/.LibraryAppController.registerLibraryMember),format.raw/*16.63*/("""">Register New Member</a><br /><br />
	  """),_display_(/*17.5*/b3/*17.7*/.date(loanBookForm("loanedDate"), '_label -> "Date Loaned")),format.raw/*17.66*/("""
	  """),_display_(/*18.5*/b3/*18.7*/.date(loanBookForm("dueDate"), '_label -> "Due Date")),format.raw/*18.60*/("""
	  """),_display_(/*19.5*/b3/*19.7*/.submit('class -> "btn btn-primary")/*19.43*/{_display_(Seq[Any](format.raw/*19.44*/(""" """),format.raw/*19.45*/("""Loan Book """)))}),format.raw/*19.56*/("""
	""")))}),format.raw/*20.3*/("""
""")))}))
      }
    }
  }

  def render(loanBookForm:Form[LoanBookDTO],book:Book,libraryMembers:Seq[LibraryMember],messages:Messages,req:RequestHeader): play.twirl.api.HtmlFormat.Appendable = apply(loanBookForm,book,libraryMembers)(messages,req)

  def f:((Form[LoanBookDTO],Book,Seq[LibraryMember]) => (Messages,RequestHeader) => play.twirl.api.HtmlFormat.Appendable) = (loanBookForm,book,libraryMembers) => (messages,req) => apply(loanBookForm,book,libraryMembers)(messages,req)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Mon Jan 08 16:48:44 KST 2018
                  SOURCE: /Users/kpyancey/Projects/Library-Demo-for-OOD/app/views/loanBook.scala.html
                  HASH: 7023bc77dc446273b98727c67285ac8b88ee65a3
                  MATRIX: 432->1|475->39|498->56|533->85|570->116|603->143|631->165|1034->205|1263->338|1292->341|1318->358|1358->360|1387->362|1431->379|1444->383|1471->389|1505->397|1515->399|1589->464|1629->466|1660->471|1672->475|1703->485|1734->490|1744->492|1888->614|1919->618|1956->628|1971->634|2035->677|2103->719|2113->721|2193->780|2224->785|2234->787|2308->840|2339->845|2349->847|2394->883|2433->884|2462->885|2504->896|2537->899
                  LINES: 17->1|18->2|19->3|20->4|21->5|22->6|23->7|28->9|33->9|35->11|35->11|35->11|36->12|36->12|36->12|36->12|37->13|37->13|37->13|37->13|38->14|38->14|38->14|39->15|39->15|39->15|40->16|40->16|40->16|40->16|41->17|41->17|41->17|42->18|42->18|42->18|43->19|43->19|43->19|43->19|43->19|43->19|44->20
                  -- GENERATED --
              */
          