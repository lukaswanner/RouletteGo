package controller

import model.Player
import model.Playboard
import util.Observable
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

  def run(): Unit = {
    atui.getInput()
    println("test " + getPlayerCount())
    notifyObservers
  }

  def getRows(): Unit = {
    notifyObservers
  }


}
