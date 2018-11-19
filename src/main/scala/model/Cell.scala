package model

import java.awt.Color

case class Cell(value: Int, color: Color,set:Boolean) {


  def isSet:Boolean =  {
    set
  }

  def getColor():Color = {
    return color
  }


  def setColor(newColor:Color):Cell = {
    Cell(value,newColor,set)
  }

  def setSet(): Cell = {
    Cell(value,color,true)
  }

  def unsetSet(): Cell = {
    Cell(value,color,false)
  }

 /* override def toString = {
    if(color.getRGB.equals(-16777216)) {
      s"The color is black."
    }else if(color.getRGB.equals(-65536)) {
      s"The color is red."
    }else {
      s"The color is green."
    }
  }
  */

}
