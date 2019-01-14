package model.playboardComponent.playboardBaseImpl

import model.playboardComponent.playboardInterface

class Solver(playboard: playboardInterface) {


  def checkforWin(winningNmbr: Int, Position: Int): Boolean = {
    if (playboard.getindvrow(Position).getCell(winningNmbr).set || playboard.getindvrow(Position).playerColor == playboard.getindvrow(Position).getCell(winningNmbr).getColor) {
      playboard.setPlayer(Position, playboard.getPlayer(Position).plus(200))
      return true
    }
    return false
  }

}
