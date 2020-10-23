package io.keepcoding.advanceduiplayground.customviews


import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import io.keepcoding.advanceduiplayground.R

class Pizza: View {
    // Definimos un pincel
    private var paint: Paint? = null
    private var strokeWidth = DEFAULT_STROKE_WIDTH
    private var wedgets = DEFAULT_WEDGES
    private var strokeColor = DEFAULT_COLOR

    // Constantes de clase
    companion object {
        private val DEFAULT_COLOR = Color.BLACK
        private val DEFAULT_WEDGES = 5
        private val DEFAULT_STROKE_WIDTH = 200f
    }


    /**
     * CONSTRUCTOR / INIT
     **/

    private fun init(context:Context, attrs: AttributeSet?) {
        // En el fichero res/values/attrs se han definido atributos para esta clase
        // Aqui los recuperamos pasando a la funcion los atributos que trae el constructor y el styleable definido
        attrs?.let {
            val attributes = context.obtainStyledAttributes(attrs, R.styleable.Pizza)
            this.strokeWidth = attributes.getFloat(R.styleable.Pizza_stroke_width, DEFAULT_STROKE_WIDTH)
            this.wedgets = attributes.getInteger(R.styleable.Pizza_num_wedges, DEFAULT_WEDGES)
            this.strokeColor = attributes.getColor(R.styleable.Pizza_color, DEFAULT_COLOR)
            // Metodo del sistema que nos permite limpiar el array
            attributes.recycle()
        }

        // Inicializamos el pincel
        paint = Paint()
        paint?.apply {
            // Definimos caracteristicas del pincel
            color = this@Pizza.strokeColor
            strokeWidth = this@Pizza.strokeWidth
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
        // No necesitamos que el super pinte la vista puesto que lo vamos a hacer nosotros
        //super.onDraw(canvas)

        // Calculamos el lienzo
        val width = width - paddingLeft - paddingRight
        val heigth = height - paddingTop - paddingBottom
        // Calculamos el diametro y el radio
        val diameter = Math.min(width, heigth) - strokeWidth
        // Calculamos el centro en X y en Y
        val xCenter = width / 2 + paddingLeft
        val yCenter = heigth / 2 + paddingTop
        // Calculamos el radio
        val radius = diameter / 2

        paint?.let {
            // Pintamos el circulo
            canvas?.drawCircle(xCenter.toFloat(), yCenter.toFloat(), radius, it)
            // Pintamos los radios
            drawRadius(canvas, xCenter.toFloat(), yCenter.toFloat(), radius)
        }
    }


    /**
     * PRIVATE FUNCTIONS
     **/

    private fun drawRadius(canvas: Canvas?, xCenter: Float, yCenter: Float, radius: Float) {
        canvas?.save()
        val degrees = 360f / wedgets
        for (i in 0 until wedgets) {
            paint?.let {
                canvas?.apply {
                    rotate(degrees, xCenter, yCenter)
                    drawLine(xCenter, yCenter, xCenter, yCenter - radius, it)
                }
            }
        }
        // Para que vuelva a la posicion inicial
        canvas?.restore()
    }
}