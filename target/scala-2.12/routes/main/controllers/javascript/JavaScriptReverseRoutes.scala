
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/kpyancey/Projects/Library-Demo-for-OOD/conf/routes
// @DATE:Mon Jan 08 09:22:21 KST 2018

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset

// @LINE:7
package controllers.javascript {

  // @LINE:7
  class ReverseLibraryAppController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:11
    def editBook: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.LibraryAppController.editBook",
      """
        function(id0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "books/edit/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Int]].javascriptUnbind + """)("id", id0))})
        }
      """
    )
  
    // @LINE:14
    def registerPublisherPost: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.LibraryAppController.registerPublisherPost",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "publishers/register"})
        }
      """
    )
  
    // @LINE:13
    def registerPublisher: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.LibraryAppController.registerPublisher",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "publishers/register"})
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
  
    // @LINE:10
    def viewBookDetails: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.LibraryAppController.viewBookDetails",
      """
        function(id0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "books/details/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[BookID]].javascriptUnbind + """)("id", id0))})
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
  
    // @LINE:7
    def index: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.LibraryAppController.index",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + """"})
        }
      """
    )
  
    // @LINE:12
    def editBookPost: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.LibraryAppController.editBookPost",
      """
        function(id0) {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "books/edit/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Int]].javascriptUnbind + """)("id", id0))})
        }
      """
    )
  
  }

  // @LINE:17
  class ReverseAssets(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:17
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
