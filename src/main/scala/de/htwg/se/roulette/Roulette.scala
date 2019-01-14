package de.htwg.se.roulette

import aview.agui.gui
import aview.tui
import com.google.inject.Guice
import controller.ControllerInterface
import model.playboardComponent.playboardBaseImpl.Solver

object Roulette {

  def main(args: Array[String]): Unit = {

    val injector = Guice.createInjector(new RouletteModule)
    val acontroller = injector.getInstance(classOf[ControllerInterface])
    val atui = new tui(acontroller)
    var input: String = ""
    var solver: Solver = null
    val gui = new gui(acontroller)
    atui.getRandomNmbr(13)
    atui.commands()
    atui.processInputLine("create", 0)
    do {
      for (i <- 0 until acontroller.getPlayerCount()) {
       input = readLine()
       atui.processInputLine(input, i)
      }
    } while (!atui.isFinished())

  }


}
