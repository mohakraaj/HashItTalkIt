
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
    <title>HashItTalkIT</title>
    <link rel="stylesheet" media="screen" href=""""),_display_(/*8.50*/routes/*8.56*/.Assets.at("stylesheets/main.css")),format.raw/*8.90*/("""">
    <link rel="shortcut icon" type="image/png" href=""""),_display_(/*9.55*/routes/*9.61*/.Assets.at("images/favicon.png")),format.raw/*9.93*/("""">
  </head>
  <body>
  """),_display_(/*12.4*/helper/*12.10*/.form(action = routes.Application.addRoomDetails())/*12.61*/ {_display_(Seq[Any](format.raw/*12.63*/("""
    """),_display_(/*13.6*/helper/*13.12*/.inputText(roomForm("name"))),format.raw/*13.40*/("""
    """),format.raw/*14.5*/("""<input type="submit" text="submit" />
  """)))}),format.raw/*15.4*/("""
  """),format.raw/*16.3*/("""</body>
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
                  DATE: Tue Jun 09 02:22:03 PDT 2015
                  SOURCE: /Users/MAC/Documents/PlayExperiment/HashItTalkIt/app/views/home.scala.html
                  HASH: 225ddd19e464d3ebbdd8eacbf9e81399eca44942
                  MATRIX: 549->1|706->63|734->65|884->189|898->195|952->229|1035->286|1049->292|1101->324|1152->349|1167->355|1227->406|1267->408|1299->414|1314->420|1363->448|1395->453|1466->494|1496->497
                  LINES: 20->1|25->1|27->3|32->8|32->8|32->8|33->9|33->9|33->9|36->12|36->12|36->12|36->12|37->13|37->13|37->13|38->14|39->15|40->16
                  -- GENERATED --
              */
          