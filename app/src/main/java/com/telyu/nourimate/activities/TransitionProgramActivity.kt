package com.telyu.nourimate.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.telyu.nourimate.R
import com.telyu.nourimate.databinding.ActivitySignUpBinding
import com.telyu.nourimate.databinding.ActivityTransitionProgramBinding
import com.telyu.nourimate.viewmodels.SignUpViewModel
import com.telyu.nourimate.viewmodels.TransitionProgramViewModel
import com.telyu.nourimate.viewmodels.ViewModelFactory

class TransitionProgramActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTransitionProgramBinding
    private lateinit var viewModel: TransitionProgramViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransitionProgramBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setStatusBarColor(resources.getColor(R.color.color48, theme))

        viewModel = obtainViewModel(this@TransitionProgramActivity)

        viewModel.history.observe(this) {history ->

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

    private fun obtainViewModel(activity: AppCompatActivity): TransitionProgramViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[TransitionProgramViewModel::class.java]
    }

}