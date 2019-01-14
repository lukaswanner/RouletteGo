package de.htwg.se.roulette.model.fileIoComponent

import de.htwg.se.roulette.model.playboardComponent.{playboardInterface}

trait FileIOInterface {

  def load: playboardInterface
  def save(playboard: playboardInterface):Unit


}
