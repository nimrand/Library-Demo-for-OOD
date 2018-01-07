
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/kpyancey/Projects/Library-Demo-for-OOD/conf/routes
// @DATE:Sun Jan 07 18:38:07 KST 2018

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:7
  LibraryAppController_0: controllers.LibraryAppController,
  // @LINE:14
  Assets_1: controllers.Assets,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:7
    LibraryAppController_0: controllers.LibraryAppController,
    // @LINE:14
    Assets_1: controllers.Assets
  ) = this(errorHandler, LibraryAppController_0, Assets_1, "/")

  def withPrefix(prefix: String): Routes = {
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, LibraryAppController_0, Assets_1, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix, """controllers.LibraryAppController.index"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """books/register""", """controllers.LibraryAppController.registerBook"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """books/register""", """controllers.LibraryAppController.registerBookPost"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """publishers/register""", """controllers.LibraryAppController.registerPublisher"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """publishers/register""", """controllers.LibraryAppController.registerPublisherPost"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assets/""" + "$" + """file<.+>""", """controllers.Assets.versioned(path:String = "/public", file:Asset)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:7
  private[this] lazy val controllers_LibraryAppController_index0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private[this] lazy val controllers_LibraryAppController_index0_invoker = createInvoker(
    LibraryAppController_0.index,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.LibraryAppController",
      "index",
      Nil,
      "GET",
      this.prefix + """""",
      """ An example controller showing a sample home page""",
      Seq()
    )
  )

  // @LINE:8
  private[this] lazy val controllers_LibraryAppController_registerBook1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("books/register")))
  )
  private[this] lazy val controllers_LibraryAppController_registerBook1_invoker = createInvoker(
    LibraryAppController_0.registerBook,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.LibraryAppController",
      "registerBook",
      Nil,
      "GET",
      this.prefix + """books/register""",
      """""",
      Seq()
    )
  )

  // @LINE:9
  private[this] lazy val controllers_LibraryAppController_registerBookPost2_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("books/register")))
  )
  private[this] lazy val controllers_LibraryAppController_registerBookPost2_invoker = createInvoker(
    LibraryAppController_0.registerBookPost,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.LibraryAppController",
      "registerBookPost",
      Nil,
      "GET",
      this.prefix + """books/register""",
      """""",
      Seq()
    )
  )

  // @LINE:10
  private[this] lazy val controllers_LibraryAppController_registerPublisher3_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("publishers/register")))
  )
  private[this] lazy val controllers_LibraryAppController_registerPublisher3_invoker = createInvoker(
    LibraryAppController_0.registerPublisher,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.LibraryAppController",
      "registerPublisher",
      Nil,
      "GET",
      this.prefix + """publishers/register""",
      """""",
      Seq()
    )
  )

  // @LINE:11
  private[this] lazy val controllers_LibraryAppController_registerPublisherPost4_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("publishers/register")))
  )
  private[this] lazy val controllers_LibraryAppController_registerPublisherPost4_invoker = createInvoker(
    LibraryAppController_0.registerPublisherPost,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.LibraryAppController",
      "registerPublisherPost",
      Nil,
      "POST",
      this.prefix + """publishers/register""",
      """""",
      Seq()
    )
  )

  // @LINE:14
  private[this] lazy val controllers_Assets_versioned5_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_versioned5_invoker = createInvoker(
    Assets_1.versioned(fakeValue[String], fakeValue[Asset]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "versioned",
      Seq(classOf[String], classOf[Asset]),
      "GET",
      this.prefix + """assets/""" + "$" + """file<.+>""",
      """ Map static resources from the /public folder to the /assets URL path""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:7
    case controllers_LibraryAppController_index0_route(params@_) =>
      call { 
        controllers_LibraryAppController_index0_invoker.call(LibraryAppController_0.index)
      }
  
    // @LINE:8
    case controllers_LibraryAppController_registerBook1_route(params@_) =>
      call { 
        controllers_LibraryAppController_registerBook1_invoker.call(LibraryAppController_0.registerBook)
      }
  
    // @LINE:9
    case controllers_LibraryAppController_registerBookPost2_route(params@_) =>
      call { 
        controllers_LibraryAppController_registerBookPost2_invoker.call(LibraryAppController_0.registerBookPost)
      }
  
    // @LINE:10
    case controllers_LibraryAppController_registerPublisher3_route(params@_) =>
      call { 
        controllers_LibraryAppController_registerPublisher3_invoker.call(LibraryAppController_0.registerPublisher)
      }
  
    // @LINE:11
    case controllers_LibraryAppController_registerPublisherPost4_route(params@_) =>
      call { 
        controllers_LibraryAppController_registerPublisherPost4_invoker.call(LibraryAppController_0.registerPublisherPost)
      }
  
    // @LINE:14
    case controllers_Assets_versioned5_route(params@_) =>
      call(Param[String]("path", Right("/public")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_Assets_versioned5_invoker.call(Assets_1.versioned(path, file))
      }
  }
}
