import scalafx.animation.{KeyFrame, Timeline}
import scalafx.application.JFXApp3
import scalafx.application.JFXApp3.PrimaryStage
import scalafx.beans.property.ObjectProperty
import scalafx.scene.Scene
import scalafx.stage.Screen
import scalafx.util.Duration
import scala.language.postfixOps

object Main extends JFXApp3 {

  override def start(): Unit = {

    val numberOfParticles: Int = 1500 //1500
    val particleRadius: Int = 3
    val (boardWidth, boardHeight): (Int, Int) = (
      Screen.primary.visualBounds.width.intValue,
      Screen.primary.visualBounds.height.intValue
    )

    val particlesList: ObjectProperty[List[(Particle, Direction)]] = ObjectProperty(
      List.fill(numberOfParticles)(
        (Particle.randomPosition(particleRadius, boardWidth, boardHeight), Particle.randomDirection)
      )
    )

    def updateParticles(particles: List[(Particle, Direction)]): List[(Particle, Direction)] = {
      val movedParticles = particles.map { case (particle, direction) =>
        val movedParticle = particle.move(direction, boardWidth, boardHeight, particleRadius)
        (movedParticle, direction)
      }

      movedParticles.map { case (particle, direction) =>
        val otherParticles = movedParticles.map(_._1).filter(_ != particle)
        if (particle.hasCollision(otherParticles, particleRadius * 2)) {
          (particle, Particle.randomDirection)
        } else {
          (particle, direction)
        }
      }
    }

    stage = new PrimaryStage {
      title = "Particles Simulator"
      width = boardWidth
      height = boardHeight
      scene = new Scene {
        content = particlesList.value.map(_._1.draw)
        particlesList.onChange {
          content = particlesList.value.map(_._1.draw)
        }
      }
    }

    new Timeline {
      keyFrames = List(
        KeyFrame(
          time = Duration(50),
          onFinished = _ => {
            particlesList.update(updateParticles(particlesList.value))
          }
        )
      )
      cycleCount = Timeline.Indefinite
    }.play()
  }
}

