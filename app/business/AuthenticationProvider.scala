package business

import scala.concurrent._

import com.google.inject.ImplementedBy

@ImplementedBy(classOf[database.DbAuthenticationProvider])
trait AuthenticationProvider {
  def authenticate(username : String, password : String) : Future[Boolean]
}