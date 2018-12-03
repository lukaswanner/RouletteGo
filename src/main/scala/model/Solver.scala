package model

class Solver(playboard: Playboard) {


  def checkforWin(winningNmbr: Int): Unit = {
    for (i <- 0 to playboard.getrow().length - 1) {
      if (playboard.getindvrow(i).getCell(winningNmbr).set || playboard.getindvrow(i).playerColor == playboard.getindvrow(i).getCell(winningNmbr).getColor) {
        playboard.setPlayer(i,playboard.getPlayer(i).plus(200))
        println(playboard.getPlayer(i).getName() + " hat : " + playboard.getPlayer(i).getWallet() + "$$$")
      }
    }
  }

}
