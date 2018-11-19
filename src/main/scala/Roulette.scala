
import model.{Playboard, Player}
import aview.tui

object Roulette {

  def main(args: Array[String]): Unit = {

    val r = scala.util.Random
    val start = 0
    val end = 13
    val range = end - start
    println("Wie viele Spieler habt ihr ?")
    val PlayerCount = readInt()
    var playboard = new Playboard(14,Players = new Array[Player](PlayerCount))
    playboard = playboard.setUp()
    val tui = new tui()

    do {
      var random = start + r.nextInt((end - start) + 1)
      random = random + 1
      System.out.println(random)
      do {
        playboard = playboard.run()
        println("Tippe 0 zum beenden")

      } while (readLine() != "0")
      playboard.checkforWin(random)
      println("Tippe 0 zum beenden oder beliebige Taste zum fortfahren")
      var i = 0
      for (i <- 0 until PlayerCount) {
        println(playboard.getPlayer(i) + " hat vollgende Zahlen gesetzt oder Farben gesetzt")
        tui.printTui(range, playboard.getrow(i))
      }
      playboard.refresh()
    } while (readLine() != "0")
    println("Gewinner ist : ")
  }


}



