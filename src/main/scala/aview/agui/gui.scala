package aview.agui

import controller.{CellChanged, ControllerInterface, GameStart}
import model.playboardComponent.playboardBaseImpl.Solver
import util.UndoManager
import model.playerComponent.Player

import model.fileIoComponent.fileIoXmlImpl.FileIO

import scala.swing._
import scala.swing.Swing.LineBorder
import scala.swing.event._
import scala.io.Source._


class gui(controller: ControllerInterface) extends Frame {
  preferredSize = new Dimension(1400, 400)
  title = "Roulette ist sutper"

  var frame = new MainFrame()
  val Label1 = new Label()
  val statusline = new TextField("hallo", 20)
  var buttonArr: Array[Button] = new Array[Button](controller.getRange() + 2)
  listenTo(controller)


  PlayerCount()

  reactions += {
    case e: CellChanged => redraw(controller.getPlayBoard().getactivePlayer())
    case g: GameStart => frame.close()
      Game()
      redraw(controller.getPlayBoard().getactivePlayer())
  }

  //redraw(controller.getPlayBoard().getactivePlayer())
  centerOnScreen()
  repaint()


  def Game() {


    contents = new BorderPanel {
      add(highlightpanel(), BorderPanel.Position.North)
      add(statusline, BorderPanel.Position.South)
    }

    menuBar = new MenuBar {
      contents += new Menu("File") {
        mnemonic = Key.F
        contents += new MenuItem(Action("New") {
          controller.setPlayBoard(controller.getPlayBoard().refresh())
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
          controller.redo(controller.getPlayBoard().getactivePlayer())
        })
      }
      contents += new Menu("Solve") {
        mnemonic = Key.S
        contents += new MenuItem(Action("Solve") {
          var solver = new Solver(controller.getPlayBoard())
          if (solver.checkforWin(controller.getRandom(), controller.getPlayBoard().getactivePlayer())) {
            controller.getNewRandom(controller.getRange())
            statusline.text = "Gewonnen ! Die richtige Zahl war: " + controller.getRandom()
          } else {
            statusline.text = "Verloren ! Die richtige Zahl war: " + controller.getRandom()
          }
          for (i <- 0 until controller.getPlayerCount()) {
            controller.getPlayBoard().undo(i) = new UndoManager
          }
          controller.setPlayBoard(controller.getPlayBoard().refreshOne(controller.getPlayBoard().getactivePlayer()))
          redraw(controller.getPlayBoard().getactivePlayer())
        })
      }
      contents += new Menu("Options") {
        mnemonic = Key.O
        contents += new MenuItem(Action("Refresh") {
          redraw(controller.getPlayBoard().getactivePlayer())
        })
        contents += new MenuItem(Action("read JSON") {
          val js = new FileIO
          js.load
        })
        contents += new MenuItem(Action("read XML") {
          val xml = new FileIO
          xml.load
        })
        contents += new MenuItem(Action("save JSON") {
          val js = new FileIO
          js.save(controller.getPlayBoard())
        })
        contents += new MenuItem(Action("save XML") {
          val xml = new FileIO
          xml.save(controller.getPlayBoard())
        })
        contents += new MenuItem(Action("Size of 1") {
          controller.resize(1)
          highlightpanel()
          controller.getNewRandom(controller.getRange())
          println(controller.getRandom())
          Game()
          redraw(controller.getPlayBoard().getactivePlayer())
        })
        contents += new MenuItem(Action("Size of 4") {
          controller.resize(4)
          highlightpanel()
          controller.getNewRandom(controller.getRange())
          println(controller.getRandom())
          Game()
          redraw(controller.getPlayBoard().getactivePlayer())
        })
        contents += new MenuItem(Action("Size of 9") {
          controller.resize(9)
          highlightpanel()
          controller.getNewRandom(controller.getRange())
          println(controller.getRandom())
          Game()
          redraw(controller.getPlayBoard().getactivePlayer())
        })

        contents += new MenuItem(Action("Size of 14") {
          controller.resize(14)
          highlightpanel()
          controller.getNewRandom(controller.getRange())
          println(controller.getRandom())
          Game()
          redraw(controller.getPlayBoard().getactivePlayer())
        })

      }

    }
    visible = true
    centerOnScreen()
    repaint()
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
    //highlightpanel()
    //Game()
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

  def PlayerCount(): Unit = {

    frame = new MainFrame() {
      title = "Spielereingabe"
      size = new Dimension(1400, 700)
      contents = new BoxPanel(Orientation.Vertical) {
        for (i <- 1 to 4) {
          val button = new Button(i.toString) {
            reactions += {
              case ButtonClicked(_) => println(i + " Spieler ausgewählt")
                SetupPlayer(i)
                close()
            }
          }
          contents += button
        }
        val button = new Button("exit") {
          reactions += {
            case ButtonClicked(_) => close()
          }
        }
        contents += button
        border = Swing.EmptyBorder(10, 100, 10, 100)
      }
    }
    frame.centerOnScreen()
    frame.visible = true
  }

  def SetupPlayer(PlayerCount: Int): Unit = {
    val players = new Array[Player](PlayerCount)
    var ii = 0
    val frame = new MainFrame() {
      title = "Spielereingabe"
      size = new Dimension(1400, 700)
      contents = new BoxPanel(Orientation.Vertical) {
        val textField1 = new TextField("Playername")
        val textField2 = new TextField("Walletsize")
        contents += textField1
        contents += textField2
        val button = new Button("bestätigen") {
          reactions += {
            case ButtonClicked(_) => println(textField1.text + " hat " + textField2.text + " $$$")
              players(ii) = controller.createPlayer(textField1.text, textField2.text.toInt)
              ii += 1
              if (ii == PlayerCount) {
                controller.createBoard(PlayerCount, players)
                Game()
                close()
              }
          }
        }
        contents += button

        border = Swing.EmptyBorder(10, 100, 10, 100)
      }
    }

    frame.visible = true
    frame.centerOnScreen()

  }

}
