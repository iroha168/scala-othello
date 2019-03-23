package game

import field.Board
import player.Player

case class Game(board: Board, player1: Player, player2: Player){
  def finished: Boolean = false
  def show: Unit = board.display
  def next: Board = ???
}
