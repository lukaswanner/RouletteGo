package creative

case class Player(Name:String,Wallet:Int) {
  var PlayerWallet = Wallet
  override def toString:String = Name

  def minus(Amount:Int): Int = {
    PlayerWallet = PlayerWallet - Amount
    return PlayerWallet
  }

  def plus(Amount:Int): Int = {
    PlayerWallet = PlayerWallet + Amount
    return PlayerWallet
  }



}
