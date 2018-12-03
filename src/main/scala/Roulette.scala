
import aview.tui
import controller.controller
import model.Solver
//val game = new game()
//game.runtheGame()

object Roulette {

  def main(args: Array[String]): Unit = {
    val acontroller = new controller
    val atui = new tui(acontroller)
    var input: String = ""
    var solver: Solver = null
    atui.processInputLine("create", 0)

    do {
      atui.getRandomNmbr(13)
      atui.commands()
      do {
        for (i <- 0 until acontroller.getPlayerCount()) {
          input = readLine()
          atui.processInputLine(input, i)
        }
      } while (!atui.isFinished())
      solver = new Solver(acontroller.getPlayBoard())
      solver.checkforWin(acontroller.getRandom())
      atui.continue()
      atui.update
    } while (!atui.isFinished())

  }


}



