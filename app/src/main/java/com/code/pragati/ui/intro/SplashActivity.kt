package com.code.pragati.ui.intro

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.cloudinary.android.MediaManager
import com.code.pragati.R
import com.code.pragati.ui.signUp.WhoAreYou

class SplashActivity : AppCompatActivity() {

    lateinit var topAnimation: Animation
    lateinit var bottomAnimation: Animation
    lateinit var bubbleTopLeft: ImageView
    lateinit var bubbleTopRight: ImageView
    lateinit var bubbleBottomRight: ImageView
    lateinit var bubbleBottomLeft: ImageView
    lateinit var bubbleBottom: ImageView
    lateinit var logo: ImageView
    lateinit var logoText: ImageView
    lateinit var onBoardingScreen: SharedPreferences

    private val splashDisplayLength: Long = 4500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this, R.color.primary_light)
        MediaManager.init(this)

        setContentView(R.layout.activity_splash)

        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)

        logo = findViewById(R.id.logo)
        logoText = findViewById(R.id.logoText)
        bubbleTopLeft = findViewById(R.id.bubbleTopLeft)
        bubbleTopRight = findViewById(R.id.bubbleTopRight)
        bubbleBottomLeft = findViewById(R.id.bubbleBottomLeft)
        bubbleBottomRight = findViewById(R.id.bubbleBottomRight)
        bubbleBottom = findViewById(R.id.bubbleBottom)

        bubbleTopRight.animation = topAnimation
        bubbleTopLeft.animation = topAnimation
        bubbleBottomRight.animation = bottomAnimation
        bubbleBottomLeft.animation = bottomAnimation
        //bubbleBottom.animation = bottomAnimation
//        logo.animation = topAnimation
//        logoText.animation = bottomAnimation


        Handler().postDelayed({

            onBoardingScreen = getSharedPreferences("onBoardingScreen", MODE_PRIVATE)
            var isFirstTime = onBoardingScreen.getBoolean("firstTime", true)


            if (isFirstTime) {
                val editor = onBoardingScreen.edit()
                editor.putBoolean("firstTime",false)
                editor.apply()

                startActivity(Intent(this, OnBoardingActivity::class.java))
                finish()
            } else {
                Log.d("check for errors","Else working in Splash")
                startActivity(Intent(this, WhoAreYou::class.java))
                finish()
            }
        }, splashDisplayLength)
    }
}