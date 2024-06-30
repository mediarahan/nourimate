package com.telyu.nourimate.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.telyu.nourimate.databinding.FragmentSettingBinding
import android.widget.AdapterView
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.viewModels
import com.telyu.nourimate.R
import com.telyu.nourimate.adapter.date.HintArrayAdapter
import com.telyu.nourimate.utils.SettingsModel
import com.telyu.nourimate.viewmodels.AccountViewModel
import com.telyu.nourimate.viewmodels.SettingViewModel
import com.telyu.nourimate.viewmodels.ViewModelFactory


class SettingFragment : Fragment() {
    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!
    private lateinit var themesAdapter: HintArrayAdapter

    private val viewModel by viewModels<SettingViewModel> {
        ViewModelFactory.getInstance(
            requireContext().applicationContext
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setStatusBarColor(ContextCompat.getColor(requireContext(), R.color.color2))

        binding.backButton.setOnClickListener {
            // Navigasi kembali ke ProfileFragment
            saveSettingsPreferences()
            requireActivity().supportFragmentManager.popBackStack()
        }

        val themesOptions = arrayOf("Themes", "Bright", "Dark")
        themesAdapter = HintArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, themesOptions)
        themesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerThemes.adapter = themesAdapter
    }

    private fun saveSettingsPreferences() {
        val themePreference = binding.spinnerThemes.selectedItem.toString()
        val booleanThemePreference = when (themePreference) {
            "Bright" -> true
            "Dark" -> false
            else -> true
        }
        val notificationPreference = binding.switchNotification.isChecked
        val recipePreference = binding.switchRecipePreferencesRecipeTransition.isChecked
        val settingsModel = SettingsModel(booleanThemePreference, notificationPreference, recipePreference)
        viewModel.saveSettingsPreferences(settingsModel)
    }

    private fun setStatusBarColor(color: Int) {
        val window = requireActivity().window
        val insetsController = WindowInsetsControllerCompat(window, window.decorView)

        insetsController.isAppearanceLightStatusBars =
            true // Set true or false depending on the status bar icons' color
        insetsController.isAppearanceLightNavigationBars =
            true // Set true or false depending on the navigation bar icons' color

        window.statusBarColor = color
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}