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
import play.api.libs.json._

import scala.concurrent.{ExecutionContext, Future}

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class LibraryAppController @Inject()(cc: ControllerComponents, businessApi : LibraryAppBusinessLayerFacade)(implicit ec: ExecutionContext) extends AbstractController(cc) with I18nSupport {
  import Forms._
  
  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }
  
  def registerBook() = Action.async { implicit request =>
    businessApi.getPublishers().map{ publishers =>
      Ok(views.html.registerBook(bookForm, registerPublisherForm, publishers))
    }
  }
  
  def registerBookPost() = Action.async { implicit request =>
    bookForm.bindFromRequest.fold(
      formWithErrors => businessApi.getPublishers().map{ publishers =>
        BadRequest(views.html.registerBook(formWithErrors, registerPublisherForm, publishers))
      },
      registerBookRequest => businessApi.registerBook(registerBookRequest).map{ bookID =>
        Redirect(routes.LibraryAppController.viewBookDetails(bookID))
      }
    )
  }
  
  def editBook(bookID : BookID) = Action.async { implicit request =>
    businessApi.getBook(bookID).flatMap { book =>
      businessApi.getPublishers().map{ publishers =>
        Ok(views.html.editBook(bookID, bookForm.fill(book.toEditDTO), registerPublisherForm, publishers))
      }
    }
  }
  
  def editBookPost(bookID : BookID) = Action.async { implicit request =>
    bookForm.bindFromRequest.fold(
      formWithErrors => businessApi.getPublishers().map{ publishers =>
        BadRequest(views.html.editBook(bookID, formWithErrors, registerPublisherForm, publishers))
      },
      editBookRequest => businessApi.editBook(bookID, editBookRequest).map { _ =>
        Redirect(routes.LibraryAppController.viewBookDetails(bookID))
      }
    )
  }
  
  def reportBookLost(bookID : BookID) = Action.async { implicit request =>
    businessApi.reportBookLost(bookID).map { _ =>
      Redirect(routes.LibraryAppController.viewBookDetails(bookID))
    }
  }
  
  def reportBookFound(bookID : BookID) = Action.async { implicit request =>
    businessApi.reportBookFound(bookID).map { _ =>
      Redirect(routes.LibraryAppController.viewBookDetails(bookID))
    }
  }
  
  def disposeBook(bookID : BookID) = Action.async { implicit request =>
    businessApi.disposeBook(bookID).map { _ =>
      Redirect(routes.LibraryAppController.viewBookDetails(bookID))
    }
  }
  
  def viewBookDetails(bookID : BookID) = Action.async { implicit request =>
    businessApi.getBook(bookID).flatMap { book =>
      businessApi.getBookLoanHistory(bookID).map { loanHistory =>
        Ok(views.html.viewBookDetails(book, loanHistory))
      }
    }
  }
  
  def searchBooks() = Action.async { implicit request =>
    if(request.queryString.isEmpty) {
      Future.successful(Ok(views.html.searchBooks(searchBooksForm)))
    } else {
      searchBooksForm.bindFromRequest.fold(
        formWithErrors => Future.successful(BadRequest(views.html.searchBooks(formWithErrors))),
        searchTerms =>
          businessApi.searchBooks(searchTerms).map{ searchResults =>
            Ok(views.html.searchBooks(searchBooksForm.fill(searchTerms), searchResults))
          }
      )
    }
  }
  
  def registerPublisher() = Action { implicit request =>
    Ok(views.html.registerPublisher(registerPublisherForm))
  }
  
  def registerPublisherPost() = Action.async { implicit request =>
    registerPublisherForm.bindFromRequest.fold(
        formWithErrors => Future.successful(BadRequest(views.html.registerPublisher(formWithErrors))),
        publisherName => {
          businessApi.registerPublisher(publisherName).map{ publisherID =>
            Ok(Json.obj("publisherID" -> publisherID.asInt))
          }
        }
    )
  }
  
  def registerLibraryMember() = Action { implicit request =>
    Ok(views.html.registerLibraryMember(registerLibraryMemberForm.fill(EditLibraryMemberDTO(PersonName.empty, LocalDate.now))))
  }
  
  def registerLibraryMemberPost() = Action.async { implicit request =>
    registerLibraryMemberForm.bindFromRequest.fold(
        formWithErrors => Future.successful(BadRequest(views.html.registerLibraryMember(formWithErrors))),
        libraryMemberDTO => {
          businessApi.registerLibraryMember(libraryMemberDTO).map { libraryMemberID =>
            Redirect(routes.LibraryAppController.index)
          }
        }
    )
  }
  
  def loanBook(bookID : BookID) = Action.async { implicit request =>
    businessApi.getBook(bookID).flatMap { book =>
      businessApi.getLibraryMembers().map { libraryMembers =>
        val today = LocalDate.now
        val defaultForm = loanBookForm.fill(LoanBookDTO(LibraryMemberID(0), today, today.plusDays(14)))
        Ok(views.html.loanBook(defaultForm, book, libraryMembers))
      }
    }
  }
  
  def loanBookPost(bookID : BookID) = Action.async { implicit request =>
    loanBookForm.bindFromRequest.fold(
      formWithErrors => businessApi.getBook(bookID).flatMap { book =>
        businessApi.getLibraryMembers().map { libraryMembers =>
          BadRequest(views.html.loanBook(formWithErrors, book, libraryMembers))
        }
      },
      loan => businessApi.loanBook(bookID, loan).map { _ =>
        Redirect(routes.LibraryAppController.viewBookDetails(bookID))
      }
    )
  }
  
  def reportBookReturned(bookID : BookID) = Action.async { implicit request =>
    businessApi.getBook(bookID).map { book =>
      val form = reportBookReturnedForm.fill(LocalDate.now)
      Ok(views.html.reportBookReturned(form, book))
    }
  }
  
  def reportBookReturnedPost(bookID : BookID) = Action.async { implicit request =>
    reportBookReturnedForm.bindFromRequest.fold(
      formWithErrors => 
        businessApi.getBook(bookID).map { book =>
          BadRequest(views.html.reportBookReturned(formWithErrors, book))
        },
      returnedDate => 
        businessApi.reportBookReturned(bookID, returnedDate).map { _ =>
          Redirect(routes.LibraryAppController.viewBookDetails(bookID))
        }
    )
  }
}
