import scalafx.scene.paint.Color
import scalafx.scene.shape.Circle

import scala.util.Random

case class Particle(particleRadius: Int, particleX: Int, particleY: Int) {

  def randomDirection = Random.nextInt(7) match {
    case 0 => Direction.NORTH
    case 1 => Direction.SOUTH
    case 2 => Direction.EAST
    case 3 => Direction.WEST
    case 4 => Direction.NORTHEAST
    case 5 => Direction.NORTHWEST
    case 6 => Direction.SOUTHEAST
    case 7 => Direction.SOUTHWEST
  }

  def randomColor = Color(
    Random.nextDouble(),
    Random.nextDouble(),
    Random.nextDouble(),
    1
  )

  def draw: Circle =
    new Circle {
      centerX = particleX
      centerY = particleY
      radius = particleRadius
      fill = randomColor
    }
}

object Particle {
  def randomPosition(particleRadius:Int, screenWidth: Int, screenHeight: Int): Particle =
    Particle(particleRadius, Random.nextInt(screenWidth), Random.nextInt(screenHeight))
}