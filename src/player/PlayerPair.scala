package player

case class PlayerPair(currentPlayer: Player, opponent: Player) {
  def swap: PlayerPair = PlayerPair(opponent, currentPlayer)
  def currentPlayerColor = currentPlayer.color
  def opponentColor = opponent.color
}

