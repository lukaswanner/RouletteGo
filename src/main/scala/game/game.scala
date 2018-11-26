package game

import aview.tui
import controller.controller
import model.{Playboard, Player, Solver}

class game {

  def runtheGame():Unit = {
    val r = scala.util.Random
    val start = 0
    val end = 13
    val range = end - start
    val acontroller = new controller
    acontroller.createBoard()
    val playboard = acontroller.getPlayBoard()
    val solver = new Solver(playboard)

    do {
      var random = start + r.nextInt((end - start) + 1)
      random = random + 1
      System.out.println(random)
      do {
        acontroller.Step()
        println("Tippe 0 zum beenden")

      } while (readLine() != "0")
      solver.checkforWin(random)
      println("Tippe 0 zum beenden oder beliebige Taste zum fortfahren")
      var i = 0
      for (i <- 0 until acontroller.getPlayerCount()) {
        println(playboard.getPlayer(i) + " hat vollgende Zahlen gesetzt oder Farben gesetzt")
      }
      acontroller.getRows()
      playboard.refresh()
    } while (readLine() != "0")
    println("Gewinner ist : ")

  }
}
