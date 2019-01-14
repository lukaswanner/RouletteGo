package de.htwg.se.roulette.model.playboardComponent

import java.awt.Color

import de.htwg.se.roulette.controller.controllerBaseImpl.controller
import de.htwg.se.roulette.model.playboardComponent.playboardBaseImpl.{Playboard, RowOfCells}
import de.htwg.se.roulette.model.playerComponent.Player

trait playboardInterface {

  def getindvrow(position: Int): RowOfCells

  def getrow(): Array[RowOfCells]


  def getPlayer(Position: Int): Player

  def setPlayer(Position: Int, player: Player): playboardInterface

  def getAmountofCells(): Int

  def PlayerStep(PlayerPosition:Int,input:String,Set: Boolean):playboardInterface

  def getPlayerCount():Int

  def refresh(): Playboard

  def refreshOne(Position:Int): Playboard

  def Step(Position:Int,input:String,acontroller:controller): Boolean

  def undoStep(Position:Int): Boolean


}

trait CellInterface {
  def isSet:Boolean

  def getColor():Color


  def setColor(newColor:Color):CellInterface

  def setSet(): CellInterface

  def unsetSet(): CellInterface
}
