
import aview.agui.gui
import aview.tui
import controller.controllerBaseImpl.controller
import model.playboardComponent.playboardBaseImpl.Solver
//val game = new game()
//game.runtheGame()

object Roulette {

  def main(args: Array[String]): Unit = {
    val acontroller = new controller
    val atui = new tui(acontroller)
    var input: String = ""
    var solver: Solver = null
    atui.processInputLine("create", 0)
    atui.getRandomNmbr(13)
    val gui = new gui(acontroller)
    atui.commands()
    do {
      for (i <- 0 until acontroller.getPlayerCount()) {
        gui.processInput(i)
        input = readLine()
        atui.processInputLine(input, i)
      }
    } while (!atui.isFinished())

  }


}



