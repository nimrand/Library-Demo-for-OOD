
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

object index extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[Messages,RequestHeader,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/()(implicit messages: Messages, req: RequestHeader):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.53*/("""

"""),_display_(/*3.2*/main("Welcome to the Lawrence Public Library")/*3.48*/ {_display_(Seq[Any](format.raw/*3.50*/("""
"""),format.raw/*4.1*/("""<div class="jumbotron">
<div class="container">
<h1 class="display-3">Welcome to the Lawrence Public Library!</h1>
<p><a href=""""),_display_(/*7.14*/routes/*7.20*/.LibraryAppController.searchBooks),format.raw/*7.53*/("""" class="btn btn-primary btn-lg" role="button">Search Books »</a></p>
</div>
</div>
""")))}),format.raw/*10.2*/("""
"""))
      }
    }
  }

  def render(messages:Messages,req:RequestHeader): play.twirl.api.HtmlFormat.Appendable = apply()(messages,req)

  def f:(() => (Messages,RequestHeader) => play.twirl.api.HtmlFormat.Appendable) = () => (messages,req) => apply()(messages,req)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Mon Jan 08 22:41:39 KST 2018
                  SOURCE: /Users/kpyancey/Projects/Library-Demo-for-OOD/app/views/index.scala.html
                  HASH: a63c4f70b0a1521e4aaadb4d6b9133f2d910fcdf
                  MATRIX: 745->1|891->52|919->55|973->101|1012->103|1039->104|1193->232|1207->238|1260->271|1375->356
                  LINES: 21->1|26->1|28->3|28->3|28->3|29->4|32->7|32->7|32->7|35->10
                  -- GENERATED --
              */
          