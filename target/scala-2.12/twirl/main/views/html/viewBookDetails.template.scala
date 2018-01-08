
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
	"""),_display_(/*35.3*/if(!req.session.get("username").isEmpty)/*35.43*/ {_display_(Seq[Any](format.raw/*35.45*/("""
		"""),format.raw/*36.3*/("""<h2>Loan History</h2>
		<div class="table-responsive">
		  <table class="table table-striped">
		  	<tr><th>Member</th><th>Loaned Date</th><th>Due Date</th><th>Returned Date</th><tr>
		  	"""),_display_(/*40.7*/for(bookLoan <- loanHistory) yield /*40.35*/ {_display_(Seq[Any](format.raw/*40.37*/("""
		  		"""),format.raw/*41.7*/("""<tr>
		  			<td>"""),_display_(/*42.13*/bookLoan/*42.21*/.member.name),format.raw/*42.33*/("""</td>
		  			<td>"""),_display_(/*43.13*/bookLoan/*43.21*/.loanedDate),format.raw/*43.32*/("""</td>
		  			<td>"""),_display_(/*44.13*/bookLoan/*44.21*/.dueDate),format.raw/*44.29*/("""</td>
		  			<td>"""),_display_(/*45.13*/bookLoan/*45.21*/.returnedDate),format.raw/*45.34*/("""</td>
		  		</tr>
		  	""")))}),format.raw/*47.7*/("""
		  """),format.raw/*48.5*/("""</table>
	  """)))}),format.raw/*49.5*/("""
  """),format.raw/*50.3*/("""</div>
	</div>
	<div class="col-md-4">
		"""),_display_(/*53.4*/if(!req.session.get("username").isEmpty)/*53.44*/ {_display_(Seq[Any](format.raw/*53.46*/("""
			"""),format.raw/*54.4*/("""<div class="btn-group-vertical" role="group">
			  <a class="btn btn-default" href=""""),_display_(/*55.40*/routes/*55.46*/.LibraryAppController.editBook(book.id)),format.raw/*55.85*/("""">Edit Book</a>
				"""),_display_(/*56.6*/if(book.status != BookStatus.Disposed)/*56.44*/ {_display_(Seq[Any](format.raw/*56.46*/("""
				  """),_display_(/*57.8*/if(book.status == BookStatus.Available)/*57.47*/ {_display_(Seq[Any](format.raw/*57.49*/("""
				    """),format.raw/*58.9*/("""<a class="btn btn-default" href=""""),_display_(/*58.43*/routes/*58.49*/.LibraryAppController.loanBook(book.id)),format.raw/*58.88*/("""">Loan Book</a>
				  """)))}/*59.9*/else if(book.status == BookStatus.CheckedOut || loanHistory.headOption.map(_.returnedDate == None).getOrElse(false))/*59.125*/ {_display_(Seq[Any](format.raw/*59.127*/("""
				    """),format.raw/*60.9*/("""<a class="btn btn-default" data-toggle="modal" href=""""),_display_(/*60.63*/routes/*60.69*/.LibraryAppController.reportBookReturned(book.id)),format.raw/*60.118*/("""">Report Returned</a>
				  """)))}),format.raw/*61.8*/("""
				  """),_display_(/*62.8*/if(book.status == BookStatus.Available || book.status == BookStatus.CheckedOut)/*62.87*/ {_display_(Seq[Any](format.raw/*62.89*/("""
				    """),format.raw/*63.9*/("""<button class="btn btn-default" data-toggle="modal" data-target="#reportBookLostDialog">Report Lost</button>
				  """)))}/*64.9*/else if(book.status == BookStatus.Lost && !loanHistory.headOption.map(_.returnedDate == None).getOrElse(false))/*64.120*/ {_display_(Seq[Any](format.raw/*64.122*/("""
				    """),format.raw/*65.9*/("""<button class="btn btn-default" data-toggle="modal" data-target="#reportBookFoundDialog">Report Found</button>
				  """)))}),format.raw/*66.8*/("""
				  """),_display_(/*67.8*/if(book.status == BookStatus.Available)/*67.47*/ {_display_(Seq[Any](format.raw/*67.49*/("""
				    """),format.raw/*68.9*/("""<button class="btn btn-default" data-toggle="modal" data-target="#disposeBookDialog">Dispose</button>
				  """)))}),format.raw/*69.8*/("""
				""")))}),format.raw/*70.6*/("""
			"""),format.raw/*71.4*/("""</div>
		""")))}),format.raw/*72.4*/("""
	  """),format.raw/*73.4*/("""</div>
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
	
	"""),_display_(/*136.3*/form(controllers.routes.LibraryAppController.reportBookLost(book.id), 'id -> "reportLostForm")/*136.97*/ {_display_(Seq[Any](format.raw/*136.99*/("""
	  """),_display_(/*137.5*/CSRF/*137.9*/.formField),format.raw/*137.19*/("""
	""")))}),format.raw/*138.3*/("""
	"""),_display_(/*139.3*/form(controllers.routes.LibraryAppController.reportBookFound(book.id), 'id -> "reportFoundForm")/*139.99*/ {_display_(Seq[Any](format.raw/*139.101*/("""
	  """),_display_(/*140.5*/CSRF/*140.9*/.formField),format.raw/*140.19*/("""
	""")))}),format.raw/*141.3*/("""
	"""),_display_(/*142.3*/form(controllers.routes.LibraryAppController.disposeBook(book.id), 'id -> "disposeBookForm")/*142.95*/ {_display_(Seq[Any](format.raw/*142.97*/("""
	  """),_display_(/*143.5*/CSRF/*143.9*/.formField),format.raw/*143.19*/("""
	""")))}),format.raw/*144.3*/("""
	
	"""),format.raw/*146.2*/("""<script>
  $(function() """),format.raw/*147.16*/("""{"""),format.raw/*147.17*/("""
    """),format.raw/*148.5*/("""$("#confirmBookLostButton").click(function() """),format.raw/*148.50*/("""{"""),format.raw/*148.51*/("""
      """),format.raw/*149.7*/("""$("#reportLostForm").submit()
    """),format.raw/*150.5*/("""}"""),format.raw/*150.6*/(""")
    $("#confirmBookFoundButton").click(function() """),format.raw/*151.51*/("""{"""),format.raw/*151.52*/("""
      """),format.raw/*152.7*/("""$("#reportFoundForm").submit()
    """),format.raw/*153.5*/("""}"""),format.raw/*153.6*/(""")
    $("#confirmDisposeBookButton").click(function() """),format.raw/*154.53*/("""{"""),format.raw/*154.54*/("""
      """),format.raw/*155.7*/("""$("#disposeBookForm").submit()
    """),format.raw/*156.5*/("""}"""),format.raw/*156.6*/(""")
  """),format.raw/*157.3*/("""}"""),format.raw/*157.4*/(""")
  </script>
