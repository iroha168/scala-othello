package game

import field.Board
import player.HumanPlayer

case class GameManager(preferences: GamePreferences) {
  val player1 = new HumanPlayer
  val player2 = new HumanPlayer
  val game = Game(new Board, player1, player2)
  var flg = true

  def start: Unit = {
    while (flg) {
      game.show
      flg = false
    }
  }
}
