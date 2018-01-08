
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
/*3.2*/import business.BookID
/*4.2*/import scala.collection.immutable.Seq

object editBook extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[BookID,Form[EditBookDTO],Form[String],Seq[Publisher],Messages,RequestHeader,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*6.2*/(bookID : BookID, form : Form[EditBookDTO], publisherForm : Form[String], publishers : Seq[Publisher])(implicit messages: Messages, req: RequestHeader):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*6.153*/("""

"""),_display_(/*8.2*/main("Edit Book")/*8.19*/ {_display_(Seq[Any](format.raw/*8.21*/("""
	"""),format.raw/*9.2*/("""<h1>Edit Book</h1>
	"""),_display_(/*10.3*/bookForm(form, publisherForm, publishers, "Save Changes", controllers.routes.LibraryAppController.editBookPost(bookID))),format.raw/*10.122*/("""
""")))}))
      }
    }
  }

  def render(bookID:BookID,form:Form[EditBookDTO],publisherForm:Form[String],publishers:Seq[Publisher],messages:Messages,req:RequestHeader): play.twirl.api.HtmlFormat.Appendable = apply(bookID,form,publisherForm,publishers)(messages,req)

  def f:((BookID,Form[EditBookDTO],Form[String],Seq[Publisher]) => (Messages,RequestHeader) => play.twirl.api.HtmlFormat.Appendable) = (bookID,form,publisherForm,publishers) => (messages,req) => apply(bookID,form,publisherForm,publishers)(messages,req)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Mon Jan 08 11:21:37 KST 2018
                  SOURCE: /Users/kpyancey/Projects/Library-Demo-for-OOD/app/views/editBook.scala.html
                  HASH: bb24a78c8e8405bc6040d67622cfab6e3ab9d388
                  MATRIX: 432->1|467->30|500->57|530->81|944->121|1191->272|1219->275|1244->292|1283->294|1311->296|1358->317|1499->436
                  LINES: 17->1|18->2|19->3|20->4|25->6|30->6|32->8|32->8|32->8|33->9|34->10|34->10
                  -- GENERATED --
              */
          