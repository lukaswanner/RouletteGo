package creative

import java.awt.Color

class RowOfCells {

  var playerColor : Color = null
  var arr: Array[Cell] = null
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
    arr = new Array[Cell](amount+1)
    var i: Int = 1
    for (i <- 1 until amount+1) {
      arr(i) = new Cell(i, setColor(i))
    }
  }

  def setPlayerColor(color: String) = {
    color match {
      case "red" => playerColor = Color.red
      case "black" => playerColor = Color.black
      case "green" => playerColor = Color.green
      case default => playerColor = Color.red
    }
  }

  def setCell(number: Int) = {
    arr(number).setSet()
  }

  def getCell(number: Int): Cell = {

    return arr(number)
  }

}
