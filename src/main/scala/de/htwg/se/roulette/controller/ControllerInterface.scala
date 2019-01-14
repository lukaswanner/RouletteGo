package de.htwg.se.roulette.controller

import de.htwg.se.roulette.model.fileIoComponent.FileIOInterface
import de.htwg.se.roulette.model.playboardComponent.playboardBaseImpl.Playboard
import de.htwg.se.roulette.model.playerComponent.Player
import de.htwg.se.roulette.util.Observable

import scala.swing.Publisher

trait ControllerInterface extends Publisher{

  def createBoard(PlayerCount: Int, players: Array[Player]): Playboard

  def getRandom(): Int

  def getNewRandom(range: Int): Int

  def setRange(newRange: Int): Int

  def getPlayBoard(): Playboard

  def setPlayBoard(newPlayboard:Playboard): Playboard

  def getPlayerCount(): Int

  def Step(Position: Int,input:String): Boolean


  def resize(newRange: Int): Int

  def getRange():Int

  def createFileIO():FileIOInterface

  def undo(Position: Int): Boolean

  def redo(Position: Int): Boolean


  def createPlayer(Name: String, Wallet: Int): Player


}
