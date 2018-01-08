package controllers

import play.api.mvc._
import javax.inject._
import scala.concurrent._

class AuthenticatedAction @Inject() (parser: BodyParsers.Default)(implicit ec: ExecutionContext) extends ActionBuilderImpl(parser) {
  override def invokeBlock[A](request: Request[A], block: (Request[A]) => Future[Result]) = {
    if(request.session.get(Security.username).isEmpty) {
      Future.successful(Results.Redirect(routes.LibraryAppController.login))
    } else {
      block(request)
    }
  }
}