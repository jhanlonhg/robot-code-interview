package org.hanlonjohn23

sealed trait Directions
object Directions {
  object Up extends Directions
  object Down extends Directions
  object Left extends Directions
  object Right extends Directions
}

class Robot {
  var coordinates: (Int, Int) = (0, 0)

  private def parseInstructions(instructions: String): Seq[Directions] = {
    def parseInstruction(char: Char): Option[Directions] =
      char match {
        case 'U' => Some(Directions.Up)
        case 'D' => Some(Directions.Down)
        case 'L' => Some(Directions.Left)
        case 'R' => Some(Directions.Right)
        case _ => None
      }
    instructions.flatMap(instruction => parseInstruction(instruction))
  }

  private def moveSingle(instruction: Directions): (Int, Int) = {
    instruction match {
      case Directions.Up => moveY(1)
      case Directions.Down => moveY(-1)
      case Directions.Left => moveX(-1)
      case Directions.Right => moveX(1)
    }
  }

  private def moveX(amount: Int): (Int, Int) = {
    val (x, y) = this.coordinates
    (checkBounds(x + amount), y)
  }

  private def moveY(amount: Int): (Int, Int) = {
    val (x, y) = this.coordinates
    (x, checkBounds(y + amount))
  }

  private def checkBounds(coordinate: Int): Int = {
    val BOUNDS = 10
    if (coordinate > BOUNDS)
      coordinate - (BOUNDS * 2 + 1)
    else if (coordinate < -BOUNDS)
      coordinate + (BOUNDS * 2 + 1)
    else
      coordinate
  }

  def move(instructions: String): (Int, Int) = {
    val parsedInstructions: Seq[Directions] = parseInstructions(instructions)

    for {instruction <- parsedInstructions} {
      this.coordinates = moveSingle(instruction)
    }

    coordinates
  }

}
