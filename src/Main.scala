import game.{GameManager, GamePreferences}

object Main extends App{
  val defaultPreference = new GamePreferences
  val gameManager = GameManager(defaultPreference)
  gameManager.start
}
