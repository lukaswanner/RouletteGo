package creative

import java.awt.Color

case class Cell(value: Int, color: Color) {

  var CellColor : Color = color

  def isSet:Boolean = value != 0

  def getColor:Color = {
    return CellColor
  }

  def setColor(color:Color) = {
    CellColor = color
  }

}
