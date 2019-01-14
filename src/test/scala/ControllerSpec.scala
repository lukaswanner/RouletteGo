import de.htwg.se.roulette.controller.controllerBaseImpl.controller
import de.htwg.se.roulette.model.playerComponent.Player
import de.htwg.se.roulette.model.playboardComponent.playboardBaseImpl.Playboard
import org.scalatest.{Matchers, WordSpec}

class ControllerSpec extends WordSpec with Matchers {

  "A Controller" when {
    val controller = new controller()
    "using the method create Player" should {
      val player1 = controller.createPlayer("Lukas", 1000)
      "have the name Lukas and the Wallet shoudl be 1000"  in {
        player1.Name should be ("Lukas")
        player1.Wallet should be (1000)
      }

      val oldrange = controller.getRange()
      val newrange = controller.setRange(12)
      "oldrange should be 13(which is the default value) and the new value should be 12" in{
        oldrange should be (13)
        newrange should be (12)
      }
      val random = controller.getRandom()
      val range = controller.setRange(0)
      val newRandom = controller.getNewRandom(range)

      "random should be 0(again default value) and newRandom should be 1 since the range is 1, range it self should be 1 " in {
        random should be (0)
        newRandom should be (1)
        range should be(0)
      }

      val Players = new Array[Player](1)
      Players(0) = player1
      var playboard1 = controller.createBoard(1,Players)
      "playboard1 should have only 1 player which should be equal to player1" in{
        playboard1.getPlayer(0) should be(player1)
      }
      val playercount = controller.getPlayerCount()
      val size = controller.resize(4)
      playboard1 = controller.getPlayBoard()
      controller.resize(15)
      "playercount should be 1 and the size should be 4 playboard1 needs to be reasigned because we changed the size" in {
        playercount should be (1)
        size should be (3)
        playboard1 should not be (controller.getPlayBoard())
      }

    }
    val controller2 = new controller()
    "using the step methods " should {
      val Players = new Array[Player](1)
      Players(0) = new Player("Lukas",2000)
      val playboard = controller2.createBoard(1,Players)
      val canStep = controller2.Step(0,"10")
      "position 10 shoudl be true" in {
        canStep should be (true)
      }
      val cantStep = controller2.Step(0,"10")
      "position 10 shouldnt be true because its set already" in {
        cantStep should be(false)
      }

    }

  }
}
