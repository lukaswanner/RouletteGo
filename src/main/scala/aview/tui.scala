package aview

import java.awt.Color

import model.{Player, RowOfCells}
import util.Observer
import controller.controller
class tui(acontroller:controller) extends Observer {

  acontroller.add(this)

  def printTui(range: Int, row: RowOfCells): Unit = {
    var i: Int = 0
    if (row.playerColor == null) {
      for (i <- 0 to range) {
        if (i != range) {
          print("+----------")
        } else {
          print("+----------+")
        }
      }
      println()

      for (i <- 0 to range + 1) {
        print("|          ")
      }
      println

      for (i <- 0 to range + 1) {
        if (i != range + 1) {
          if (!row.getCell(i + 1).set) {
            if (i > 8) {
              print("|    " + (i + 1) + "    ")
            } else {
              print("|    " + (i + 1) + "     ")
            }
          } else {
            print("|    " + "X" + "     ")
          }
        } else {
          print("|")
        }

      }
      println

      for (i <- 0 to range + 1) {
        print("|          ")
      }
      println

      for (i <- 0 to range) {
        if (i != range) {
          print("+----------")
        } else {
          print("+----------+")
        }
      }
    }
    if (row.playerColor == Color.black) {
      println("BLACK")
    }
    if (row.playerColor == Color.red) {
      println("RED")
    }
    if (row.playerColor == Color.green) {
      println("GREEN")
    }
    println("")
  }

  def getPlayerCount(): Int = {
    println("Wie viel Spieler habt ihr?")
    var Amount: Int = readInt()
    return Amount
  }

  def createPlayer(): Player = {
    println("Gib bitte Name des Spielers ein : ")
    var Name: String = readLine()
    println("Gib bitte das Geld des Spielers ein : ")
    var Geld: Int = readInt()
    val player = new Player(Name,Geld)
    return player
  }

  def getInput(): Unit = {
    for (i <- 0 until acontroller.getPlayBoard().getrow().length) {
      println(" tippe die Zahl ein auf die du tippen möchtest: ")
      var in = readLine()
      if (in.toInt != 0 && acontroller.getPlayBoard().getindvrow(i).getCell(in.toInt).set != true) {
        if (acontroller.getPlayBoard().getPlayer(i).getWallet() >= 100) {
          acontroller.getPlayBoard().setPlayer(i, acontroller.getPlayBoard().getPlayer(i).minus(100))
          println(acontroller.getPlayBoard().getPlayer(i).getWallet())
          acontroller.getPlayBoard().getindvrow(i).setCell(in.toInt)
        } else if (acontroller.getPlayBoard().getPlayer(i).getWallet() < 100) {
          println("zu wenig Geld !")
        }
      } else if (in.toInt != 0) {
        println("Zelle schon gesetzt")
      }
      if (in.toInt == 0) {
        println("0 ist keine gültige Zahl")
      }
    }
  }


  override def update: Boolean = {
    for (i<-0 until acontroller.getPlayerCount()) {
      printTui(acontroller.getPlayBoard().getAmountofCells() - 1, acontroller.getPlayBoard().getindvrow(i))
    }
    true
  }
}
