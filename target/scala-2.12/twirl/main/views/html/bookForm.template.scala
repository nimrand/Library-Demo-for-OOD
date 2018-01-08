
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
/*3.2*/import business.EditBookDTO
/*4.2*/import business.Publisher
/*5.2*/import scala.collection.immutable.Seq

object bookForm extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template7[Form[EditBookDTO],Form[String],Seq[Publisher],String,Call,Messages,RequestHeader,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*7.2*/(bookForm : Form[EditBookDTO], publisherForm : Form[String], publishers : Seq[Publisher], buttonText : String, action : Call)(implicit messages: Messages, req: RequestHeader):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*7.176*/("""

"""),_display_(/*9.2*/b3/*9.4*/.form(action = action)/*9.26*/ {_display_(Seq[Any](format.raw/*9.28*/("""
  """),_display_(/*10.4*/CSRF/*10.8*/.formField),format.raw/*10.18*/("""
  """),_display_(/*11.4*/b3/*11.6*/.text(bookForm("title"), '_label -> "Title", 'placeholder -> "Enter the book's title here.")),format.raw/*11.98*/("""
  """),_display_(/*12.4*/b3/*12.6*/.date(bookForm("publicationDate"), '_label -> "Publication Date")),format.raw/*12.71*/("""
  """),_display_(/*13.4*/b3/*13.6*/.textarea(bookForm("description"), '_label -> "Description", 'rows -> 3, 'placeholder -> "Enter a description here.")),format.raw/*13.123*/("""
  """),_display_(/*14.4*/b3/*14.6*/.text(bookForm("isbn"), '_label -> "ISBN", 'placeholder -> "978-1-56619-909-4")),format.raw/*14.85*/("""
  """),_display_(/*15.4*/b3/*15.6*/.text(bookForm("price"), '_label -> "Price", 'placeholder -> "10.99")),format.raw/*15.75*/("""
  """),_display_(/*16.4*/b3/*16.6*/.text(bookForm("keywords"), '_label -> "Keywords", 'placeholder -> "flowers plants agriculture")),format.raw/*16.102*/("""
  """),_display_(/*17.4*/b3/*17.6*/.text(bookForm("callNumber"), '_label -> "Call Number", 'placeholder -> "630.51 B661")),format.raw/*17.92*/("""
  """),_display_(/*18.4*/b3/*18.6*/.select(bookForm("publisherID"), publishers.map(publisher => (publisher.id.asInt.toString, publisher.name)), '_label -> "Publisher", 'id -> "publisherID")),format.raw/*18.160*/("""
  """),format.raw/*19.3*/("""<div class="form-group">
  	<button id="newPublisherButton" type="button" class="btn btn-default" data-toggle="modal" data-target="#addPublisherDialog">New Publisher</button>
  </div>
  """),_display_(/*22.4*/b3/*22.6*/.submit('class -> "btn btn-primary")/*22.42*/{_display_(Seq[Any](format.raw/*22.43*/(""" """),_display_(/*22.45*/buttonText),format.raw/*22.55*/(""" """)))}),format.raw/*22.57*/("""
""")))}),format.raw/*23.2*/("""

"""),format.raw/*25.1*/("""<div id="addPublisherDialog" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">New Publisher</h4>
      </div>
      <div class="modal-body">
        <form id="submitNewPublisherForm">
		  """),_display_(/*36.6*/CSRF/*36.10*/.formField),format.raw/*36.20*/("""
		  """),_display_(/*37.6*/b3/*37.8*/.text(publisherForm("name"), '_label -> "Publisher Name")),format.raw/*37.65*/("""
		"""),format.raw/*38.3*/("""</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
        <button type="button" class="btn btn-default" id="submitNewPublisherButton">Add Publisher</button>
      </div>
    </div>
  </div>
</div>

<script>
  $(function() """),format.raw/*49.16*/("""{"""),format.raw/*49.17*/("""
    """),format.raw/*50.5*/("""$("#submitNewPublisherButton").click(function() """),format.raw/*50.53*/("""{"""),format.raw/*50.54*/("""
	    	"""),format.raw/*51.7*/("""$.ajax("""),format.raw/*51.14*/("""{"""),format.raw/*51.15*/("""
	        """),format.raw/*52.10*/("""url: '"""),_display_(/*52.17*/controllers/*52.28*/.routes.LibraryAppController.registerPublisherPost()),format.raw/*52.80*/("""',
	        type: 'POST',
	        data: $("#submitNewPublisherForm").serialize(),
	        async: false,
	        success: function(data) """),format.raw/*56.34*/("""{"""),format.raw/*56.35*/("""
	        		"""),format.raw/*57.12*/("""$('#publisherID').append($("<option></option>")
	                           .attr("value",data["publisherID"])
	                           .text($("#name").val()));
	        		$("#publisherID").val(data["publisherID"]).change();
	        		$('#addPublisherDialog').modal('toggle')
	        """),format.raw/*62.10*/("""}"""),format.raw/*62.11*/("""
	      """),format.raw/*63.8*/("""}"""),format.raw/*63.9*/(""")
    		"""),format.raw/*64.7*/("""}"""),format.raw/*64.8*/("""
    """),format.raw/*65.5*/(""")
  """),format.raw/*66.3*/("""}"""),format.raw/*66.4*/(""")
  </script>"""))
      }
    }
  }

  def render(bookForm:Form[EditBookDTO],publisherForm:Form[String],publishers:Seq[Publisher],buttonText:String,action:Call,messages:Messages,req:RequestHeader): play.twirl.api.HtmlFormat.Appendable = apply(bookForm,publisherForm,publishers,buttonText,action)(messages,req)

  def f:((Form[EditBookDTO],Form[String],Seq[Publisher],String,Call) => (Messages,RequestHeader) => play.twirl.api.HtmlFormat.Appendable) = (bookForm,publisherForm,publishers,buttonText,action) => (messages,req) => apply(bookForm,publisherForm,publishers,buttonText,action)(messages,req)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Mon Jan 08 09:16:11 KST 2018
                  SOURCE: /Users/kpyancey/Projects/Library-Demo-for-OOD/app/views/bookForm.scala.html
                  HASH: cff57cc7eae571444335aa3e964c5db20eb33206
                  MATRIX: 432->1|475->39|498->56|533->85|566->112|985->152|1255->326|1283->329|1292->331|1322->353|1361->355|1391->359|1403->363|1434->373|1464->377|1474->379|1587->471|1617->475|1627->477|1713->542|1743->546|1753->548|1892->665|1922->669|1932->671|2032->750|2062->754|2072->756|2162->825|2192->829|2202->831|2320->927|2350->931|2360->933|2467->1019|2497->1023|2507->1025|2683->1179|2713->1182|2926->1369|2936->1371|2981->1407|3020->1408|3049->1410|3080->1420|3113->1422|3145->1424|3174->1426|3609->1835|3622->1839|3653->1849|3685->1855|3695->1857|3773->1914|3803->1917|4147->2233|4176->2234|4208->2239|4284->2287|4313->2288|4347->2295|4382->2302|4411->2303|4449->2313|4483->2320|4503->2331|4576->2383|4743->2522|4772->2523|4812->2535|5130->2825|5159->2826|5194->2834|5222->2835|5257->2843|5285->2844|5317->2849|5348->2853|5376->2854
                  LINES: 17->1|18->2|19->3|20->4|21->5|26->7|31->7|33->9|33->9|33->9|33->9|34->10|34->10|34->10|35->11|35->11|35->11|36->12|36->12|36->12|37->13|37->13|37->13|38->14|38->14|38->14|39->15|39->15|39->15|40->16|40->16|40->16|41->17|41->17|41->17|42->18|42->18|42->18|43->19|46->22|46->22|46->22|46->22|46->22|46->22|46->22|47->23|49->25|60->36|60->36|60->36|61->37|61->37|61->37|62->38|73->49|73->49|74->50|74->50|74->50|75->51|75->51|75->51|76->52|76->52|76->52|76->52|80->56|80->56|81->57|86->62|86->62|87->63|87->63|88->64|88->64|89->65|90->66|90->66
                  -- GENERATED --
              */
          