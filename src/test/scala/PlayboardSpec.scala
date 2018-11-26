import model.{Playboard, Player}
import org.scalatest.{Matchers, WordSpec}

class PlayboardSpec extends WordSpec with Matchers {

  "A Playboard" when {
    "given certain values " should {
      val players = new Array[Player](2)
      players(0) = new Player("lukas",1000)
      players(1) = new Player("tobi",1000)
      val playboard = Playboard(14, players)
      "have 2 rows" in {
        val row = playboard.getrow()
        row.length should be(players.length)
      }
      "give the right arrays " in {
        for(i <- 0 until playboard.getrow().length){
          var row = playboard.getindvrow(i)
          row should be (playboard.row(i))
        }
      }
      "give the right player" in {
        for(i <- 0 until playboard.getrow().length) {
          val player = playboard.getPlayer(i)
          player should be(playboard.Players(i))
        }
      }
      "give the right amount of cells" in {
        val amount = playboard.getAmountofCells()
        amount should be (playboard.AmountofCells)
      }
      "have a new player when set to " in {
        val newPlayer = Player("Hans",1000)
        val newPlayerPlayboard = playboard.setPlayer(0,newPlayer)
        newPlayerPlayboard.getPlayer(0) should be (newPlayer)
      }
    }
  }
}
