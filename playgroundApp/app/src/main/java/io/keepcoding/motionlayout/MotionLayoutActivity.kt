package io.keepcoding.motionlayout

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.keepcoding.advanceduiplayground.R

class MotionLayoutActivity: AppCompatActivity(){
    /**
     * STATIC FUNCTIONS
     **/

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, MotionLayoutActivity::class.java)
        }
    }


    /**
     * LIFE CYCLE
     **/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Se crea la ventana en la que colocal la UI
        //setContentView(R.layout.activity_motionlayout_playground)
        //setContentView(R.layout.activity_motionlayout_playground2)
        setContentView(R.layout.activity_motionlayout)
    }
}