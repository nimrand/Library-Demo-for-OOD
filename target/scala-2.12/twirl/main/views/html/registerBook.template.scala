
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
/*1.2*/import business.EditBookDTO
/*2.2*/import business.Publisher
/*3.2*/import scala.collection.immutable.Seq

object registerBook extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Form[EditBookDTO],Form[String],Seq[Publisher],Messages,RequestHeader,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*5.2*/(form : Form[EditBookDTO], publisherForm : Form[String], publishers : Seq[Publisher])(implicit messages: Messages, req: RequestHeader):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*5.136*/("""

"""),_display_(/*7.2*/main("Register Book")/*7.23*/ {_display_(Seq[Any](format.raw/*7.25*/("""
	"""),format.raw/*8.2*/("""<h1>Register Book</h1>
	"""),_display_(/*9.3*/bookForm(form, publisherForm, publishers, "Register Book", controllers.routes.LibraryAppController.registerBookPost())),format.raw/*9.121*/("""
""")))}))
      }
    }
  }

  def render(form:Form[EditBookDTO],publisherForm:Form[String],publishers:Seq[Publisher],messages:Messages,req:RequestHeader): play.twirl.api.HtmlFormat.Appendable = apply(form,publisherForm,publishers)(messages,req)

  def f:((Form[EditBookDTO],Form[String],Seq[Publisher]) => (Messages,RequestHeader) => play.twirl.api.HtmlFormat.Appendable) = (form,publisherForm,publishers) => (messages,req) => apply(form,publisherForm,publishers)(messages,req)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Mon Jan 08 11:21:37 KST 2018
                  SOURCE: /Users/kpyancey/Projects/Library-Demo-for-OOD/app/views/registerBook.scala.html
                  HASH: f0d25c17db82c1040e3d5f898b4ce0ff43a262f8
                  MATRIX: 432->1|467->30|500->57|911->97|1141->231|1169->234|1198->255|1237->257|1265->259|1315->284|1454->402
                  LINES: 17->1|18->2|19->3|24->5|29->5|31->7|31->7|31->7|32->8|33->9|33->9
                  -- GENERATED --
              */
          