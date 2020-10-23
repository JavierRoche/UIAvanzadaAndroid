package io.keepcoding.animations

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import io.keepcoding.advanceduiplayground.R

class SharedElementActivity: AppCompatActivity() {
    /**
     * STATIC FUNCTIONS
     **/

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, SharedElementActivity::class.java)
        }
    }


    /**
     * LIFE CYCLE
     **/

    override fun onCreate(savedInstanceState: Bundle?) {
        // Siempre antes del setContentView indicamos a la ventana que tendra actividades  con transitions
        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)

        super.onCreate(savedInstanceState)

        // Se crea la ventana en la que colocal la UI
        setContentView(R.layout.activity_shared_element)
    }
}