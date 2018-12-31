
import java.awt.Color

import model.playboardComponent.playboardBaseImpl.Cell
import org.scalatest.{Matchers, WordSpec}

class CellSpec extends WordSpec with Matchers {
  "A Cell" when {
    "not set to any value " should {
      val emptyCell = Cell(0, null,false)
      "have value 0 and color null" in {
        emptyCell.value should be(0)
        emptyCell.getColor should be(null)
      }
      "not be set" in {
        emptyCell.isSet should be(false)
      }
    }
    "set to a specific value and color" should {
      val nonEmptyCell = Cell(5, Color.black,true)
      "return that value and color" in {
        nonEmptyCell.value should be(5)
        nonEmptyCell.getColor should be(Color.black)
      }
      "be set" in {
        nonEmptyCell.isSet should be(true)
      }
    }
    "an set Cell getColor" should {
      val coloredCell = Cell(1, Color.black,false)
      "respond with the color its given" in {
        coloredCell.getColor should be(Color.black)
      }
    }
    "an existing Cell" should {
      val existingCell = Cell(1, Color.black,false)
      "have the color its set after the set methode" in {
        existingCell.setColor(Color.red) should be(Cell(1,Color.red,false))
      }
    }
    "an Cell which isnt Set yet" should {
      val notSetCell = Cell(1, Color.black,true)
      "be set after calling the method" in {
        notSetCell.setSet()
        notSetCell.isSet should be(true)
      }
    }

  }

}
