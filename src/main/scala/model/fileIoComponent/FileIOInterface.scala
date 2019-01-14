package model.fileIoComponent

import model.playboardComponent.playboardBaseImpl.Playboard

trait FileIOInterface {

  def load: Playboard
  def save(playboard: Playboard):Unit


}
