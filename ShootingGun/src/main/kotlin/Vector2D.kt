import java.lang.Math.*
import java.util.*

data class Vector2D(var posX: Double,var posY: Double) {

    val  maxSpeed = 15

    fun refreshVector(vector: Vector2D): Vector2D{
        this.posX = vector.posX + this.posX
        this.posY = vector.posY + this.posY
        return this
    }

    fun substract( vectorTemp: Vector2D): Vector2D{
        this.posX = posX - vectorTemp.posX
        this.posY = posY - vectorTemp.posY
        return this
    }
    private fun getMagnitude(): Double{
        var magnitude= Math.pow(Math.pow(posX.toDouble(), 2.0) + Math.pow(posY.toDouble(), 2.0),0.5)
        return magnitude
    }
    fun toVectorUnitario(): Vector2D{
        var newMagnitude = getMagnitude()
        var newVector2D = Vector2D(posX/newMagnitude.toInt(), posY/newMagnitude.toInt())
        return newVector2D
    }
}