""")))}),format.raw/*159.2*/("""
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
                  DATE: Mon Jan 08 23:32:40 KST 2018
                  SOURCE: /Users/kpyancey/Projects/Library-Demo-for-OOD/app/views/viewBookDetails.scala.html
                  HASH: 9c31da514923c7f31fb26344ff7210adba3f5ade
                  MATRIX: 432->34|460->56|490->80|524->108|556->134|921->152|1107->243|1136->246|1165->266|1205->268|1234->270|1379->389|1392->393|1419->399|1476->430|1489->434|1524->448|1588->486|1601->490|1633->501|1694->536|1707->540|1751->563|1815->601|1828->605|1861->617|1923->653|1936->657|1972->672|2041->715|2054->719|2100->744|2161->778|2174->782|2210->797|2269->830|2282->834|2315->846|2356->861|2405->901|2445->903|2475->906|2690->1095|2734->1123|2774->1125|2808->1132|2852->1149|2869->1157|2902->1169|2947->1187|2964->1195|2996->1206|3041->1224|3058->1232|3087->1240|3132->1258|3149->1266|3183->1279|3237->1303|3269->1308|3312->1321|3342->1324|3410->1366|3459->1406|3499->1408|3530->1412|3642->1497|3657->1503|3717->1542|3764->1563|3811->1601|3851->1603|3885->1611|3933->1650|3973->1652|4009->1661|4070->1695|4085->1701|4145->1740|4186->1764|4312->1880|4353->1882|4389->1891|4470->1945|4485->1951|4556->2000|4615->2029|4649->2037|4737->2116|4777->2118|4813->2127|4947->2244|5068->2355|5109->2357|5145->2366|5293->2484|5327->2492|5375->2531|5415->2533|5451->2542|5590->2651|5626->2657|5657->2661|5697->2671|5728->2675|7917->4837|8021->4931|8062->4933|8094->4938|8107->4942|8139->4952|8173->4955|8203->4958|8309->5054|8351->5056|8383->5061|8396->5065|8428->5075|8462->5078|8492->5081|8594->5173|8635->5175|8667->5180|8680->5184|8712->5194|8746->5197|8778->5201|8831->5225|8861->5226|8894->5231|8968->5276|8998->5277|9033->5284|9095->5318|9124->5319|9205->5371|9235->5372|9270->5379|9333->5414|9362->5415|9445->5469|9475->5470|9510->5477|9573->5512|9602->5513|9634->5517|9663->5518|9709->5533
                  LINES: 17->3|18->4|19->5|20->6|21->7|26->9|31->9|33->11|33->11|33->11|34->12|39->17|39->17|39->17|41->19|41->19|41->19|43->21|43->21|43->21|45->23|45->23|45->23|47->25|47->25|47->25|49->27|49->27|49->27|51->29|51->29|51->29|53->31|53->31|53->31|55->33|55->33|55->33|57->35|57->35|57->35|58->36|62->40|62->40|62->40|63->41|64->42|64->42|64->42|65->43|65->43|65->43|66->44|66->44|66->44|67->45|67->45|67->45|69->47|70->48|71->49|72->50|75->53|75->53|75->53|76->54|77->55|77->55|77->55|78->56|78->56|78->56|79->57|79->57|79->57|80->58|80->58|80->58|80->58|81->59|81->59|81->59|82->60|82->60|82->60|82->60|83->61|84->62|84->62|84->62|85->63|86->64|86->64|86->64|87->65|88->66|89->67|89->67|89->67|90->68|91->69|92->70|93->71|94->72|95->73|158->136|158->136|158->136|159->137|159->137|159->137|160->138|161->139|161->139|161->139|162->140|162->140|162->140|163->141|164->142|164->142|164->142|165->143|165->143|165->143|166->144|168->146|169->147|169->147|170->148|170->148|170->148|171->149|172->150|172->150|173->151|173->151|174->152|175->153|175->153|176->154|176->154|177->155|178->156|178->156|179->157|179->157|181->159
                  -- GENERATED --
              */
          