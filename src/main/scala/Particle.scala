import scalafx.scene.paint.Color
import scalafx.scene.shape.Circle

import scala.util.Random

case class Particle(particleRadius: Int, particleX: Int, particleY: Int, color: Color) {

  def draw: Circle = {
    new Circle {
      centerX = particleX
      centerY = particleY
      radius = particleRadius
      fill = color
    }
  }
  
  def hasCollision(otherParticles: List[Particle], collisionRadius: Int): Boolean = {
    otherParticles
      .filter(_ != this)
      .exists { other =>
        val dx = other.particleX - particleX
        val dy = other.particleY - particleY
        val distanceSquared = dx * dx + dy * dy
        val minDistance = collisionRadius + collisionRadius
        distanceSquared <= minDistance * minDistance
      }
  }

  def move(direction: Direction, boardWidth: Int, boardHeight: Int, step: Int): Particle = {
    val (nx, ny) = direction match {
      case Direction.NORTH => (particleX, particleY - step)
      case Direction.SOUTH => (particleX, particleY + step)
      case Direction.EAST => (particleX + step, particleY)
      case Direction.WEST => (particleX - step, particleY)
      case Direction.NORTHEAST => (particleX + step, particleY - step)
      case Direction.NORTHWEST => (particleX - step, particleY - step)
      case Direction.SOUTHEAST => (particleX + step, particleY + step)
      case Direction.SOUTHWEST => (particleX - step, particleY + step)
    }

    val newParticleX = if (nx < 0) boardWidth + nx
                       else if (nx > boardWidth) nx - boardWidth
                       else nx

    val newParticleY = if (ny < 0) boardHeight + ny
                       else if (ny > boardHeight) ny - boardHeight
                       else ny

    copy(particleX = newParticleX, particleY = newParticleY)
  }
}

object Particle {
  def randomPosition(particleRadius: Int, screenWidth: Int, screenHeight: Int): Particle =
    Particle(particleRadius, Random.nextInt(screenWidth), Random.nextInt(screenHeight), randomColor)

  def randomDirection: Direction = Random.nextInt(8) match {
    case 0 => Direction.NORTH
    case 1 => Direction.SOUTH
    case 2 => Direction.EAST
    case 3 => Direction.WEST
    case 4 => Direction.NORTHEAST
    case 5 => Direction.NORTHWEST
    case 6 => Direction.SOUTHEAST
    case _ => Direction.SOUTHWEST
  }

  private def randomColor: Color = Color(
    Random.nextDouble(),
    Random.nextDouble(),
    Random.nextDouble(),
    1
  )
}

