package model.fileIoComponent.fileIoXmlImpl

import model.fileIoComponent.FileIOInterface
import model.playboardComponent.playboardBaseImpl.Playboard
import model.playerComponent.Player

import scala.io.Source
import scala.xml.PrettyPrinter

class FileIO extends FileIOInterface {
  override def load: Playboard = {
    var playboard: Playboard = null
    val file = scala.xml.XML.loadFile("C:\\Users\\lu851wan\\IdeaProjects\\RouletteGo\\src\\main\\scala\\FILES\\test.xml")
    val playerCount = (file \\ "Playercount").text.toInt
    val playernameArray = (file \\ "Playername").text.toString
    val playerWalletArray = (file \\ "PlayerWallet").text.toString

    val Names = playernameArray.split(",")

    var Wallet = playerWalletArray.split(",")

    println(playerCount)
    println(playernameArray)
    println(playerWalletArray)

    val players = new Array[Player](playerCount)
    for (i <- 0 until playerCount.toInt) {
      var name = Names(i)
      var wallet = Wallet(i)
      println(name + " " + wallet)
      players(i) = new Player(name, wallet.toInt)
    }
    playboard = new Playboard(playerCount, players)

    playboard
  }

  override def save(playboard: Playboard): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("C:\\Users\\lu851wan\\IdeaProjects\\RouletteGo\\src\\main\\scala\\FILES\\test1.xml"))
    val prettyPrinter = new PrettyPrinter(120, 4)
    val xml = prettyPrinter.format(playboardtoXML(playboard))
    pw.write(xml)
    pw.close
  }

  def playboardtoXML(playboard: Playboard) = {
    val names = new Array[String](playboard.Players.length)
    for (i <- 0 until playboard.Players.length) {
      names(i) = playboard.Players(i).Name
    }
    <Player Playercount={ playboard.Players.length.toString } PlayerNames =
      {
      for {
        i <- 0 until playboard.Players.length
      } yield playboard.Players(i).Name.toString + ","
      }>
    </Player>
  }


}
