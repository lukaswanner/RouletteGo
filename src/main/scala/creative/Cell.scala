package creative

import java.awt.Color

case class Cell(value: Int, color: Color) {

  def isSet:Boolean = value != 0

}
