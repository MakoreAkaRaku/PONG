import java.awt.BasicStroke
import java.awt.Color
import java.awt.Graphics2D
import kotlin.properties.Delegates
import kotlin.random.Random

const val BASE_SPEED_PX = 10
const val BASE_ACCEL_PX = 5
const val MAX_SPEED_PX = 45
const val DIAMETER = 40

class Ball2D(val screenWidth: Int, val screenHeight: Int) : Entity {


    var currentSpeed by Delegates.notNull<Int>()
    lateinit var position : Vector2D
    lateinit var direction: Vector2D

    init {
        initialConfig()
    }

    override fun paint(g2d: Graphics2D) {
        g2d.apply {
            stroke = BasicStroke(2F)
            drawOval((position.posX - (DIAMETER/2)).toInt(), (position.posY -(DIAMETER/2)).toInt(),DIAMETER,DIAMETER)
            color = Color.RED
            fillOval((position.posX - (DIAMETER/2)).toInt(), (position.posY -(DIAMETER/2)).toInt(),DIAMETER,DIAMETER)
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
        val collidesWithBottom = (position.posY + DIAMETER/2) >= screenHeight
        val collidesWithTop = (position.posY + DIAMETER/2) <= 0
        val collidesWithLeft = (position.posX + DIAMETER/2) <= 0
        val collidesWithRight = (position.posX + DIAMETER/2) >= screenWidth

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
    fun reset(){
        initialConfig()
    }
    private fun initialConfig(){
        currentSpeed  = BASE_SPEED_PX
        position = Vector2D(screenWidth/2.toDouble(), screenHeight/2.toDouble())
        val directTemp = Vector2D(Random.nextInt(screenWidth).toDouble(),Random.nextInt(screenHeight).toDouble())
        direction =
                directTemp
                        .toVectorUnitario()
                        .let {
                            if(Random.nextBoolean()) it.copy(posX = -it.posX) else it
                        }

    }
}