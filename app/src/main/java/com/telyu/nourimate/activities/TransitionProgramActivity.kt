package com.telyu.nourimate.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import com.telyu.nourimate.R
import com.telyu.nourimate.databinding.ActivityTransitionProgramBinding
import com.telyu.nourimate.fragments.HistoryFragment
import com.telyu.nourimate.fragments.ProfileFragment
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
        setStatusBarColor(resources.getColor(R.color.color54, theme))

        viewModel = obtainViewModel(this@TransitionProgramActivity)
        viewModel.getLatestHistory()
        setupHistoryData()

        binding.textViewDetailCongrats.setOnClickListener {
            val intent = Intent(this, NavigationBarActivity::class.java).apply {
                action = "ACTION_SHOW_HISTORY_TUTORIAL"
//                putExtra("loadFragment", "ProfileFragment")
            }
            startActivity(intent)
        }
    }

    private fun setupHistoryData() {
        viewModel.history.observe(this) { history ->
            val status = if (history.startWeight < history.endWeight) "gained" else "lost"
            val weightChange = abs(history.endWeight - history.startWeight)
            val todayDate = Converters().dateFromTimestamp(System.currentTimeMillis())
            val todayDateString = Converters().formatDateToString(todayDate)
            val dayDiff = GeneralUtil.calculateDaysBetweenDates(history.startDate, todayDateString)

            // Logging various details
            Log.d("HistoryData", "Status: $status")
            Log.d("HistoryData", "Weight change: $weightChange kg")
            Log.d("HistoryData", "Start Date: $history.startDate")
            Log.d("HistoryData", "Start Date: $todayDateString")
            Log.d("Step 3", "Start Weight: ${history.startWeight} kg, End Weight: ${history.endWeight} kg")
            Log.d("HistoryData", "Total Calories: ${history.calories}")
            Log.d("HistoryData", "Total Fat: ${history.fat}")
            Log.d("HistoryData", "Total Protein: ${history.protein}")
            Log.d("HistoryData", "Total Carbs: ${history.carbs}")

            // Updating UI
            binding.textViewGreetCongrats.text = "You've $status $weightChange kg over $dayDiff days"
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