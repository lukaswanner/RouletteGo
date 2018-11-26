package model

import controller.{SetCommand, controller}
import util.UndoManager

case class Player(Name:String,Wallet:Int) {
  //override def toString:String = Name

  def minus(Amount:Int): Player = {
    Player(Name,Wallet-Amount)
  }

  def plus(Amount:Int): Player = {
    Player(Name,Wallet+Amount)
  }

  def getName(): String ={
    Name
  }

  def getWallet():Int = {
    Wallet
  }




}
