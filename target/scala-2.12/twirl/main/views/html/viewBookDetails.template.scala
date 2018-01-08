
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
/*6.2*/import helper._

object viewBookDetails extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[BookID,Book,Messages,RequestHeader,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*8.2*/(bookID : BookID, book : Book)(implicit messages: Messages, req: RequestHeader):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*8.81*/("""

"""),_display_(/*10.2*/main("Book Details")/*10.22*/ {_display_(Seq[Any](format.raw/*10.24*/("""
	"""),format.raw/*11.2*/("""<h1>Book Details</h1>
	<div class="row">
	<div class="col-md-8">
	<dl class="dl-horizontal">
	  <dt>Title</dt>
	  <dd>"""),_display_(/*16.9*/book/*16.13*/.title),format.raw/*16.19*/("""</dd>
	  <dt>ISBN</dt>
	  <dd>"""),_display_(/*18.9*/book/*18.13*/.isbn.toString),format.raw/*18.27*/("""</dd>
	  <dt>Call Number</dt>
	  <dd>"""),_display_(/*20.9*/book/*20.13*/.callNumber),format.raw/*20.24*/("""</dd>
	  <dt>Keywords</dt>
	  <dd>"""),_display_(/*22.9*/book/*22.13*/.keywords.mkString(" ")),format.raw/*22.36*/("""</dd>
	  <dt>Description</dt>
	  <dd>"""),_display_(/*24.9*/book/*24.13*/.description),format.raw/*24.25*/("""</dd>
	  <dt>Publisher</dt>
	  <dd>"""),_display_(/*26.9*/book/*26.13*/.publisher.name),format.raw/*26.28*/("""</dd>
	  <dt>Publication Date</dt>
	  <dd>"""),_display_(/*28.9*/book/*28.13*/.publicationDate.toString),format.raw/*28.38*/("""</dd>
	  <dt>Price</dt>
	  <dd>$ """),_display_(/*30.11*/book/*30.15*/.price.toString),format.raw/*30.30*/("""</dd>
	  <dt>Status</dt>
	  <dd>"""),_display_(/*32.9*/book/*32.13*/.status.name),format.raw/*32.25*/("""</dd>
	</dl>
	</div>
	<div class="col-md-4">
		<div class="btn-group-vertical" role="group">
		  <a class="btn btn-default" href=""""),_display_(/*37.39*/routes/*37.45*/.LibraryAppController.editBook(bookID)),format.raw/*37.83*/("""">Edit Book</a>
			"""),_display_(/*38.5*/if(book.status != BookStatus.Disposed)/*38.43*/ {_display_(Seq[Any](format.raw/*38.45*/("""
			  """),_display_(/*39.7*/if(book.status == BookStatus.Available)/*39.46*/ {_display_(Seq[Any](format.raw/*39.48*/("""
			    """),format.raw/*40.8*/("""<a class="btn btn-default" href=""""),_display_(/*40.42*/routes/*40.48*/.LibraryAppController.loanBook(bookID)),format.raw/*40.86*/("""">Loan Book</a>
			  """)))}/*41.8*/else if(book.status == BookStatus.CheckedOut)/*41.53*/ {_display_(Seq[Any](format.raw/*41.55*/("""
			    """),format.raw/*42.8*/("""<button class="btn btn-default" data-toggle="modal" data-target="#bookReturnedDialog">Report Returned</button>
			  """)))}),format.raw/*43.7*/("""
			  """),_display_(/*44.7*/if(book.status == BookStatus.Available || book.status == BookStatus.CheckedOut)/*44.86*/ {_display_(Seq[Any](format.raw/*44.88*/("""
			    """),format.raw/*45.8*/("""<button class="btn btn-default" data-toggle="modal" data-target="#reportBookLostDialog">Report Lost</button>
			  """)))}/*46.8*/else if(book.status == BookStatus.Lost)/*46.47*/ {_display_(Seq[Any](format.raw/*46.49*/("""
			    """),format.raw/*47.8*/("""<button class="btn btn-default" data-toggle="modal" data-target="#reportBookFoundDialog">Report Found</button>
			  """)))}),format.raw/*48.7*/("""
			  """),_display_(/*49.7*/if(book.status == BookStatus.Available)/*49.46*/ {_display_(Seq[Any](format.raw/*49.48*/("""
			    """),format.raw/*50.8*/("""<button class="btn btn-default" data-toggle="modal" data-target="#disposeBookDialog">Dispose</button>
			  """)))}),format.raw/*51.7*/("""
			""")))}),format.raw/*52.5*/("""
		"""),format.raw/*53.3*/("""</div>
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
	
	<div id="reportBookReturned" class="modal fade" role="dialog">
	  <div class="modal-dialog">
	
	    <!-- Modal content-->
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        <h4 class="modal-title">Book Returned</h4>
	      </div>
	      <div class="modal-body">
	        """),_display_(/*127.11*/form(action = routes.LibraryAppController.reportBookReturned(book.id), 'id -> "bookReturnedForm")/*127.108*/ {_display_(Seq[Any](format.raw/*127.110*/("""
	          """),_display_(/*128.13*/CSRF/*128.17*/.formField),format.raw/*128.27*/("""
	          """),_display_(/*129.13*/b3/*129.15*/.date(controllers.Forms.defaultBookReturnedForm()("returnedDate"), '_label -> "Returned Date")),format.raw/*129.109*/("""
	        """)))}),format.raw/*130.11*/("""
	      """),format.raw/*131.8*/("""</div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-primary" id="confirmBookReturnedButton">Report Book Returned</button>
	        <button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	"""),_display_(/*140.3*/form(controllers.routes.LibraryAppController.reportBookLost(bookID), 'id -> "reportLostForm")/*140.96*/ {_display_(Seq[Any](format.raw/*140.98*/("""
	  """),_display_(/*141.5*/CSRF/*141.9*/.formField),format.raw/*141.19*/("""
	""")))}),format.raw/*142.3*/("""
	"""),_display_(/*143.3*/form(controllers.routes.LibraryAppController.reportBookFound(bookID), 'id -> "reportFoundForm")/*143.98*/ {_display_(Seq[Any](format.raw/*143.100*/("""
	  """),_display_(/*144.5*/CSRF/*144.9*/.formField),format.raw/*144.19*/("""
	""")))}),format.raw/*145.3*/("""
	"""),_display_(/*146.3*/form(controllers.routes.LibraryAppController.disposeBook(bookID), 'id -> "disposeBookForm")/*146.94*/ {_display_(Seq[Any](format.raw/*146.96*/("""
	  """),_display_(/*147.5*/CSRF/*147.9*/.formField),format.raw/*147.19*/("""
	""")))}),format.raw/*148.3*/("""
	
	"""),format.raw/*150.2*/("""<script>
  $(function() """),format.raw/*151.16*/("""{"""),format.raw/*151.17*/("""
    """),format.raw/*152.5*/("""$("#confirmBookLostButton").click(function() """),format.raw/*152.50*/("""{"""),format.raw/*152.51*/("""
      """),format.raw/*153.7*/("""$("#reportLostForm").submit()
    """),format.raw/*154.5*/("""}"""),format.raw/*154.6*/(""")
    $("#confirmBookFoundButton").click(function() """),format.raw/*155.51*/("""{"""),format.raw/*155.52*/("""
      """),format.raw/*156.7*/("""$("#reportFoundForm").submit()
    """),format.raw/*157.5*/("""}"""),format.raw/*157.6*/(""")
    $("#confirmDisposeBookButton").click(function() """),format.raw/*158.53*/("""{"""),format.raw/*158.54*/("""
      """),format.raw/*159.7*/("""$("#disposeBookForm").submit()
    """),format.raw/*160.5*/("""}"""),format.raw/*160.6*/(""")
    $("#confirmBookReturnedButton").click(function() """),format.raw/*161.54*/("""{"""),format.raw/*161.55*/("""
      """),format.raw/*162.7*/("""$("#bookReturnedForm").submit()
    """),format.raw/*163.5*/("""}"""),format.raw/*163.6*/(""")
  """),format.raw/*164.3*/("""}"""),format.raw/*164.4*/(""")
  </script>
""")))}),format.raw/*166.2*/("""
"""))
      }
    }
  }

  def render(bookID:BookID,book:Book,messages:Messages,req:RequestHeader): play.twirl.api.HtmlFormat.Appendable = apply(bookID,book)(messages,req)

  def f:((BookID,Book) => (Messages,RequestHeader) => play.twirl.api.HtmlFormat.Appendable) = (bookID,book) => (messages,req) => apply(bookID,book)(messages,req)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Mon Jan 08 17:07:49 KST 2018
                  SOURCE: /Users/kpyancey/Projects/Library-Demo-for-OOD/app/views/viewBookDetails.scala.html
                  HASH: 8bfb604d4970145f08b431c3537dbc8021b671c2
                  MATRIX: 432->34|460->56|490->80|524->108|882->126|1056->205|1085->208|1114->228|1154->230|1183->232|1328->351|1341->355|1368->361|1425->392|1438->396|1473->410|1537->448|1550->452|1582->463|1643->498|1656->502|1700->525|1764->563|1777->567|1810->579|1872->615|1885->619|1921->634|1990->677|2003->681|2049->706|2110->740|2123->744|2159->759|2218->792|2231->796|2264->808|2422->939|2437->945|2496->983|2542->1003|2589->1041|2629->1043|2662->1050|2710->1089|2750->1091|2785->1099|2846->1133|2861->1139|2920->1177|2960->1200|3014->1245|3054->1247|3089->1255|3236->1372|3269->1379|3357->1458|3397->1460|3432->1468|3565->1584|3613->1623|3653->1625|3688->1633|3835->1750|3868->1757|3916->1796|3956->1798|3991->1806|4129->1914|4164->1919|4194->1922|6773->4473|6881->4570|6923->4572|6964->4585|6978->4589|7010->4599|7051->4612|7063->4614|7180->4708|7223->4719|7259->4727|7582->5023|7685->5116|7726->5118|7758->5123|7771->5127|7803->5137|7837->5140|7867->5143|7972->5238|8014->5240|8046->5245|8059->5249|8091->5259|8125->5262|8155->5265|8256->5356|8297->5358|8329->5363|8342->5367|8374->5377|8408->5380|8440->5384|8493->5408|8523->5409|8556->5414|8630->5459|8660->5460|8695->5467|8757->5501|8786->5502|8867->5554|8897->5555|8932->5562|8995->5597|9024->5598|9107->5652|9137->5653|9172->5660|9235->5695|9264->5696|9348->5751|9378->5752|9413->5759|9477->5795|9506->5796|9538->5800|9567->5801|9613->5816
                  LINES: 17->3|18->4|19->5|20->6|25->8|30->8|32->10|32->10|32->10|33->11|38->16|38->16|38->16|40->18|40->18|40->18|42->20|42->20|42->20|44->22|44->22|44->22|46->24|46->24|46->24|48->26|48->26|48->26|50->28|50->28|50->28|52->30|52->30|52->30|54->32|54->32|54->32|59->37|59->37|59->37|60->38|60->38|60->38|61->39|61->39|61->39|62->40|62->40|62->40|62->40|63->41|63->41|63->41|64->42|65->43|66->44|66->44|66->44|67->45|68->46|68->46|68->46|69->47|70->48|71->49|71->49|71->49|72->50|73->51|74->52|75->53|149->127|149->127|149->127|150->128|150->128|150->128|151->129|151->129|151->129|152->130|153->131|162->140|162->140|162->140|163->141|163->141|163->141|164->142|165->143|165->143|165->143|166->144|166->144|166->144|167->145|168->146|168->146|168->146|169->147|169->147|169->147|170->148|172->150|173->151|173->151|174->152|174->152|174->152|175->153|176->154|176->154|177->155|177->155|178->156|179->157|179->157|180->158|180->158|181->159|182->160|182->160|183->161|183->161|184->162|185->163|185->163|186->164|186->164|188->166
                  -- GENERATED --
              */
          