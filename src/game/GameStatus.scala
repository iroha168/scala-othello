package game

import field.Board
import player.Player

case class GameStatus(board: Board, currentPlayer: Player, opponentPlayer: Player) {
}
