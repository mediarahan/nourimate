package com.telyu.nourimate.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import com.telyu.nourimate.R
import com.telyu.nourimate.databinding.ActivityTransitionProgramBinding
import com.telyu.nourimate.viewmodels.TransitionProgramViewModel
import com.telyu.nourimate.viewmodels.ViewModelFactory
import com.telyu.nourimate.utils.Converters
import com.telyu.nourimate.utils.GeneralUtil
import kotlin.math.abs

class TransitionProgramActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTransitionProgramBinding
    private lateinit var viewModel: TransitionProgramViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransitionProgramBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setStatusBarColor(resources.getColor(R.color.color2, theme))

        viewModel = obtainViewModel(this@TransitionProgramActivity)
        viewModel.getLatestHistory()
        setupHistoryData()

        binding.textViewDetailCongrats.setOnClickListener {
            val intent = Intent(this, NavigationBarActivity::class.java).apply {
                putExtra("loadFragment", "HistoryFragment")
            }
            startActivity(intent)
        }
    }

    private fun setupHistoryData() {
        viewModel.history.observe(this) { history ->
            val status = if (history.startWeight < history.endWeight) "gained" else "lost"
            Log.d("TAG", "$status = ${history.startWeight} - ${history.endWeight}")
            val weight = history.endWeight - history.startWeight
            val dayDiff = GeneralUtil.calculateDaysBetweenDates(history.startDate, history.endDate)
            binding.textViewGreetCongrats.text = "You've $status $weight kg over $dayDiff days"

            binding.TotalCalories.text = history.calories.toString()
            binding.TotalFat.text = history.fat.toString()
            binding.TotalProtein.text = history.protein.toString()
            binding.TotalCarbs.text = history.carbs.toString()
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