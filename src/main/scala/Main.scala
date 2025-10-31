import scalafx.application.JFXApp3
import scalafx.application.JFXApp3.PrimaryStage
import scalafx.beans.property.ObjectProperty
import scalafx.scene.Scene
import scalafx.stage.Screen

import scala.language.postfixOps
import scala.util.Random

object Main extends JFXApp3 {

  override def start(): Unit = {

    val numberOfParticles: Int = 1 //1500
    val particleRadius: Int = 10
    val (boardWidth, boardHeight): (Int, Int) = (
      Screen.primary.visualBounds.width.intValue,
      Screen.primary.visualBounds.height.intValue
    )

    val particlesList: ObjectProperty[List[Particle]] = ObjectProperty(
      List.fill(numberOfParticles)(
        Particle.randomPosition(particleRadius, boardWidth, boardHeight)
      )
    )

    val particle: ObjectProperty[Particle] = ObjectProperty(
      Particle.randomPosition(particleRadius, boardWidth, boardHeight)
    )

    stage = new PrimaryStage {
      title = "Particles Simulator"
      width = boardWidth
      height = boardHeight
      scene = new Scene {
        content = particlesList.value.map(_.draw)
      }
    }
  }
}