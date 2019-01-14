import de.htwg.se.roulette.controller.controllerBaseImpl.controller
import de.htwg.se.roulette.model.playboardComponent
import playboardComponent.playboardBaseImpl
import de.htwg.se.roulette.model.playerComponent.Player
import org.scalatest.{Matchers, WordSpec}

class PlayboardSpec extends WordSpec with Matchers {

  "A Playboard" when {
    "given certain values " should {
      val players = new Array[Player](2)
      players(0) = new Player("lukas", 1000)
      players(1) = new Player("tobi", 1000)
      val playboard = playboardBaseImpl.Playboard(14, players)
      "have 2 rows" in {
        val row = playboard.getrow()
        row.length should be(players.length)
      }
      "give the right arrays " in {
        for (i <- 0 until playboard.getrow().length) {
          var row = playboard.getindvrow(i)
          row should be(playboard.row(i))
        }
      }
      "give the right player" in {
        for (i <- 0 until playboard.getrow().length) {
          val player = playboard.getPlayer(i)
          player should be(playboard.Players(i))
        }
      }
      "be null" in {
        val player = playboard.getPlayer(8)
        player should be(null)

      }
      "give the right amount of cells" in {
        val amount = playboard.getAmountofCells()
        amount should be(playboard.AmountofCells)
      }
      "have a new player when set to " in {
        val oldplayer = playboard.getPlayer(0)
        playboard.getPlayer(0) should be (oldplayer)
        val newPlayer = Player("Hans", 1000)
        val newPlayerPlayboard = playboard.setPlayer(0, newPlayer)
        newPlayerPlayboard.getPlayer(0) should be(newPlayer)
      }
      "have the length of 2" in {
        playboard.getPlayerCount() should be(2)
      }
      "have the player Lukas pay 100 and Tobi should recieve 100" in {
        val newPlayBoardLukas = playboard.PlayerStep(0, "4", true)
        val newPlayBoardTobi = playboard.PlayerStep(1, "3", false)
        newPlayBoardLukas.getPlayer(0).Wallet should be(900)
        newPlayBoardTobi.getPlayer(1).Wallet should be(1100)
      }
      "have all the cell for each player reset so all shouldn't be set" in {
        val newPlayboard = playboard.refresh()
        for (i <- 0 until newPlayboard.getPlayerCount()) {
          for (j <- 1 until newPlayboard.getAmountofCells()) {
            var CellSet = newPlayboard.getindvrow(i).getCell(j).isSet should be(false)
          }
        }
      }

      "have all the cell for one player reset so all shouldn't be set" in {
        val newPlayboard = playboard.refreshOne(0)
        for (j <- 1 until newPlayboard.getAmountofCells()) {
          var CellSet = newPlayboard.getindvrow(0).getCell(j).isSet should be(false)
        }
      }

      "be true when stepping and undo stepping" in {
        val controller = new controller
        controller.setPlayBoard(playboard)
        playboard.Step(0, "4", controller) should be(true)
        playboard.undoStep(0) should be(true)
        playboard.redoStep(0) should be (true)
        playboard.undoStep(10) should be(false)
        playboard.redoStep(10) should be (false)
      }

      "be true the active player is 1 or higher and false if hes 0" in {
        val playerone = playboard.activatePlayer(1)
        playerone should be(false)
        playboard.getactivePlayer() should be(0)
        val playerzero = playboard.activatePlayer(0)
        playboard.getactivePlayer() should be(1)
        playerzero should be(true)
      }


    }
  }
}
