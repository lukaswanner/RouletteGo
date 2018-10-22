
import creative.{Player, RowOfCells}

object Roulette {

  def main(args: Array[String]): Unit = {

    val r = scala.util.Random
    val random = r.nextInt(15)
    System.out.println(random)
    var input: String = ""
    val tobi = new Player("tobi",1000)
    val row = new RowOfCells()
    row.setAmountOfRows(14)

    do {
      println("Tippe die Zahl ein auf die du tippen möchtest: ")
      input = readLine()
      var i: Int = input.toInt
      if(tobi.PlayerWallet >= 100 && i != 0 && row.getCell(i).set != true){
        tobi.minus(100)
        System.out.println(tobi.PlayerWallet)
        row.setCell(i)
        System.out.println(row.getCell(i))
      } else if(input.equals("red") || input.equals("black") || input.equals("green")) {
        tobi.minus(100)
        System.out.println(tobi.PlayerWallet)
        row.setPlayerColor(input)
      }

    } while (input.toInt != 0)
    if(row.getCell(random).set || row.playerColor.equals(row.getCell(random).getColor)) {
      tobi.plus(200)
      System.out.print(tobi.PlayerWallet)
    }
  }

}



