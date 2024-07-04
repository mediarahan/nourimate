package com.telyu.nourimate.activities

import android.content.Intent
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.WindowCompat
import com.telyu.nourimate.R
import com.telyu.nourimate.databinding.ActivityForgotPasswordBinding

class ForgotPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityForgotPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setStatusBarColor(resources.getColor(R.color.color25, theme))
        val view = binding.root
        setContentView(view)

        val gradientColors = intArrayOf(
            getColor(R.color.color25),
            getColor(R.color.color20),
            getColor(R.color.white),
            getColor(R.color.white),
            getColor(R.color.white),
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

        setupButton()
    }

    private fun setStatusBarColor(color: Int) {
        val window = window
        val insetsController = WindowCompat.getInsetsController(window, window.decorView)

        // Set to 'true' to ensure status bar icons are dark, useful for light status bar backgrounds
        insetsController.isAppearanceLightStatusBars = true
        // Set to 'true' to ensure navigation bar icons are dark, useful for light navigation bar backgrounds
        insetsController.isAppearanceLightNavigationBars = true

        // Set the status bar color
        window.statusBarColor = color
    }

    private fun setupButton() {
        binding.buttonVerifEmail.setOnClickListener {
            openForgotPassword1Page()
        }
//        binding.buttonVerifNumber.setOnClickListener {
//            openForgotPassword2Page()
//        }
    }

    private fun openForgotPassword1Page() {
        // Buat Intent untuk membuka VerificationActivity
        val intent = Intent(this, ForgotPassword1Activity::class.java)
        startActivity(intent)
    }

    private fun openForgotPassword2Page() {
        // Buat Intent untuk membuka VerificationActivity
        val intent = Intent(this, ForgotPassword2Activity::class.java)
        startActivity(intent)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

}