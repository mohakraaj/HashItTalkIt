import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import play.api.Play
import play.api.libs.ws.WS
import play.api.test._
import play.api.test.Helpers._

/** *****************************************************
  * Copyright (C) 2015 mohakraaj@gmail.com
  *
  * This file is part of  .
  *
  * This can  be copied and/or distributed without the express
  * permission of anyone
  * ******************************************************/
@RunWith(classOf[JUnitRunner])
class ServerSpec extends PlaySpecification {

  val serverurl= "http://localhost:"

  "HomePage status is ok " in new WithServer {
    // verify that home page returns ok status
    await(WS.url(serverurl+ port).get()).status must equalTo(OK)
  }

  "AboutPage status is ok " in new WithServer {
    // Verify that about page returns ok status
    await(WS.url(serverurl+ port+"/about").get()).status must equalTo(OK)
  }

}
