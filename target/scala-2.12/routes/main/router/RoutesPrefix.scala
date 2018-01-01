
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/kpyancey/Projects/library/conf/routes
// @DATE:Sat Nov 18 23:08:12 KST 2017


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
