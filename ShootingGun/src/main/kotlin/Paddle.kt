import java.awt.Color
import java.awt.Graphics2D
import java.awt.event.MouseEvent

class Paddle(posX: Int, posY: Int) : Entity {
    private var dimWidth: Int = 20
    private var dimHeight: Int = 100
    var paddPosX: Int
    var paddPosY: Int
    init {
        paddPosX = posX
        paddPosY = posY
    }
    /**
     * Paints de paddle in his current position.
     */
    override fun paint(g2d: Graphics2D){
        g2d.color = Color(0,0,159,60)
        g2d.fillRect(paddPosX,paddPosY -(dimHeight/2), dimWidth, dimHeight)
    }
    override fun update() {
        paddPosY = Game.userMousePosY
    }
}