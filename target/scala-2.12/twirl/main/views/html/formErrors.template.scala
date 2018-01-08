
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

object formErrors extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template3[Form[_$1] forSome { 
   type _$1
},Messages,RequestHeader,play.twirl.api.HtmlFormat.Appendable] {

  /* formErrors Template File */
  def apply/*2.2*/(form : Form[_])(implicit messages: Messages, req: RequestHeader):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.67*/("""

"""),_display_(/*4.2*/if(form.hasGlobalErrors)/*4.26*/ {_display_(Seq[Any](format.raw/*4.28*/("""
  """),format.raw/*5.3*/("""<ul>
  """),_display_(/*6.4*/for(error <- form.globalErrors) yield /*6.35*/ {_display_(Seq[Any](format.raw/*6.37*/("""
    """),format.raw/*7.5*/("""<li>"""),_display_(/*7.10*/error/*7.15*/.format),format.raw/*7.22*/("""</li>
  """)))}),format.raw/*8.4*/("""
  """),format.raw/*9.3*/("""</ul>
""")))}),format.raw/*10.2*/("""
"""))
      }
    }
  }

  def render(form:Form[_$1] forSome { 
   type _$1
},messages:Messages,req:RequestHeader): play.twirl.api.HtmlFormat.Appendable = apply(form)(messages,req)

  def f:((Form[_$1] forSome { 
   type _$1
}) => (Messages,RequestHeader) => play.twirl.api.HtmlFormat.Appendable) = (form) => (messages,req) => apply(form)(messages,req)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Mon Jan 08 22:25:17 KST 2018
                  SOURCE: /Users/kpyancey/Projects/Library-Demo-for-OOD/app/views/formErrors.scala.html
                  HASH: d111b62ecc9ea28af36822e2346b6b134c2e8743
                  MATRIX: 811->32|971->97|999->100|1031->124|1070->126|1099->129|1132->137|1178->168|1217->170|1248->175|1279->180|1292->185|1319->192|1357->201|1386->204|1423->211
                  LINES: 23->2|28->2|30->4|30->4|30->4|31->5|32->6|32->6|32->6|33->7|33->7|33->7|33->7|34->8|35->9|36->10
                  -- GENERATED --
              */
          