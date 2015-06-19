package controllers

import models._
import models.UserActor._
import akka.actor.ActorRef
import models.RoomData
import play.api.Logger
import play.api.data.Forms._
import play.api.data._
import play.api.libs.json._
import play.api.mvc._
import play.api.i18n.Messages.Implicits._
import play.api.Play.current
import play.api.mvc.WebSocket
import scala.collection.mutable.{HashMap,Set}
import scala.concurrent.Future


// TODO: Page refresh creates stale data in userRoomMapping (may be lazy val or streams )
// TODO : On refresh uid to roommapping is gone. "Couldn decoe the cookie error"
// TODO: Create logging Action instance

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
  def chat() = Action { implicit request =>
    val uid = request.session.get(UID).map { id => updateStaleData(id); id}.getOrElse {
      counter = counter + 1
      counter.toString()
    }


    logger.debug("User uid: " + uid + " has started the application")
    Ok(views.html.chat(uid)).withSession(request.session + (UID -> uid))
  }


  // Bind user form to room details and store it in hashmap
  //TODO : Make room details as FlashScope
  //TODO : Make this asynchronous call
  def addRoomDetails() = Action { implicit request =>
    val uid = request.session.get(UID).getOrElse{ logger.error(" Request has no user Id attached"); " "}
    roomDetailsForm.bindFromRequest.fold(
      formWithErrors => {
        logger.error("User has entered incompatible form values")
        BadRequest("Error in form filling")
      },
      RoomData => {
        logger.debug("Room Form is in acceptable format")
        logger.info("Room created by user "+ uid)
        if (uidToRoomMap(uid).add(RoomData.name) ) {

          // investigate why it is returning false. more clearly why roomToUidMap("anything") = "1"
          roomToUidMap(RoomData.name).add(uid)
          Ok(RoomData.name)
        }
        else
          BadRequest("Duplicate roomname")
      }
    )
  }

  // Method to return Json val of the list of rooms
  def getRoomDetails() = Action { implicit request =>
    val uid = request.session.get(UID).getOrElse{ logger.error(" Request has no user Id attached"); " "}
    logger.info("GetRoomdetails requested from "+ uid)
    val roomListJson = Json.toJson(uidToRoomMap(uid))

    Ok(roomListJson)
  }

  // Add method to describe the usefullness of the app
  def about = TODO

  // Method to attach websockets with Actor class
  def ws = WebSocket.tryAcceptWithActor[JsValue, JsValue] { implicit request =>
    logger.debug("Initiating websockets with session value :" + request.session.get(UID))
    Future.successful((request.session.get(UID)) match {
      case (Some(uid: String)) => Right {
        UserActor.props(uid)
      }
      case (None) => {
        logger.error("Session has no user Id ")
        Left(Forbidden)
      }

    })

  }

  // On refresh of the browser the websockets are closed and new websockets are created
  // hence new actors are created. We need to update hashtables to link to new actors
  def updateStaleData(uid : String) = {
    uidToUserActor.remove(uid)
  }


}