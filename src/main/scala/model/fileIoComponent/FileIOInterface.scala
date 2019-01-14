package model.fileIoComponent

import model.playboardComponent.{playboardInterface}

trait FileIOInterface {

  def load: playboardInterface
  def save(playboard: playboardInterface):Unit


}
