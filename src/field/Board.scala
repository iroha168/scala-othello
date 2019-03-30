package field

import scala.collection.{SortedMap, mutable}
import scala.collection.immutable.TreeMap

final case class NumericCoordinate(x: Int, y: Int) {
  def +(ncoord: NumericCoordinate): NumericCoordinate =
    NumericCoordinate(this.x + ncoord.x, this.y + ncoord.y)

  override def toString: String = x + " " + y
}

final case class Coordinate(str: String) {
  override def toString: String = str
}

object Coordinate {
  private[this] val horizontalAlphaNumMap = Map('a' -> 0, 'b' -> 1, 'c' -> 2, 'd' -> 3, 'e' -> 4, 'f' -> 5, 'g' -> 6, 'h' -> 7)

  implicit def toNum(coord: Coordinate): NumericCoordinate = {
    NumericCoordinate(horizontalAlphaNumMap(coord.str(0)), coord.str(1).asDigit - 1)
  }

  private[this] val horizontalAlpha = Set('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h')

  private[this] def validateCord(cord: String): Boolean = {
    val yChar = cord(1)
    if (Character.isDigit(yChar)) {
      val yInt = yChar.asDigit
      horizontalAlpha.contains(cord(0)) && yInt >= 1 && yInt <= 8
    }
    else false
  }

  def apply(cord: String): Option[Coordinate] =
    if (cord.size != 2) None
    else if (validateCord(cord)) Some(new Coordinate(cord))
    else None

}


sealed case class Status(val symbol: String)

final object Black extends Status("x")

final object White extends Status("o")

final object Empty extends Status(".")

case class Board(val cells: mutable.SortedMap[NumericCoordinate, Status] = Board.initCells) {
  def display: Unit = {
    for {y <- 0 to 7; x <- 0 to 7} yield NumericCoordinate(x, y) match {
      case coord@NumericCoordinate(7, _) => println(" " + cells(coord).symbol + " ")
      case coord => print(" " + cells(coord).symbol + " ")
    }
    println
  }

  def countEmpty: Int = cells.count(_._2 == Empty)
}

object Board {
  implicit val ordering = new Ordering[NumericCoordinate] {
    def compare(a: NumericCoordinate, b: NumericCoordinate) = {
      if (a.x != b.x) implicitly[Ordering[Int]].compare(a.x, b.x)
      else implicitly[Ordering[Int]].compare(a.y, b.y)
    }
  }

  def initCells: mutable.SortedMap[NumericCoordinate, Status] = mutable.TreeMap(
    (for {row <- 0 to 7; col <- 0 to 7} yield (row, col) match {
      case (3, 4) => (NumericCoordinate(row, col), Black)
      case (4, 3) => (NumericCoordinate(row, col), Black)
      case (3, 3) => (NumericCoordinate(row, col), White)
      case (4, 4) => (NumericCoordinate(row, col), White)
      case _ => (NumericCoordinate(row, col), Empty)
    }): _*)
}
