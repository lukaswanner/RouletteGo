package de.htwg.se.roulette



import com.google.inject.AbstractModule
import controller.ControllerInterface
import de.htwg.se.roulette.model.playboardComponent.playboardInterface
import model.fileIoComponent.FileIOInterface
import net.codingwell.scalaguice.ScalaModule


class RouletteModule extends AbstractModule with ScalaModule {


  override def configure(): Unit = {

    bind[ControllerInterface].to[controller.controllerBaseImpl.controller]
    bind[FileIOInterface].toInstance(new model.fileIoComponent.fileIoJsonImpl.FileIO)
    //bind[FileIOInterface].toInstance(new model.fileIoComponent.fileIoXmlImpl.FileIO)

  }


}
