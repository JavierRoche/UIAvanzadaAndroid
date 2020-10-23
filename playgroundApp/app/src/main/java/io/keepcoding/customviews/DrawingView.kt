package io.keepcoding.advanceduiplayground.customviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import android.util.AttributeSet
import android.view.View
import android.view.MotionEvent

class DrawingView: View {
    // Definimos un pincel
    private var paint: Paint? = null
    // Lista de puntos
    val circlePoints: MutableList<Point> = mutableListOf()

    /**
     * CONSTRUCTOR / INIT
     **/

    private fun init(context: Context, attrs: AttributeSet?) {
        // Inicializamos el pincel
        paint = Paint()
        paint?.apply {
            // Definimos caracteristicas del pincel
            color = Color.BLACK
            strokeWidth = 50f
            style = Paint.Style.STROKE
        }
    }

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init(context, attrs)
    }


    /**
     * LIFE CYCLE
     **/

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        // Recorremos los puntos para pintar el circulo
        paint?.let {
            circlePoints.forEach { point ->
                canvas?.drawCircle(point.x.toFloat(), point.y.toFloat(), 5f, it)
            }
        }
    }


    /**
     * USER INTERACTIONS
     **/

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        // No necesitamos que el super pinte la vista puesto que lo vamos a hacer nosotros
        //return super.onTouchEvent(event)

        // Obtenemos las coordenadas del evento
        val touchX = event?.x ?: 0f
        val touchY = event?.y ?: 0f
        // Dibujamos un punto en dichas coordenadas
        circlePoints.add(Point(touchX.toInt(), touchY.toInt()))
        // Para que la lista se actualice cada vez que se añada un punto
        invalidate()

        return true
    }


    /**
     * PRIVATE FUNCTIONS
     **/

    fun clearScreen() {
        circlePoints.clear()
        invalidate()
    }
}