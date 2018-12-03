package controller

import util.Command

class SetCommand(PlayerPosition:Int,input:String, controller: controller) extends Command {
  override def doStep: Unit =   controller.playboard = controller.playboard.PlayerStep(PlayerPosition,input,true)

  override def undoStep: Unit = controller.playboard = controller.playboard.PlayerStep(PlayerPosition,input,false)

  override def redoStep: Unit = controller.playboard = controller.playboard.PlayerStep(PlayerPosition,input,true)
}

