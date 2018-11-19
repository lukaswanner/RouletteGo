package model

import java.awt.Color

case class RowOfCells(playerColor:Color,arr:Array[Cell]) {

  val r = scala.util.Random

  def setColor(amount: Int):Color = {
    if(amount % 14 == 0) {
      val green = Color.green
      return green
    }else if (amount % 2 == 0) {
      val black = Color.black
      return black
    } else {
      val red = Color.red
      return red
    }
  }

  def setAmountOfRows(amount: Int) = {
    var i: Int = 1
    for (i <- 1 until amount+1) {
      arr(i) = new Cell(i, setColor(i),false)
    }
  }

  def setPlayerColor(color: String):RowOfCells = {
    color match {
      case "red" => return RowOfCells(Color.red,arr)
      case "black" => return RowOfCells(Color.black,arr)
      case "green" => return RowOfCells(Color.green,arr)
      case default => return RowOfCells(null,arr)
    }
  }

  def setCell(number: Int): Unit = {
    arr(number) = arr(number).setSet()
  }

  def unsetCell(number:Int): Unit = {
    arr(number) = arr(number).unsetSet()
  }

  def getCell(number: Int): Cell = {
    return arr(number)
  }

}
