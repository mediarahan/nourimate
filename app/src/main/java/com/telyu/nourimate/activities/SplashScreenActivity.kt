package com.telyu.nourimate.activities

import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.os.Looper
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.telyu.nourimate.R
import com.telyu.nourimate.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this, R.color.color0)

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Buat array warna untuk gradien
        val gradientColors = intArrayOf(
            getColor(R.color.color0),
            getColor(R.color.color1),
            getColor(R.color.color2),
            getColor(R.color.color3),
            getColor(R.color.color4),
            getColor(R.color.color5),
            getColor(R.color.color6),
            getColor(R.color.color7),
            getColor(R.color.color8),
            getColor(R.color.color9),
            getColor(R.color.color10),
            getColor(R.color.color11),
            getColor(R.color.color12),
            getColor(R.color.color13),
            getColor(R.color.color14),
            getColor(R.color.color15)
        )

        // Buat objek GradientDrawable
        val gradientDrawable = GradientDrawable(
            GradientDrawable.Orientation.TOP_BOTTOM,
            gradientColors
        )

        // Set corner radius jika diinginkan
        gradientDrawable.cornerRadius = 0f

        // Terapkan drawable ke root view
        binding.root.background = gradientDrawable

        // Optional: Customize the duration of the splash screen
        val splashDuration = 2000L // 2 seconds
        Handler(Looper.getMainLooper()).postDelayed({
            // Start the main activity after the splash duration
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, splashDuration)
    }
}
