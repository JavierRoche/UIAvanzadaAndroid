package io.keepcoding.customviews

import android.content.Context
import android.widget.LinearLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.CheckBox
import android.widget.CompoundButton
import io.keepcoding.advanceduiplayground.R

class RatingView: LinearLayout {
    // Variable que guarda el numero de estrellas
    private var rating = 0
    //
    private var numStars = 5
    // Listener para saber en que estrella se cliqueo
    private val onCheckBoxChangedListener = OnClickListener { checkBox ->
        if (checkBox.id >= 0 && checkBox.id <= childCount - 1) {
            updateRating(checkBox.id)
        }
    }

    // Variable publica que computa la variable privada rating
    private var ratingValue: Int
        get() = rating
        set(rating) {
            this.rating = rating
            // Actualizamos los checked de las estrellas con el rating actual
            updateRating(this.rating - 1)
        }

    // Inflamos un Checkbox con su inicializador de vista como Checkbox
    private val ratingView: CheckBox
        get() {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val checkBox= inflater.inflate(R.layout.rating_star_item, this, false) as CheckBox
            // Añadimos como valor de id el numero de hijas actuales. Nos servira para conocer que estrella se cliquea
            checkBox.id = childCount
            checkBox.setOnClickListener(onCheckBoxChangedListener)
            return checkBox
        }


    /**
     * CONSTRUCTOR / INIT
     **/

    // Metodo donde se inicializa la vista
    private fun init(context: Context, attrs: AttributeSet?) {
        // En el fichero res/values/attrs se han definido atributos para el RatingView
        // Los recuperamos pasando a la funcion los atributos que trae el constructor y el styleable definido
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.RatingView)

        // Recogemos de los atributos el numero de estrallas y las añadimos
        this.numStars = attributes.getInteger(R.styleable.RatingView_numStars, 5)
        addStars()

        // Recogemos de los atributos el rating inicial y se autopintaran
        this.ratingValue = attributes.getInteger(R.styleable.RatingView_rating, 1)
        // Metodo del sistema que nos permite limpiar el array
        attributes.recycle()
    }

    constructor(context: Context): super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet): super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    /**
     * PRIVATE FUNCTIONS
     **/

    // Metodo para añadir estrellas
    private fun addStars(){
        // Se incluyen las 5 estrellas en el LinearLayout que es un ViewGroup
        if (numStars != 0) {
            for (i in 0 until numStars) {
                addView(ratingView)
            }
        }
    }

    // Metodo para actualizar las estrellas. Recibe la posicion de la estrella
    private fun updateRating(position: Int) {
        // Actualizamos el rating
        this.rating = position + 1
        // El elemento childCount nos dice cuantas vistas tiene el ViewGroup
        if (position < childCount) {
            // Marcamos las estrellas como seleccionadas
            for (i in 0 until childCount) {
                // Obtenemos la vista hija que esta en determinada posicion
                (getChildAt(i) as CheckBox).isChecked = i <= position
            }
        }
    }
}