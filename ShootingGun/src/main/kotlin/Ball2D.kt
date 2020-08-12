import java.awt.BasicStroke
import java.awt.Color
import java.awt.Graphics2D
import java.util.Random

const val BASE_SPEED_PX = 10
const val BASE_ACCEL_PX = 5
const val MAX_SPEED_PX = 45
class Ball2D(val screenWidth: Int, val screenHeight: Int) : Entity {

    var currentSpeed = BASE_SPEED_PX
    var position : Vector2D
    var direction: Vector2D
    private val diameter: Int = 40

    init {
        position = Vector2D(screenWidth/2.toDouble(), screenHeight/2.toDouble())
        val directTemp = Vector2D(Random().nextInt(screenWidth).toDouble(),Random().nextInt(screenHeight).toDouble())
        direction = directTemp.toVectorUnitario()
    }

    override fun paint(g2d: Graphics2D) {
        g2d.apply {
            stroke = BasicStroke(2F)
            drawOval((position.posX - (diameter/2)).toInt(), (position.posY -(diameter/2)).toInt(),diameter,diameter)
            color = Color.RED
            fillOval((position.posX - (diameter/2)).toInt(), (position.posY -(diameter/2)).toInt(),diameter,diameter)
        }
    }
    override fun update() {
        val dx = direction.posX * currentSpeed
        val dy = direction.posY * currentSpeed
        position.apply {
            posX += dx
            posY += dy
        }
        checkForCollisions()
    }

    private fun checkForCollisions() {
        val collidesWithBottom = (position.posY + diameter/2) >= screenHeight
        val collidesWithTop = (position.posY + diameter/2) <= 0
        val collidesWithLeft = (position.posX + diameter/2) <= 0
        val collidesWithRight = (position.posX + diameter/2) >= screenWidth

        when {
            collidesWithBottom || collidesWithTop -> {
                direction.posY = direction.posY * -1

                if(currentSpeed < MAX_SPEED_PX) {
                    currentSpeed += BASE_ACCEL_PX
                }

            }
            collidesWithLeft || collidesWithRight -> {
                direction.posX = direction.posX * -1

                if(currentSpeed < MAX_SPEED_PX) {
                    currentSpeed += BASE_ACCEL_PX
                }
            }
        }
    }
}