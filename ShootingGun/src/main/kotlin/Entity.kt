import java.awt.Graphics2D

interface Entity {

    fun paint(g2d: Graphics2D)
    fun update()
}