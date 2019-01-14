package model.fileIoComponent.fileIoJsonImpl

import model.fileIoComponent.FileIOInterface
import model.playboardComponent.playboardBaseImpl.Playboard
import model.playboardComponent.playboardInterface
import model.playerComponent.Player
import play.api.libs.json.{JsNumber, JsString, JsValue, Json}

import scala.io._

class FileIO extends FileIOInterface {

  override def load: Playboard = {
    var playboard: Playboard = null
    val source: String = Source.fromFile("C:\\Users\\lu851wan\\IdeaProjects\\RouletteGo\\src\\main\\scala\\FILES\\test.json").getLines().mkString
    val json: JsValue = Json.parse(source)
    val playerCount = (json \ "playboard" \ "Playercount").get.toString().toInt
    val playernameArray = (json \ "playboard" \ "Playername")
    val playerWalletArray = (json \ "playboard" \ "PlayerWallet")

    val players = new Array[Player](playerCount)
    for (i <- 0 until playerCount.toInt) {
      var name = playernameArray(i).toString().replaceAll("\"", "")
      var wallet = playerWalletArray(i).toString().toInt
      println(name + " " + wallet)
      players(i) = new Player(name, wallet)
    }
    playboard = new Playboard(playerCount, players)

    playboard
  }

  override def save(playboard: playboardInterface): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("C:\\Users\\lu851wan\\IdeaProjects\\RouletteGo\\src\\main\\scala\\FILES\\test.json"))
    pw.write(Json.prettyPrint(playboardtoJson(playboard)))
    pw.close
    println("finished writing")
  }

  def playboardtoJson(playboard: playboardInterface) = {
    val Names = new Array[String](playboard.getPlayerCount())
    for (i <- 0 until playboard.getPlayerCount()) {
      Names(i) = playboard.getPlayer(i).Name
    }

    val Wallet = new Array[Int]((playboard.getPlayerCount()))
    for (i <- 0 until playboard.getPlayerCount()) {
      Wallet(i) = playboard.getPlayer(i).Wallet
    }
    Json.obj(
      "playboard" -> Json.obj(
        "Playercount" -> JsNumber(playboard.getPlayerCount()),
        "Playername" -> Names.toList,
        "PlayerWallet" -> Wallet.toList
      )
    )
  }
}