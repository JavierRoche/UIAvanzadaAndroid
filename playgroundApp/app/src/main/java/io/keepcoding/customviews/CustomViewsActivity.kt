package io.keepcoding.customviews

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import io.keepcoding.advanceduiplayground.R
import io.keepcoding.advanceduiplayground.customviews.DrawingViewWithPaths
import kotlinx.android.synthetic.main.activity_custom_views.*
import io.keepcoding.advanceduiplayground.customviews.DrawingView as DrawingView

class CustomViewsActivity: AppCompatActivity(){
    /**
     * STATIC FUNCTIONS
     **/

    companion object {
        const val CUSTOM_VIEWS_MODE_KEY = "MODE_KEY"

        // Metodo estatico que devuelve el Intent de una CustomViewsActivity con un CustomViewActivityMode
        fun getIntent(context: Context, mode: CustomViewActivityMode): Intent {
            val intent = Intent(context, CustomViewsActivity::class.java)
            intent.apply {
                // Aplicamos el extra al Intent con el tipo de vista
                this.putExtra(CUSTOM_VIEWS_MODE_KEY, mode)
            }
            return intent
        }
    }


    /**
     * LIFE CYCLE
     **/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Se crea la ventana en la que colocal la UI
        setContentView(R.layout.activity_custom_views)

        // Intentamos recuperar el extra enviado desde MainActivity como CustomViewActivityMode
        val mode = intent.getSerializableExtra(CUSTOM_VIEWS_MODE_KEY) as? CustomViewActivityMode
        // Si el modo de CustomViewActivityMode se ha recibido correctamente configuramos la vista
        mode?.let{
            configureViews(it)
        }

        // Creamos el listener para el boton de borrado
        clearButton.setOnClickListener {
            onClearTapped()
        }
    }


    /**
     * USER INTERACTIONS
     **/

    private fun onClearTapped() {
        drawingView.clearScreen()
        drawingViewWithPaths.clearScreen()
    }


    /**
     * PRIVATE FUNCTIONS
     **/

    // Metodo que segun el modo seleccionado en la MainActivity muestra una vista u otra
    private fun configureViews(mode: CustomViewActivityMode) {
        hideViews()
        when(mode) {
            CustomViewActivityMode.FLAT_CUSTOM -> pizza.isVisible = true
            CustomViewActivityMode.COMPOSITE -> ratingStars.isVisible = true
            CustomViewActivityMode.TOUCH_CIRCLES -> drawingView.isVisible = true
            CustomViewActivityMode.TOUCH_PATH -> drawingViewWithPaths.isVisible = true
        }
    }

    private fun hideViews() {
        pizza.isVisible = false
        ratingStars.isVisible  = false
        drawingView.isVisible = false
        drawingViewWithPaths.isVisible = false
    }
}