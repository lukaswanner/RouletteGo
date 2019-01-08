import java.awt.Color

import org.scalatest.{Matchers, WordSpec}
import model.playboardComponent.playboardBaseImpl.{Cell, RowOfCells}

class RowOfCellsSpec extends WordSpec with Matchers {
  "A RowOfCells " when {
    "not set to any value " should {
      val row:RowOfCells = new RowOfCells(null,arr = new Array[Cell](14))
      "not set at any value" in {
        row.playerColor should be(null)
        row.arr.length should be(14)
      }
    }
    "given a value " should {
      val row:RowOfCells = new RowOfCells(null,arr = new Array[Cell](14))
      "respond with the corresponding color" in {
        row.setColor(14) should be(Color.green)
        row.setColor(1) should be (Color.red)
        row.setColor(2) should be(Color.black)
      }
    }
    "setting a certain amount of cells" should {
      val row:RowOfCells = new RowOfCells(null,arr = new Array[Cell](14))
      row.setAmountOfRows(4)
      "set the color of each cell with the given lenght" in {
        row.getCell(1).color should be(Color.red)
        row.getCell(2).color should be(Color.black)
        row.getCell(3).color should be(Color.red)
        row.getCell(4).color should be(Color.black)
      }
    }
    "setting the player color" should {
      val row: RowOfCells = new RowOfCells(null, arr = new Array[Cell](14))
      "have the color null to begin with" in {
        row.playerColor should be(null)
      }
      "have the color red" in {
        val redRow = row.setPlayerColor("red")
        redRow.playerColor should be(Color.red)
      }
      "have the color blac" in {
        val blackRow = row.setPlayerColor("black")
        blackRow.playerColor should be(Color.black)
      }
      "have the color green" in {
        val greenRow = row.setPlayerColor("green")
        greenRow.playerColor should be(Color.green)
      }
      "have the color null" in {
        val greenRow = row.setPlayerColor("pink")
        greenRow.playerColor should be(null)
      }
    }

    "setting a single cell" should {
      val row:RowOfCells = new RowOfCells(null,arr = new Array[Cell](14))
      row.setAmountOfRows(13)
      "have no set Cell yet" in {
        row.getCell(1).isSet should be (false)
      }
      "have one set cell" in {
        row.setCell(1)
        row.getCell(1).isSet should be (true)
      }

    }

    "unsetting a single cell" should {
      val row:RowOfCells = new RowOfCells(null,arr= new Array[Cell](14))
      row.setAmountOfRows(13)
      row.setCell(1)
      "have one set cell" in {
        row.getCell(1).isSet should be (true)
      }
      "have no set cell" in {
        row.unsetCell(1)
        row.getCell(1).isSet should be (false)
      }
    }


  }

}
