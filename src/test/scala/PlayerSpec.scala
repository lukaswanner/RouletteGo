
import creative.Player
import org.scalatest.{Matchers, WordSpec}

class PlayerSpec extends WordSpec with Matchers {

  "A Player " when {
    "given a name and value" should {
      val player = Player("Tobi", 10)
      "have name and value " in {
        player.Name should be("Tobi")
        player.PlayerWallet should be (10)
      }
    }
  }


}
