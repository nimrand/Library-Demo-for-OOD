
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/kpyancey/Projects/Library-Demo-for-OOD/conf/routes
// @DATE:Mon Jan 08 22:01:14 KST 2018

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset
import _root_.controllers.Binders._

// @LINE:7
package controllers.javascript {

  // @LINE:7
  class ReverseLibraryAppController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:18
    def registerLibraryMember: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.LibraryAppController.registerLibraryMember",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "libraryMembers/register"})
        }
      """
    )
  
    // @LINE:16
    def disposeBook: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.LibraryAppController.disposeBook",
      """
        function(id0) {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "books/dispose/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[business.BookID]].javascriptUnbind + """)("id", id0))})
        }
      """
    )
  
    // @LINE:17
    def registerPublisherPost: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.LibraryAppController.registerPublisherPost",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "publishers/register"})
        }
      """
    )
  
    // @LINE:22
    def reportBookReturned: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.LibraryAppController.reportBookReturned",
      """
        function(id0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "books/reportReturned/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[business.BookID]].javascriptUnbind + """)("id", id0))})
        }
      """
    )
  
    // @LINE:9
    def registerBookPost: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.LibraryAppController.registerBookPost",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "books/register"})
        }
      """
    )
  
    // @LINE:12
    def editBookPost: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.LibraryAppController.editBookPost",
      """
        function(id0) {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "books/edit/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[business.BookID]].javascriptUnbind + """)("id", id0))})
        }
      """
    )
  
    // @LINE:11
    def editBook: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.LibraryAppController.editBook",
      """
        function(id0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "books/edit/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[business.BookID]].javascriptUnbind + """)("id", id0))})
        }
      """
    )
  
    // @LINE:10
    def viewBookDetails: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.LibraryAppController.viewBookDetails",
      """
        function(id0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "books/details/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[business.BookID]].javascriptUnbind + """)("id", id0))})
        }
      """
    )
  
    // @LINE:15
    def reportBookFound: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.LibraryAppController.reportBookFound",
      """
        function(id0) {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "books/reportFound/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[business.BookID]].javascriptUnbind + """)("id", id0))})
        }
      """
    )
  
    // @LINE:26
    def logout: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.LibraryAppController.logout",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "signout"})
        }
      """
    )
  
    // @LINE:8
    def registerBook: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.LibraryAppController.registerBook",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "books/register"})
        }
      """
    )
  
    // @LINE:13
    def searchBooks: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.LibraryAppController.searchBooks",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "books/search"})
        }
      """
    )
  
    // @LINE:25
    def loginPost: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.LibraryAppController.loginPost",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "signin"})
        }
      """
    )
  
    // @LINE:14
    def reportBookLost: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.LibraryAppController.reportBookLost",
      """
        function(id0) {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "books/reportLost/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[business.BookID]].javascriptUnbind + """)("id", id0))})
        }
      """
    )
  
    // @LINE:23
    def reportBookReturnedPost: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.LibraryAppController.reportBookReturnedPost",
      """
        function(id0) {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "books/reportReturned/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[business.BookID]].javascriptUnbind + """)("id", id0))})
        }
      """
    )
  
    // @LINE:21
    def loanBookPost: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.LibraryAppController.loanBookPost",
      """
        function(id0) {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "books/loan/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[business.BookID]].javascriptUnbind + """)("id", id0))})
        }
      """
    )
  
    // @LINE:20
    def loanBook: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.LibraryAppController.loanBook",
      """
        function(id0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "books/loan/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[business.BookID]].javascriptUnbind + """)("id", id0))})
        }
      """
    )
  
    // @LINE:7
    def index: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.LibraryAppController.index",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + """"})
        }
      """
    )
  
    // @LINE:19
    def registerLibraryMemberPost: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.LibraryAppController.registerLibraryMemberPost",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "libraryMembers/register"})
        }
      """
    )
  
    // @LINE:24
    def login: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.LibraryAppController.login",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "signin"})
        }
      """
    )
  
  }

  // @LINE:29
  class ReverseAssets(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:29
    def versioned: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Assets.versioned",
      """
        function(file1) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[play.api.mvc.PathBindable[Asset]].javascriptUnbind + """)("file", file1)})
        }
      """
    )
  
  }


}
