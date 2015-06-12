package controllers

import models._
import models.UserActor._
import akka.actor.ActorRef
import models.RoomData
import play.api.Logger
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

  val logger = Logger(this.getClass())

  // Form structure to collect Room details
  val roomDetailsForm = Form(
    mapping(
      "name" -> nonEmptyText
    )(RoomData.apply)(RoomData.unapply)
  )

  //Load home page with Room details form
  // TODO : Redirect to chat room if the user has already added Room
  def home() = Action { implicit request =>
    val uid = request.session.get(UID).getOrElse {
      counter = counter + 1
      counter.toString()
    }
    logger.debug("User uid: " + uid + " has started the application")
    Ok(views.html.home(roomDetailsForm)).withSession(request.session + (UID -> uid))
  }

  // Sets up page for chatting with the webSocket
  def chat = Action { implicit request =>
    request.session.get(roomName) match {
      case (Some(room: String)) =>  {
        logger.debug("Sending user uid : "+ request.session.get(UID) +" to chat room")
        Ok(views.html.chat("Your new application is ready."))
      }
      case None => {
        logger.debug("Redirecting user to home page")
        Redirect(routes.Application.home())
      }
    }
  }

  // Bind user form to room details and store it in hashmap
  //TODO : Make room details as FlashScope
  def addRoomDetails() = Action { implicit request =>
    roomDetailsForm.bindFromRequest.fold(
      formWithErrors => {
        logger.error("User has entered in compatible form values")
        BadRequest(views.html.home(formWithErrors))
      },
      RoomData => {
        logger.debug("Redirecting user to chat room")
        Redirect(routes.Application.chat()).withSession(request.session + (roomName -> RoomData.name))
      }
    )
  }

  // Method to attach websockets with Actor class
  def ws = WebSocket.tryAcceptWithActor[JsValue, JsValue] { implicit request =>
    logger.debug("Initiating websockets with session value :" + request.session.get(UID))
    logger.debug("Initiating websockets with roomName : " + request.session.get(roomName))

    Future.successful((request.session.get(UID), request.session.get(roomName)) match {
      case (Some(uid: String), Some(room: String)) => Right {
        UserActor.props(uid, room)
      }
      case (None, x) => {
        logger.error("Session has no Room name, but has userId: " + x)
        Left(Forbidden)
      }
      case (x, None) => {
        logger.error("Session has no UserID, but has roomName: " + x)
        Left(Forbidden)
      }
      case (None, None) => {
        logger.error("No session values are available")
        //TODO : Warn user about cookies
        Left(Forbidden)
      }
    })

  }


}