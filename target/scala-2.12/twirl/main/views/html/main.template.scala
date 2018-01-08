
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

object main extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[String,Html,play.twirl.api.HtmlFormat.Appendable] {

  /*
 * This template is called from the `index` template. This template
 * handles the rendering of the page header and body tags. It takes
 * two arguments, a `String` for the title of the page and an `Html`
 * object to insert into the body of the page.
 */
  def apply/*7.2*/(title: String)(content: Html):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*7.32*/("""

"""),format.raw/*9.1*/("""<!DOCTYPE html>
<html lang="en">
    <head>
        """),format.raw/*12.62*/("""
        """),format.raw/*13.9*/("""<title>"""),_display_(/*13.17*/title),format.raw/*13.22*/("""</title>
        <link rel="stylesheet" media="screen" href=""""),_display_(/*14.54*/routes/*14.60*/.Assets.versioned("stylesheets/main.css")),format.raw/*14.101*/("""">
        <link rel="shortcut icon" type="image/png" href=""""),_display_(/*15.59*/routes/*15.65*/.Assets.versioned("images/favicon.png")),format.raw/*15.104*/("""">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <body>
    		<div class="container-fluid">
    		<h1>"""),_display_(/*22.12*/title),format.raw/*22.17*/("""</h1>
        """),format.raw/*24.32*/("""
        """),_display_(/*25.10*/content),format.raw/*25.17*/("""
		"""),format.raw/*26.3*/("""</div>
      <script src=""""),_display_(/*27.21*/routes/*27.27*/.Assets.versioned("javascripts/main.js")),format.raw/*27.67*/("""" type="text/javascript"></script>
    </body>
</html>
"""))
      }
    }
  }

  def render(title:String,content:Html): play.twirl.api.HtmlFormat.Appendable = apply(title)(content)

  def f:((String) => (Html) => play.twirl.api.HtmlFormat.Appendable) = (title) => (content) => apply(title)(content)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Mon Jan 08 08:51:32 KST 2018
                  SOURCE: /Users/kpyancey/Projects/Library-Demo-for-OOD/app/views/main.scala.html
                  HASH: aa4be5a3c8c43c2f78d68df5df4de8a428e0bfec
                  MATRIX: 987->260|1112->290|1140->292|1220->397|1256->406|1291->414|1317->419|1406->481|1421->487|1484->528|1572->589|1587->595|1648->634|2036->995|2062->1000|2104->1104|2141->1114|2169->1121|2199->1124|2253->1151|2268->1157|2329->1197
                  LINES: 26->7|31->7|33->9|36->12|37->13|37->13|37->13|38->14|38->14|38->14|39->15|39->15|39->15|46->22|46->22|47->24|48->25|48->25|49->26|50->27|50->27|50->27
                  -- GENERATED --
              */
          