package org.hanlonjohn23

sealed trait Directions
object Directions {
  object Up extends Directions
  object Down extends Directions
  object Left extends Directions
  object Right extends Directions
}

case class Coordinates(x:Int, y:Int)

object Robot {
  private def parseInstructions(instructions: String): Seq[Directions] = {
    def parseInstruction(char: Char): Option[Directions] =
      char match {
        case 'U' => Some(Directions.Up)
        case 'D' => Some(Directions.Down)
        case 'L' => Some(Directions.Left)
        case 'R' => Some(Directions.Right)
        case _   => None
      }
    instructions.flatMap(parseInstruction)
  }

  private def moveAll(coordinates: Coordinates, instructions: Seq[Directions]): Coordinates = {
    if (instructions.isEmpty)
      coordinates
    else
      moveAll(moveSingle(coordinates, instructions.head), instructions.tail)
  }

  private def moveSingle(coordinates: Coordinates, instruction: Directions): Coordinates = {
    instruction match {
      case Directions.Up    => moveY(coordinates, 1)
      case Directions.Down  => moveY(coordinates, -1)
      case Directions.Left  => moveX(coordinates, -1)
      case Directions.Right => moveX(coordinates, 1)
    }
  }

  private def moveX(coordinates: Coordinates, amount: Int): Coordinates = {
    coordinates.copy(
      checkBounds(coordinates.x + amount),
      coordinates.y
    )
  }

  private def moveY(coordinates: Coordinates, amount: Int): Coordinates = {
    coordinates.copy(
      coordinates.x,
      checkBounds(coordinates.y + amount)
    )
  }

  private def checkBounds(coordinate: Int): Int = {
    val BOUNDS = 10
    def wrap = (bound: Int) => bound * 2 + 1

    if (coordinate > BOUNDS)
      coordinate - wrap(BOUNDS)
    else if (coordinate < -BOUNDS)
      coordinate + wrap(BOUNDS)
    else
      coordinate
  }

  def move(instructions: String): Coordinates = {
    val parsedInstructions: Seq[Directions] = parseInstructions(instructions)
    val startingCoordinates = Coordinates(0,0)

    moveAll(startingCoordinates, parsedInstructions)
  }
}
