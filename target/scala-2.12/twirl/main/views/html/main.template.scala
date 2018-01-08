
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

object main extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template3[String,Html,RequestHeader,play.twirl.api.HtmlFormat.Appendable] {

  /*
 * This template is called from the `index` template. This template
 * handles the rendering of the page header and body tags. It takes
 * two arguments, a `String` for the title of the page and an `Html`
 * object to insert into the body of the page.
 */
  def apply/*7.2*/(title: String)(content: Html)(implicit request: RequestHeader):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*7.65*/("""

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
	    <div class="navbar navbar-inverse navbar-static-top">
	        <div class="container">
	            <div class="navbar-header">
	                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
	                    <span class="icon-bar"></span>
	                    <span class="icon-bar"></span>
	                    <span class="icon-bar"></span>
	                </button>
	                <a class="navbar-brand" href=""""),_display_(/*29.49*/routes/*29.55*/.LibraryAppController.index),format.raw/*29.82*/("""">Lawrence Public Library</a>
	            </div>
	            <div class="navbar-collapse collapse" id="navbar">
	                <ul class="nav navbar-nav">
	                    <li><a href=""""),_display_(/*33.36*/routes/*33.42*/.LibraryAppController.searchBooks),format.raw/*33.75*/("""">Search Books</a></li>
	                    <li class="dropdown">
	                      <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button">Register<span class="caret"></span></a>
	                      <ul class="dropdown-menu">
                             <li><a href=""""),_display_(/*37.44*/routes/*37.50*/.LibraryAppController.registerBook),format.raw/*37.84*/("""">New Book</a></li>
                             <li><a href=""""),_display_(/*38.44*/routes/*38.50*/.LibraryAppController.registerLibraryMember),format.raw/*38.93*/("""">New Library Member</a></li>
                          </ul>
	                    </li>
	                    
	                </ul>
	                <ul class="nav navbar-nav navbar-right">
	                	"""),_display_(/*44.20*/request/*44.27*/.session.get("username").map/*44.55*/ { username =>_display_(Seq[Any](format.raw/*44.69*/("""
	                	  """),format.raw/*45.21*/("""<li><a href=""""),_display_(/*45.35*/routes/*45.41*/.LibraryAppController.logout),format.raw/*45.69*/("""">Sign Out</a></li>
	                	""")))}/*46.20*/.getOrElse/*46.30*/{_display_(Seq[Any](format.raw/*46.31*/("""
	                  """),format.raw/*47.20*/("""<li><a href=""""),_display_(/*47.34*/routes/*47.40*/.LibraryAppController.login),format.raw/*47.67*/("""">Sign In</a></li>
	                """)))}),format.raw/*48.19*/("""
	                """),format.raw/*49.18*/("""</ul>
	            </div>
	        </div>
	    </div>
    		<div class="container body-content">
    		"""),_display_(/*54.8*/request/*54.15*/.flash.get("success").map/*54.40*/ { message =>_display_(Seq[Any](format.raw/*54.53*/("""
	   		"""),format.raw/*55.7*/("""<div class="alert alert-success">
			  """),_display_(/*56.7*/message),format.raw/*56.14*/("""
			"""),format.raw/*57.4*/("""</div>
		""")))}),format.raw/*58.4*/("""
        """),format.raw/*60.32*/("""
        """),_display_(/*61.10*/content),format.raw/*61.17*/("""
		"""),format.raw/*62.3*/("""</div>
      <script src=""""),_display_(/*63.21*/routes/*63.27*/.Assets.versioned("javascripts/main.js")),format.raw/*63.67*/("""" type="text/javascript"></script>
    </body>
</html>
"""))
      }
    }
  }

  def render(title:String,content:Html,request:RequestHeader): play.twirl.api.HtmlFormat.Appendable = apply(title)(content)(request)

  def f:((String) => (Html) => (RequestHeader) => play.twirl.api.HtmlFormat.Appendable) = (title) => (content) => (request) => apply(title)(content)(request)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Mon Jan 08 22:53:35 KST 2018
                  SOURCE: /Users/kpyancey/Projects/Library-Demo-for-OOD/app/views/main.scala.html
                  HASH: 8d691a691c6b748a1b96cc705c01dcabe6028d37
                  MATRIX: 1001->260|1159->323|1187->325|1267->430|1303->439|1338->447|1364->452|1453->514|1468->520|1531->561|1619->622|1634->628|1695->667|2516->1461|2531->1467|2579->1494|2800->1688|2815->1694|2869->1727|3192->2023|3207->2029|3262->2063|3352->2126|3367->2132|3431->2175|3669->2386|3685->2393|3722->2421|3774->2435|3823->2456|3864->2470|3879->2476|3928->2504|3986->2543|4005->2553|4044->2554|4092->2574|4133->2588|4148->2594|4196->2621|4264->2658|4310->2676|4440->2780|4456->2787|4490->2812|4541->2825|4575->2832|4641->2872|4669->2879|4700->2883|4740->2893|4777->2992|4814->3002|4842->3009|4872->3012|4926->3039|4941->3045|5002->3085
                  LINES: 26->7|31->7|33->9|36->12|37->13|37->13|37->13|38->14|38->14|38->14|39->15|39->15|39->15|53->29|53->29|53->29|57->33|57->33|57->33|61->37|61->37|61->37|62->38|62->38|62->38|68->44|68->44|68->44|68->44|69->45|69->45|69->45|69->45|70->46|70->46|70->46|71->47|71->47|71->47|71->47|72->48|73->49|78->54|78->54|78->54|78->54|79->55|80->56|80->56|81->57|82->58|83->60|84->61|84->61|85->62|86->63|86->63|86->63
                  -- GENERATED --
              */
          