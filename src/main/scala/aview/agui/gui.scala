package aview.agui

import controller.{CellChanged, ControllerInterface}
import model.playboardComponent.playboardBaseImpl.Solver

import scala.swing._
import scala.swing.Swing.LineBorder
import scala.swing.event._
import scala.io.Source._


class gui(controller: ControllerInterface) extends Frame {

  preferredSize = new Dimension(1400, 400)

  title = "HTWG Sudoku"
  var buttonArr: Array[Button] = new Array[Button](controller.getPlayBoard().AmountofCells + 1)
  listenTo(controller)

  def processInput(Position: Int) {

    val statusline = new TextField("hallo", 20)


    def highlightpanel = new FlowPanel {
      contents += new Label("Felder:")
      for {index <- 1 to controller.getPlayBoard().AmountofCells} {
        val button = new Button(index.toString) {
          reactions += {
            case ButtonClicked(_) => controller.Step(Position, index.toString)
              if (controller.getPlayBoard().row(Position).getCell(index).isSet == true) {
                text = "X"
                statusline.text = "Gesetzt =)"
              } else {
                statusline.text = "Kein Geld mehr =("
              }
          }
        }
        buttonArr(index) = button
        button.preferredSize_=(new Dimension(50, 50))
        contents += button
        listenTo(button)
      }
    }


    contents = new BorderPanel {
      add(highlightpanel, BorderPanel.Position.North)
      add(statusline, BorderPanel.Position.South)
    }

    menuBar = new MenuBar {
      contents += new Menu("File") {
        mnemonic = Key.F
        contents += new MenuItem(Action("New") {
          controller.getPlayBoard().refresh()
        })
        contents += new MenuItem(Action("Quit") {
          System.exit(0)
        })
      }
      contents += new Menu("Edit") {
        mnemonic = Key.E
        contents += new MenuItem(Action("Undo") {
          controller.undo(Position)
        })
        contents += new MenuItem(Action("Redo") {
          controller.undo(Position)
        })
      }
      contents += new Menu("Solve") {
        mnemonic = Key.S
        contents += new MenuItem(Action("Solve") {
          var solver = new Solver(controller.getPlayBoard())
          if (solver.checkforWin(controller.getRandom(), Position)) {
            statusline.text = "Gewonnen !"
          } else {
            statusline.text = "Verloren !"
          }
          controller.getPlayBoard().refreshOne(Position)
        })
      }
      contents += new Menu("Options") {
        mnemonic = Key.O
        contents += new MenuItem(Action("Show all candidates") {})
        contents += new MenuItem(Action("Size 1*1") {
        })
        contents += new MenuItem(Action("Size 4*4") {
        })
        contents += new MenuItem(Action("Size 9*9") {
        })

      }
    }

    reactions += {
      case e: CellChanged => redraw()
    }

    visible = true
    repaint()

  }

  def redraw() = {
    for (i <- 1 until buttonArr.length) {
      if(!controller.getPlayBoard().row(0).arr(i).isSet) {
          buttonArr(i).text = i.toString
      }
    }
    repaint()

  }

}
