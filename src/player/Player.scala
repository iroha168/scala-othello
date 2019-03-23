package player

import field.Coordinate

trait Player {
  def nextMove: Coordinate
}

class HumanPlayer extends Player {
  override def nextMove: Coordinate = {
    print("Input your move: ")
    Coordinate(io.StdIn.readLine()).getOrElse(nextMove)
  }
}

