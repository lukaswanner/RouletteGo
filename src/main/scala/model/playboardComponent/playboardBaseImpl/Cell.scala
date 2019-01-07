package model.playboardComponent.playboardBaseImpl

import java.awt.Color

import model.playboardComponent.CellInterface

case class Cell(value: Int, color: Color,set:Boolean) extends CellInterface {


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


}

object Cell {
  import play.api.libs.json._
   //val cellWrites = Json.writes[Cell]
   //val cellReads = Json.reads[Cell]
}

