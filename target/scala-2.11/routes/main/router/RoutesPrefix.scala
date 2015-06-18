
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/MAC/Documents/PlayExperiment/HashItTalkIt/conf/routes
// @DATE:Wed Jun 17 20:03:19 PDT 2015


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
