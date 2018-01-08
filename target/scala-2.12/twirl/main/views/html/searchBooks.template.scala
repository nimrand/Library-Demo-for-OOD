
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
/*3.2*/import b3.vertical.fieldConstructor
/*4.2*/import helper._
/*5.2*/import business.BookListing
/*6.2*/import scala.collection.immutable.Seq

object searchBooks extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Form[Seq[String]],Seq[BookListing],Messages,RequestHeader,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*8.2*/(searchForm: Form[Seq[String]], books : Seq[BookListing] = Seq())(implicit messages: Messages, req: RequestHeader):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*8.116*/("""

"""),_display_(/*10.2*/main("Search Books")/*10.22*/ {_display_(Seq[Any](format.raw/*10.24*/("""
	"""),format.raw/*11.2*/("""<h1>Search Books</h1>
	"""),_display_(/*12.3*/b3/*12.5*/.form(action = routes.LibraryAppController.searchBooks())/*12.62*/ {_display_(Seq[Any](format.raw/*12.64*/("""
	  """),_display_(/*13.5*/CSRF/*13.9*/.formField),format.raw/*13.19*/("""
	  """),_display_(/*14.5*/b3/*14.7*/.text(searchForm("searchTerms"), '_label -> "Search Terms", 'placeholder -> "Search by book title, keyword, description, call no., ISBN, or publisher.")),format.raw/*14.159*/("""
	  """),_display_(/*15.5*/b3/*15.7*/.submit('class -> "btn btn-primary")/*15.43*/{_display_(Seq[Any](format.raw/*15.44*/(""" """),format.raw/*15.45*/("""Search Books """)))}),format.raw/*15.59*/("""
	""")))}),format.raw/*16.3*/("""
	"""),_display_(/*17.3*/if(!books.isEmpty)/*17.21*/ {_display_(Seq[Any](format.raw/*17.23*/("""
	  """),format.raw/*18.4*/("""<div class="table-responsive">
		  <table class="table table-striped">
		  	<tr><th>Title</th><th>ISBN</th><th>Call Number</th><th>Status</th><tr>
		  	"""),_display_(/*21.7*/for(book <- books) yield /*21.25*/ {_display_(Seq[Any](format.raw/*21.27*/("""
		  		"""),format.raw/*22.7*/("""<tr><td><a href=""""),_display_(/*22.25*/routes/*22.31*/.LibraryAppController.viewBookDetails(book.bookID)),format.raw/*22.81*/("""">"""),_display_(/*22.84*/book/*22.88*/.title),format.raw/*22.94*/("""</a></td><td>"""),_display_(/*22.108*/book/*22.112*/.isbn),format.raw/*22.117*/("""</td><td>"""),_display_(/*22.127*/book/*22.131*/.callNumber),format.raw/*22.142*/("""</td><td>"""),_display_(/*22.152*/book/*22.156*/.status.name),format.raw/*22.168*/("""</td></tr>
		  		<tr><td colspan="4"><em>Keywords:</em> """),_display_(/*23.47*/book/*23.51*/.keywords.mkString(" ")),format.raw/*23.74*/("""<br /><br /> """),_display_(/*23.88*/book/*23.92*/.description),format.raw/*23.104*/("""</td></tr>
		  	""")))}),format.raw/*24.7*/("""
		  """),format.raw/*25.5*/("""</table>
	  </div>
	""")))}),format.raw/*27.3*/("""
""")))}))
      }
    }
  }

  def render(searchForm:Form[Seq[String]],books:Seq[BookListing],messages:Messages,req:RequestHeader): play.twirl.api.HtmlFormat.Appendable = apply(searchForm,books)(messages,req)

  def f:((Form[Seq[String]],Seq[BookListing]) => (Messages,RequestHeader) => play.twirl.api.HtmlFormat.Appendable) = (searchForm,books) => (messages,req) => apply(searchForm,books)(messages,req)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Mon Jan 08 21:03:10 KST 2018
                  SOURCE: /Users/kpyancey/Projects/Library-Demo-for-OOD/app/views/searchBooks.scala.html
                  HASH: a7f2e8c3fceea3bdf9cea8be580931e68df6dbb9
                  MATRIX: 432->34|475->72|498->89|533->118|932->158|1142->272|1171->275|1200->295|1240->297|1269->299|1319->323|1329->325|1395->382|1435->384|1466->389|1478->393|1509->403|1540->408|1550->410|1724->562|1755->567|1765->569|1810->605|1849->606|1878->607|1923->621|1956->624|1985->627|2012->645|2052->647|2083->651|2262->804|2296->822|2336->824|2370->831|2415->849|2430->855|2501->905|2531->908|2544->912|2571->918|2613->932|2627->936|2654->941|2692->951|2706->955|2739->966|2777->976|2791->980|2825->992|2909->1049|2922->1053|2966->1076|3007->1090|3020->1094|3054->1106|3101->1123|3133->1128|3184->1149
                  LINES: 17->3|18->4|19->5|20->6|25->8|30->8|32->10|32->10|32->10|33->11|34->12|34->12|34->12|34->12|35->13|35->13|35->13|36->14|36->14|36->14|37->15|37->15|37->15|37->15|37->15|37->15|38->16|39->17|39->17|39->17|40->18|43->21|43->21|43->21|44->22|44->22|44->22|44->22|44->22|44->22|44->22|44->22|44->22|44->22|44->22|44->22|44->22|44->22|44->22|44->22|45->23|45->23|45->23|45->23|45->23|45->23|46->24|47->25|49->27
                  -- GENERATED --
              */
          