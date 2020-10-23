package io.keepcoding.animations

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.transition.Slide
import android.view.Gravity
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import io.keepcoding.advanceduiplayground.R
import kotlinx.android.synthetic.main.activity_transitions_activities.*
import kotlinx.android.synthetic.main.activity_transitions_activities.view.*
import android.util.Pair as AndroidPair


class ActivityTransitionsActivity: AppCompatActivity() {
    /**
     * STATIC FUNCTIONS
     **/

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, ActivityTransitionsActivity::class.java)
        }
    }


    /**
     * USER INTERACTIONS
     **/

    private fun onClick(view: View?) {
        // Segun el boton en el que se pulso
        when (view) {
            explodeCode -> {
                // Incluimos en la ActivityOptions que es una TransitionAnimation
                // Lo pasamos como Bundle al arrancar la actividad
                val options = ActivityOptions.makeSceneTransitionAnimation(this)
                startActivity(TransitionActivity.getIntent(this, TransitionActivity.AnimType.ExplodeCode, "Animacion Explode Code"),
                                                 options.toBundle())
            }
            explodeXML -> {
                // Incluimos en la ActivityOptions que es una TransitionAnimation
                // Lo pasamos como Bundle al arrancar la actividad
                val options = ActivityOptions.makeSceneTransitionAnimation(this)
                startActivity(TransitionActivity.getIntent(this, TransitionActivity.AnimType.ExplodeXML, "Animacion Explode XML"),
                                                 options.toBundle())
            }
            slideCode -> {
                // Incluimos en la ActivityOptions que es una TransitionAnimation
                // Lo pasamos como Bundle al arrancar la actividad
                val options = ActivityOptions.makeSceneTransitionAnimation(this)
                startActivity(TransitionActivity.getIntent(this, TransitionActivity.AnimType.SlideCode, "Animacion Slide Code"),
                                                 options.toBundle())
            }
            slideXML -> {
                // Incluimos en la ActivityOptions que es una TransitionAnimation
                // Lo pasamos como Bundle al arrancar la actividad
                val options = ActivityOptions.makeSceneTransitionAnimation(this)
                startActivity(TransitionActivity.getIntent(this, TransitionActivity.AnimType.SlideXML, "Animacion Slide XML"),
                                                 options.toBundle())
            }
            fadeCode -> {
                // Incluimos en la ActivityOptions que es una TransitionAnimation
                // Lo pasamos como Bundle al arrancar la actividad
                val options = ActivityOptions.makeSceneTransitionAnimation(this)
                startActivity(TransitionActivity.getIntent(this, TransitionActivity.AnimType.FadeCode, "Animacion Fade Code"),
                                                 options.toBundle())
            }
            fadeXML -> {
                // Incluimos en la ActivityOptions que es una TransitionAnimation
                // Lo pasamos como Bundle al arrancar la actividad
                val options = ActivityOptions.makeSceneTransitionAnimation(this)
                startActivity(TransitionActivity.getIntent(this, TransitionActivity.AnimType.FadeXML, "Animacion Fade XML"),
                                                 options.toBundle())
            }
            sharedElement -> {
                // Creamos el array de los elementos que vamos a compartir
                // Se le pasa la view que se va a animar y el transition name
                val sharedElements = arrayOf(
                    AndroidPair<View, String>(linkSharedImage, "linkImage"),
                    AndroidPair<View, String>(linkSharedDescription, "linkDescription")
                )
                // Cremos las optiones de transicion de la actividad
                val options = ActivityOptions.makeSceneTransitionAnimation(this, *sharedElements)
                startActivity(SharedElementActivity.getIntent(this), options.toBundle())
            }
        }
    }


    /**
     * LIFE CYCLE
     **/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Se crea la ventana en la que colocal la UI
        setContentView(R.layout.activity_transitions_activities)

        // Creamos el listener para los botones
        configureButtons()
    }



    /**
     * PRIVATE FUNCTIONS
     **/

    private fun configureButtons() {
        explodeCode.setOnClickListener { onClick(it) }
        explodeXML.setOnClickListener { onClick(it) }
        slideCode.setOnClickListener { onClick(it) }
        slideXML.setOnClickListener { onClick(it) }
        fadeCode.setOnClickListener { onClick(it) }
        fadeXML.setOnClickListener { onClick(it) }

        sharedElement.setOnClickListener { onClick(it) }
    }
}