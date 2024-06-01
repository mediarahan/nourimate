package com.telyu.nourimate.activities

import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.os.Looper
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.telyu.nourimate.R
import com.telyu.nourimate.data.local.models.Detail
import com.telyu.nourimate.data.local.models.User
import com.telyu.nourimate.databinding.ActivitySplashScreenBinding
import com.telyu.nourimate.utils.Converters
import com.telyu.nourimate.utils.UserPreference
import com.telyu.nourimate.utils.dataStore
import com.telyu.nourimate.viewmodels.SplashScreenViewModel
import com.telyu.nourimate.viewmodels.ViewModelFactory
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    private lateinit var userPreference: UserPreference
    private lateinit var viewModel: SplashScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = obtainViewModel(this@SplashScreenActivity)
        setStatusBarColor(resources.getColor(R.color.color0, theme))

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userPreference = UserPreference.getInstance(dataStore)

        insertAdminAccounts()
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

    private fun insertAdminAccounts() {
        val users = listOf(
            User(5, "Admin1", "admin1@gmail.com", 123, "admin123", true, true),
            User(6, "Admin2", "admin2@gmail.com", 124, "admin124", true, false)
        )

        val details = listOf(
            Detail(5, Converters().fromTimestamp(1054628979000), 181f, 80f, 81f, "Laki-laki", "Nuts", "Diabetes", 24.4f),
            Detail(6, Converters().fromTimestamp(1054628979000), 160f, 65f, 71f, "Laki-laki", "Shellfish", "Cholesterol", 25.4f)
        )

        users.forEachIndexed { index, user ->
            viewModel.insertUser(user)
            viewModel.insertDetail(details[index])
        }
    }


    private fun obtainViewModel(activity: AppCompatActivity): SplashScreenViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[SplashScreenViewModel::class.java]
    }

}
