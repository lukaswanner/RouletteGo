package model.fileIoComponent.fileIoJsonImpl

import model.fileIoComponent.FileIOInterface
import model.playboardComponent.playboardBaseImpl.Playboard
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

  override def save(playboard: Playboard): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("C:\\Users\\lu851wan\\IdeaProjects\\RouletteGo\\src\\main\\scala\\JSONFILE\\test.json"))
    pw.write(Json.prettyPrint(playboardtoJson(playboard)))
    pw.close
    println("finished writing")
  }

  def playboardtoJson(playboard: Playboard) = {
    val Names = new Array[String](playboard.Players.length)
    for (i <- 0 until playboard.Players.length) {
      Names(i) = playboard.Players(i).Name
    }

    val Wallet = new Array[Int](playboard.Players.length)
    for (i <- 0 until playboard.Players.length) {
      Wallet(i) = playboard.Players(i).Wallet
    }
    Json.obj(
      "playboard" -> Json.obj(
        "Playercount" -> JsNumber(playboard.Players.length),
        "Playername" -> Names.toList,
        "PlayerWallet" -> Wallet.toList
      )
    )
  }
}