package io.keepcoding.advanceduiplayground

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import io.keepcoding.animations.*
import io.keepcoding.customviews.CustomViewActivityMode
import io.keepcoding.customviews.CustomViewsActivity
import io.keepcoding.motionlayout.MotionLayoutActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.ratingStars

class MainActivity : AppCompatActivity() {
    /**
     * LIFE CYCLE
     **/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Se crea la ventana en la que colocal la UI
        setContentView(R.layout.activity_main)
        // Definimos la UI con los elementos interactuables
        prepareButtons()
    }


    /**
     * USER INTERACTIONS
     **/

    // Metodo que recorre segun el boton donde se ha hecho clic muestra una actividad u otra
    private fun onButtonClicked(view: View) {
        when (view) {
            ratingStars -> launchCustomViewsActivity(CustomViewActivityMode.COMPOSITE)
            canvasPizza -> launchCustomViewsActivity(CustomViewActivityMode.FLAT_CUSTOM)
            drawingViewWithCircle -> launchCustomViewsActivity(CustomViewActivityMode.TOUCH_CIRCLES)
            drawingViewWithPaths -> launchCustomViewsActivity(CustomViewActivityMode.TOUCH_PATH)

            valueAnimationsButton -> launchValueAnimatorActivity()
            sequenceAnimationsButton -> launchSequenceActivity()
            drawableAnimationsButton -> launchDrawablesActivity()
            layoutTransitionsButton -> launchTrasitionsActivity()
            activitiesTransitionsButton -> launchActivitiesTransitions()

            motionLayoutButton -> launchMotionLayout()
        }
    }


    /**
     * PRIVATE FUNCTIONS
     **/

    private fun prepareButtons() {
        // Incluimos el evento de click al boton ESTRELLAS
        ratingStars.setOnClickListener{ onButtonClicked(it) }
        // Incluimos el evento de click al boton CANVAS PIZZA
        canvasPizza.setOnClickListener{ onButtonClicked(it) }
        // Incluimos el evento de click al boton A DIBUJAR!
        drawingViewWithCircle.setOnClickListener{ onButtonClicked(it) }
        // Incluimos el evento de click al boton A DIBUJAR PATHS!
        drawingViewWithPaths.setOnClickListener{ onButtonClicked(it) }

        // Incluimos el evento de click al boton VALUE ANIMATORS
        valueAnimationsButton.setOnClickListener { onButtonClicked(it) }
        // Incluimos el evento de click al boton SECUENCIAS
        sequenceAnimationsButton.setOnClickListener { onButtonClicked(it) }
        // Incluimos el evento de click al boton DRAWABLES
        drawableAnimationsButton.setOnClickListener { onButtonClicked(it) }
        // Incluimos el evento de click al boton TRANSIC. LAYOUTS
        layoutTransitionsButton.setOnClickListener { onButtonClicked(it) }
        // Incluimos el evento de click al boton TRANSIC. ACTIVITIES
        activitiesTransitionsButton.setOnClickListener { onButtonClicked(it) }

        // Incluimos el evento de click al boton TRANSIC. ACTIVITIES
        motionLayoutButton.setOnClickListener { onButtonClicked(it) }
    }

    // Metodo que lanza una actividad CustomViewsActivity con un CustomViewActivityMode incluido en un extra
    private fun launchCustomViewsActivity(mode: CustomViewActivityMode) {
        startActivity(CustomViewsActivity.getIntent(this, mode))
    }

    private fun launchValueAnimatorActivity() {
        startActivity(ValueAnimatorActivity.getIntent(this))
    }

    private fun launchSequenceActivity() {
        startActivity(SequenceAnimationsActivity.getIntent(this))
    }

    private fun launchDrawablesActivity() {
        startActivity(DrawablesActivity.getIntent(this))
    }

    private fun launchTrasitionsActivity() {
        startActivity(LayoutTransitionsActivity.getIntent(this))
    }

    private fun launchActivitiesTransitions() {
        startActivity(ActivityTransitionsActivity.getIntent(this))
    }

    private fun launchMotionLayout() {
        startActivity(MotionLayoutActivity.getIntent(this))
    }
}
