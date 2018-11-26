package controller

import model.Player
import model.Playboard
import util.{Observable, UndoManager}
import aview.tui
//TODO controller fertig machen

class controller extends Observable {

  val atui: tui = new tui(this)
  var playboard: Playboard = null

  def createBoard(): Unit = {
    val players = new Array[Player](atui.getPlayerCount())
    for (i <- 0 until players.length) {
      players(i) = atui.createPlayer()
    }
    playboard = new Playboard(14, players)
    notifyObservers
  }

  def getPlayBoard(): Playboard = {
    playboard
  }

  def getPlayerCount(): Int = {
    playboard.Players.length
  }

  def Step(): Unit = {
    for(i <- 0 until playboard.getrow().length) {
      var in  = atui.getInput(i)
      if (in.toInt != 0 && playboard.getindvrow(i).getCell(in.toInt).set != true) {
        if (playboard.getPlayer(i).getWallet() >= 100) {
          playboard.Step(i,in,this)
          notifyObservers
        } else if (playboard.getPlayer(i).getWallet() < 100) {
          println("zu wenig Geld !")
          notifyObservers
        }
      }
    }
  }

  def undo(Position:Int):Unit = {
    playboard.undoStep(Position)
    notifyObservers
  }

  def getRows(): Unit = {
    notifyObservers
  }


}
