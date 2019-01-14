package controller.controllerBaseImpl

import controller.{CellChanged, ControllerInterface, GameStart}
import model.playboardComponent.playboardBaseImpl.Playboard
import model.playerComponent.Player
import util.Observable
//TODO redo implimentieren

class controller extends ControllerInterface {

  var playboard: Playboard = null
  var range: Int = 13
  val r = scala.util.Random
  var random: Int = 0

  def createBoard(PlayerCount: Int, players: Array[Player]): Playboard = {
    playboard = new Playboard(range + 1, players)
    playboard.active(0) = true
    publish(new GameStart)
    playboard
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

  def setPlayBoard(newPlayboard:Playboard): Playboard = {
    this.playboard = newPlayboard
    newPlayboard
  }

  def getPlayerCount(): Int = {
    playboard.Players.length
  }

  def Step(Position: Int, input: String): Boolean = {
    if (input.toInt != 0 && playboard.getindvrow(Position).getCell(input.toInt).set != true) {
      playboard.activatePlayer(Position)
      if (playboard.getPlayer(Position).getWallet() >= 100) {
        playboard.Step(Position, input, this)
        publish(new CellChanged)
        return true
      } else if (playboard.getPlayer(Position).getWallet() < 100) {
        println("zu wenig Geld !")
        publish(new CellChanged)
        return false
      }
    }
    return false
  }

  def resize(newRange: Int): Int = {
    range = newRange - 1
    createBoard(getPlayerCount(), getPlayBoard().Players)
    range
  }

  def getRange(): Int = {
    range
  }

  def undo(Position: Int): Unit = {
    playboard.undoStep(Position)
    publish(new CellChanged)
  }

  def redo(Position: Int): Unit = {
    playboard.redoStep(Position)
    publish(new CellChanged)
  }


  def createPlayer(Name: String, Wallet: Int): Player = {
    return Player(Name, Wallet)
  }

}
