package com.telyu.nourimate.activities

import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.os.Looper
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.telyu.nourimate.R
import com.telyu.nourimate.databinding.ActivitySplashScreenBinding
import com.telyu.nourimate.utils.UserPreference
import com.telyu.nourimate.utils.dataStore
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    private lateinit var userPreference: UserPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this, R.color.color0)

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userPreference = UserPreference.getInstance(dataStore)

        createGradientBackground()

        // Delay to display the splash screen, after which we check the login status
        Handler(Looper.getMainLooper()).postDelayed({
            checkLoginStatus()
        }, 2500L) // 4 seconds for clarity in delay
    }

    private fun createGradientBackground() {
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

        val gradientDrawable = GradientDrawable(
            GradientDrawable.Orientation.TOP_BOTTOM, gradientColors
        ).apply {
            cornerRadius = 0f
        }

        binding.root.background = gradientDrawable
    }

    private fun checkLoginStatus() {
        lifecycleScope.launch {
            val isLoggedIn = userPreference.getUserLoginState().firstOrNull()
            Log.d("SplashScreenActivity", "isLoggedIn: $isLoggedIn")
            val isVerified = userPreference.getUserVerificationState().firstOrNull()
            Log.d("SplashScreenActivity", "isVerified: $isVerified")

            if (isLoggedIn == true && isVerified == true) {
                startActivity(Intent(this@SplashScreenActivity, NavigationBarActivity::class.java))
            } else {
                Log.d("SplashScreenActivity", "Not logged in, going to the LoginActivity...")
                startActivity(Intent(this@SplashScreenActivity, LoginActivity::class.java))
            }
            finish()
        }
    }
}
