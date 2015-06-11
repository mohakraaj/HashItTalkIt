
package views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object chat_Scope0 {
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._

     object chat_Scope1 {
import play.api.mvc.RequestHeader

class chat extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template2[String,RequestHeader,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*3.2*/(message: String)(implicit request: RequestHeader):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.52*/("""

"""),format.raw/*5.1*/("""<!DOCTYPE html>


<html>
  <head>
    <title>Anonymous Chat</title>
    <link rel='stylesheet' href='"""),_display_(/*11.35*/routes/*11.41*/.Assets.at("stylesheets/bootstrap-min.css")),format.raw/*11.84*/("""'>
    <link rel='stylesheet' href='"""),_display_(/*12.35*/routes/*12.41*/.Assets.at("stylesheets/bootstrap-theme.css")),format.raw/*12.86*/("""'>
    <link rel="stylesheet" media="screen" href=""""),_display_(/*13.50*/routes/*13.56*/.Assets.at("stylesheets/main.css")),format.raw/*13.90*/("""">
    <script type='text/javascript'
    src='"""),_display_(/*15.11*/routes/*15.17*/.Assets.at("javascripts/jquery.min.js")),format.raw/*15.56*/("""'></script>
    <script type='text/javascript'
    src='"""),_display_(/*17.11*/routes/*17.17*/.Assets.at("javascripts/jquery.flot.js")),format.raw/*17.57*/("""'></script>
    <script type='text/javascript' src='"""),_display_(/*18.42*/routes/*18.48*/.Assets.at("javascripts/index.js")),format.raw/*18.82*/("""'></script>
  </head>

  <body data-ws-url=""""),_display_(/*21.23*/routes/*21.29*/.Application.ws().webSocketURL()),format.raw/*21.61*/("""" class="bg-primary" >

    <div class="container ">
      <div class="row">
        <div class="col-xs-12">\
          <table id="board" class="col-xs-12 table table-bordered info">
            <thead>
              <tr>
                <th class="col-xs-3 text-center" >user</th>
                <th class="col-xs-9" >message</th>
              </tr>
            </thead>
            <tbody>
            </tbody>
          </table>
        </div>
      </div>
      <div class="row">
        <div class="col-xs-3 strong text-right ">
          User """),_display_(/*40.17*/message),format.raw/*40.24*/("""
        """),format.raw/*41.9*/("""</div>
        <div class="col-xs-9">
          <form id="msgform" class="form-horizontal" >
            <div class="col-xs-8">
              <input id="msgtext" type="text"  class="form-control"
              placeholder="please type a message" />
            </div>
            <div class="col-xs-1">
              <button type="submit" class="btn btn-default ">Submit</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </body>
</html>"""))
      }
    }
  }

  def render(message:String,request:RequestHeader): play.twirl.api.HtmlFormat.Appendable = apply(message)(request)

  def f:((String) => (RequestHeader) => play.twirl.api.HtmlFormat.Appendable) = (message) => (request) => apply(message)(request)

  def ref: this.type = this

}


}
}

/**/
object chat extends chat_Scope0.chat_Scope1.chat
              /*
                  -- GENERATED --
                  DATE: Tue Jun 09 19:34:12 PDT 2015
                  SOURCE: /Users/MAC/Documents/PlayExperiment/HashItTalkIt/app/views/chat.scala.html
                  HASH: f9946082d1525bfdb76eac18e3d1293ac3f614ba
                  MATRIX: 600->37|745->87|773->89|902->191|917->197|981->240|1045->277|1060->283|1126->328|1205->380|1220->386|1275->420|1350->468|1365->474|1425->513|1509->570|1524->576|1585->616|1665->669|1680->675|1735->709|1807->754|1822->760|1875->792|2454->1344|2482->1351|2518->1360
                  LINES: 23->3|28->3|30->5|36->11|36->11|36->11|37->12|37->12|37->12|38->13|38->13|38->13|40->15|40->15|40->15|42->17|42->17|42->17|43->18|43->18|43->18|46->21|46->21|46->21|65->40|65->40|66->41
                  -- GENERATED --
              */
          