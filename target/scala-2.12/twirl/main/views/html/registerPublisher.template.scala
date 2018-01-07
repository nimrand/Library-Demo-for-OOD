
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

object registerPublisher extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template3[Form[String],Messages,RequestHeader,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*4.2*/(publisherForm : Form[String])(implicit messages: Messages, req: RequestHeader):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*4.81*/("""

"""),_display_(/*6.2*/main("Register Publisher")/*6.28*/ {_display_(Seq[Any](format.raw/*6.30*/("""
	
	"""),_display_(/*8.3*/b3/*8.5*/.form(action = controllers.routes.LibraryAppController.registerPublisherPost())/*8.84*/ {_display_(Seq[Any](format.raw/*8.86*/("""
	  """),_display_(/*9.5*/CSRF/*9.9*/.formField),format.raw/*9.19*/("""
	  """),_display_(/*10.5*/b3/*10.7*/.text(publisherForm("name"), '_label -> "Publisher Name")),format.raw/*10.64*/("""
	  """),_display_(/*11.5*/b3/*11.7*/.submit('class -> "btn btn-default")/*11.43*/{_display_(Seq[Any](format.raw/*11.44*/(""" """),format.raw/*11.45*/("""Register """)))}),format.raw/*11.55*/("""
	""")))}),format.raw/*12.3*/("""
""")))}))
      }
    }
  }

  def render(publisherForm:Form[String],messages:Messages,req:RequestHeader): play.twirl.api.HtmlFormat.Appendable = apply(publisherForm)(messages,req)

  def f:((Form[String]) => (Messages,RequestHeader) => play.twirl.api.HtmlFormat.Appendable) = (publisherForm) => (messages,req) => apply(publisherForm)(messages,req)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Sun Jan 07 18:39:14 KST 2018
                  SOURCE: /Users/kpyancey/Projects/Library-Demo-for-OOD/app/views/registerPublisher.scala.html
                  HASH: 50daad96a91be8a08a6e54122173a674d6805e73
                  MATRIX: 432->1|475->39|836->57|1010->136|1038->139|1072->165|1111->167|1141->172|1150->174|1237->253|1276->255|1306->260|1317->264|1347->274|1378->279|1388->281|1466->338|1497->343|1507->345|1552->381|1591->382|1620->383|1661->393|1694->396
                  LINES: 17->1|18->2|23->4|28->4|30->6|30->6|30->6|32->8|32->8|32->8|32->8|33->9|33->9|33->9|34->10|34->10|34->10|35->11|35->11|35->11|35->11|35->11|35->11|36->12
                  -- GENERATED --
              */
          