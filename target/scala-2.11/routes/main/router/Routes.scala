
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/MAC/Documents/PlayExperiment/HashItTalkIt/conf/routes
// @DATE:Tue Jun 09 02:21:34 PDT 2015

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._

import _root_.controllers.Assets.Asset

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:6
  Application_1: controllers.Application,
  // @LINE:9
  Assets_0: controllers.Assets,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:6
    Application_1: controllers.Application,
    // @LINE:9
    Assets_0: controllers.Assets
  ) = this(errorHandler, Application_1, Assets_0, "/")

  import ReverseRouteContext.empty

  def withPrefix(prefix: String): Routes = {
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, Application_1, Assets_0, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """chat""", """controllers.Application.chat"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""", """controllers.Assets.at(path:String = "/public", file:String)"""),
    ("""GET""", this.prefix, """controllers.Application.home"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """ws""", """controllers.Application.ws"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """addroomdetails""", """controllers.Application.addRoomDetails"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:6
  private[this] lazy val controllers_Application_chat0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("chat")))
  )
  private[this] lazy val controllers_Application_chat0_invoker = createInvoker(
    Application_1.chat,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Application",
      "chat",
      Nil,
      "GET",
      """ Home page""",
      this.prefix + """chat"""
    )
  )

  // @LINE:9
  private[this] lazy val controllers_Assets_at1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_at1_invoker = createInvoker(
    Assets_0.at(fakeValue[String], fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "at",
      Seq(classOf[String], classOf[String]),
      "GET",
      """ Map static resources from the /public folder to the /assets URL path""",
      this.prefix + """assets/$file<.+>"""
    )
  )

  // @LINE:11
  private[this] lazy val controllers_Application_home2_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private[this] lazy val controllers_Application_home2_invoker = createInvoker(
    Application_1.home,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Application",
      "home",
      Nil,
      "GET",
      """""",
      this.prefix + """"""
    )
  )

  // @LINE:13
  private[this] lazy val controllers_Application_ws3_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("ws")))
  )
  private[this] lazy val controllers_Application_ws3_invoker = createInvoker(
    Application_1.ws,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Application",
      "ws",
      Nil,
      "GET",
      """""",
      this.prefix + """ws"""
    )
  )

  // @LINE:15
  private[this] lazy val controllers_Application_addRoomDetails4_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("addroomdetails")))
  )
  private[this] lazy val controllers_Application_addRoomDetails4_invoker = createInvoker(
    Application_1.addRoomDetails,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Application",
      "addRoomDetails",
      Nil,
      "POST",
      """ Post request to get Room Name""",
      this.prefix + """addroomdetails"""
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:6
    case controllers_Application_chat0_route(params) =>
      call { 
        controllers_Application_chat0_invoker.call(Application_1.chat)
      }
  
    // @LINE:9
    case controllers_Assets_at1_route(params) =>
      call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        controllers_Assets_at1_invoker.call(Assets_0.at(path, file))
      }
  
    // @LINE:11
    case controllers_Application_home2_route(params) =>
      call { 
        controllers_Application_home2_invoker.call(Application_1.home)
      }
  
    // @LINE:13
    case controllers_Application_ws3_route(params) =>
      call { 
        controllers_Application_ws3_invoker.call(Application_1.ws)
      }
  
    // @LINE:15
    case controllers_Application_addRoomDetails4_route(params) =>
      call { 
        controllers_Application_addRoomDetails4_invoker.call(Application_1.addRoomDetails)
      }
  }
}