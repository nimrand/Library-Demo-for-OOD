
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
/*3.2*/import business.RegisterBookRequest

object registerBook extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template3[Form[RegisterBookRequest],Messages,RequestHeader,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*5.2*/(publisherForm : Form[RegisterBookRequest])(implicit messages: Messages, req: RequestHeader):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*5.94*/("""

"""),_display_(/*7.2*/main("Register Publisher")/*7.28*/ {_display_(Seq[Any](format.raw/*7.30*/("""
	
	"""),_display_(/*9.3*/b3/*9.5*/.form(action = controllers.routes.LibraryAppController.registerBookPost())/*9.79*/ {_display_(Seq[Any](format.raw/*9.81*/("""
	  """),_display_(/*10.5*/CSRF/*10.9*/.formField),format.raw/*10.19*/("""
	  """),_display_(/*11.5*/b3/*11.7*/.text(publisherForm("title"), '_label -> "Title", 'placeholder -> "Enter the book's title here.")),format.raw/*11.104*/("""
	  """),_display_(/*12.5*/b3/*12.7*/.date(publisherForm("publicationDate"), '_label -> "Publication Date")),format.raw/*12.77*/("""
	  """),_display_(/*13.5*/b3/*13.7*/.textarea(publisherForm("description"), '_label -> "Description", 'rows -> 3, 'placeholder -> "Enter a description here.")),format.raw/*13.129*/("""
	  """),_display_(/*14.5*/b3/*14.7*/.text(publisherForm("isbn"), '_label -> "ISBN", 'placeholder -> "978-1-56619-909-4")),format.raw/*14.91*/("""
	  """),_display_(/*15.5*/b3/*15.7*/.text(publisherForm("price"), '_label -> "Price", 'placeholder -> "10.99")),format.raw/*15.81*/("""
	  """),_display_(/*16.5*/b3/*16.7*/.text(publisherForm("keywords"), '_label -> "Keywords", 'placeholder -> "flowers plants agriculture")),format.raw/*16.108*/("""
	  """),_display_(/*17.5*/b3/*17.7*/.text(publisherForm("callNumber"), '_label -> "Call Number", 'placeholder -> "630.51 B661")),format.raw/*17.98*/("""
	  """),_display_(/*18.5*/b3/*18.7*/.submit('class -> "btn btn-default")/*18.43*/{_display_(Seq[Any](format.raw/*18.44*/(""" """),format.raw/*18.45*/("""Register """)))}),format.raw/*18.55*/("""
	""")))}),format.raw/*19.3*/("""
""")))}))
      }
    }
  }

  def render(publisherForm:Form[RegisterBookRequest],messages:Messages,req:RequestHeader): play.twirl.api.HtmlFormat.Appendable = apply(publisherForm)(messages,req)

  def f:((Form[RegisterBookRequest]) => (Messages,RequestHeader) => play.twirl.api.HtmlFormat.Appendable) = (publisherForm) => (messages,req) => apply(publisherForm)(messages,req)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Sun Jan 07 18:39:14 KST 2018
                  SOURCE: /Users/kpyancey/Projects/Library-Demo-for-OOD/app/views/registerBook.scala.html
                  HASH: e1fed5820bce73466ec046ab76582e4dd13bd42c
                  MATRIX: 432->1|475->39|498->56|887->94|1074->186|1102->189|1136->215|1175->217|1205->222|1214->224|1296->298|1335->300|1366->305|1378->309|1409->319|1440->324|1450->326|1569->423|1600->428|1610->430|1701->500|1732->505|1742->507|1886->629|1917->634|1927->636|2032->720|2063->725|2073->727|2168->801|2199->806|2209->808|2332->909|2363->914|2373->916|2485->1007|2516->1012|2526->1014|2571->1050|2610->1051|2639->1052|2680->1062|2713->1065
                  LINES: 17->1|18->2|19->3|24->5|29->5|31->7|31->7|31->7|33->9|33->9|33->9|33->9|34->10|34->10|34->10|35->11|35->11|35->11|36->12|36->12|36->12|37->13|37->13|37->13|38->14|38->14|38->14|39->15|39->15|39->15|40->16|40->16|40->16|41->17|41->17|41->17|42->18|42->18|42->18|42->18|42->18|42->18|43->19
                  -- GENERATED --
              */
          