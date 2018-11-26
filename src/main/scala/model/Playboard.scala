package model

import util.UndoManager

case class Playboard(AmountofCells: Int, Players: Array[Player]) {

  val row: Array[RowOfCells] = new Array[RowOfCells](Players.length) //die Spielfelder der verschiedenen Spieler

  var i: Int = 0
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

  def PlayerStep(PlayerPosition:Int,input:String,Set: Boolean):Playboard = {
    if(Set) {
      Players(PlayerPosition) = Players(PlayerPosition).minus(100)
      println("Wallet: " + getPlayer(PlayerPosition).getWallet())
      row(PlayerPosition).setCell(input.toInt)
      return this
    }else{
      Players(PlayerPosition) = Players(PlayerPosition).plus(100)
      println("Wallet: " + getPlayer(PlayerPosition).getWallet())
      row(PlayerPosition).unsetCell(input.toInt)
      return this
    }
  }


  def refresh(): Unit = {
    var i = 0
    for (i <- 0 until Players.length) {
      row(i).setAmountOfRows(AmountofCells)
    }
  }


}
