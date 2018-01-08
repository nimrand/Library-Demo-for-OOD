
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/kpyancey/Projects/Library-Demo-for-OOD/conf/routes
// @DATE:Mon Jan 08 09:29:48 KST 2018

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset
import _root_.controllers.Binders._

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:7
  LibraryAppController_0: controllers.LibraryAppController,
  // @LINE:17
  Assets_1: controllers.Assets,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:7
    LibraryAppController_0: controllers.LibraryAppController,
    // @LINE:17
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
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """books/register""", """controllers.LibraryAppController.registerBookPost"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """books/details/""" + "$" + """id<[^/]+>""", """controllers.LibraryAppController.viewBookDetails(id:business.BookID)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """books/edit/""" + "$" + """id<[^/]+>""", """controllers.LibraryAppController.editBook(id:Int)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """books/edit/""" + "$" + """id<[^/]+>""", """controllers.LibraryAppController.editBookPost(id:Int)"""),
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
  private[this] lazy val controllers_LibraryAppController_registerBookPost2_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("books/register")))
  )
  private[this] lazy val controllers_LibraryAppController_registerBookPost2_invoker = createInvoker(
    LibraryAppController_0.registerBookPost,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.LibraryAppController",
      "registerBookPost",
      Nil,
      "POST",
      this.prefix + """books/register""",
      """""",
      Seq()
    )
  )

  // @LINE:10
  private[this] lazy val controllers_LibraryAppController_viewBookDetails3_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("books/details/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_LibraryAppController_viewBookDetails3_invoker = createInvoker(
    LibraryAppController_0.viewBookDetails(fakeValue[business.BookID]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.LibraryAppController",
      "viewBookDetails",
      Seq(classOf[business.BookID]),
      "GET",
      this.prefix + """books/details/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:11
  private[this] lazy val controllers_LibraryAppController_editBook4_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("books/edit/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_LibraryAppController_editBook4_invoker = createInvoker(
    LibraryAppController_0.editBook(fakeValue[Int]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.LibraryAppController",
      "editBook",
      Seq(classOf[Int]),
      "GET",
      this.prefix + """books/edit/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:12
  private[this] lazy val controllers_LibraryAppController_editBookPost5_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("books/edit/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_LibraryAppController_editBookPost5_invoker = createInvoker(
    LibraryAppController_0.editBookPost(fakeValue[Int]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.LibraryAppController",
      "editBookPost",
      Seq(classOf[Int]),
      "POST",
      this.prefix + """books/edit/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:13
  private[this] lazy val controllers_LibraryAppController_registerPublisher6_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("publishers/register")))
  )
  private[this] lazy val controllers_LibraryAppController_registerPublisher6_invoker = createInvoker(
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

  // @LINE:14
  private[this] lazy val controllers_LibraryAppController_registerPublisherPost7_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("publishers/register")))
  )
  private[this] lazy val controllers_LibraryAppController_registerPublisherPost7_invoker = createInvoker(
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

  // @LINE:17
  private[this] lazy val controllers_Assets_versioned8_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_versioned8_invoker = createInvoker(
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
    case controllers_LibraryAppController_viewBookDetails3_route(params@_) =>
      call(params.fromPath[business.BookID]("id", None)) { (id) =>
        controllers_LibraryAppController_viewBookDetails3_invoker.call(LibraryAppController_0.viewBookDetails(id))
      }
  
    // @LINE:11
    case controllers_LibraryAppController_editBook4_route(params@_) =>
      call(params.fromPath[Int]("id", None)) { (id) =>
        controllers_LibraryAppController_editBook4_invoker.call(LibraryAppController_0.editBook(id))
      }
  
    // @LINE:12
    case controllers_LibraryAppController_editBookPost5_route(params@_) =>
      call(params.fromPath[Int]("id", None)) { (id) =>
        controllers_LibraryAppController_editBookPost5_invoker.call(LibraryAppController_0.editBookPost(id))
      }
  
    // @LINE:13
    case controllers_LibraryAppController_registerPublisher6_route(params@_) =>
      call { 
        controllers_LibraryAppController_registerPublisher6_invoker.call(LibraryAppController_0.registerPublisher)
      }
  
    // @LINE:14
    case controllers_LibraryAppController_registerPublisherPost7_route(params@_) =>
      call { 
        controllers_LibraryAppController_registerPublisherPost7_invoker.call(LibraryAppController_0.registerPublisherPost)
      }
  
    // @LINE:17
    case controllers_Assets_versioned8_route(params@_) =>
      call(Param[String]("path", Right("/public")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_Assets_versioned8_invoker.call(Assets_1.versioned(path, file))
      }
  }
}
