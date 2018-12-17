package aview.agui

import controller.{CellChanged, ControllerInterface}
import model.playboardComponent.playboardBaseImpl.Solver
import util.UndoManager

import scala.swing._
import scala.swing.Swing.LineBorder
import scala.swing.event._
import scala.io.Source._


class gui(controller: ControllerInterface) extends Frame {

  preferredSize = new Dimension(1400, 400)

  title = "Roulette ist sutper"
  val Label1 = new Label()
  val statusline = new TextField("hallo", 20)
  var buttonArr: Array[Button] = new Array[Button](controller.getRange() + 2)
  listenTo(controller)

  setUp()


  reactions += {
    case e: CellChanged => redraw(controller.getPlayBoard().getactivePlayer())
  }

  //redraw(controller.getPlayBoard().getactivePlayer())
  visible = true
  repaint()


  def setUp() {


    contents = new BorderPanel {
      add(highlightpanel(), BorderPanel.Position.North)
      add(statusline, BorderPanel.Position.South)
    }

    menuBar = new MenuBar {
      contents += new Menu("File") {
        mnemonic = Key.F
        contents += new MenuItem(Action("New") {
          controller.getPlayBoard().refresh()
          redraw(controller.getPlayBoard().getactivePlayer())
        })
        contents += new MenuItem(Action("Quit") {
          System.exit(0)
        })
      }
      contents += new Menu("Edit") {
        mnemonic = Key.E
        contents += new MenuItem(Action("Undo") {
          controller.undo(controller.getPlayBoard().getactivePlayer())
        })
        contents += new MenuItem(Action("Redo") {
          controller.undo(controller.getPlayBoard().getactivePlayer())
        })
      }
      contents += new Menu("Solve") {
        mnemonic = Key.S
        contents += new MenuItem(Action("Solve") {
          var solver = new Solver(controller.getPlayBoard())
          if (solver.checkforWin(controller.getRandom(), controller.getPlayBoard().getactivePlayer())) {
            controller.getNewRandom(controller.getRange())
            statusline.text = "Gewonnen !"
          } else {
            statusline.text = "Verloren !"
          }
          for (i <- 0 until controller.getPlayBoard().Players.length) {
            controller.getPlayBoard().undo(i) = new UndoManager
          }
          controller.getPlayBoard().refreshOne(controller.getPlayBoard().getactivePlayer())
          redraw(controller.getPlayBoard().getactivePlayer())
        })
      }
      contents += new Menu("Options") {
        mnemonic = Key.O
        contents += new MenuItem(Action("Refresh") {
          redraw(controller.getPlayBoard().getactivePlayer())
        })
        contents += new MenuItem(Action("Size of 1") {
          controller.resize(1)
          highlightpanel()
          controller.getNewRandom(controller.getRange())
          println(controller.getRandom())
          setUp()
          redraw(controller.getPlayBoard().getactivePlayer())
        })
        contents += new MenuItem(Action("Size of 4") {
          controller.resize(4)
          highlightpanel()
          controller.getNewRandom(controller.getRange())
          println(controller.getRandom())
          setUp()
          redraw(controller.getPlayBoard().getactivePlayer())
        })
        contents += new MenuItem(Action("Size of 9") {
          controller.resize(9)
          highlightpanel()
          controller.getNewRandom(controller.getRange())
          println(controller.getRandom())
          setUp()
          redraw(controller.getPlayBoard().getactivePlayer())
        })

        contents += new MenuItem(Action("Size of 14") {
          controller.resize(14)
          highlightpanel()
          controller.getNewRandom(controller.getRange())
          println(controller.getRandom())
          setUp()
          redraw(controller.getPlayBoard().getactivePlayer())
        })

      }

    }
  }

  def highlightpanel() = new FlowPanel {
    contents += Label1
    for (index <- 1 to controller.getRange() + 1) {
      val button = new Button(index.toString) {
        reactions += {
          case ButtonClicked(_) => controller.Step(controller.getPlayBoard().getactivePlayer(), index.toString)
            statusline.text = "Gesetzt!"

        }
      }
      buttonArr(index) = button
      button.preferredSize_=(new Dimension(50, 50))
      contents += button
      listenTo(button)
    }

  }



  def redraw(Position: Int) = {
    highlightpanel()
    setUp()
    Label1.text = (controller.getPlayBoard().getPlayer(controller.getPlayBoard().getactivePlayer()).getName() + " hat "
      + controller.getPlayBoard().getPlayer(controller.getPlayBoard().getactivePlayer()).getWallet()
      + "$")
    for (i <- 1 to controller.getRange() + 1) {
      if (!controller.getPlayBoard().row(controller.getPlayBoard().getactivePlayer()).arr(i).isSet) {
        buttonArr(i).text = i.toString
      } else {
        buttonArr(i).text = "X"
      }
    }
    repaint()

  }

}
