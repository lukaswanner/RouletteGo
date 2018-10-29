package creative

import java.awt.Color

case class Cell(value: Int, color: Color) {

  var CellColor : Color = color

  var set : Boolean = false

  def isSet:Boolean = value != 0

  def getColor:Color = {
    return CellColor
  }


  def setColor(color:Color) = {
    CellColor = color
  }

  def setSet() = {
    set = true
  }

  override def toString = {
    val Color = CellColor
    if(CellColor.getRGB.equals(-16777216)) {
      s"The color is black."
    }else if(CellColor.getRGB.equals(-65536)) {
      s"The color is red."
    }else {
      s"The color is green."
    }
  }

}
