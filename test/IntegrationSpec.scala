import org.junit.runner._
import org.openqa.selenium.By
import org.specs2.mutable._
import org.specs2.runner._
import play.api.test._

/**
 * add your integration spec here.
 * An integration test will fire up a whole play application in a real (or headless) browser
 */
@RunWith(classOf[JUnitRunner])
class IntegrationSpec extends PlaySpecification {

  "Application run in browser" should {

    "home page title is set" in new WithBrowser(webDriver = WebDriverFactory(play.api.test.Helpers.FIREFOX)) {
      browser.goTo("/")

      var title = webDriver.findElement(By.xpath("//head//title"))
      title.getText() mustEqual "HashItTalkIt"
    }

    "Clicking Addroom is popping form" in new WithBrowser(webDriver = WebDriverFactory(play.api.test.Helpers.FIREFOX)) {
      browser.goTo("/")

      browser.$("#roomList th").click()

      var addRoomButton = webDriver.findElement(By.xpath("//table[contains(@id,'roomList')]//th"))
      addRoomButton.click()

      browser.$(".modal-dialog").size() mustNotEqual(0)
    }

  }
}
