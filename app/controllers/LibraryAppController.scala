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
 * The controller provides the glue code to bind together business operations provided by the business layer, with HTTP handling and HTML views.  It
 * creates an `Action`s to handle HTTP requests.  Actions that involve the business layer execute asynchronously using `Future`s so that web handlings
 * threads do not block on database operations.
 * 
 * Objects that the controller uses to interact with the business and authentication APIs are provided through injection.
 */
@Singleton
class LibraryAppController @Inject()(cc: ControllerComponents, businessApi : LibraryAppBusinessLayerFacade, authenticationProvider : AuthenticationProvider, authenticatedAction : AuthenticatedAction)(implicit ec: ExecutionContext) extends AbstractController(cc) with I18nSupport {
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
  
  private implicit class ExtendedForm[T](form : Form[T]) {
    def businessFold[U](onError : Form[T] => Future[U], onSuccess : T => Future[U]) =
      form.fold(onError, data => onSuccess(data).recoverWith{ case BusinessException(message) => onError(form.withGlobalError(message)) })
  }
  
  private def registerBookView(bookForm : Form[EditBookDTO]) (implicit req: RequestHeader) =
    businessApi.getAuthors().flatMap{ authors =>
      businessApi.getPublishers().map{ publishers =>
        views.html.registerBook(bookForm, registerAuthorForm, registerPublisherForm, authors, publishers)
      }
    }
  
  def registerBook() = authenticatedAction.async { implicit request =>
    registerBookView(bookForm).map(Ok(_))
  }
  
  def registerBookPost() = authenticatedAction.async { implicit request =>
    bookForm.bindFromRequest.businessFold(
      formWithErrors => registerBookView(formWithErrors).map(BadRequest(_)),
      registerBookRequest => businessApi.registerBook(registerBookRequest).map{ bookID =>
        Redirect(routes.LibraryAppController.viewBookDetails(bookID)).flashing("success" -> "Book registered.")
      }
    )
  }
  
  private def editBookView(bookID : BookID, bookForm : Form[EditBookDTO]) (implicit req: RequestHeader) =
    businessApi.getAuthors().flatMap{ authors =>
      businessApi.getPublishers().map{ publishers =>
        views.html.editBook(bookID, bookForm, registerAuthorForm, registerPublisherForm, authors, publishers)
      }
    }
  
  def editBook(bookID : BookID) = authenticatedAction.async { implicit request =>
    businessApi.getBook(bookID).flatMap { book =>
      editBookView(bookID, bookForm.fill(book.toEditDTO)).map(Ok(_))
    }
  }
  
  def editBookPost(bookID : BookID) = authenticatedAction.async { implicit request =>
    bookForm.bindFromRequest.businessFold(
      formWithErrors => editBookView(bookID, formWithErrors).map(BadRequest(_)),
      editBookRequest => businessApi.editBook(bookID, editBookRequest).map { _ =>
        Redirect(routes.LibraryAppController.viewBookDetails(bookID)).flashing("success" -> "Book updated.")
      }
    )
  }
  
  def reportBookLost(bookID : BookID) = authenticatedAction.async { implicit request =>
    businessApi.reportBookLost(bookID).map { _ =>
      Redirect(routes.LibraryAppController.viewBookDetails(bookID)).flashing("success" -> "Book reported lost.")
    }.recover {
      case BusinessException(message) => Redirect(routes.LibraryAppController.viewBookDetails(bookID)).flashing("error" -> message)
    }
  }
  
  def reportBookFound(bookID : BookID) = authenticatedAction.async { implicit request =>
    businessApi.reportBookFound(bookID).map { _ =>
      Redirect(routes.LibraryAppController.viewBookDetails(bookID)).flashing("success" -> "Book reported found.")
    }.recover {
      case BusinessException(message) => Redirect(routes.LibraryAppController.viewBookDetails(bookID)).flashing("error" -> message)
    }
  }
  
  def disposeBook(bookID : BookID) = authenticatedAction.async { implicit request =>
    businessApi.disposeBook(bookID).map { _ =>
      Redirect(routes.LibraryAppController.viewBookDetails(bookID)).flashing("success" -> "Book disposed.")
    }.recover {
      case BusinessException(message) => Redirect(routes.LibraryAppController.viewBookDetails(bookID)).flashing("error" -> message)
    }
  }
  
  def viewBookDetails(bookID : BookID) = Action.async { implicit request =>
    businessApi.getBook(bookID).flatMap { book =>
      businessApi.getBookLoanHistory(bookID).map { loanHistory =>
        Ok(views.html.viewBookDetails(book, loanHistory))
      }
    }
  }
  
