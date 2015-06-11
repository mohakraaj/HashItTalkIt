package controllers

import models._
import models.UserActor._
import akka.actor.ActorRef
import models.RoomData
import play.Logger
import play.api.data.Forms._
import play.api.data._
import play.api.libs.json.JsValue
import play.api.mvc._
import play.api.i18n.Messages.Implicits._
import play.api.Play.current
import play.api.mvc.WebSocket

import scala.concurrent.Future

class Application extends Controller {


  // Create a userId counter
  var counter = 0

  val UID = "UID"

  val roomName = "ROOMNAME"

  // Form structure to collect Room details
  val roomDetailsForm = Form(
    mapping(
      "name" -> nonEmptyText
    )(RoomData.apply)(RoomData.unapply)
  )

  //Load home page with Room details form
  def home() = Action {
    Ok(views.html.home(roomDetailsForm))
  }

  // Sets up page for chatting with the webSocket
  def chat = Action { implicit request =>

    val uid = request.session.get(UID).getOrElse {
      counter = counter + 1
      counter.toString()
    }

    Ok(views.html.chat("Your new application is ready.")).withSession(request.session +(UID, uid))
  }

  // Bind user form to room details and store it in hashmap
  def addRoomDetails() = Action { implicit request =>
    roomDetailsForm.bindFromRequest.fold(
      formWithErrors => {
        BadRequest(views.html.home(formWithErrors))
      },
      RoomData => {
        Redirect(routes.Application.chat()).withSession(request.session +(roomName, RoomData.name))
      }
    )
  }

  def ws = WebSocket.tryAcceptWithActor[JsValue, JsValue] { implicit request =>
    Future.successful((request.session.get(UID), request.session.get(roomName)) match {
      case (Some(uid: String), Some(room: String)) => Right {
        UserActor.props(uid, room)
      }
      case (None, x) => {
        Logger.warn("Session has no Room name, but has userId: " + x)
        Left(Forbidden)
      }
      case (x, None) => {
        Logger.warn("Session has no UserID, but has roomName: " + x)
        Left(Forbidden)
      }
    })

  }


}