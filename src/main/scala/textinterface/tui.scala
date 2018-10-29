package textinterface

import java.awt.Color

import creative.RowOfCells

class tui {
  def printTui(range: Int, row: RowOfCells): Unit = {
    var i: Int = 0
    if (row.playerColor == null) {
      for (i <- 0 to range) {
        if (i != range) {
          print("+----------")
        } else {
          print("+----------+")
        }
      }
      println()

      for (i <- 0 to range + 1) {
        print("|          ")
      }
      println

      for (i <- 0 to range + 1) {
        if (i != range + 1) {
          if (!row.getCell(i + 1).set) {
            if (i > 8) {
              print("|    " + (i + 1) + "    ")
            } else {
              print("|    " + (i + 1) + "     ")
            }
          } else {
            print("|    " + "X" + "     ")
          }
        } else {
          print("|")
        }

      }
      println

      for (i <- 0 to range + 1) {
        print("|          ")
      }
      println

      for (i <- 0 to range) {
        if (i != range) {
          print("+----------")
        } else {
          print("+----------+")
        }
      }
    } /* else {
      for (i <- 0 to 2) {
        if (i != 2) {
          print("+----------")
        } else {
          print("+----------+")
        }
      }
      println()

      for (i <- 0 to 3) {
        print("|          ")
      }
      println

      for (i <- 0 to 3) {
        if (i != 3) {
          if (!row.getCell(i + 1).set) {
              print("|    " + (i + 1) + "     ")
          } else {
            print("|    " + "X" + "     ")
          }
        } else {
          print("|")
        }

      }
      println

      for (i <- 0 to 3) {
        print("|          ")
      }
      println

      for (i <- 0 to 2) {
        if (i != 2) {
          print("+----------")
        } else {
          print("+----------+")
        }
      }
      */
    if (row.playerColor == Color.black) {
      println("BLACK")
    }
    if (row.playerColor == Color.red) {
      println("RED")
    }
    if (row.playerColor == Color.green) {
      println("GREEN")
    }
  }

}
