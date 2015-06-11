package models

import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import akka.event.LoggingReceive
import play.api.Logger
import play.api.libs.json.JsValue

import scala.collection.mutable._
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


  override def preStart =  {
    UserActor.updateRoomUserMapping(this.self , roomName)
  }

  def receive() = LoggingReceive {
    case Message(uid,msg) => {
      //Logger.info("User: " + uid+ " has received the message")
      out ! msg
    }
    case js: JsValue =>  {
      //Logger.info("User: " + uid+ " has sent the message")
      val msg = (js \ "msg").validate[String] map {
        Utility.escape(_)
      } map {
        Message(uid, _)
      }

      UserActor.userRoomMapping.get(roomName) match {
        case users : List[ActorRef] => users.toList.map(x => x ! msg)
        case None => out ! "No one else in Room"
      }

    }
    case others => {
      Logger.warn("User Actor instance for the user: "+ uid + " has received an unexpected message")
    }
  }
}

case class RoomData(name: String)

case class Message(uid: String, msg: String)

object UserActor {
  // HashMap of (RoomName) -> List[UserActors]
  val userRoomMapping = HashMap[String, List[ActorRef]]()

  // method to create an Actor for user Instance
  def props(uid: String, roomName: String)(out: ActorRef) = Props(new UserActor(uid,roomName,out))

  //Method to update HashMap for roomName -> userActor
  def updateRoomUserMapping(user: ActorRef, roomName: String): Boolean = {
    //Restrain user from adding himself to multiple rooms
    if (userRoomMapping.exists(_._2.exists(_ == user))) {
      Logger.warn("User can participate in only one secret conversation")
      false
    }
    else {
      // Create a new Map if roomName is not already mapped, orelse add to the existing list
      if (userRoomMapping.exists(_._1 != roomName)) {
        userRoomMapping(roomName) = List(user)
      }
      else {
        userRoomMapping(roomName) = user :: userRoomMapping(roomName)
      }
      true
    }
  }
}


