package controller

import model.playboardComponent.playboardBaseImpl.Playboard
import model.playerComponent.Player
import util.Observable

import scala.swing.Publisher

trait ControllerInterface extends Publisher{

  def createBoard(PlayerCount: Int, players: Array[Player]): Playboard

  def getRandom(): Int

  def getNewRandom(range: Int): Int

  def setRange(newRange: Int): Int

  def getPlayBoard(): Playboard

  def getPlayerCount(): Int

  def Step(Position: Int,input:String): Boolean


  def resize(newRange: Int): Int

  def getRange():Int

  def undo(Position: Int): Unit


  def createPlayer(Name: String, Wallet: Int): Player


}
