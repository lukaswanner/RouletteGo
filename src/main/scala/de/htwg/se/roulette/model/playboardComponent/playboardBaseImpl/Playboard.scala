package de.htwg.se.roulette.model.playboardComponent.playboardBaseImpl

import de.htwg.se.roulette.controller.controllerBaseImpl.{SetCommand, controller}
import de.htwg.se.roulette.model.playboardComponent.playboardInterface
import de.htwg.se.roulette.model.playerComponent.Player
import de.htwg.se.roulette.util.UndoManager

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

  def getPlayerCount(): Int = {
    Players.length
  }

  def getAmountofCells(): Int = {
    AmountofCells
  }

  def PlayerStep(PlayerPosition: Int, input: String, Set: Boolean): Playboard = {
    if (Set) {
      Players(PlayerPosition) = Players(PlayerPosition).minus(100)
      row(PlayerPosition).setCell(input.toInt)
      return this
    } else {
      Players(PlayerPosition) = Players(PlayerPosition).plus(100)
      row(PlayerPosition).unsetCell(input.toInt)
      return this
    }
  }


  def refresh(): Playboard = {
    val newPlayboard = copy(this.getAmountofCells(), this.Players)
    for (i <- 0 until Players.length) {
      newPlayboard.row(i).setAmountOfRows(AmountofCells)
    }
    newPlayboard
  }

  def refreshOne(Position: Int): Playboard = {
    val newPlayboard = copy(this.getAmountofCells(), this.Players)
    newPlayboard.row(Position).setAmountOfRows(AmountofCells)
    newPlayboard
  }

  def Step(Position: Int, input: String, acontroller: controller): Boolean = {
    undo(Position).doStep(new SetCommand(Position, input, acontroller))
    true
  }

  def undoStep(Position: Int): Boolean = {
    if (Position < Players.length) {
      undo(Position).undoStep()
      return true
    }
    return false
  }

  def redoStep(Position: Int): Boolean = {
    if (Position < Players.length) {
      undo(Position).redoStep
      return true
    }
    return false
  }

  def activatePlayer(Position: Int): Boolean = {
    active(Position) = false
    if (Position + 1 >= active.length) {
      active(0) = true
    } else {
      active(Position + 1) = true
    }
    for (i <- 1 until active.length) {
      if (active(i)) {
        return true
      }
    }
    return false
  }

  def getactivePlayer(): Int = {
    var player = 0
    for (i <- 0 until active.length) {
      if (active(i)) {
        player = i
      }
    }
    return player
  }
}

/*
  object Playboard {
  import play.api.libs.json._
    implicit val playWrites = Json.writes[Playboard]
    implicit val playReads = Json.reads[Playboard]
}

*/
