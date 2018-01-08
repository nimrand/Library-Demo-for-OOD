
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
/*3.2*/import business.Book
/*4.2*/import business.BookID
/*5.2*/import business.BookStatus
/*6.2*/import business.BookLoan
/*7.2*/import helper._

object viewBookDetails extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Book,Seq[BookLoan],Messages,RequestHeader,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*9.2*/(book : Book, loanHistory : Seq[BookLoan])(implicit messages: Messages, req: RequestHeader):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*9.93*/("""

"""),_display_(/*11.2*/main("Book Details")/*11.22*/ {_display_(Seq[Any](format.raw/*11.24*/("""
	"""),format.raw/*12.2*/("""<h1>Book Details</h1>
	<div class="row">
	<div class="col-md-8">
	<dl class="dl-horizontal">
	  <dt>Title</dt>
	  <dd>"""),_display_(/*17.9*/book/*17.13*/.title),format.raw/*17.19*/("""</dd>
	  <dt>ISBN</dt>
	  <dd>"""),_display_(/*19.9*/book/*19.13*/.isbn.toString),format.raw/*19.27*/("""</dd>
	  <dt>Call Number</dt>
	  <dd>"""),_display_(/*21.9*/book/*21.13*/.callNumber),format.raw/*21.24*/("""</dd>
	  <dt>Keywords</dt>
	  <dd>"""),_display_(/*23.9*/book/*23.13*/.keywords.mkString(" ")),format.raw/*23.36*/("""</dd>
	  <dt>Description</dt>
	  <dd>"""),_display_(/*25.9*/book/*25.13*/.description),format.raw/*25.25*/("""</dd>
	  <dt>Publisher</dt>
	  <dd>"""),_display_(/*27.9*/book/*27.13*/.publisher.name),format.raw/*27.28*/("""</dd>
	  <dt>Publication Date</dt>
	  <dd>"""),_display_(/*29.9*/book/*29.13*/.publicationDate.toString),format.raw/*29.38*/("""</dd>
	  <dt>Price</dt>
	  <dd>$ """),_display_(/*31.11*/book/*31.15*/.price.toString),format.raw/*31.30*/("""</dd>
	  <dt>Status</dt>
	  <dd>"""),_display_(/*33.9*/book/*33.13*/.status.name),format.raw/*33.25*/("""</dd>
	</dl>
	<h2>Loan History</h2>
	<div class="table-responsive">
	  <table class="table table-striped">
	  	<tr><th>Member</th><th>Loaned Date</th><th>Due Date</th><th>Returned Date</th><tr>
	  	"""),_display_(/*39.6*/for(bookLoan <- loanHistory) yield /*39.34*/ {_display_(Seq[Any](format.raw/*39.36*/("""
	  		"""),format.raw/*40.6*/("""<tr>
	  			<td>"""),_display_(/*41.12*/bookLoan/*41.20*/.member.name),format.raw/*41.32*/("""</td>
	  			<td>"""),_display_(/*42.12*/bookLoan/*42.20*/.loanedDate),format.raw/*42.31*/("""</td>
	  			<td>"""),_display_(/*43.12*/bookLoan/*43.20*/.dueDate),format.raw/*43.28*/("""</td>
	  			<td>"""),_display_(/*44.12*/bookLoan/*44.20*/.returnedDate),format.raw/*44.33*/("""</td>
	  		</tr>
	  	""")))}),format.raw/*46.6*/("""
	  """),format.raw/*47.4*/("""</table>
  </div>
	</div>
	<div class="col-md-4">
		<div class="btn-group-vertical" role="group">
		  <a class="btn btn-default" href=""""),_display_(/*52.39*/routes/*52.45*/.LibraryAppController.editBook(book.id)),format.raw/*52.84*/("""">Edit Book</a>
			"""),_display_(/*53.5*/if(book.status != BookStatus.Disposed)/*53.43*/ {_display_(Seq[Any](format.raw/*53.45*/("""
			  """),_display_(/*54.7*/if(book.status == BookStatus.Available)/*54.46*/ {_display_(Seq[Any](format.raw/*54.48*/("""
			    """),format.raw/*55.8*/("""<a class="btn btn-default" href=""""),_display_(/*55.42*/routes/*55.48*/.LibraryAppController.loanBook(book.id)),format.raw/*55.87*/("""">Loan Book</a>
			  """)))}/*56.8*/else if(book.status == BookStatus.CheckedOut || loanHistory.headOption.map(_.returnedDate == None).getOrElse(false))/*56.124*/ {_display_(Seq[Any](format.raw/*56.126*/("""
			    """),format.raw/*57.8*/("""<a class="btn btn-default" data-toggle="modal" href=""""),_display_(/*57.62*/routes/*57.68*/.LibraryAppController.reportBookReturned(book.id)),format.raw/*57.117*/("""">Report Returned</a>
			  """)))}),format.raw/*58.7*/("""
			  """),_display_(/*59.7*/if(book.status == BookStatus.Available || book.status == BookStatus.CheckedOut)/*59.86*/ {_display_(Seq[Any](format.raw/*59.88*/("""
			    """),format.raw/*60.8*/("""<button class="btn btn-default" data-toggle="modal" data-target="#reportBookLostDialog">Report Lost</button>
			  """)))}/*61.8*/else if(book.status == BookStatus.Lost && !loanHistory.headOption.map(_.returnedDate == None).getOrElse(false))/*61.119*/ {_display_(Seq[Any](format.raw/*61.121*/("""
			    """),format.raw/*62.8*/("""<button class="btn btn-default" data-toggle="modal" data-target="#reportBookFoundDialog">Report Found</button>
			  """)))}),format.raw/*63.7*/("""
			  """),_display_(/*64.7*/if(book.status == BookStatus.Available)/*64.46*/ {_display_(Seq[Any](format.raw/*64.48*/("""
			    """),format.raw/*65.8*/("""<button class="btn btn-default" data-toggle="modal" data-target="#disposeBookDialog">Dispose</button>
			  """)))}),format.raw/*66.7*/("""
			""")))}),format.raw/*67.5*/("""
		"""),format.raw/*68.3*/("""</div>
	  </div>
	</div>
	
	<div id="reportBookLostDialog" class="modal fade" role="dialog">
	  <div class="modal-dialog">
	
	    <!-- Modal content-->
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        <h4 class="modal-title">Report Lost</h4>
	      </div>
	      <div class="modal-body">
	        Are you sure you want to report the book as lost?
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-danger" id="confirmBookLostButton">Yes</button>
	        <button type="button" class="btn btn-primary" data-dismiss="modal">Nevermind</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	<div id="reportBookFoundDialog" class="modal fade" role="dialog">
	  <div class="modal-dialog">
	
	    <!-- Modal content-->
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        <h4 class="modal-title">Report Lost</h4>
	      </div>
	      <div class="modal-body">
	        Are you sure you want to report the book as found?
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-danger" id="confirmBookFoundButton">Yes</button>
	        <button type="button" class="btn btn-primary" data-dismiss="modal">Nevermind</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	<div id="disposeBookDialog" class="modal fade" role="dialog">
	  <div class="modal-dialog">
	
	    <!-- Modal content-->
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        <h4 class="modal-title">Report Lost</h4>
	      </div>
	      <div class="modal-body">
	        Are you sure you want to report the book as found?
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-danger" id="confirmDisposeBookButton">Yes</button>
	        <button type="button" class="btn btn-primary" data-dismiss="modal">Nevermind</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	"""),_display_(/*132.3*/form(controllers.routes.LibraryAppController.reportBookLost(book.id), 'id -> "reportLostForm")/*132.97*/ {_display_(Seq[Any](format.raw/*132.99*/("""
	  """),_display_(/*133.5*/CSRF/*133.9*/.formField),format.raw/*133.19*/("""
	""")))}),format.raw/*134.3*/("""
	"""),_display_(/*135.3*/form(controllers.routes.LibraryAppController.reportBookFound(book.id), 'id -> "reportFoundForm")/*135.99*/ {_display_(Seq[Any](format.raw/*135.101*/("""
	  """),_display_(/*136.5*/CSRF/*136.9*/.formField),format.raw/*136.19*/("""
	""")))}),format.raw/*137.3*/("""
	"""),_display_(/*138.3*/form(controllers.routes.LibraryAppController.disposeBook(book.id), 'id -> "disposeBookForm")/*138.95*/ {_display_(Seq[Any](format.raw/*138.97*/("""
	  """),_display_(/*139.5*/CSRF/*139.9*/.formField),format.raw/*139.19*/("""
	""")))}),format.raw/*140.3*/("""
	
	"""),format.raw/*142.2*/("""<script>
  $(function() """),format.raw/*143.16*/("""{"""),format.raw/*143.17*/("""
    """),format.raw/*144.5*/("""$("#confirmBookLostButton").click(function() """),format.raw/*144.50*/("""{"""),format.raw/*144.51*/("""
      """),format.raw/*145.7*/("""$("#reportLostForm").submit()
    """),format.raw/*146.5*/("""}"""),format.raw/*146.6*/(""")
    $("#confirmBookFoundButton").click(function() """),format.raw/*147.51*/("""{"""),format.raw/*147.52*/("""
      """),format.raw/*148.7*/("""$("#reportFoundForm").submit()
    """),format.raw/*149.5*/("""}"""),format.raw/*149.6*/(""")
    $("#confirmDisposeBookButton").click(function() """),format.raw/*150.53*/("""{"""),format.raw/*150.54*/("""
      """),format.raw/*151.7*/("""$("#disposeBookForm").submit()
    """),format.raw/*152.5*/("""}"""),format.raw/*152.6*/(""")
  """),format.raw/*153.3*/("""}"""),format.raw/*153.4*/(""")
  </script>
""")))}),format.raw/*155.2*/("""
"""))
      }
    }
  }

  def render(book:Book,loanHistory:Seq[BookLoan],messages:Messages,req:RequestHeader): play.twirl.api.HtmlFormat.Appendable = apply(book,loanHistory)(messages,req)

  def f:((Book,Seq[BookLoan]) => (Messages,RequestHeader) => play.twirl.api.HtmlFormat.Appendable) = (book,loanHistory) => (messages,req) => apply(book,loanHistory)(messages,req)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Mon Jan 08 18:24:26 KST 2018
                  SOURCE: /Users/kpyancey/Projects/Library-Demo-for-OOD/app/views/viewBookDetails.scala.html
                  HASH: 0fdda734767225380df416737bb007b915d9dc9f
                  MATRIX: 432->34|460->56|490->80|524->108|556->134|921->152|1107->243|1136->246|1165->266|1205->268|1234->270|1379->389|1392->393|1419->399|1476->430|1489->434|1524->448|1588->486|1601->490|1633->501|1694->536|1707->540|1751->563|1815->601|1828->605|1861->617|1923->653|1936->657|1972->672|2041->715|2054->719|2100->744|2161->778|2174->782|2210->797|2269->830|2282->834|2315->846|2540->1045|2584->1073|2624->1075|2657->1081|2700->1097|2717->1105|2750->1117|2794->1134|2811->1142|2843->1153|2887->1170|2904->1178|2933->1186|2977->1203|2994->1211|3028->1224|3080->1246|3111->1250|3274->1386|3289->1392|3349->1431|3395->1451|3442->1489|3482->1491|3515->1498|3563->1537|3603->1539|3638->1547|3699->1581|3714->1587|3774->1626|3814->1649|3940->1765|3981->1767|4016->1775|4097->1829|4112->1835|4183->1884|4241->1912|4274->1919|4362->1998|4402->2000|4437->2008|4570->2124|4691->2235|4732->2237|4767->2245|4914->2362|4947->2369|4995->2408|5035->2410|5070->2418|5208->2526|5243->2531|5273->2534|7472->4706|7576->4800|7617->4802|7649->4807|7662->4811|7694->4821|7728->4824|7758->4827|7864->4923|7906->4925|7938->4930|7951->4934|7983->4944|8017->4947|8047->4950|8149->5042|8190->5044|8222->5049|8235->5053|8267->5063|8301->5066|8333->5070|8386->5094|8416->5095|8449->5100|8523->5145|8553->5146|8588->5153|8650->5187|8679->5188|8760->5240|8790->5241|8825->5248|8888->5283|8917->5284|9000->5338|9030->5339|9065->5346|9128->5381|9157->5382|9189->5386|9218->5387|9264->5402
                  LINES: 17->3|18->4|19->5|20->6|21->7|26->9|31->9|33->11|33->11|33->11|34->12|39->17|39->17|39->17|41->19|41->19|41->19|43->21|43->21|43->21|45->23|45->23|45->23|47->25|47->25|47->25|49->27|49->27|49->27|51->29|51->29|51->29|53->31|53->31|53->31|55->33|55->33|55->33|61->39|61->39|61->39|62->40|63->41|63->41|63->41|64->42|64->42|64->42|65->43|65->43|65->43|66->44|66->44|66->44|68->46|69->47|74->52|74->52|74->52|75->53|75->53|75->53|76->54|76->54|76->54|77->55|77->55|77->55|77->55|78->56|78->56|78->56|79->57|79->57|79->57|79->57|80->58|81->59|81->59|81->59|82->60|83->61|83->61|83->61|84->62|85->63|86->64|86->64|86->64|87->65|88->66|89->67|90->68|154->132|154->132|154->132|155->133|155->133|155->133|156->134|157->135|157->135|157->135|158->136|158->136|158->136|159->137|160->138|160->138|160->138|161->139|161->139|161->139|162->140|164->142|165->143|165->143|166->144|166->144|166->144|167->145|168->146|168->146|169->147|169->147|170->148|171->149|171->149|172->150|172->150|173->151|174->152|174->152|175->153|175->153|177->155
                  -- GENERATED --
              */
          