
import model.playerComponent.Player
import org.scalatest.{Matchers, WordSpec}

class PlayerSpec extends WordSpec with Matchers {

  "A Player" when {
    "given a name and value" should {
      val player = Player("Tobi", 10)
      "have name and value " in {
        player.getName() should be("Tobi")
        player.getWallet() should be (10)
      }
    }
    "wallet " should {
      val richPlayer = Player("Lukas",1000)
      "lose 100 units of it's money" in {
        val poorPlayer = richPlayer.minus(100)
        poorPlayer.getWallet() should be(900)
        poorPlayer.getName() should be("Lukas")
      }
      "gain 100 units of its money" in {
        val superRichPlayer = richPlayer.plus(100)
        superRichPlayer.getWallet() should be (1100)
        superRichPlayer.getName() should be("Lukas")
      }
    }

  }


}
