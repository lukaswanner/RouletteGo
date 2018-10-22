
import java.awt.Color

import creative.Cell
import org.scalatest.{Matchers, WordSpec}

class CellSpec extends WordSpec with Matchers {
  "A Cell" when {
    "not set to any value " should {
      val emptyCell = Cell(0,null)
      "have value 0 and color null" in {
        emptyCell.value should be(0)
        emptyCell.getColor should be(null)
      }
      "not be set" in {
        emptyCell.isSet should be(false)
      }
    }
    "set to a specific value and color" should {
      val nonEmptyCell = Cell(5,Color.black)
      "return that value and color" in {
        nonEmptyCell.value should be(5)
        nonEmptyCell.getColor should be(Color.black)
      }
      "be set" in {
        nonEmptyCell.isSet should be(true)
      }
    }
  }

}
