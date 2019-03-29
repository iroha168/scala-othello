package game

import field.{Board, Direction, NumericCoordinate}
import player.PlayerPair

import scala.collection.mutable

case class Game(board: Board, pair: PlayerPair) {
  def finished: Boolean = false

  def show: Unit = board.display

  def reverse(current: NumericCoordinate, next: NumericCoordinate => Option[NumericCoordinate],
              turnable: mutable.Seq[NumericCoordinate] = mutable.Seq[NumericCoordinate]()): Board = {
    next(current) match {
      case Some(coord) =>
        if (board.cells(coord) == pair.opponentColor) reverse(coord, next, turnable :+ coord)
        else if (board.cells(coord) == pair.currentPlayerColor)
          turnable.foldLeft(board)((brd, c) => {
            brd.cells(c) = pair.currentPlayerColor
            brd
          })
        else board
      case None => board
    }
  }

  def next(move: NumericCoordinate): Board = Direction.values.foldLeft(board)((brd, d) =>
    d(move) match {
      case Some(coord) if board.cells(coord) == pair.opponentColor => {
        reverse(move, d).cells(move) = pair.currentPlayerColor
        board
      }
      case _ => brd
    })
}

