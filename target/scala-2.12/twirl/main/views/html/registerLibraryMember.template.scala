
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
/*3.2*/import business.EditLibraryMemberDTO
/*4.2*/import business.Publisher
/*5.2*/import scala.collection.immutable.Seq

object registerLibraryMember extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template3[Form[EditLibraryMemberDTO],Messages,RequestHeader,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*7.2*/(libraryMemberForm : Form[EditLibraryMemberDTO])(implicit messages: Messages, req: RequestHeader):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*7.99*/("""

"""),_display_(/*9.2*/main("Register Library Member")/*9.33*/ {_display_(Seq[Any](format.raw/*9.35*/("""
	"""),format.raw/*10.2*/("""<h1>Register Library Member</h1>
	"""),_display_(/*11.3*/b3/*11.5*/.form(action = routes.LibraryAppController.registerLibraryMemberPost())/*11.76*/ {_display_(Seq[Any](format.raw/*11.78*/("""
	  """),_display_(/*12.5*/CSRF/*12.9*/.formField),format.raw/*12.19*/("""
	  """),_display_(/*13.5*/b3/*13.7*/.text(libraryMemberForm("firstName"), '_label -> "First Name", 'placeholder -> "George")),format.raw/*13.95*/("""
	  """),_display_(/*14.5*/b3/*14.7*/.text(libraryMemberForm("middleName"), '_label -> "Middle Name", 'placeholder -> "R. R.")),format.raw/*14.96*/("""
	  """),_display_(/*15.5*/b3/*15.7*/.text(libraryMemberForm("lastName"), '_label -> "Last Name", 'placeholder -> "Martin")),format.raw/*15.93*/("""
	  """),_display_(/*16.5*/b3/*16.7*/.text(libraryMemberForm("suffixName"), '_label -> "Suffix Name", 'placeholder -> "Jr")),format.raw/*16.93*/("""
	  """),_display_(/*17.5*/b3/*17.7*/.text(libraryMemberForm("titles"), '_label -> "Titles", 'placeholder -> "PhD MD CFP etc.")),format.raw/*17.97*/("""
	  """),_display_(/*18.5*/b3/*18.7*/.date(libraryMemberForm("joinedDate"), '_label -> "Joined Date")),format.raw/*18.71*/("""
	  """),_display_(/*19.5*/b3/*19.7*/.submit('class -> "btn btn-primary")/*19.43*/{_display_(Seq[Any](format.raw/*19.44*/(""" """),format.raw/*19.45*/("""Register Library Member """)))}),format.raw/*19.70*/("""
	""")))}),format.raw/*20.3*/("""
""")))}))
      }
    }
  }

  def render(libraryMemberForm:Form[EditLibraryMemberDTO],messages:Messages,req:RequestHeader): play.twirl.api.HtmlFormat.Appendable = apply(libraryMemberForm)(messages,req)

  def f:((Form[EditLibraryMemberDTO]) => (Messages,RequestHeader) => play.twirl.api.HtmlFormat.Appendable) = (libraryMemberForm) => (messages,req) => apply(libraryMemberForm)(messages,req)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Mon Jan 08 11:49:16 KST 2018
                  SOURCE: /Users/kpyancey/Projects/Library-Demo-for-OOD/app/views/registerLibraryMember.scala.html
                  HASH: 2fd214bd5c6722bccef823ec53323317fbdbc443
                  MATRIX: 432->1|475->39|498->56|542->94|575->121|976->161|1168->258|1196->261|1235->292|1274->294|1303->296|1364->331|1374->333|1454->404|1494->406|1525->411|1537->415|1568->425|1599->430|1609->432|1718->520|1749->525|1759->527|1869->616|1900->621|1910->623|2017->709|2048->714|2058->716|2165->802|2196->807|2206->809|2317->899|2348->904|2358->906|2443->970|2474->975|2484->977|2529->1013|2568->1014|2597->1015|2653->1040|2686->1043
                  LINES: 17->1|18->2|19->3|20->4|21->5|26->7|31->7|33->9|33->9|33->9|34->10|35->11|35->11|35->11|35->11|36->12|36->12|36->12|37->13|37->13|37->13|38->14|38->14|38->14|39->15|39->15|39->15|40->16|40->16|40->16|41->17|41->17|41->17|42->18|42->18|42->18|43->19|43->19|43->19|43->19|43->19|43->19|44->20
                  -- GENERATED --
              */
          