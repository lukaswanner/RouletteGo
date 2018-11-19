import java.awt.Color

import org.scalatest.{Matchers, WordSpec}
import model.{Cell, RowOfCells}

class RowOfCellsSpec extends WordSpec with Matchers {
  "A RowOfCells " when {
    "not set to any value " should {
      val row:RowOfCells = new RowOfCells(null,arr = new Array[Cell](14))
      "not set at any value" in {
        row.playerColor should be(null)
        row.arr should be (null)
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
  }

}
