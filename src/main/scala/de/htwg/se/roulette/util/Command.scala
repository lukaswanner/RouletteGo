package de.htwg.se.roulette.util

trait Command {

  def doStep:Unit
  def undoStep:Unit
  def redoStep:Unit

}
