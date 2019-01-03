import model.playboardComponent.playboardBaseImpl.{Cell, Playboard, Solver}
import model.playerComponent.Player
import org.scalatest.{Matchers, WordSpec}

class SolverSpec extends WordSpec with Matchers {
  "A solver" when {
    "given the random number and the right set number" should {
      val Players = new Array[Player](1)
      Players(0) = new Player("lukas",1000)
      val newplayboard = new Playboard(14, Players)
      newplayboard.PlayerStep(0, "10", true)
      var solver = new Solver(newplayboard)
      var boolean = solver.checkforWin(10,0)
      "have value true" in {
        boolean should be(true)
      }

    }
  }

}
