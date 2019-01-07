package model.playboardComponent.playboardBaseImpl

import controller.controllerBaseImpl.{SetCommand, controller}
import model.playboardComponent.playboardInterface
import model.playerComponent.Player
import util.UndoManager

case class Playboard(AmountofCells: Int, Players: Array[Player]) extends playboardInterface {

  val row: Array[RowOfCells] = new Array[RowOfCells](Players.length) //die Spielfelder der verschiedenen Spieler
  val undo: Array[UndoManager] = new Array[UndoManager](Players.length)
  val active: Array[Boolean] = new Array[Boolean](Players.length)

  for (i <- 0 until undo.length) {
    undo(i) = new UndoManager
  }

  for (i <- 0 until active.length) {
    active(i) = false
  }

  for (i <- 0 to row.length - 1) {
    row(i) = new RowOfCells(null, arr = new Array[Cell](AmountofCells + 1))
    row(i).setAmountOfRows(AmountofCells)
  }

  def getindvrow(position: Int): RowOfCells = { //return the row corresponding to the position. row is explained above
    row(position)
  }

  def getrow(): Array[RowOfCells] = { //return the row corresponding to the position. row is explained above
    row
  }


  def getPlayer(Position: Int): Player = {
    if (Position <= Players.length) {
      return Players(Position)
    }
    return null
  }

  def setPlayer(Position: Int, player: Player): Playboard = {
    if (Position <= Players.length) {
      Players(Position) = player
    }
    return Playboard(AmountofCells, Players)
  }

  def getAmountofCells(): Int = {
    AmountofCells
  }

  def PlayerStep(PlayerPosition: Int, input: String, Set: Boolean): Playboard = {
    if (Set) {
      Players(PlayerPosition) = Players(PlayerPosition).minus(100)
      println("Wallet: " + getPlayer(PlayerPosition).getWallet())
      row(PlayerPosition).setCell(input.toInt)
      return this
    } else {
      Players(PlayerPosition) = Players(PlayerPosition).plus(100)
      println("Wallet: " + getPlayer(PlayerPosition).getWallet())
      row(PlayerPosition).unsetCell(input.toInt)
      return this
    }
  }


  def refresh(): Unit = {
    for (i <- 0 until Players.length) {
      row(i).setAmountOfRows(AmountofCells)
    }
  }

  def refreshOne(Position: Int): Unit = {
    row(Position).setAmountOfRows(AmountofCells)

  }

  def Step(Position: Int, input: String, acontroller: controller): Unit = {
    undo(Position).doStep(new SetCommand(Position, input, acontroller))
  }

  def undoStep(Position: Int): Unit = {
    undo(Position).undoStep()
  }

  def activatePlayer(Position: Int): Boolean = {
    active(Position) = false
    if (Position + 1 >= active.length) {
      active(0) = true
    } else {
      active(Position + 1) = true
    }
    true
  }

  def getactivePlayer(): Int = {
    for (i <- 0 until active.length) {
      if (active(i)) {
        return i
      }
    }
    return 0
  }
}


  object Playboard {
  import play.api.libs.json._
    implicit val playWrites = Json.writes[Playboard]
    implicit val playReads = Json.reads[Playboard]
}
