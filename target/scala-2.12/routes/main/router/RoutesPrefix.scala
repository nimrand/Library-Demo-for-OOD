
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/kpyancey/Projects/Library-Demo-for-OOD/conf/routes
// @DATE:Mon Jan 08 09:29:48 KST 2018


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
