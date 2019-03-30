package game

import field.{Board, NumericCoordinate}
import player.{HumanPlayer, PlayerPair}

import scala.util.Random

case class GameManager(preferences: GamePreferences) {
  val player1 = new HumanPlayer(true)
  val player2 = new HumanPlayer(false)
  val game = Game(new Board, PlayerPair(player1, player2))
  game.show

  def start: Unit = {
    while (!game.isFinished) {
      //      val move = player1.nextMove
      val moveList = game.avaliableCoords
      val nextMoveIndex = Random.nextInt(moveList.size)
      val move = moveList.toSeq(nextMoveIndex)
      println(s"Your move is $move")
      game.next(move)
      game.show
      game.turn
      Thread.sleep(1000)
    }
  }
}
