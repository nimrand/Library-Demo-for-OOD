
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
	"""),_display_(/*7.3*/b3/*7.5*/.form(action = controllers.routes.LibraryAppController.registerPublisherPost())/*7.84*/ {_display_(Seq[Any](format.raw/*7.86*/("""
	  """),_display_(/*8.5*/CSRF/*8.9*/.formField),format.raw/*8.19*/("""
	  """),_display_(/*9.5*/b3/*9.7*/.text(publisherForm("name"), '_label -> "Publisher Name")),format.raw/*9.64*/("""
	  """),_display_(/*10.5*/b3/*10.7*/.submit('class -> "btn btn-default")/*10.43*/{_display_(Seq[Any](format.raw/*10.44*/(""" """),format.raw/*10.45*/("""Register """)))}),format.raw/*10.55*/("""
	""")))}),format.raw/*11.3*/("""
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
                  DATE: Sun Jan 07 21:29:43 KST 2018
                  SOURCE: /Users/kpyancey/Projects/Library-Demo-for-OOD/app/views/registerPublisher.scala.html
                  HASH: c747fa0fa78981a7884b1226db0298e5e499ae17
                  MATRIX: 432->1|475->39|836->57|1010->136|1038->139|1072->165|1111->167|1139->170|1148->172|1235->251|1274->253|1304->258|1315->262|1345->272|1375->277|1384->279|1461->336|1492->341|1502->343|1547->379|1586->380|1615->381|1656->391|1689->394
                  LINES: 17->1|18->2|23->4|28->4|30->6|30->6|30->6|31->7|31->7|31->7|31->7|32->8|32->8|32->8|33->9|33->9|33->9|34->10|34->10|34->10|34->10|34->10|34->10|35->11
                  -- GENERATED --
              */
          