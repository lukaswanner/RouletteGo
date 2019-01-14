import de.htwg.se.roulette.model.playboardComponent.playboardBaseImpl.{Cell, Playboard, Solver}
import de.htwg.se.roulette.model.playerComponent.Player
import org.scalatest.{Matchers, WordSpec}

class SolverSpec extends WordSpec with Matchers {
  "A solver" when {
    "given the random number and the right set number" should {
      val Players = new Array[Player](1)
      Players(0) = new Player("lukas",1000)
      val newplayboard = new Playboard(14, Players)
      newplayboard.PlayerStep(0, "10", true)
      val solver = new Solver(newplayboard)
      val booleanTrue = solver.checkforWin(10,0)
      "have value true" in {
        booleanTrue should be(true)
      }
      val booleanFalse = solver.checkforWin(9,0)
      "have value false" in {
        booleanFalse should be(false)
      }

    }
  }

}
