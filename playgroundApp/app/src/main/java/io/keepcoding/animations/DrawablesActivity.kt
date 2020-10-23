package io.keepcoding.animations

import android.animation.Animator
import android.content.Context
import android.content.Intent
import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.keepcoding.advanceduiplayground.R
import kotlinx.android.synthetic.main.activity_drawable_animations.*


class DrawablesActivity: AppCompatActivity() {
    /**
     * STATIC FUNCTIONS
     **/

    companion object {
        // Metodo estatico que devuelve el Intent de una CustomViewsActivity con un CustomViewActivityMode
        fun getIntent(context: Context): Intent {
            return Intent(context, DrawablesActivity::class.java)
        }
    }


    /**
     * LIFE CYCLE
     **/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Se crea la ventana en la que colocal la UI
        setContentView(R.layout.activity_drawable_animations)

        // Creamos el listener para los botones
        setButtons()
    }


    /**
     * USER INTERACTIONS
     **/


    private fun setWifiAnimation() {
        // Asignamos a la imagen el recurso de lista de animacion
        image.setImageResource(R.drawable.wifi_animation)
        // Creamos un Animator a partir del elemento anterior
        val animator = image.drawable as AnimationDrawable
        // Iniciamos la animacion
        animator.start()
    }

    private fun setVectorAnimation() {
        // Asignamos a la imagen el recurso de lista de animacion
        image.setImageResource(R.drawable.avd_anim_alarm)
        // Creamos un Animator a partir del elemento anterior
        val animator = image.drawable as AnimatedVectorDrawable
        // Iniciamos la animacion
        animator.start()
    }


    /**
     * PRIVATE FUNCTIONS
     **/

    private fun setButtons() {
        drawableAnimationButton.setOnClickListener { setWifiAnimation() }
        vectorDrawableAnimation.setOnClickListener { setVectorAnimation() }
    }

    /**
     * ANIMATE VECTORS
     *
     * En la web https://shapeshifter.design
     * arrastramos un drawable de tipo vector
     * Le damos al reloj y seleccionamos pathData
     * Nos aparecera el fromValue con los datos de la primera imagen vector
     * Incluimos los datos de la segunda imagen vector
     * Con la varita magina adecuamos la transicion entre imagenes
     * Cuando este terminada la exportamos como Animatedd Vector Drawable
     **/
}