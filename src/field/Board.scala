package field

import scala.collection.SortedMap
import scala.collection.immutable.TreeMap

case class Coordinate(x: Int, y: Int)

object Coordinate {
  private val horizontalAlpha = Set('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h')

  private def validateCord(cord: String): Boolean = {
    val yChar = cord(1)
    if (Character.isDigit(yChar)) {
      val yInt = yChar.asDigit
      horizontalAlpha.contains(cord(0)) && yInt >= 1 && yInt <= 8
    }
    else false
  }

  def apply(cord: String): Option[Coordinate] =
    if (cord.size != 2) None
    else if (validateCord(cord)) Some(new Coordinate(cord(0).toInt, cord(1).toInt))
    else None
}

sealed class Status(val symbol: String)

object Black extends Status("x")

object White extends Status("o")

object Empty extends Status(".")

class Board(private[this] val cells: SortedMap[Coordinate, Status] = Board.initCells) {
  def display: Unit =
    for {row <- 0 to 7; col <- 0 to 7} yield new Coordinate(row, col) match {
      case coord@Coordinate(_, 7) => println(" " + cells(coord).symbol + " ")
      case coord@_ => print(" " + cells(coord).symbol + " ")
    }
}

object Board {
  implicit val ordering = new Ordering[Coordinate] {
    def compare(a: Coordinate, b: Coordinate) = {
      if (a.x != b.x) implicitly[Ordering[Int]].compare(a.x, b.x)
      else implicitly[Ordering[Int]].compare(a.y, b.y)
    }
  }

  def initCells: SortedMap[Coordinate, Status] = TreeMap(
    (for {row <- 0 to 7; col <- 0 to 7} yield (row, col) match {
      case (3, 4) => (Coordinate(row, col), Black)
      case (4, 3) => (Coordinate(row, col), Black)
      case (3, 3) => (Coordinate(row, col), White)
      case (4, 4) => (Coordinate(row, col), White)
      case _ => (Coordinate(row, col), Empty)
    }): _*)
}
