package com.ruthvikbr.hospital_demo



import android.os.Bundle
import android.view.animation.AnimationUtils

import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity



class LandingPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_page)
        val animFadeIn = AnimationUtils.loadAnimation(
            applicationContext,
            R.anim.fade_in)

        val imageView:ImageView = findViewById(R.id.imageView)
        imageView.startAnimation(animFadeIn)


    }
}