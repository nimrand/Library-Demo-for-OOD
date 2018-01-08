
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
/*3.2*/import business.LoanBookDTO
/*4.2*/import business.LibraryMember
/*5.2*/import business.Publisher
/*6.2*/import business.Book
/*7.2*/import scala.collection.immutable.Seq
/*8.2*/import java.time.LocalDate

object login extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template3[Form[scala.Tuple2[String, String]],Messages,RequestHeader,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*10.2*/(loginForm : Form[(String, String)])(implicit messages: Messages, req: RequestHeader):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*10.87*/("""

"""),_display_(/*12.2*/main("Login")/*12.15*/ {_display_(Seq[Any](format.raw/*12.17*/("""
	"""),format.raw/*13.2*/("""<h1>Sign In</h1>
	"""),_display_(/*14.3*/form(action = routes.LibraryAppController.loginPost, 'id -> "bookReturnedForm")/*14.82*/ {_display_(Seq[Any](format.raw/*14.84*/("""
	  """),_display_(/*15.5*/formErrors(loginForm)),format.raw/*15.26*/("""
      """),_display_(/*16.8*/CSRF/*16.12*/.formField),format.raw/*16.22*/("""
      """),_display_(/*17.8*/b3/*17.10*/.text(loginForm("username"), '_label -> "Username")),format.raw/*17.61*/("""
      """),_display_(/*18.8*/b3/*18.10*/.text(loginForm("password"), '_label -> "Password")),format.raw/*18.61*/("""
      """),_display_(/*19.8*/b3/*19.10*/.submit('class -> "btn btn-primary")/*19.46*/{_display_(Seq[Any](format.raw/*19.47*/(""" """),format.raw/*19.48*/("""Sign In """)))}),format.raw/*19.57*/("""
    """)))}),format.raw/*20.6*/("""
""")))}),format.raw/*21.2*/("""
"""))
      }
    }
  }

  def render(loginForm:Form[scala.Tuple2[String, String]],messages:Messages,req:RequestHeader): play.twirl.api.HtmlFormat.Appendable = apply(loginForm)(messages,req)

  def f:((Form[scala.Tuple2[String, String]]) => (Messages,RequestHeader) => play.twirl.api.HtmlFormat.Appendable) = (loginForm) => (messages,req) => apply(loginForm)(messages,req)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Mon Jan 08 22:23:38 KST 2018
                  SOURCE: /Users/kpyancey/Projects/Library-Demo-for-OOD/app/views/login.scala.html
                  HASH: 06b5f14a46f6774c05ca658d5398632ac9f1f42b
                  MATRIX: 432->1|475->39|498->56|533->85|570->116|603->143|631->165|676->204|1059->233|1240->318|1269->321|1291->334|1331->336|1360->338|1405->357|1493->436|1533->438|1564->443|1606->464|1640->472|1653->476|1684->486|1718->494|1729->496|1801->547|1835->555|1846->557|1918->608|1952->616|1963->618|2008->654|2047->655|2076->656|2116->665|2152->671|2184->673
                  LINES: 17->1|18->2|19->3|20->4|21->5|22->6|23->7|24->8|29->10|34->10|36->12|36->12|36->12|37->13|38->14|38->14|38->14|39->15|39->15|40->16|40->16|40->16|41->17|41->17|41->17|42->18|42->18|42->18|43->19|43->19|43->19|43->19|43->19|43->19|44->20|45->21
                  -- GENERATED --
              */
          