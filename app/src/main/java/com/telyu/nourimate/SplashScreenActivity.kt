package com.telyu.nourimate

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.telyu.nourimate.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Optional: Customize the duration of the splash screen
        val splashDuration = 2000L // 2 seconds

        Handler().postDelayed({
            // Start the main activity after the splash duration
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, splashDuration)
    }
}

