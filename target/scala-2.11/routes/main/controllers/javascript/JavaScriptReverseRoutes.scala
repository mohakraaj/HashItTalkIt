
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/MAC/Documents/PlayExperiment/HashItTalkIt/conf/routes
// @DATE:Wed Jun 17 20:03:19 PDT 2015

import play.api.routing.JavaScriptReverseRoute
import play.api.mvc.{ QueryStringBindable, PathBindable, Call, JavascriptLiteral }
import play.core.routing.{ HandlerDef, ReverseRouteContext, queryString, dynamicString }


import _root_.controllers.Assets.Asset

// @LINE:6
package controllers.javascript {
  import ReverseRouteContext.empty

  // @LINE:9
  class ReverseAssets(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:9
    def at: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Assets.at",
      """
        function(file) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
        }
      """
    )
  
  }

  // @LINE:6
  class ReverseApplication(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:17
    def addRoomDetails: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Application.addRoomDetails",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "addroomdetails"})
        }
      """
    )
  
    // @LINE:13
    def ws: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Application.ws",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "ws"})
        }
      """
    )
  
    // @LINE:15
    def about: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Application.about",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "about"})
        }
      """
    )
  
    // @LINE:6
    def chat: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Application.chat",
      """
        function() {
        
          if (true) {
            return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "chat"})
          }
        
        }
      """
    )
  
    // @LINE:19
    def getRoomDetails: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Application.getRoomDetails",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getroomdetails"})
        }
      """
    )
  
  }


}