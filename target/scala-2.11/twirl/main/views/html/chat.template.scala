
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
        <title>HashItTalkIt</title>

        <link rel='stylesheet' href='"""),_display_(/*12.39*/routes/*12.45*/.Assets.at("stylesheets/slate-bootstrap.min.css")),format.raw/*12.94*/("""' >
        <link rel='stylesheet' href='"""),_display_(/*13.39*/routes/*13.45*/.Assets.at("stylesheets/bootstrap-theme.css")),format.raw/*13.90*/("""' >
        <link rel="stylesheet" href=""""),_display_(/*14.39*/routes/*14.45*/.Assets.at("stylesheets/main.css")),format.raw/*14.79*/("""" >
        <link rel="shortcut icon" type="image/png" href=""""),_display_(/*15.59*/routes/*15.65*/.Assets.at("images/chat.png")),format.raw/*15.94*/("""" >

        <script type='text/javascript' src='"""),_display_(/*17.46*/routes/*17.52*/.Assets.at("javascripts/jquery.min.js")),format.raw/*17.91*/("""'> </script>
        <script type='text/javascript' src='"""),_display_(/*18.46*/routes/*18.52*/.Assets.at("javascripts/bootstrap.min.js")),format.raw/*18.94*/("""'> </script>
        <script type='text/javascript' src='"""),_display_(/*19.46*/routes/*19.52*/.Assets.at("javascripts/aes.js")),format.raw/*19.84*/("""'></script>
        <script type='text/javascript' src='"""),_display_(/*20.46*/routes/*20.52*/.Assets.at("javascripts/index.js")),format.raw/*20.86*/("""'></script>

        <script>

    </script>
    </head>

    <body data-ws-url=""""),_display_(/*27.25*/routes/*27.31*/.Application.ws().webSocketURL()),format.raw/*27.63*/("""" class="bg-primary" >
        <div class="container-fluid navbar navbar-primary navbar-fixed-top">
            <div class="row">
                <ul class="nav">
                    <li class="col-md-6 col-sm-6 col-xs-6 nav active" ><a href="/"><h4>HashTalk</h4></a></li>
                    <li class="col-md-6 col-sm-6 col-xs-6 nav"><a href="/about"> <h4>About</h4></a></li>
                </ul>
            </div>
        </div>
            <!--            <div id="passworddiv" >


                <p> Password : <input id="password" type=password placeholder="password" value=""/> </p>
                <p> <input type="submit" id="encryptbutton"  value="Encrypt" onclick="EnableChat()" class="btn btn-primary"> </p>
            </div> -->
        <div class="container-fluid"></div>
        <div class="row">
            <div id="rooms" class="col-md-4">
                <table id="roomList" data-click-to-select="true" class="table table-hover" data-search="true">
                    <thead>
                        <th>
                            <a href="#" class="btn btn-large btn-primary" rel="popover" data-toggle="popover"
                            data-content='<form id="addroom" action=""""),_display_(/*49.71*/routes/*49.77*/.Application.addRoomDetails()),format.raw/*49.106*/("""" method="post" class="form-inline">
                                               <input type="text" name="name" class="form-control" placeholder="Room Name"/>
                                               <button type="submit" class="btn btn-primary editable-submit"><span class="glyphicon glyphicon-ok "></span></button>
                                            </form>'
                            data-placement="bottom" data-html="true" >Add Rooms </a>

                        </th>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
            </div>
            <div class=" col-md-8 ">
                <table id="board" class="col-xs-12 table table-hover table-striped">
                    <thead>
                        <tr>
                            <th class="col-xs-3 text-center" >user</th>
                            <th class="col-xs-9" >Message</th>
                        </tr>
                    </thead>
                    <tbody>
                            </tbody>
                </table>
            </div>
        </div>
        <div id="chatform" class="row" style="display: none">
            <div class="col-xs-3 strong text-right ">
                        User """),_display_(/*77.31*/message),format.raw/*77.38*/("""
            """),format.raw/*78.13*/("""</div>
            <div class="col-xs-9">
                <form id="msgform" class="form-horizontal" >
                    <div class="col-xs-8">
                        <input id="msgtext" type="text" class="form-control"
                        placeholder="please type a message" />
                    </div>
                    <div class="col-xs-1">
                        <button type="submit" class="btn btn-primary ">Submit</button>
                    </div>
                </form>
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
                  DATE: Wed Jun 17 21:04:01 PDT 2015
                  SOURCE: /Users/MAC/Documents/PlayExperiment/HashItTalkIt/app/views/chat.scala.html
                  HASH: 99b254880db8bd6aa9f4ad7d4d2b94947daba3a0
                  MATRIX: 600->37|745->87|773->89|911->200|926->206|996->255|1065->297|1080->303|1146->348|1215->390|1230->396|1285->430|1374->492|1389->498|1439->527|1516->577|1531->583|1591->622|1676->680|1691->686|1754->728|1839->786|1854->792|1907->824|1991->881|2006->887|2061->921|2170->1003|2185->1009|2238->1041|3475->2251|3490->2257|3541->2286|4837->3555|4865->3562|4906->3575
                  LINES: 23->3|28->3|30->5|37->12|37->12|37->12|38->13|38->13|38->13|39->14|39->14|39->14|40->15|40->15|40->15|42->17|42->17|42->17|43->18|43->18|43->18|44->19|44->19|44->19|45->20|45->20|45->20|52->27|52->27|52->27|74->49|74->49|74->49|102->77|102->77|103->78
                  -- GENERATED --
              */
          