  def searchBooks(sort : BookSearchSort) = Action.async { implicit request =>
    if(request.queryString.isEmpty) {
      Future.successful(Ok(views.html.searchBooks(searchBooksForm, sort, None)))
    } else {
      searchBooksForm.bindFromRequest.fold(
        formWithErrors => Future.successful(BadRequest(views.html.searchBooks(formWithErrors, sort, None))),
        searchTerms =>
          businessApi.searchBooks(searchTerms, sort).map{ searchResults =>
            Ok(views.html.searchBooks(searchBooksForm.fill(searchTerms), sort, Some(searchResults)))
          }
      )
    }
  }
  
  def searchBooksWithAdvancedQuery(sort : BookSearchSort) = Action.async { implicit request =>
    if(request.queryString.isEmpty) {
      Future.successful(Ok(views.html.searchBooksAdvanced(advancedSearchForm, sort, None)))
    } else {
      advancedSearchForm.bindFromRequest.businessFold(
        formWithErrors => Future.successful(BadRequest(views.html.searchBooksAdvanced(formWithErrors, sort, None))),
        query =>
          businessApi.searchBooks(query, sort).map{ searchResults =>
            Ok(views.html.searchBooksAdvanced(advancedSearchForm.fill(query), sort, Some(searchResults)))
          }
      )
    }
  }
  
  def registerPublisherPost() = authenticatedAction.async { implicit request =>
    registerPublisherForm.bindFromRequest.fold(
        formWithErrors => Future.successful(BadRequest(Json.obj("message" -> formWithErrors.errors.map(_.format)))),
        publisherName => {
          businessApi.registerPublisher(publisherName).map{ publisherID =>
            Ok(Json.obj("publisherID" -> publisherID.asInt))
          }.recover {
            case BusinessException(message) => BadRequest(Json.obj("message" -> message))
          }
        }
    )
  }
  
  def registerLibraryMember() = authenticatedAction { implicit request =>
    Ok(views.html.registerLibraryMember(registerLibraryMemberForm.fill(EditLibraryMemberDTO(PersonName.empty, LocalDate.now))))
  }
  
  def registerLibraryMemberPost() = authenticatedAction.async { implicit request =>
    registerLibraryMemberForm.bindFromRequest.businessFold(
        formWithErrors => Future.successful(BadRequest(views.html.registerLibraryMember(formWithErrors))),
        libraryMemberDTO => {
          businessApi.registerLibraryMember(libraryMemberDTO).map { libraryMemberID =>
            Redirect(routes.LibraryAppController.index).flashing("success" -> s"Library member registered with Member ID $libraryMemberID.")
          }
        }
    )
  }
  
  private def loanBookView(bookID : BookID, loanForm : Form[LoanBookDTO]) (implicit req: RequestHeader) =
    businessApi.getBook(bookID).flatMap { book =>
      businessApi.getLibraryMembers().map { libraryMembers =>
        views.html.loanBook(loanForm, book, libraryMembers)
      }
    }
  
  def loanBook(bookID : BookID) = authenticatedAction.async { implicit request =>
    val today = LocalDate.now
    val defaultForm = loanBookForm.fill(LoanBookDTO(LibraryMemberID(0), today, today.plusDays(14)))
    loanBookView(bookID, defaultForm).map(Ok(_))
  }
  
  def loanBookPost(bookID : BookID) = authenticatedAction.async { implicit request =>
    loanBookForm.bindFromRequest.businessFold(
      formWithErrors => loanBookView(bookID, formWithErrors).map(BadRequest(_)),
      loan => businessApi.loanBook(bookID, loan).map { _ =>
        Redirect(routes.LibraryAppController.viewBookDetails(bookID)).flashing("success" -> "Book loaned.")
      }
    )
  }
  
  private def reportBookReturnedView(bookID : BookID, returnedForm : Form[LocalDate]) (implicit req: RequestHeader) =
    businessApi.getBook(bookID).map { book =>
      views.html.reportBookReturned(returnedForm, book)
    }
  
  def reportBookReturned(bookID : BookID) = authenticatedAction.async { implicit request =>
    reportBookReturnedView(bookID, reportBookReturnedForm.fill(LocalDate.now)).map(Ok(_))
  }
  
  def reportBookReturnedPost(bookID : BookID) = authenticatedAction.async { implicit request =>
    reportBookReturnedForm.bindFromRequest.businessFold(
      formWithErrors => reportBookReturnedView(bookID, formWithErrors).map(BadRequest(_)),
      returnedDate => 
        businessApi.reportBookReturned(bookID, returnedDate).map { _ =>
          Redirect(routes.LibraryAppController.viewBookDetails(bookID)).flashing("success" -> "Book reported returned.")
        }
    )
  }
  
  def registerAuthorPost = authenticatedAction.async { implicit request =>
    registerAuthorForm.bindFromRequest.fold(
      formWithErrors => Future.successful(BadRequest(Json.obj("message" -> formWithErrors.errors.map(_.format)))),
      personName => businessApi.registerAuthor(personName).map { authorID => Ok(Json.obj("authorID" -> authorID.toString, "name" -> personName.toString)) }
    )
  }
}
