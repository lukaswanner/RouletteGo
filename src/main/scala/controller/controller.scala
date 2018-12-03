package controller

import model.Player
import model.Playboard
import util.{Observable, UndoManager}
import aview.tui
//TODO controller fertig machen

class controller extends Observable {

  var playboard: Playboard = null
  var range: Int = 13
  val r = scala.util.Random
  var random: Int = 0

  def createBoard(PlayerCount: Int, players: Array[Player]): Unit = {
    playboard = new Playboard(range + 1, players)
    notifyObservers
  }


  def getRandom(): Int = {
    return random
  }

  def getNewRandom(range: Int): Int = {
    random = r.nextInt((range) + 1)
    random = random + 1
    System.out.println(random)
    return random
  }

  def setRange(newRange: Int): Int = {
    range = newRange
    return range
  }

  def getPlayBoard(): Playboard = {
    playboard
  }

  def getPlayerCount(): Int = {
    playboard.Players.length
  }

  def Step(Position: Int,input:String): Unit = {
    if (input.toInt != 0 && playboard.getindvrow(Position).getCell(input.toInt).set != true) {
      if (playboard.getPlayer(Position).getWallet() >= 100) {
        playboard.Step(Position, input, this)
        notifyObservers
      } else if (playboard.getPlayer(Position).getWallet() < 100) {
        println("zu wenig Geld !")
        notifyObservers
      }
    }
  }

  def resize(newRange: Int): Unit = {
    range = newRange
  }

  def undo(Position: Int): Unit = {
    playboard.undoStep(Position)
    notifyObservers
  }


  def createPlayer(Name: String, Wallet: Int): Player = {
    return Player(Name, Wallet)
  }

}
