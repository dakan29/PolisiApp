package com.example.polisiapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

//        start animation declaration
        val top_bottom = AnimationUtils.loadAnimation(this, R.anim.top_bottom_anim)
        val bottom_top = AnimationUtils.loadAnimation(this, R.anim.bottom_top_anim)
        val fadein = AnimationUtils.loadAnimation(this, R.anim.fadein_anim)

//        put animation into action
        splash_img.startAnimation(top_bottom)
        tv_welcome.startAnimation(fadein)

        Handler().postDelayed({
            val intent = Intent(this, OnBoardingScreen::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}