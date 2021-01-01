import java.awt.Color
import java.awt.Font
import java.awt.Graphics2D
import WIDTH
class Marker (var widthS:Int): Entity {
    val markerWIDTH = 80
    val markerHEIGHT = 30
    var teamLeft = 0
    var teamRight = 0
    var markerText: String = teamLeft.toString() + " - " + teamRight.toString()
    override fun paint(g2d: Graphics2D) {
        g2d.apply {
            color = Color.WHITE
            font = Font("Robotic", Font.PLAIN, 30)
            drawRect(widthS/2 - markerWIDTH/2,0, markerWIDTH, markerHEIGHT)
            drawString(markerText,widthS/2 + 9 - markerWIDTH/2, markerHEIGHT -4)
        }
    }

    override fun update() {
        val ballTouchesLeft : Boolean = (Game.ball.position.posX - DIAMETER/2) <= 0.0
        val ballTouchesRight : Boolean = (Game.ball.position.posX + DIAMETER/2) >= WIDTH.toDouble()
        if (ballTouchesLeft){
            teamRight++
        }else if (ballTouchesRight){
            teamLeft++
        }

        
        when{
            ballTouchesLeft || ballTouchesRight -> {
                Game.restart()
                markerText = "$teamLeft - $teamRight"
            }
        }

    }
}