package game

import field.{Board, Direction, NumericCoordinate}
import player.PlayerPair

import scala.collection.mutable

case class Game(board: Board, pair: PlayerPair) {
  def finished: Boolean = false

  type Turnable = mutable.Seq[NumericCoordinate]

  def show: Unit = board.display

  def reverse(current: NumericCoordinate, next: NumericCoordinate => Option[NumericCoordinate],
              turnable: Turnable = mutable.Seq[NumericCoordinate](), board: Board = board): Turnable = {

    next(current) match {
      case Some(coord) =>
        if (board.cells(coord) == pair.opponentColor) reverse(coord, next, turnable :+ coord)
        else if (board.cells(coord) == pair.currentPlayerColor) {
          turnable
        }
        else mutable.Seq()
      case None => mutable.Seq()
    }
  }

  def next(move: NumericCoordinate, board: Board = board): Board = Direction.values.foldLeft(board) { (brd, d) =>
    d(move) match {
      case Some(coord) if board.cells(coord) == pair.opponentColor => {
        val turnable = reverse(move, d)
        if(!turnable.isEmpty)
          turnable.foldLeft(board)((brd, c) => {
            brd.cells(c) = pair.currentPlayerColor
            brd
          }).cells(move) = pair.currentPlayerColor
        board
      }
      case _ => brd
    }
  }

  def avaliableCoords: Set[NumericCoordinate] = Direction.values.foldLeft(Set[NumericCoordinate]()) { (turnable, d) =>
    val result = for (x <- 0 until 8; y <- 0 until 8) yield {
      val move = NumericCoordinate(x, y)
      d(move) match {
        case Some(coord) if board.cells(coord) == pair.opponentColor => {
          val list = reverse(move, d)
          if (!list.isEmpty)
            turnable + move
          else turnable
        }
        case _ => turnable
      }
    }
    result.flatten.toSet
  }
}
