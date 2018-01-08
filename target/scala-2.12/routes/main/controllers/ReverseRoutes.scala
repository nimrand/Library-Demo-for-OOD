
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/kpyancey/Projects/Library-Demo-for-OOD/conf/routes
// @DATE:Mon Jan 08 09:22:21 KST 2018

import play.api.mvc.Call


import _root_.controllers.Assets.Asset

// @LINE:7
package controllers {

  // @LINE:7
  class ReverseLibraryAppController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:11
    def editBook(id:Int): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "books/edit/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Int]].unbind("id", id)))
    }
  
    // @LINE:14
    def registerPublisherPost(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "publishers/register")
    }
  
    // @LINE:13
    def registerPublisher(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "publishers/register")
    }
  
    // @LINE:9
    def registerBookPost(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "books/register")
    }
  
    // @LINE:10
    def viewBookDetails(id:BookID): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "books/details/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[BookID]].unbind("id", id)))
    }
  
    // @LINE:8
    def registerBook(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "books/register")
    }
  
    // @LINE:7
    def index(): Call = {
      
      Call("GET", _prefix)
    }
  
    // @LINE:12
    def editBookPost(id:Int): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "books/edit/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Int]].unbind("id", id)))
    }
  
  }

  // @LINE:17
  class ReverseAssets(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:17
    def versioned(file:Asset): Call = {
      implicit lazy val _rrc = new play.core.routing.ReverseRouteContext(Map(("path", "/public"))); _rrc
      Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[play.api.mvc.PathBindable[Asset]].unbind("file", file))
    }
  
  }


}
