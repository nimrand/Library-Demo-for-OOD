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
import database.DbAuthenticationProvider

import scala.concurrent.{ExecutionContext, Future}

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class LibraryAppController @Inject()(cc: ControllerComponents, businessApi : LibraryAppBusinessLayerFacade, authenticationProvider : DbAuthenticationProvider, authenticatedAction : AuthenticatedAction)(implicit ec: ExecutionContext) extends AbstractController(cc) with I18nSupport {
  import Forms._
  import Security._
  
  def index() = Action { implicit request =>
    Ok(views.html.index())
  }
  
  def login() = Action { implicit request =>
    Ok(views.html.login(loginForm))
  }
  
  def loginPost() = Action.async { implicit request =>
    val boundLoginForm = loginForm.bindFromRequest
    boundLoginForm.fold(
      formWithErrors => Future.successful(BadRequest(views.html.login(formWithErrors))),
      loginData => authenticationProvider.authenticate(loginData._1, loginData._2).map { result =>
        if(result) {
          Redirect(routes.LibraryAppController.index).withSession(Security.username -> loginData._1).flashing("success" -> "Logged in.")
        } else {
          BadRequest(views.html.login(boundLoginForm.withGlobalError("Wrong username or password.")))
        }
      }
    )
  }
  
  def logout = Action {
    Redirect(routes.LibraryAppController.index).withNewSession.flashing(
      "success" -> "You are now logged out."
    )
  }
  
  def registerBook() = authenticatedAction.async { implicit request =>
    businessApi.getPublishers().map{ publishers =>
      Ok(views.html.registerBook(bookForm, registerPublisherForm, publishers))
    }
  }
  
  def registerBookPost() = authenticatedAction.async { implicit request =>
    bookForm.bindFromRequest.fold(
      formWithErrors => businessApi.getPublishers().map{ publishers =>
        BadRequest(views.html.registerBook(formWithErrors, registerPublisherForm, publishers))
      },
      registerBookRequest => businessApi.registerBook(registerBookRequest).map{ bookID =>
        Redirect(routes.LibraryAppController.viewBookDetails(bookID)).flashing("success" -> "Book registered.")
      }
    )
  }
  
  def editBook(bookID : BookID) = authenticatedAction.async { implicit request =>
    businessApi.getBook(bookID).flatMap { book =>
      businessApi.getPublishers().map{ publishers =>
        Ok(views.html.editBook(bookID, bookForm.fill(book.toEditDTO), registerPublisherForm, publishers))
      }
    }
  }
  
  def editBookPost(bookID : BookID) = authenticatedAction.async { implicit request =>
    bookForm.bindFromRequest.fold(
      formWithErrors => businessApi.getPublishers().map{ publishers =>
        BadRequest(views.html.editBook(bookID, formWithErrors, registerPublisherForm, publishers))
      },
      editBookRequest => businessApi.editBook(bookID, editBookRequest).map { _ =>
        Redirect(routes.LibraryAppController.viewBookDetails(bookID)).flashing("success" -> "Book updated.")
      }
    )
  }
  
  def reportBookLost(bookID : BookID) = authenticatedAction.async { implicit request =>
    businessApi.reportBookLost(bookID).map { _ =>
      Redirect(routes.LibraryAppController.viewBookDetails(bookID)).flashing("success" -> "Book reported lost.")
    }
  }
  
  def reportBookFound(bookID : BookID) = authenticatedAction.async { implicit request =>
    businessApi.reportBookFound(bookID).map { _ =>
      Redirect(routes.LibraryAppController.viewBookDetails(bookID)).flashing("success" -> "Book reported found.")
    }
  }
  
  def disposeBook(bookID : BookID) = authenticatedAction.async { implicit request =>
    businessApi.disposeBook(bookID).map { _ =>
      Redirect(routes.LibraryAppController.viewBookDetails(bookID)).flashing("success" -> "Book disposed.")
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
  /*
  def registerPublisher() = Action { implicit request =>
    Ok(views.html.registerPublisher(registerPublisherForm))
  }*/
  
  def registerPublisherPost() = authenticatedAction.async { implicit request =>
    registerPublisherForm.bindFromRequest.fold(
        formWithErrors => Future.successful(BadRequest(views.html.registerPublisher(formWithErrors))),
        publisherName => {
          businessApi.registerPublisher(publisherName).map{ publisherID =>
            Ok(Json.obj("publisherID" -> publisherID.asInt))
          }
        }
    )
  }
  
  def registerLibraryMember() = authenticatedAction { implicit request =>
    Ok(views.html.registerLibraryMember(registerLibraryMemberForm.fill(EditLibraryMemberDTO(PersonName.empty, LocalDate.now))))
  }
  
  def registerLibraryMemberPost() = authenticatedAction.async { implicit request =>
    registerLibraryMemberForm.bindFromRequest.fold(
        formWithErrors => Future.successful(BadRequest(views.html.registerLibraryMember(formWithErrors))),
        libraryMemberDTO => {
          businessApi.registerLibraryMember(libraryMemberDTO).map { libraryMemberID =>
            Redirect(routes.LibraryAppController.index).flashing("success" -> "Library member registered.")
          }
        }
    )
  }
  
  def loanBook(bookID : BookID) = authenticatedAction.async { implicit request =>
    businessApi.getBook(bookID).flatMap { book =>
      businessApi.getLibraryMembers().map { libraryMembers =>
        val today = LocalDate.now
        val defaultForm = loanBookForm.fill(LoanBookDTO(LibraryMemberID(0), today, today.plusDays(14)))
        Ok(views.html.loanBook(defaultForm, book, libraryMembers))
      }
    }
  }
  
  def loanBookPost(bookID : BookID) = authenticatedAction.async { implicit request =>
    loanBookForm.bindFromRequest.fold(
      formWithErrors => businessApi.getBook(bookID).flatMap { book =>
        businessApi.getLibraryMembers().map { libraryMembers =>
          BadRequest(views.html.loanBook(formWithErrors, book, libraryMembers))
        }
      },
      loan => businessApi.loanBook(bookID, loan).map { _ =>
        Redirect(routes.LibraryAppController.viewBookDetails(bookID)).flashing("success" -> "Book loaned.")
      }
    )
  }
  
  def reportBookReturned(bookID : BookID) = authenticatedAction.async { implicit request =>
    businessApi.getBook(bookID).map { book =>
      val form = reportBookReturnedForm.fill(LocalDate.now)
      Ok(views.html.reportBookReturned(form, book))
    }
  }
  
  def reportBookReturnedPost(bookID : BookID) = authenticatedAction.async { implicit request =>
    reportBookReturnedForm.bindFromRequest.fold(
      formWithErrors => 
        businessApi.getBook(bookID).map { book =>
          BadRequest(views.html.reportBookReturned(formWithErrors, book))
        },
      returnedDate => 
        businessApi.reportBookReturned(bookID, returnedDate).map { _ =>
          Redirect(routes.LibraryAppController.viewBookDetails(bookID)).flashing("success" -> "Book reported returned.")
        }
    )
  }
}
