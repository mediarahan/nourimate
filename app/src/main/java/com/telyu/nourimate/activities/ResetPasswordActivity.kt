package com.telyu.nourimate.activities

import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import com.telyu.nourimate.R
import com.telyu.nourimate.data.remote.Result
import com.telyu.nourimate.databinding.ActivityResetPasswordBinding
import com.telyu.nourimate.viewmodels.ForgotPasswordViewModel
import com.telyu.nourimate.viewmodels.ViewModelFactory

class ResetPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResetPasswordBinding
    private lateinit var viewModel: ForgotPasswordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResetPasswordBinding.inflate(layoutInflater)
        setStatusBarColor(resources.getColor(R.color.color25, theme))
        val view = binding.root
        setContentView(view)

        viewModel = obtainViewModel(this@ResetPasswordActivity)

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

        val data = intent?.data

        val token = data?.getQueryParameter("token")
        val parsedToken = token?.removeSuffix(";")

        binding.buttonNext.setOnClickListener {
            if(token != null)
            {
                viewModel.deleteUserEmail()
                val password = binding.verifyPasswordEditText.text.toString()
                val confirmPassword = binding.verifyConfirmPasswordEditText.text.toString()
                viewModel.forgotPassword(password, confirmPassword, parsedToken.toString()).observe(this)  { result ->
                    when (result) {
                        is Result.Loading -> showLoading(true)
                        is Result.Success -> {
                            showLoading(false)
                            Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@ResetPasswordActivity, LoginActivity::class.java))
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
    }

    private fun setStatusBarColor(color: Int) {
        val window = window
        val insetsController = WindowCompat.getInsetsController(window, window.decorView)

        insetsController.isAppearanceLightStatusBars = true
        insetsController.isAppearanceLightNavigationBars = true

        window.statusBarColor = color
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun obtainViewModel(activity: AppCompatActivity): ForgotPasswordViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[ForgotPasswordViewModel::class.java]
    }
}