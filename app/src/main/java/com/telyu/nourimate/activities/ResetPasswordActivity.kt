package com.telyu.nourimate.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
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

        setupBackButtonHandling()

        val data = intent?.data
        val token = data?.getQueryParameter("token")

        binding.buttonNext.setOnClickListener {
            if(token != null)
            {
                val password = binding.verifyPasswordEditText.text.toString()
                viewModel.forgotPassword(password, token).observe(this)  {result ->
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

    private fun setupBackButtonHandling() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (isTaskRoot) {
                    startActivity(Intent(this@ResetPasswordActivity, LoginActivity::class.java))
                    finish()
                } else {
                    finishWithResultOK()
                }
            }
        }
        onBackPressedDispatcher.addCallback(this, callback)
    }

    private fun finishWithResultOK() {
        setResult(RESULT_OK)
        finish()
    }


    private fun obtainViewModel(activity: AppCompatActivity): ForgotPasswordViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[ForgotPasswordViewModel::class.java]
    }
}