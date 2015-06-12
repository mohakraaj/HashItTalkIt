package models

import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import akka.event.LoggingReceive
import play.api.Logger
import play.api.libs.json.JsValue
import play.api.libs.json.Json

import scala.collection.mutable.HashMap
import scala.xml.Utility

/** *****************************************************
  * Copyright (C) 2015 mohakraaj@gmail.com
  *
  * This file is part of models .
  *
  * This can  be copied and/or distributed without the express
  * permission of anyone
  * ******************************************************/
class UserActor(uid : String, roomName: String, out: ActorRef) extends Actor with ActorLogging {

  val logger = Logger(this.getClass())

  override def preStart =  {
    logger.info("Creating new instance of UserActor for roomName: "+ roomName + " and UID: " + uid)
    UserActor.updateRoomUserMapping(this.self , roomName)
  }

  def receive() = LoggingReceive {
    case Message(uid,msg) => {
      logger.debug("User: " + uid+ " has received the message")
      out ! Json.obj("type" -> "message", "uid" -> uid, "msg" -> msg)
    }
    case js: JsValue =>  {
      logger.debug("User uid: "+ uid +" has sent the message" )
      logger.debug("Retrieving all the users mapped to the room name: " + roomName)

      UserActor.userRoomMapping.get(roomName) match {
        case Some(users: List[ActorRef]) => {
          if (users.length ==1) {
            logger.info("Only user available on chat room : " + roomName)
            //TODO : NOTIFY user about this
          }
          users.toList.map(x => x ! createMessageObject(js))
        }
        case None => {
          logger.error("mapping for the roomname is resulting in no users")
        }
      }

    }
    case others => {
      logger.warn("User Actor instance for the user: "+ uid + " has received an unexpected message")
    }
  }

  private def createMessageObject(jsValue: JsValue): Message = {
    return Message(uid, Utility.escape((jsValue\ "msg").as[String]))
  }
}

case class RoomData(name: String)

case class Message(uid: String, msg: String)

object UserActor {

  val logger = Logger(this.getClass())

  // HashMap of (RoomName) -> List[UserActors]
  val userRoomMapping = HashMap[String, List[ActorRef]]().withDefaultValue(List())

  // method to create an Actor for user Instance
  def props(uid: String, roomName: String)(out: ActorRef) = Props(new UserActor(uid,roomName,out))

  //Method to update HashMap for roomName -> userActor
  def updateRoomUserMapping(user: ActorRef, roomName: String) = {

    logger.info("Updating user mapping for room name: " + roomName)

    //Restrain user from adding himself to multiple rooms
    if (userRoomMapping.exists(_._2.exists(_ == user))) {
      logger.error("User can participate in only one secret conversation")
    }
    else {
      // Create a new Map if roomName is not already mapped, orelse add to the existing list
        logger.info("Updating useractor to the existing mapping, for room name :" + roomName)
        userRoomMapping(roomName) = user :: userRoomMapping(roomName)
    }
  }
}


