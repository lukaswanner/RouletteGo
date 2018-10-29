package creative

import creative.{Player, RowOfCells}

case class Playboard(AmountofCells: Int, PlayerCount: Int) {

  var Players: Array[Player] = new Array[Player](PlayerCount)
  val row: Array[RowOfCells] = new Array[RowOfCells](PlayerCount)
  var i: Int = 0
  for (i <- 0 to row.length - 1) {
    row(i) = new RowOfCells()
    row(i).setAmountOfRows(AmountofCells)
  }

  def setUp(): Unit = {
    var i: Int = 0
    for (i <- 0 to PlayerCount - 1) {
      println("Gib bitte Name des Spielers ein : ")
      var Name: String = readLine()
      println("Gib bitte das Geld des Spielers ein : ")
      var Geld: Int = readInt()
      Players(i) = new Player(Name, Geld)
    }

  }

  def run(): Unit = {
    var i: Int = 0
    for (i <- 0 to PlayerCount - 1) {
      println(getPlayer(i).getName() + " tippe die Zahl ein auf die du tippen möchtest: ")
      var in = readLine()

      if (row(i).playerColor == null) {
        if (in.equals("red") || in.equals("black") || in.equals("green")) {
          getPlayer(i).minus(100)
          println(getPlayer(i).PlayerWallet)
          row(i).setPlayerColor(in)
          return Unit
        }
      }

      if (row(i).getCell(in.toInt).set != true) {
        if (getPlayer(i).PlayerWallet >= 100 && in != 0) {
          getPlayer(i).minus(100)
          println(getPlayer(i).PlayerWallet)
          row(i).setCell(in.toInt)
        } else if (getPlayer(i).PlayerWallet < 100) {
          println("zu wenig Geld !")
        }
      } else {
        println("Zelle schon gesetzt")
      }

    }
  }

  def checkforWin(winningNmbr: Int): Unit = {
    var i: Int = 0
    for (i <- 0 to row.length - 1) {
      //
      if (row(i).getCell(winningNmbr).set || row(i).playerColor == row(i).getCell(winningNmbr).getColor) {
        getPlayer(i).plus(200)
        println(getPlayer(i).getName() + " hat : " + getPlayer(i).PlayerWallet + "$$$")
      }
    }
  }

  def getrow(position: Int): RowOfCells = {
    return row(position)
  }

  def getPlayer(Position: Int): Player = {
    if (Position <= Players.length) {
      return Players(Position)
    }
    return null
  }

}
