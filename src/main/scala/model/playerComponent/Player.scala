package model.playerComponent


case class Player(Name:String,Wallet:Int) {
  //override def toString:String = Name

  def minus(Amount: Int): Player = {
    Player(Name, Wallet - Amount)
  }

  def plus(Amount: Int): Player = {
    Player(Name, Wallet + Amount)
  }

  def getName(): String = {
    Name
  }

  def getWallet(): Int = {
    Wallet
  }

}

/*
object Player {
  import play.api.libs.json._
  implicit val playerWrites = Json.writes[Player]
  implicit val playerReads = Json.reads[Player]
}
*/