
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
	"""),_display_(/*11.3*/b3/*11.5*/.form(action = routes.LibraryAppController.searchBooks())/*11.62*/ {_display_(Seq[Any](format.raw/*11.64*/("""
	  """),_display_(/*12.5*/CSRF/*12.9*/.formField),format.raw/*12.19*/("""
	  """),_display_(/*13.5*/b3/*13.7*/.text(searchForm("searchTerms"), '_label -> "Search Terms", 'placeholder -> "Search by book title, keyword, description, call no., ISBN, or publisher.")),format.raw/*13.159*/("""
	  """),_display_(/*14.5*/b3/*14.7*/.submit('class -> "btn btn-primary")/*14.43*/{_display_(Seq[Any](format.raw/*14.44*/(""" """),format.raw/*14.45*/("""Search Books """)))}),format.raw/*14.59*/("""
	""")))}),format.raw/*15.3*/("""
	"""),_display_(/*16.3*/if(!books.isEmpty)/*16.21*/ {_display_(Seq[Any](format.raw/*16.23*/("""
	  """),format.raw/*17.4*/("""<div class="table-responsive">
		  <table class="table table-striped">
		  	<tr><th>Title</th><th>ISBN</th><th>Call Number</th><th>Status</th><tr>
		  	"""),_display_(/*20.7*/for(book <- books) yield /*20.25*/ {_display_(Seq[Any](format.raw/*20.27*/("""
		  		"""),format.raw/*21.7*/("""<tr><td><a href=""""),_display_(/*21.25*/routes/*21.31*/.LibraryAppController.viewBookDetails(book.bookID)),format.raw/*21.81*/("""">"""),_display_(/*21.84*/book/*21.88*/.title),format.raw/*21.94*/("""</a></td><td>"""),_display_(/*21.108*/book/*21.112*/.isbn),format.raw/*21.117*/("""</td><td>"""),_display_(/*21.127*/book/*21.131*/.callNumber),format.raw/*21.142*/("""</td><td>"""),_display_(/*21.152*/book/*21.156*/.status.name),format.raw/*21.168*/("""</td></tr>
		  		<tr><td colspan="4"><em>Keywords:</em> """),_display_(/*22.47*/book/*22.51*/.keywords.mkString(" ")),format.raw/*22.74*/("""<br /><br /> """),_display_(/*22.88*/book/*22.92*/.description),format.raw/*22.104*/("""</td></tr>
		  	""")))}),format.raw/*23.7*/("""
		  """),format.raw/*24.5*/("""</table>
	  </div>
	""")))}),format.raw/*26.3*/("""
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
                  DATE: Mon Jan 08 15:12:31 KST 2018
                  SOURCE: /Users/kpyancey/Projects/Library-Demo-for-OOD/app/views/searchBooks.scala.html
                  HASH: c788970b6024ca017c46327ed0acd35b696cc282
                  MATRIX: 432->34|475->72|498->89|533->118|932->158|1142->272|1171->275|1200->295|1240->297|1269->300|1279->302|1345->359|1385->361|1416->366|1428->370|1459->380|1490->385|1500->387|1674->539|1705->544|1715->546|1760->582|1799->583|1828->584|1873->598|1906->601|1935->604|1962->622|2002->624|2033->628|2212->781|2246->799|2286->801|2320->808|2365->826|2380->832|2451->882|2481->885|2494->889|2521->895|2563->909|2577->913|2604->918|2642->928|2656->932|2689->943|2727->953|2741->957|2775->969|2859->1026|2872->1030|2916->1053|2957->1067|2970->1071|3004->1083|3051->1100|3083->1105|3134->1126
                  LINES: 17->3|18->4|19->5|20->6|25->8|30->8|32->10|32->10|32->10|33->11|33->11|33->11|33->11|34->12|34->12|34->12|35->13|35->13|35->13|36->14|36->14|36->14|36->14|36->14|36->14|37->15|38->16|38->16|38->16|39->17|42->20|42->20|42->20|43->21|43->21|43->21|43->21|43->21|43->21|43->21|43->21|43->21|43->21|43->21|43->21|43->21|43->21|43->21|43->21|44->22|44->22|44->22|44->22|44->22|44->22|45->23|46->24|48->26
                  -- GENERATED --
              */
          