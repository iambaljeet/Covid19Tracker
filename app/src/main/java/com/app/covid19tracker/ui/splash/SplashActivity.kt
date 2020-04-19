package com.app.covid19tracker.ui.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.covid19tracker.R
import com.app.covid19tracker.ui.main.MainActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        MainActivity.launchActivity(this)
    }
}