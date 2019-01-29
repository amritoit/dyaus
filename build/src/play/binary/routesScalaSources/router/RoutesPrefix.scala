// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/amritendu/learning/play/dyaus/conf/routes
// @DATE:Tue Jan 22 18:35:33 IST 2019


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
