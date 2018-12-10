package aview

import java.awt.Color

import controller.controllerBaseImpl.controller
import _root_.controller.CellChanged
import util.Observer
import model.playboardComponent.playboardBaseImpl.{RowOfCells, Solver}
import model.playerComponent.Player

import scala.swing.Reactor

class tui(acontroller: controller) extends Reactor {

  listenTo(acontroller)
  var Amount = 0
  var finished = false

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
    Amount = readInt()
    return Amount
  }

  def createPlayer(Amount:Int): Array[Player] = {
    val players = new Array[Player](Amount)
    for(i <- 0 until Amount) {
      println("Gib bitte Name des Spielers ein : ")
      var Name: String = readLine()
      println("Gib bitte das Geld des Spielers ein : ")
      var Wallet: Int = readInt()
      players(i) = acontroller.createPlayer(Name,Wallet)
    }
    return players
  }

  def getInput(Position:Int): String = {
    println("tippe die Zahl ein auf die du tippen möchtest: ")
    var in = readLine()
    if (in.toInt == 0) {
      println("0 ist keine gültige Zahl")
      return "0"
    }
    return in
  }

  def getRandomNmbr(Amount:Int):Unit = {
    acontroller.getNewRandom(Amount)
  }

  def isFinished():Boolean = {
    finished
  }

  def setFinished(boolean: Boolean): Boolean = {
    finished = boolean
    finished
  }

  def processInputLine(input: String,Position:Int):Unit = {
    input match {
      case "create"=> acontroller.createBoard(getPlayerCount(),createPlayer(Amount))
      case "undo" => acontroller.undo(Position)
      case "." => acontroller.resize(1)
      case "+" => acontroller.resize(4)
      case "#" => acontroller.resize(14)
      case "step" => acontroller.Step(Position,getInput(Position))
      case "exit" => setFinished(true)
      case "resize" => acontroller.resize(readLine().toInt)
      case "solve" => var solver = new Solver(acontroller.getPlayBoard())
        if(solver.checkforWin(acontroller.getRandom(),Position)){
          println(acontroller.getPlayBoard().getPlayer(Position) + "hat gewonnen!")
        }else{
          println(acontroller.getPlayBoard().getPlayer(Position) + "hat verloren!")
        }
        acontroller.getPlayBoard().refreshOne(Position)
      case "reset" => acontroller.getPlayBoard().refresh()
        setFinished(false)
      case default => println("Falsche eingabe")
      }
    }

  def commands():Unit = {
    println("create  ------> erstellt ein neues Spielbrett")
    println("undo    ------> Undo des letzten Schrittes")
    println("solve   ------> schaut ob man gewonnen hat")
    println(".       ------> das Spielbrett ist nun 1 groß")
    println("+       ------> das Spielbrett ist nun 4 groß")
    println("#       ------> das Spielbrett ist nun 14 groß")
    println("step    ------> ermöglicht auf eine Zahl zu tippen")
    println("exit    ------> zum Beenden")
    println("reset   ------> resetet die gesetzen Zahlen")
  }

  def continue(): Unit = {
    println("Zum beenden exit zum fortfahren beliebige Taste")
    if(!readLine().equals("exit")){
      processInputLine("reset",0)
      finished = false
    }
  }

  reactions += {
    case event: CellChanged => update
  }


   def update: Boolean = {
    for (i <- 0 until acontroller.getPlayerCount()) {
      println(acontroller.getPlayBoard().Players(i))
      printTui(acontroller.getPlayBoard().getAmountofCells() - 1, acontroller.getPlayBoard().getindvrow(i))
    }
    true
  }
}
