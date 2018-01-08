
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/kpyancey/Projects/Library-Demo-for-OOD/conf/routes
// @DATE:Mon Jan 08 17:47:11 KST 2018

import play.api.mvc.Call


import _root_.controllers.Assets.Asset
import _root_.controllers.Binders._

// @LINE:7
package controllers {

  // @LINE:7
  class ReverseLibraryAppController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:18
    def registerLibraryMember(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "libraryMembers/register")
    }
  
    // @LINE:16
    def disposeBook(id:business.BookID): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "books/dispose/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[business.BookID]].unbind("id", id)))
    }
  
    // @LINE:17
    def registerPublisherPost(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "publishers/register")
    }
  
    // @LINE:22
    def reportBookReturned(id:business.BookID): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "books/reportReturned/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[business.BookID]].unbind("id", id)))
    }
  
    // @LINE:9
    def registerBookPost(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "books/register")
    }
  
    // @LINE:12
    def editBookPost(id:business.BookID): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "books/edit/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[business.BookID]].unbind("id", id)))
    }
  
    // @LINE:11
    def editBook(id:business.BookID): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "books/edit/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[business.BookID]].unbind("id", id)))
    }
  
    // @LINE:10
    def viewBookDetails(id:business.BookID): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "books/details/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[business.BookID]].unbind("id", id)))
    }
  
    // @LINE:15
    def reportBookFound(id:business.BookID): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "books/reportFound/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[business.BookID]].unbind("id", id)))
    }
  
    // @LINE:8
    def registerBook(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "books/register")
    }
  
    // @LINE:13
    def searchBooks(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "books/search")
    }
  
    // @LINE:14
    def reportBookLost(id:business.BookID): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "books/reportLost/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[business.BookID]].unbind("id", id)))
    }
  
    // @LINE:23
    def reportBookReturnedPost(id:business.BookID): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "books/reportReturned/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[business.BookID]].unbind("id", id)))
    }
  
    // @LINE:21
    def loanBookPost(id:business.BookID): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "books/loan/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[business.BookID]].unbind("id", id)))
    }
  
    // @LINE:20
    def loanBook(id:business.BookID): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "books/loan/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[business.BookID]].unbind("id", id)))
    }
  
    // @LINE:7
    def index(): Call = {
      
      Call("GET", _prefix)
    }
  
    // @LINE:19
    def registerLibraryMemberPost(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "libraryMembers/register")
    }
  
  }

  // @LINE:26
  class ReverseAssets(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:26
    def versioned(file:Asset): Call = {
      implicit lazy val _rrc = new play.core.routing.ReverseRouteContext(Map(("path", "/public"))); _rrc
      Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[play.api.mvc.PathBindable[Asset]].unbind("file", file))
    }
  
  }


}
