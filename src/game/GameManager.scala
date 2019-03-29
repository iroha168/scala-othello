package game

import field.{Board, NumericCoordinate}
import player.{HumanPlayer, PlayerPair}

case class GameManager(preferences: GamePreferences) {
  val player1 = new HumanPlayer(true)
  val player2 = new HumanPlayer(false)
  val game = Game(new Board, PlayerPair(player1, player2))
  var flg = true

  def start: Unit = {
    while (flg) {
      game.show
      val move = player1.nextMove
      println(s"Your move is $move")
      game.next(move)
      flg = false
      game.show
    }
  }
}
