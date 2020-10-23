package io.keepcoding.animations

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.transition.Explode
import android.transition.Fade
import android.transition.Slide
import android.transition.TransitionInflater
import android.view.Gravity
import android.view.Window
import android.view.animation.AnticipateOvershootInterpolator
import android.view.animation.LinearInterpolator
import androidx.appcompat.app.AppCompatActivity
import io.keepcoding.advanceduiplayground.R
import kotlinx.android.synthetic.main.activity_transitions.*


class TransitionActivity: AppCompatActivity() {
    /**
     * STATIC FUNCTIONS
     **/

    companion object {
        const val KEY_TYPE = "anim_type"
        const val KEY_TITLE = "anim_title"

        // Al instanciarse estaticamente el intent de la actividad se hace con el tipo de animacion y un titulo como extras
        fun getIntent(context: Context, type: AnimType, title: String): Intent {
            val intent = Intent(context, TransitionActivity::class.java)
            return intent.apply {
                putExtra(KEY_TYPE, type)
                putExtra(KEY_TITLE, title)
            }
        }
    }

    // Enumerado de clase con los tipos de animacion que podemos desencadenar
    enum class AnimType {
        ExplodeCode, ExplodeXML, SlideCode, SlideXML, FadeCode, FadeXML
    }


    /**
     * LIFE CYCLE
     **/

    override fun onCreate(savedInstanceState: Bundle?) {
        // Siempre antes del setContentView indicamos a la ventana que tendra actividades  con transitions
        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        super.onCreate(savedInstanceState)

        // Se crea la ventana en la que colocal la UI
        setContentView(R.layout.activity_transitions)

        // Recuperamos los dos extras de comunicacion
        val type = intent.getSerializableExtra(KEY_TYPE) as AnimType
        val toolbarTitle = intent.extras?.getString(KEY_TITLE) ?: ""

        supportActionBar?.setTitle(toolbarTitle)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setUpAnimation(type)
        bindControl()

        window.allowEnterTransitionOverlap = false
    }


    /**
     * PRIVATE FUNCTIONS
     **/

    private fun bindControl() {
        exitButton.setOnClickListener { finishAfterTransition() }
    }

    override fun onSupportNavigateUp(): Boolean {
        finishAfterTransition()
        return true
    }

    private fun setUpAnimation(type: AnimType) {
        when (type) {
            AnimType.ExplodeCode -> {
                val enterTransition = Explode()
                enterTransition.duration = 1000
                window.enterTransition = enterTransition
            }
            AnimType.ExplodeXML -> {
                val enterTransition = TransitionInflater.from(this).inflateTransition(R.transition.explode)
                enterTransition.duration = 1000
                window.enterTransition = enterTransition
            }
            AnimType.SlideCode -> {
                val enterTransition = Slide()
                enterTransition.slideEdge = Gravity.RIGHT
                enterTransition.duration = 1000
                enterTransition.interpolator = LinearInterpolator()
                window.enterTransition = enterTransition
            }
            AnimType.SlideXML -> {
                val enterTransition = TransitionInflater.from(this).inflateTransition(R.transition.slide)
                window.enterTransition = enterTransition
            }
            AnimType.FadeCode -> {
                val enterTransition = Fade()
                enterTransition.duration = 1000
                window.enterTransition = enterTransition
            }
            AnimType.FadeXML -> {
                val enterTransition = TransitionInflater.from(this).inflateTransition(R.transition.fade)
                window.enterTransition = enterTransition
            }
        }
    }
}