package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.i18n.I18nSupport
import business._
import java.time._
import java.util.Date
import scala.collection.immutable.Seq

import scala.concurrent.{ExecutionContext, Future}

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class LibraryAppController @Inject()(cc: ControllerComponents, businessApi : LibraryAppBusinessLayerFacade)(implicit ec: ExecutionContext) extends AbstractController(cc) with I18nSupport {
  val registerBookForm = Form(
    mapping(
      "title" -> nonEmptyText(maxLength=256),
      "publicationDate" -> date.transform[LocalDate](_.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), d => Date.from(d.atStartOfDay(ZoneId.systemDefault()).toInstant())),
      "description" -> text,
      "isbn" -> nonEmptyText.verifying("Must be a valid 13-digit ISBN number.", isbn => ISBN.tryParse(isbn).successful).transform[ISBN](s => ISBN.tryParse(s).get, isbn => isbn.toString),
      "publisherID" -> number.transform[PublisherID](id => PublisherID(id), id => id.asInt),
      "price" -> bigDecimal(precision=2, scale=10),
      "keywords" -> text.transform[Seq[String]](_.split(" ").to[Seq], _.mkString(" ")),
      "callNumber" -> nonEmptyText(maxLength=32).verifying("Must be in the format '#.# A'.", callNumber => callNumber.matches("""\d.\d [A-Z0-9]"""))
    ) (RegisterBookRequest.apply) (RegisterBookRequest.unapply)
  )
  val registerPublisherForm = Form(single("name" -> nonEmptyText(maxLength=256)))
  
  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }
  
  def registerBook() = Action { implicit request =>
    Ok(views.html.registerBook(registerBookForm))
  }
  
  def registerBookPost() = Action.async { implicit request =>
    registerBookForm.bindFromRequest.fold(
      formWithErrors => Future.successful(BadRequest(views.html.registerBook(formWithErrors))),
      registerBookRequest => Future.successful(Redirect(routes.LibraryAppController.index()))
    )
  }
  
  def registerPublisher() = Action { implicit request =>
    Ok(views.html.registerPublisher(registerPublisherForm))
  }
  
  def registerPublisherPost() = Action.async { implicit request =>
    registerPublisherForm.bindFromRequest.fold(
        formWithErrors => Future.successful(BadRequest(views.html.registerPublisher(formWithErrors))),
        publisherName => {
          businessApi.registerPublisher(publisherName).map{ publisher =>
            Redirect(routes.LibraryAppController.index())
          }
        }
    )
  }
}
