package model


case class Playboard(AmountofCells: Int ,Players:Array[Player]) {

  val row: Array[RowOfCells] = new Array[RowOfCells](Players.length)

  var i: Int = 0
  for (i <- 0 to row.length - 1) {
    row(i) = new RowOfCells(null,arr = new Array[Cell](AmountofCells+1))
    row(i).setAmountOfRows(AmountofCells)
  }

  def setUp(): Playboard = {
    var i: Int = 0
    for (i <- 0 until Players.length) {
      println("Gib bitte Name des Spielers ein : ")
      var Name: String = readLine()
      println("Gib bitte das Geld des Spielers ein : ")
      var Geld: Int = readInt()
      Players(i) = new Player(Name, Geld)
    }
    val aktuell = this.copy(AmountofCells,Players)
    return aktuell
  }

  def run(): Unit = {
    var i: Int = 0
    for (i <- 0 until Players.length) {
      println(getPlayer(i).getName() + " tippe die Zahl ein auf die du tippen möchtest: ")
      var in = readLine()
      if (row(i).playerColor == null) {
        if (in.equals("red") || in.equals("black") || in.equals("green")) {
          setPlayer(i,getPlayer(i).minus(100))
          println(getPlayer(i).getWallet())
          row(i).setPlayerColor(in)
        }
      }

      if (in.toInt != 0 && row(i).getCell(in.toInt).set != true) {
        if (getPlayer(i).getWallet() >= 100) {
          setPlayer(i,getPlayer(i).minus(100))
          println(getPlayer(i).getWallet())
          row(i).setCell(in.toInt)
        } else if (getPlayer(i).getWallet() < 100) {
          println("zu wenig Geld !")
        }
      } else if(in.toInt != 0){
        println("Zelle schon gesetzt")
      }
      if(in.toInt == 0) {
        println("0 ist keine gültige Zahl")
      }

    }
  }

  def checkforWin(winningNmbr: Int): Unit = {
    var i: Int = 0
    for (i <- 0 to row.length - 1) {
      //
      if (row(i).getCell(winningNmbr).set || row(i).playerColor == row(i).getCell(winningNmbr).getColor) {
        setPlayer(i,getPlayer(i).plus(200))
        println(getPlayer(i).getName() + " hat : " + getPlayer(i).getWallet() + "$$$")
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

  def setPlayer(Position:Int,player:Player) : Unit = {
    if(Position <= Players.length) {
      Players(Position) = player
    }
  }

  def refresh():Unit = {
    var i = 0
    for (i <- 0 until Players.length){
      row(i).setAmountOfRows(AmountofCells)
    }
  }

}
