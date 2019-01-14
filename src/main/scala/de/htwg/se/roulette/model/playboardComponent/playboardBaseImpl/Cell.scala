package de.htwg.se.roulette.model.playboardComponent.playboardBaseImpl

import java.awt.Color

import de.htwg.se.roulette.model.playboardComponent.CellInterface

case class Cell(value: Int, color: Color,set:Boolean) extends CellInterface {


  def isSet:Boolean =  {
    set
  }

  def getColor():Color = {
    color
  }


  def setColor(newColor:Color):Cell = {
    Cell(value,newColor,set)
  }

  def setSet(): Cell = {
    Cell(value,color,set = true)
  }

  def unsetSet(): Cell = {
    Cell(value,color,set = false)
  }


}


