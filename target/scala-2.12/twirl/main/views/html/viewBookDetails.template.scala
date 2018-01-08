
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

object viewBookDetails extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[BookID,Book,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*6.2*/(bookID : BookID, book : Book):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*6.32*/("""

"""),_display_(/*8.2*/main("Book Details")/*8.22*/ {_display_(Seq[Any](format.raw/*8.24*/("""
	"""),format.raw/*9.2*/("""<div class="row">
	<div class="col-md-8">
	<dl class="dl-horizontal">
	  <dt>Title</dt>
	  <dd>"""),_display_(/*13.9*/book/*13.13*/.title),format.raw/*13.19*/("""</dd>
	  <dt>ISBN</dt>
	  <dd>"""),_display_(/*15.9*/book/*15.13*/.isbn.toString),format.raw/*15.27*/("""</dd>
	  <dt>Call Number</dt>
	  <dd>"""),_display_(/*17.9*/book/*17.13*/.callNumber),format.raw/*17.24*/("""</dd>
	  <dt>Keywords</dt>
	  <dd>"""),_display_(/*19.9*/book/*19.13*/.keywords),format.raw/*19.22*/("""</dd>
	  <dt>Description</dt>
	  <dd>"""),_display_(/*21.9*/book/*21.13*/.description),format.raw/*21.25*/("""</dd>
	  <dt>Publisher</dt>
	  <dd>"""),_display_(/*23.9*/book/*23.13*/.publisher.name),format.raw/*23.28*/("""</dd>
	  <dt>Publication Date</dt>
	  <dd>"""),_display_(/*25.9*/book/*25.13*/.publicationDate.toString),format.raw/*25.38*/("""</dd>
	  <dt>Price</dt>
	  <dd>$ """),_display_(/*27.11*/book/*27.15*/.price.toString),format.raw/*27.30*/("""</dd>
	</dl>
	</div>
	<div class="col-md-4">
		<a class="btn btn-default" href=""""),_display_(/*31.37*/routes/*31.43*/.LibraryAppController.editBook(bookID.asInt)),format.raw/*31.87*/("""">Edit Book</a>
	</div>
	</div>
""")))}),format.raw/*34.2*/("""
"""))
      }
    }
  }

  def render(bookID:BookID,book:Book): play.twirl.api.HtmlFormat.Appendable = apply(bookID,book)

  def f:((BookID,Book) => play.twirl.api.HtmlFormat.Appendable) = (bookID,book) => apply(bookID,book)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Mon Jan 08 09:18:51 KST 2018
                  SOURCE: /Users/kpyancey/Projects/Library-Demo-for-OOD/app/views/viewBookDetails.scala.html
                  HASH: a19bc53521c03bf9060e563c8a8b1d085f9819b1
                  MATRIX: 432->34|460->56|802->81|927->111|955->114|983->134|1022->136|1050->138|1172->234|1185->238|1212->244|1269->275|1282->279|1317->293|1381->331|1394->335|1426->346|1487->381|1500->385|1530->394|1594->432|1607->436|1640->448|1702->484|1715->488|1751->503|1820->546|1833->550|1879->575|1940->609|1953->613|1989->628|2097->709|2112->715|2177->759|2240->792
                  LINES: 17->3|18->4|23->6|28->6|30->8|30->8|30->8|31->9|35->13|35->13|35->13|37->15|37->15|37->15|39->17|39->17|39->17|41->19|41->19|41->19|43->21|43->21|43->21|45->23|45->23|45->23|47->25|47->25|47->25|49->27|49->27|49->27|53->31|53->31|53->31|56->34
                  -- GENERATED --
              */
          