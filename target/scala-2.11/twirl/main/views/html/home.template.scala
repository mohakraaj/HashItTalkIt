
package views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object home_Scope0 {
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._

class home extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template2[Form[models.RoomData],Messages,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(roomForm: Form[models.RoomData])(implicit messages: Messages):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.64*/("""

"""),format.raw/*3.1*/("""<!DOCTYPE html>

<html lang="en">
    <head>
        <title>HashItTalkIt</title>

        <link rel='stylesheet' href='"""),_display_(/*9.39*/routes/*9.45*/.Assets.at("stylesheets/slate-bootstrap.min.css")),format.raw/*9.94*/("""' >
        <link rel='stylesheet' href='"""),_display_(/*10.39*/routes/*10.45*/.Assets.at("stylesheets/bootstrap-theme.css")),format.raw/*10.90*/("""' >
        <link rel="stylesheet" href=""""),_display_(/*11.39*/routes/*11.45*/.Assets.at("stylesheets/main.css")),format.raw/*11.79*/("""" >
        <link rel="shortcut icon" type="image/png" href=""""),_display_(/*12.59*/routes/*12.65*/.Assets.at("images/chat.png")),format.raw/*12.94*/("""" >

        <script type='text/javascript' src='"""),_display_(/*14.46*/routes/*14.52*/.Assets.at("javascripts/bootstrap.min.js")),format.raw/*14.94*/("""'> </script>
        <script type='text/javascript' src='"""),_display_(/*15.46*/routes/*15.52*/.Assets.at("javascripts/jquery.min.js")),format.raw/*15.91*/("""'> </script>
    </head>
    <body>
        <div class="container-fluid navbar navbar-primary navbar-fixed-top">
            <div class="row">
                <ul class="nav">
                <li class="col-md-6 col-sm-6 col-xs-6 nav active" ><a href="/"><h4>HashTalk</h4></a></li>
                <li class="col-md-6 col-sm-6 col-xs-6 nav"><a href="/about"> <h4>About</h4></a></li>
                    </ul>
            </div>
        </div>
        <div id="main" class="container-fluid col-md-offset-4 col-sm-offset-4 col-xs-offset-4" >
            <form action=""""),_display_(/*27.28*/routes/*27.34*/.Application.addRoomDetails()),format.raw/*27.63*/("""" method="post">
                <label for="name">Room Name</label>
                <input type="text" name="name" class="form-control"/>
                <input type="password"  class="form-control"/>
                <input type="submit" text="submit" class="btn btn-primary ">
            </form>
        </div>
    </body>
</html>
"""))
      }
    }
  }

  def render(roomForm:Form[models.RoomData],messages:Messages): play.twirl.api.HtmlFormat.Appendable = apply(roomForm)(messages)

  def f:((Form[models.RoomData]) => (Messages) => play.twirl.api.HtmlFormat.Appendable) = (roomForm) => (messages) => apply(roomForm)(messages)

  def ref: this.type = this

}


}

/**/
object home extends home_Scope0.home
              /*
                  -- GENERATED --
                  DATE: Mon Jun 15 02:17:22 PDT 2015
                  SOURCE: /Users/MAC/Documents/PlayExperiment/HashItTalkIt/app/views/home.scala.html
                  HASH: c7f0ba4f66f8d94208a5e5944703ee5c03f5105d
                  MATRIX: 549->1|706->63|734->65|880->185|894->191|963->240|1032->282|1047->288|1113->333|1182->375|1197->381|1252->415|1341->477|1356->483|1406->512|1483->562|1498->568|1561->610|1646->668|1661->674|1721->713|2315->1280|2330->1286|2380->1315
                  LINES: 20->1|25->1|27->3|33->9|33->9|33->9|34->10|34->10|34->10|35->11|35->11|35->11|36->12|36->12|36->12|38->14|38->14|38->14|39->15|39->15|39->15|51->27|51->27|51->27
                  -- GENERATED --
              */
          