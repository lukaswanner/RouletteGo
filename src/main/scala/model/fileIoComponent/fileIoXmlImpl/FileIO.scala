package model.fileIoComponent.fileIoXmlImpl

import model.fileIoComponent.FileIOInterface
import model.playboardComponent.playboardBaseImpl.Playboard
import model.playerComponent.Player

import scala.io.Source
import scala.xml.{Elem, PrettyPrinter}

class FileIO extends FileIOInterface {
  override def load: Playboard = {
    var playboard: Playboard = null
    val file = scala.xml.XML.loadFile("C:\\Users\\lu851wan\\IdeaProjects\\RouletteGo\\src\\main\\scala\\FILES\\test.xml")
    val playerCount = (file \\ "Playercount").text.toString.toInt
    val playernameArray = (file \\ "Playername").text.toString
    val playerWalletArray = (file \\ "PlayerWallet").text.toString

    val Names = playernameArray.split(",")

    var Wallet = playerWalletArray.split(",")

    val players = new Array[Player](playerCount)
    for (i <- 0 until playerCount) {
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
    val pw = new PrintWriter(new File("C:\\Users\\lu851wan\\IdeaProjects\\RouletteGo\\src\\main\\scala\\FILES\\test.xml"))
    val prettyPrinter = new PrettyPrinter(120, 4)
    val xml = prettyPrinter.format(playboardtoXML(playboard))
    pw.write(xml)
    println("finished writing xml")
    pw.close
  }

  def playboardtoXML(playboard: Playboard):Elem = {
    var strNames = new StringBuilder
    var strWallet = new StringBuilder
    for (i <- 0 until playboard.getPlayerCount()) {
      strNames.append(playboard.getPlayer(i).Name)
      strWallet.append(playboard.getPlayer(i).Wallet)
      if (playboard.getPlayerCount() > 1 && i+1 < playboard.getPlayerCount()) {
        strNames.append(",")
        strWallet.append(",")
      }
    }
    <playboard>
      <Playercount>{playboard.getPlayerCount().toString}</Playercount>
      <Playername>{strNames.toString()}</Playername>
      <PlayerWallet>{strWallet.toString()}</PlayerWallet>
    </playboard>
  }


}
