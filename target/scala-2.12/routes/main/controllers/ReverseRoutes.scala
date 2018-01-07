
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/kpyancey/Projects/Library-Demo-for-OOD/conf/routes
// @DATE:Sun Jan 07 18:38:07 KST 2018

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
    def registerPublisherPost(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "publishers/register")
    }
  
    // @LINE:10
    def registerPublisher(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "publishers/register")
    }
  
    // @LINE:9
    def registerBookPost(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "books/register")
    }
  
    // @LINE:8
    def registerBook(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "books/register")
    }
  
    // @LINE:7
    def index(): Call = {
      
      Call("GET", _prefix)
    }
  
  }

  // @LINE:14
  class ReverseAssets(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:14
    def versioned(file:Asset): Call = {
      implicit lazy val _rrc = new play.core.routing.ReverseRouteContext(Map(("path", "/public"))); _rrc
      Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[play.api.mvc.PathBindable[Asset]].unbind("file", file))
    }
  
  }


}
