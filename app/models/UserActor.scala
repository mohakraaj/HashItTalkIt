package models

import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import akka.event.LoggingReceive
import play.api.Logger
import play.api.libs.json.JsValue
import play.api.libs.json.Json

import scala.collection.mutable.{Set, HashMap}
import scala.xml.Utility

/** *****************************************************
  * Copyright (C) 2015 mohakraaj@gmail.com
  *
  * This file is part of models .
  *
  * This can  be copied and/or distributed without the express
  * permission of anyone
  * ******************************************************/
class UserActor(uid: String, out: ActorRef) extends Actor with ActorLogging {

  val logger = Logger(this.getClass())

  override def preStart = {
    UserActor.uidToUserActor(uid) = this.self
    logger.info("Creating new instance of UserActor for  UID: " + uid)
  }

  def receive() = LoggingReceive {
    case Message(uid, msg, roomname) => {
      logger.info("User: " + uid + " has received the message" + " to room : " + roomname)
      out ! Json.obj("type" -> "message", "uid" -> uid, "msg" -> msg, "roomname" -> roomname)
    }
    case js: JsValue => {
      logger.debug("User uid: " + uid + " has sent the message")
      //logger.debug("Retrieving all the users mapped to the room name: " + roomName)

      val roomName = Utility.escape((js \ "roomname").as[String])

      val chatters = UserActor.roomToUidMap(roomName)
      if (chatters.count(x=> true) == 1) {
        logger.info("Only user available on chat room : " + roomName)
        //TODO : NOTIFY user about this
      }
      chatters.foreach(x => UserActor.uidToUserActor(x) ! createMessageObject(js))
    }

    case others => {
      logger.warn("User Actor instance for the user: " + uid + " has received an unexpected message")
    }
  }

  private def createMessageObject(jsValue: JsValue): Message = {
    val msg = Utility.escape((jsValue \ "msg").as[String])
    val roomName = Utility.escape((jsValue \ "roomname").as[String])
    return Message(this.uid, msg, roomName)
  }
}

case class RoomData(name: String)

case class Message(uid: String, msg: String, roomname: String)

object UserActor {

  val logger = Logger(this.getClass())
  // Mapping uid to List of Rooms
  val uidToRoomMap = HashMap[String, Set[String]]().withDefaultValue(Set())

  // Mapping room name to list of user Id
  val roomToUidMap = HashMap[String, Set[String]]().withDefaultValue(Set())
  //HashMap uid -> user Actor instance
  val uidToUserActor = HashMap[String, ActorRef]()

  // method to create an Actor for user Instance
  def props(uid: String)(out: ActorRef) = Props(new UserActor(uid, out))


}


