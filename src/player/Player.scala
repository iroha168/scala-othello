package player

import field.{Black, Coordinate, Status, White}

trait Player {
  def nextMove: Coordinate
  def color: Status
}

class HumanPlayer(isFirst: Boolean) extends Player {
  //TODO making side effect(=printing) here is a wrong idea?
  override def nextMove: Coordinate = {
    print("Input your move: ")
    Coordinate(io.StdIn.readLine()).getOrElse(nextMove)
  }

  override def color: Status = if(isFirst) Black else White
}

