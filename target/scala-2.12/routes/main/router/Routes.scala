
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/kpyancey/Projects/Library-Demo-for-OOD/conf/routes
// @DATE:Mon Jan 08 22:01:14 KST 2018

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
  // @LINE:29
  Assets_1: controllers.Assets,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:7
    LibraryAppController_0: controllers.LibraryAppController,
    // @LINE:29
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
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """books/edit/""" + "$" + """id<[^/]+>""", """controllers.LibraryAppController.editBook(id:business.BookID)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """books/edit/""" + "$" + """id<[^/]+>""", """controllers.LibraryAppController.editBookPost(id:business.BookID)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """books/search""", """controllers.LibraryAppController.searchBooks"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """books/reportLost/""" + "$" + """id<[^/]+>""", """controllers.LibraryAppController.reportBookLost(id:business.BookID)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """books/reportFound/""" + "$" + """id<[^/]+>""", """controllers.LibraryAppController.reportBookFound(id:business.BookID)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """books/dispose/""" + "$" + """id<[^/]+>""", """controllers.LibraryAppController.disposeBook(id:business.BookID)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """publishers/register""", """controllers.LibraryAppController.registerPublisherPost"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """libraryMembers/register""", """controllers.LibraryAppController.registerLibraryMember"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """libraryMembers/register""", """controllers.LibraryAppController.registerLibraryMemberPost"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """books/loan/""" + "$" + """id<[^/]+>""", """controllers.LibraryAppController.loanBook(id:business.BookID)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """books/loan/""" + "$" + """id<[^/]+>""", """controllers.LibraryAppController.loanBookPost(id:business.BookID)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """books/reportReturned/""" + "$" + """id<[^/]+>""", """controllers.LibraryAppController.reportBookReturned(id:business.BookID)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """books/reportReturned/""" + "$" + """id<[^/]+>""", """controllers.LibraryAppController.reportBookReturnedPost(id:business.BookID)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """signin""", """controllers.LibraryAppController.login"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """signin""", """controllers.LibraryAppController.loginPost"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """signout""", """controllers.LibraryAppController.logout"""),
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
    LibraryAppController_0.editBook(fakeValue[business.BookID]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.LibraryAppController",
      "editBook",
      Seq(classOf[business.BookID]),
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
    LibraryAppController_0.editBookPost(fakeValue[business.BookID]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.LibraryAppController",
      "editBookPost",
      Seq(classOf[business.BookID]),
      "POST",
      this.prefix + """books/edit/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:13
  private[this] lazy val controllers_LibraryAppController_searchBooks6_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("books/search")))
  )
  private[this] lazy val controllers_LibraryAppController_searchBooks6_invoker = createInvoker(
    LibraryAppController_0.searchBooks,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.LibraryAppController",
      "searchBooks",
      Nil,
      "GET",
      this.prefix + """books/search""",
      """""",
      Seq()
    )
  )

  // @LINE:14
  private[this] lazy val controllers_LibraryAppController_reportBookLost7_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("books/reportLost/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_LibraryAppController_reportBookLost7_invoker = createInvoker(
    LibraryAppController_0.reportBookLost(fakeValue[business.BookID]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.LibraryAppController",
      "reportBookLost",
      Seq(classOf[business.BookID]),
      "POST",
      this.prefix + """books/reportLost/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:15
  private[this] lazy val controllers_LibraryAppController_reportBookFound8_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("books/reportFound/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_LibraryAppController_reportBookFound8_invoker = createInvoker(
    LibraryAppController_0.reportBookFound(fakeValue[business.BookID]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.LibraryAppController",
      "reportBookFound",
      Seq(classOf[business.BookID]),
      "POST",
      this.prefix + """books/reportFound/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:16
  private[this] lazy val controllers_LibraryAppController_disposeBook9_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("books/dispose/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_LibraryAppController_disposeBook9_invoker = createInvoker(
    LibraryAppController_0.disposeBook(fakeValue[business.BookID]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.LibraryAppController",
      "disposeBook",
      Seq(classOf[business.BookID]),
      "POST",
      this.prefix + """books/dispose/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:17
  private[this] lazy val controllers_LibraryAppController_registerPublisherPost10_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("publishers/register")))
  )
  private[this] lazy val controllers_LibraryAppController_registerPublisherPost10_invoker = createInvoker(
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

  // @LINE:18
  private[this] lazy val controllers_LibraryAppController_registerLibraryMember11_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("libraryMembers/register")))
  )
  private[this] lazy val controllers_LibraryAppController_registerLibraryMember11_invoker = createInvoker(
    LibraryAppController_0.registerLibraryMember,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.LibraryAppController",
      "registerLibraryMember",
      Nil,
      "GET",
      this.prefix + """libraryMembers/register""",
      """""",
      Seq()
    )
  )

  // @LINE:19
  private[this] lazy val controllers_LibraryAppController_registerLibraryMemberPost12_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("libraryMembers/register")))
  )
  private[this] lazy val controllers_LibraryAppController_registerLibraryMemberPost12_invoker = createInvoker(
    LibraryAppController_0.registerLibraryMemberPost,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.LibraryAppController",
      "registerLibraryMemberPost",
      Nil,
      "POST",
      this.prefix + """libraryMembers/register""",
      """""",
      Seq()
    )
  )

  // @LINE:20
  private[this] lazy val controllers_LibraryAppController_loanBook13_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("books/loan/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_LibraryAppController_loanBook13_invoker = createInvoker(
    LibraryAppController_0.loanBook(fakeValue[business.BookID]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.LibraryAppController",
      "loanBook",
      Seq(classOf[business.BookID]),
      "GET",
      this.prefix + """books/loan/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:21
  private[this] lazy val controllers_LibraryAppController_loanBookPost14_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("books/loan/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_LibraryAppController_loanBookPost14_invoker = createInvoker(
    LibraryAppController_0.loanBookPost(fakeValue[business.BookID]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.LibraryAppController",
      "loanBookPost",
      Seq(classOf[business.BookID]),
      "POST",
      this.prefix + """books/loan/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:22
  private[this] lazy val controllers_LibraryAppController_reportBookReturned15_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("books/reportReturned/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_LibraryAppController_reportBookReturned15_invoker = createInvoker(
    LibraryAppController_0.reportBookReturned(fakeValue[business.BookID]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.LibraryAppController",
      "reportBookReturned",
      Seq(classOf[business.BookID]),
      "GET",
      this.prefix + """books/reportReturned/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:23
  private[this] lazy val controllers_LibraryAppController_reportBookReturnedPost16_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("books/reportReturned/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_LibraryAppController_reportBookReturnedPost16_invoker = createInvoker(
    LibraryAppController_0.reportBookReturnedPost(fakeValue[business.BookID]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.LibraryAppController",
      "reportBookReturnedPost",
      Seq(classOf[business.BookID]),
      "POST",
      this.prefix + """books/reportReturned/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:24
  private[this] lazy val controllers_LibraryAppController_login17_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("signin")))
  )
  private[this] lazy val controllers_LibraryAppController_login17_invoker = createInvoker(
    LibraryAppController_0.login,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.LibraryAppController",
      "login",
      Nil,
      "GET",
      this.prefix + """signin""",
      """""",
      Seq()
    )
  )

  // @LINE:25
  private[this] lazy val controllers_LibraryAppController_loginPost18_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("signin")))
  )
  private[this] lazy val controllers_LibraryAppController_loginPost18_invoker = createInvoker(
    LibraryAppController_0.loginPost,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.LibraryAppController",
      "loginPost",
      Nil,
      "POST",
      this.prefix + """signin""",
      """""",
      Seq()
    )
  )

  // @LINE:26
  private[this] lazy val controllers_LibraryAppController_logout19_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("signout")))
  )
  private[this] lazy val controllers_LibraryAppController_logout19_invoker = createInvoker(
    LibraryAppController_0.logout,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.LibraryAppController",
      "logout",
      Nil,
      "GET",
      this.prefix + """signout""",
      """""",
      Seq()
    )
  )

  // @LINE:29
  private[this] lazy val controllers_Assets_versioned20_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_versioned20_invoker = createInvoker(
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
      call(params.fromPath[business.BookID]("id", None)) { (id) =>
        controllers_LibraryAppController_editBook4_invoker.call(LibraryAppController_0.editBook(id))
      }
  
    // @LINE:12
    case controllers_LibraryAppController_editBookPost5_route(params@_) =>
      call(params.fromPath[business.BookID]("id", None)) { (id) =>
        controllers_LibraryAppController_editBookPost5_invoker.call(LibraryAppController_0.editBookPost(id))
      }
  
    // @LINE:13
    case controllers_LibraryAppController_searchBooks6_route(params@_) =>
      call { 
        controllers_LibraryAppController_searchBooks6_invoker.call(LibraryAppController_0.searchBooks)
      }
  
    // @LINE:14
    case controllers_LibraryAppController_reportBookLost7_route(params@_) =>
      call(params.fromPath[business.BookID]("id", None)) { (id) =>
        controllers_LibraryAppController_reportBookLost7_invoker.call(LibraryAppController_0.reportBookLost(id))
      }
  
    // @LINE:15
    case controllers_LibraryAppController_reportBookFound8_route(params@_) =>
      call(params.fromPath[business.BookID]("id", None)) { (id) =>
        controllers_LibraryAppController_reportBookFound8_invoker.call(LibraryAppController_0.reportBookFound(id))
      }
  
    // @LINE:16
    case controllers_LibraryAppController_disposeBook9_route(params@_) =>
      call(params.fromPath[business.BookID]("id", None)) { (id) =>
        controllers_LibraryAppController_disposeBook9_invoker.call(LibraryAppController_0.disposeBook(id))
      }
  
    // @LINE:17
    case controllers_LibraryAppController_registerPublisherPost10_route(params@_) =>
      call { 
        controllers_LibraryAppController_registerPublisherPost10_invoker.call(LibraryAppController_0.registerPublisherPost)
      }
  
    // @LINE:18
    case controllers_LibraryAppController_registerLibraryMember11_route(params@_) =>
      call { 
        controllers_LibraryAppController_registerLibraryMember11_invoker.call(LibraryAppController_0.registerLibraryMember)
      }
  
    // @LINE:19
    case controllers_LibraryAppController_registerLibraryMemberPost12_route(params@_) =>
      call { 
        controllers_LibraryAppController_registerLibraryMemberPost12_invoker.call(LibraryAppController_0.registerLibraryMemberPost)
      }
  
    // @LINE:20
    case controllers_LibraryAppController_loanBook13_route(params@_) =>
      call(params.fromPath[business.BookID]("id", None)) { (id) =>
        controllers_LibraryAppController_loanBook13_invoker.call(LibraryAppController_0.loanBook(id))
      }
  
    // @LINE:21
    case controllers_LibraryAppController_loanBookPost14_route(params@_) =>
      call(params.fromPath[business.BookID]("id", None)) { (id) =>
        controllers_LibraryAppController_loanBookPost14_invoker.call(LibraryAppController_0.loanBookPost(id))
      }
  
    // @LINE:22
    case controllers_LibraryAppController_reportBookReturned15_route(params@_) =>
      call(params.fromPath[business.BookID]("id", None)) { (id) =>
        controllers_LibraryAppController_reportBookReturned15_invoker.call(LibraryAppController_0.reportBookReturned(id))
      }
  
    // @LINE:23
    case controllers_LibraryAppController_reportBookReturnedPost16_route(params@_) =>
      call(params.fromPath[business.BookID]("id", None)) { (id) =>
        controllers_LibraryAppController_reportBookReturnedPost16_invoker.call(LibraryAppController_0.reportBookReturnedPost(id))
      }
  
    // @LINE:24
    case controllers_LibraryAppController_login17_route(params@_) =>
      call { 
        controllers_LibraryAppController_login17_invoker.call(LibraryAppController_0.login)
      }
  
    // @LINE:25
    case controllers_LibraryAppController_loginPost18_route(params@_) =>
      call { 
        controllers_LibraryAppController_loginPost18_invoker.call(LibraryAppController_0.loginPost)
      }
  
    // @LINE:26
    case controllers_LibraryAppController_logout19_route(params@_) =>
      call { 
        controllers_LibraryAppController_logout19_invoker.call(LibraryAppController_0.logout)
      }
  
    // @LINE:29
    case controllers_Assets_versioned20_route(params@_) =>
      call(Param[String]("path", Right("/public")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_Assets_versioned20_invoker.call(Assets_1.versioned(path, file))
      }
  }
}
