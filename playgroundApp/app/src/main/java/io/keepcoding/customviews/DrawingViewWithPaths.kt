package io.keepcoding.advanceduiplayground.customviews

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View


class DrawingViewWithPaths : View {
    // Definimos un pincel
    private var paint: Paint? = null
    // Definimos un path
    val path = Path()

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

        // Utilizamos el metodo para pintar path
        paint?.let {
            canvas?.drawPath(path, it)

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
        // Controlamos el tipo de evento de usuario
        when (event?.action) {
            // Si toco la pantalla trasladamos el punto de toque
            MotionEvent.ACTION_DOWN -> path.moveTo(touchX, touchY)
            // Si arrastro el dedo pintamos linea
            MotionEvent.ACTION_MOVE -> path.lineTo(touchX, touchY)
            else -> return false
        }
        // Para que se vuelva a llamar a onDraw despues de añadir el punto
        invalidate()
        return true
    }


    /**
     * PRIVATE FUNCTIONS
     **/

    fun clearScreen() {
        path.rewind()
        invalidate()
    }
}