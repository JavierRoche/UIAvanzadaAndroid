package io.keepcoding.animations

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.transition.*
import android.view.animation.LinearInterpolator
import androidx.appcompat.app.AppCompatActivity
import io.keepcoding.advanceduiplayground.R
import kotlinx.android.synthetic.main.activity_layout_transitions.*
import kotlinx.android.synthetic.main.scene1.*


class LayoutTransitionsActivity: AppCompatActivity() {
    private lateinit var scene1: Scene
    private lateinit var scene2: Scene
    private lateinit var currentScene: Scene
    private lateinit var transitionSet: TransitionSet

    /**
     * STATIC FUNCTIONS
     **/

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, LayoutTransitionsActivity::class.java)
        }
    }


    /**
     * LIFE CYCLE
     **/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Se crea la ventana en la que colocal la UI
        setContentView(R.layout.activity_layout_transitions)

        // Creamos el listener para los botones
        configureButton()

        // Creamos la escena inicial
        createScene()
    }


    /**
     * USER INTERACTIONS
     **/

    private fun onClick() {
        // Al darle al boton transicionamos de una escena a otra guardando cual es la actual
        currentScene = if (currentScene == scene1) {
            TransitionManager.go(scene2, transitionSet)
            scene2
        } else {
            TransitionManager.go(scene1, transitionSet)
            scene1
        }
    }


    /**
     * PRIVATE FUNCTIONS
     **/

    private fun configureButton() {
        layoutTransitionButton.setOnClickListener { onClick() }
    }

    private fun createScene() {
        // Creamos las dos escenas para el layout inicial y el final
        // Las escenas reflejan la foto inicial y la final
        // Se relacionan con la transitionSet solo en el momento de ejecutarlas con el TransitionManager
        scene1 = Scene.getSceneForLayout(sceneRoot, R.layout.scene1, this)
        scene2 = Scene.getSceneForLayout(sceneRoot, R.layout.scene2, this)

        // Se declara una transicion de tipo ChangeBounds
        val changeBoundsTransition = ChangeBounds()
        changeBoundsTransition.apply {
            interpolator = LinearInterpolator()
            duration = 1000
        }

        // Se declara una transicion de tipo Fade
        val fadeInTransition = Fade(Fade.IN)
        fadeInTransition.apply {
            duration = 500
            startDelay = 900
            addTarget(linkTitle)
        }

        // Se declara una transicion de tipo Fade
        val fadeOutTransition = Fade(Fade.OUT)
        fadeOutTransition.apply {
            duration = 100
            addTarget(linkTitle)
        }

        // Objeto transition que indica el tipo de transicion que se vera
        transitionSet = TransitionSet()
        transitionSet.apply {
            ordering = TransitionSet.ORDERING_TOGETHER
            addTransition(changeBoundsTransition)
            addTransition(fadeInTransition)
            addTransition(fadeOutTransition)
        }

        // Activamos la escena 1
        scene1.enter()
        // Guardamos cual es la currectScene
        currentScene = scene1
    }
}