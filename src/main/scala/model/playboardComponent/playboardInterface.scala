package model.playboardComponent

import java.awt.Color

import controller.controllerBaseImpl.controller
import model.playboardComponent.playboardBaseImpl.RowOfCells
import model.playerComponent.Player

trait playboardInterface {

  def getindvrow(position: Int): RowOfCells

  def getrow(): Array[RowOfCells]


  def getPlayer(Position: Int): Player

  def setPlayer(Position: Int, player: Player): playboardInterface

  def getAmountofCells(): Int

  def PlayerStep(PlayerPosition:Int,input:String,Set: Boolean):playboardInterface


  def refresh(): Unit

  def refreshOne(Position:Int): Unit

  def Step(Position:Int,input:String,acontroller:controller): Unit

  def undoStep(Position:Int): Unit


}

trait CellInterface {
  def isSet:Boolean

  def getColor():Color


  def setColor(newColor:Color):CellInterface

  def setSet(): CellInterface

  def unsetSet(): CellInterface
}
