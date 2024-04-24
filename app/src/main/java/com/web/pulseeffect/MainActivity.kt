package com.web.pulseeffect


import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.web.pulseeffect.ui.RippleBackground


class MainActivity : AppCompatActivity() {

    private lateinit var pulseButton: Button
    private lateinit var playIcon : Button
    private var progress = 0
    private val handler = Handler(Looper.getMainLooper())
    private var rippleBackground: RippleBackground? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        pulseButton = findViewById(R.id.play)
//        rippleBackground = findViewById(R.id.content)
//        playIcon = findViewById(R.id.playIcon)
//        rippleBackground!!.startRippleAnimation()
//        handler.post(updateProgressTask)
    }


    private val updateProgressTask = object : Runnable {
        override fun run() {
            progress++
            if (progress <= 100) {
                pulseButton.text = "$progress%"
                pulseButton.visibility = View.VISIBLE
                playIcon.visibility = View.GONE
                handler.postDelayed(this, 1000) // 1000ms = 1 second
            } else {
                progress = 0 // Reset progress to 0
                pulseButton.text = ""
                pulseButton.visibility = View.GONE
                playIcon.visibility = View.VISIBLE
                rippleBackground!!.stopRippleAnimation()
            }
        }
    }



}