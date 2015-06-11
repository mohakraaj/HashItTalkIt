
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/MAC/Documents/PlayExperiment/HashItTalkIt/conf/routes
// @DATE:Tue Jun 09 02:21:34 PDT 2015


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
