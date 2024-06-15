package com.telyu.nourimate.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import com.telyu.nourimate.R
import com.telyu.nourimate.databinding.ActivityLoginBinding
import com.telyu.nourimate.databinding.ActivityTransitionSelectRecipeBinding
import com.telyu.nourimate.fragments.HistoryFragment
import com.telyu.nourimate.fragments.HomeMealHistoryFragment
import com.telyu.nourimate.viewmodels.LoginViewModel
import com.telyu.nourimate.viewmodels.TransitionRecipeViewModel
import com.telyu.nourimate.viewmodels.ViewModelFactory

class TransitionSelectRecipeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTransitionSelectRecipeBinding
    private lateinit var viewModel: TransitionRecipeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStatusBarColor(resources.getColor(R.color.color1, theme))
        viewModel = obtainViewModel(this@TransitionSelectRecipeActivity)

        val selectedMeal = intent.getIntExtra("selectedMeal", -1)
        binding = ActivityTransitionSelectRecipeBinding.inflate(layoutInflater)

        binding.textViewDetailCongrats.setOnClickListener {
            checkTransitionPreference()
            val intent = Intent(this, NavigationBarActivity::class.java).apply {
                action = "ACTION_SHOW_MEAL_HISTORY"
                putExtra("selectedMeal", selectedMeal)
            }
            startActivity(intent)
            finish()
        }

        setContentView(binding.root)
    }

    private fun checkTransitionPreference() {
        val status = binding.checkboxDoNotShowAgain.isChecked
        if (status) {
            viewModel.setRecipeTransitionPreference(false)
        } else {
            viewModel.setRecipeTransitionPreference(true)
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

    //================ ViewModel ===============
    private fun obtainViewModel(activity: AppCompatActivity): TransitionRecipeViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[TransitionRecipeViewModel::class.java]
    }

}