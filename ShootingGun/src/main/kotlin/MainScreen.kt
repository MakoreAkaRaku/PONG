import Game.ball
import java.awt.Dimension
import javax.swing.JFrame
import javax.swing.WindowConstants
import java.awt.event.MouseEvent
import java.awt.event.MouseMotionListener
import java.awt.Color
import java.awt.event.FocusEvent
import java.awt.event.FocusListener
import kotlin.random.Random


const val WIDTH = 1920
const val HEIGHT = 1080
/**
 * Main function, where the game initialises.
 */

object Game {

    const val bigDelay: Long = 1000L
    const val gameDelay: Long = 1000L / 60
    var userMousePosY: Int  = 0
    var ball: Ball2D = Ball2D(WIDTH,HEIGHT)
    lateinit var entities: List<Entity>
    fun init(entities: List<Entity>) {
        this.entities = entities
    }

    /**
     * Starts infinite game loop
     */
    fun start() {

        while(true) {
            updateEntities()
            Thread.sleep(gameDelay)
        }
    }

    fun updateEntities() {
        for (entity in entities){
            entity.update()
        }

    }
    fun restart(){
        ball.reset()

    }
}

fun main() {


    val entities = listOf(
            ball,
            Paddle(0,HEIGHT/2),
            Paddle(WIDTH -25, HEIGHT/2),
            Marker(WIDTH)
    )

    val ballPanel = GameFrame(WIDTH, HEIGHT, entities)
    ballPanel.background = Color(0,0,0,133)
    val size = Dimension(WIDTH, HEIGHT)
    var pantalla = MainScreen(ballPanel, size).apply {
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        isVisible = true
    }

    Game.init(entities)
    Game.start()
}

/**
 * Class of the main page, well known as the window screen of the game
 */

class MainScreen(
    private val gameFrame: BallScreen,
    prefSize: Dimension
): JFrame(), MouseMotionListener {

    init {
        gameFrame.addMouseMotionListener(this)
        preferredSize = prefSize
        add(gameFrame)
        pack()
    }

    override fun mouseMoved(p0: MouseEvent) {
        Game.userMousePosY = p0.y
    }
    override fun mouseDragged(p0: MouseEvent?) = Unit
}

/**
 * Returns the panel where the game ocurrs, including ball2d prints and player's bars.
 */
fun GameFrame(width: Int, height: Int, entities: List<Entity>) = BallScreen(width,height, entities)