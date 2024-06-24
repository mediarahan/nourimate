package com.telyu.nourimate.activities

import android.content.Intent
import android.os.Bundle
import android.os.Debug
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.telyu.nourimate.R
import com.telyu.nourimate.adapter.date.HintArrayAdapter
import com.telyu.nourimate.databinding.ActivitySettingBinding
import com.telyu.nourimate.utils.SettingsModel
import com.telyu.nourimate.viewmodels.SettingViewModel
import com.telyu.nourimate.viewmodels.ViewModelFactory

class SettingActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingBinding
    private lateinit var themesAdapter: HintArrayAdapter
    private lateinit var viewModel: SettingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = obtainViewModel(this@SettingActivity)

        setStatusBarColor(ContextCompat.getColor(this, R.color.color2))
        mapPreferencesToUI()

        binding.backButton.setOnClickListener {
            // Navigate back to ProfileActivity or similar
            saveSettingsPreferences()
            finish()
        }

        binding.settingTextView.setOnClickListener {
            startActivity(Intent(this@SettingActivity, DebugActivity::class.java))
        }


        val themesOptions = arrayOf("Themes", "Bright", "Dark")
        themesAdapter = HintArrayAdapter(this, android.R.layout.simple_spinner_item, themesOptions)
        themesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerThemes.adapter = themesAdapter
    }

    private fun mapPreferencesToUI() {
        val settingsModel = viewModel.getSettingsPreferences().asLiveData()

        settingsModel.observe(this) {settings ->
            binding.switchNotification.isChecked = when(settings.areNotificationsEnabled) {
                true -> true
                false -> false
            }
            binding.switchPreferencesRecipeTransition.isChecked = when(settings.isRecipeTransition) {
                true -> true
                false -> false
            }
            binding.spinnerThemes.setSelection(when(settings.isNightModeEnabled) {
                true -> 2
                false -> 1
            })
        }

    }

    private fun saveSettingsPreferences() {
        val themePreference = binding.spinnerThemes.selectedItem.toString()
        val booleanThemePreference = when (themePreference) {
            "Bright" -> true
            "Dark" -> false
            else -> true
        }
        val notificationPreference = binding.switchNotification.isChecked
        val recipePreference = binding.switchPreferencesRecipeTransition.isChecked
        val settingsModel = SettingsModel(booleanThemePreference, notificationPreference, recipePreference)
        viewModel.saveSettingsPreferences(settingsModel)
    }

    private fun setStatusBarColor(color: Int) {
        val window = window
        val insetsController = WindowInsetsControllerCompat(window, window.decorView)

        insetsController.isAppearanceLightStatusBars = true // Set true or false depending on the status bar icons' color
        insetsController.isAppearanceLightNavigationBars = true // Set true or false depending on the navigation bar icons' color

        window.statusBarColor = color
    }

    override fun onBackPressed() {
        saveSettingsPreferences()
        super.onBackPressed()
    }

    //================ ViewModel ===============
    private fun obtainViewModel(activity: AppCompatActivity): SettingViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[SettingViewModel::class.java]
    }

}
