package io.keepcoding.animations


import android.animation.Animator
import android.animation.AnimatorInflater
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.keepcoding.advanceduiplayground.R
import kotlinx.android.synthetic.main.activity_property_animations.*


class ValueAnimatorActivity : AppCompatActivity(), Animator.AnimatorListener {
    /**
     * STATIC FUNCTIONS
     **/

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, ValueAnimatorActivity::class.java)
        }
    }


    /**
     * LIFE CYCLE
     **/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Se crea la ventana en la que colocal la UI
        setContentView(R.layout.activity_property_animations)

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
            addListener(this@ValueAnimatorActivity)
            start()
        }

//        //Para crear la animacion por codigo
//        val animator = ObjectAnimator.ofFloat(linkImage, "scaleX", 1.0f, 3.0f)
//        animator.apply {
//            duration = 1000
//            repeatCount = 1
//            repeatMode = ValueAnimator.REVERSE
//            start()
//        }
    }


    /**
     * PRIVATE FUNCTIONS
     **/

    private fun prepareButtons() {
        // Incluimos el evento de click al boton ROTAR
        rotateButton.setOnClickListener { onAnimation(R.animator.rotate) }
        // Incluimos el evento de click al boton ESCALAR
        scaleButton.setOnClickListener { onAnimation(R.animator.scale) }
        // Incluimos el evento de click al boton TRASLADAR
        translationButton.setOnClickListener { onAnimation(R.animator.translate) }
        // Incluimos el evento de click al boton FADE
        fadeButton.setOnClickListener { onAnimation(R.animator.fade) }
    }


    /**
     * DELEGATE METHODS
     **/

    override fun onAnimationRepeat(p0: Animator?) {
        Toast.makeText(this, "Repeat", Toast.LENGTH_SHORT).show()
    }

    override fun onAnimationEnd(p0: Animator?) {
        Toast.makeText(this, "End", Toast.LENGTH_SHORT).show()
    }

    override fun onAnimationCancel(p0: Animator?) {
        Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show()
    }

    override fun onAnimationStart(p0: Animator?) {
        Toast.makeText(this, "Start", Toast.LENGTH_SHORT).show()
    }
}