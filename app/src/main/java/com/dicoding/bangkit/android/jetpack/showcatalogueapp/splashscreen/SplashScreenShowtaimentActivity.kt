package com.dicoding.bangkit.android.jetpack.showcatalogueapp.splashscreen

import android.content.Intent
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.bangkit.android.jetpack.showcatalogueapp.MainActivity
import com.dicoding.bangkit.android.jetpack.showcatalogueapp.R

class SplashScreenShowtaimentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //hilangin action bar splashscreen
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_splash_screen_showtaiment)

        val bekron = object : Thread() {
            override fun run() {
                try {
                    sleep(3000L)
                    val intensplash = Intent(baseContext, MainActivity::class.java)
                    startActivity(intensplash)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        bekron.start()
    }
}