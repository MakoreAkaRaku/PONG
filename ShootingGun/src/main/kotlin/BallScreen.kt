import java.awt.BasicStroke
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import javax.swing.*
class BallScreen(width: Int,height: Int, val entities: List<Entity>): JPanel(){

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        var g2d = g as Graphics2D
        for (entity in entities) {
            entity.paint(g2d)
        }
        repaint()
    }
}
