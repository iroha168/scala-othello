package field

object Direction {
  val values: List[NumericCoordinate => Option[NumericCoordinate]] = {
    def upper(coordinate: NumericCoordinate): Option[NumericCoordinate] = next(coordinate, 0, -1)
    def upperRight(coordinate: NumericCoordinate): Option[NumericCoordinate] = next(coordinate, 1, -1)
    def upperLeft(coordinate: NumericCoordinate): Option[NumericCoordinate] = next(coordinate, -1, -1)
    def right(coordinate: NumericCoordinate): Option[NumericCoordinate] = next(coordinate, 1, 0)
    def left(coordinate: NumericCoordinate): Option[NumericCoordinate] = next(coordinate, -1, 0)
    def bottom(coordinate: NumericCoordinate): Option[NumericCoordinate] = next(coordinate, 0, 1)
    def bottomRight(coordinate: NumericCoordinate): Option[NumericCoordinate] = next(coordinate, 1, 1)
    def bottomLeft(coordinate: NumericCoordinate): Option[NumericCoordinate] = next(coordinate, -1, 1)

    def next(coordinate: NumericCoordinate, directionX: Int, directionY: Int): Option[NumericCoordinate] = {
      val x = coordinate.x + directionX
      val y = coordinate.y + directionY
      if (y < 0 || x < 0 || y > 7 || x > 7) None
      else Option(new NumericCoordinate(x, y))
    }

    List(upper, upperRight, upperLeft, right, bottom, bottomRight, bottomLeft, left)
  }
}
