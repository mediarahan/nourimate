package com.telyu.nourimate.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.telyu.nourimate.databinding.ActivityVerificationCode2Binding
import android.app.Activity
import android.graphics.drawable.GradientDrawable
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import com.telyu.nourimate.R
import com.telyu.nourimate.data.remote.Result
import com.telyu.nourimate.viewmodels.VerificationViewModel
import com.telyu.nourimate.viewmodels.ViewModelFactory


class VerificationCode2Activity : AppCompatActivity() {

    private lateinit var binding: ActivityVerificationCode2Binding
    private lateinit var viewModel: VerificationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerificationCode2Binding.inflate(layoutInflater)
        setStatusBarColor(resources.getColor(R.color.color25, theme))
        val view = binding.root
        setContentView(view)

        viewModel = obtainViewModel(this@VerificationCode2Activity)
        viewModel.sendEmailVerification()

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

        binding.TextViewReceiveCode.setOnClickListener {
            viewModel.sendEmailVerification()
            Toast.makeText(this, "A new code has been sent to your email", Toast.LENGTH_SHORT).show()
        }

        binding.buttonVerify.setOnClickListener {
            val verificationCode = binding.verifyEditText.text.toString()
            viewModel.verifyEmail(verificationCode).observe(this) { result ->
                when (result) {
                    is Result.Loading -> showLoading(true)
                    is Result.Success -> {
                        showLoading(false)
                        Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@VerificationCode2Activity, EditProfileActivity::class.java))
                        finish()
                    }
                    is Result.Error -> {
                        showLoading(false)
                        Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
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

    private fun navigateToProfile() {
        //viewModel.setAccountStateAsVerified()
        startActivity(Intent(this, EditProfileActivity::class.java))
        finish() // Optional, to finish the current activity if going to a different screen
    }

    private fun navigateToNavBar() {
        startActivity(Intent(this, NavigationBarActivity::class.java))
        finish() // Optional, to finish the current activity if going to a different screen
    }


    private fun openVerification1Page() {
        // Buat Intent untuk membuka VerificationActivity
        val intent = Intent(this, VerificationCode1Activity::class.java)
        startActivity(intent)
    }

    private fun navigateToPasswordPopupPage() {
        // Buat Intent untuk membuka VerificationActivity
        val intent = Intent(this, PasswordPopupActivity::class.java)
        startActivity(intent)
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun obtainViewModel(activity: AppCompatActivity): VerificationViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[VerificationViewModel::class.java]
    }


}

