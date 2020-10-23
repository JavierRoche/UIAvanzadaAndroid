package io.keepcoding.animations

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.appcompat.app.AppCompatActivity
import io.keepcoding.advanceduiplayground.R
import kotlinx.android.synthetic.main.activity_sequences_animation.*

class SequenceAnimationsActivity: AppCompatActivity() {
    /**
     * STATIC FUNCTIONS
     **/

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, SequenceAnimationsActivity::class.java)
        }
    }


    /**
     * LIFE CYCLE
     **/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Se crea la ventana en la que colocal la UI
        setContentView(R.layout.activity_sequences_animation)

        // Definimos la UI con los elementos interactuables
        prepareButtons()
    }


    /**
     * USER INTERACTIONS
     **/

    private fun onAnimation(mode: Int) {
        // Inflamos el objeto Animator
        val animator = AnimatorInflater.loadAnimator(this, mode)
        // Le indicamos nuestra imagen como target y arrancamos la animacion
        animator?.apply {
            setTarget(linkImage)
            start()
        }
    }

    private fun onAnimationFromCode() {
        val animationSet = AnimatorSet()

        // Creamos 3 animaciones
        val rotation = ObjectAnimator.ofFloat(linkImage, "rotationX", 0.0f, 360.0f)
        rotation.duration = 500
        val scaleX = ObjectAnimator.ofFloat(linkImage, "scaleX", 1.0f, 2.0f)
        scaleX.duration = 500
        val scaleY = ObjectAnimator.ofFloat(linkImage, "scaleY", 1.0f, 2.0f)
        scaleY.duration = 500
        // Las metemos en un set secuencial o simultaneo
        //animationSet.playTogether(rotation, scaleX, scaleY)
        animationSet.playSequentially(scaleX, rotation, scaleY)
        animationSet.start()
    }

    private fun viewPropertyAnimator() {
        // Se incluyen todas las animaciones a la vez
        linkImage.animate().apply {
            duration = 1000
            interpolator = OvershootInterpolator()
            rotation(360.0f)
            scaleX(1.5f)
            scaleY(1.5f)
            translationX(200.0f)
            alpha(0.5f)
            start()
        }
    }


    /**
     * PRIVATE FUNCTIONS
     **/

    private fun prepareButtons() {
        // Incluimos el evento de click al boton DESDE XML
        fromXMLButton.setOnClickListener { onAnimation(R.animator.set) }
        fromCodeButton.setOnClickListener { onAnimationFromCode() }
        propertyValuesHolder.setOnClickListener { viewPropertyAnimator() }
    }
}