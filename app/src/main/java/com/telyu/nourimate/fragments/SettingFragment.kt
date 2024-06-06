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
import com.telyu.nourimate.R
import com.telyu.nourimate.adapter.date.HintArrayAdapter


class SettingFragment : Fragment() {
    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!
    private lateinit var themesAdapter: HintArrayAdapter


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
            requireActivity().supportFragmentManager.popBackStack()
        }

        val themesOptions = arrayOf("Themes", "Bright", "Dark")
        themesAdapter = HintArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, themesOptions)
        themesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerThemes.adapter = themesAdapter

        binding.switchNotification.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Proses untuk menyalakan notifikasi
            } else {
                // Proses untuk mematikan notifikasi
            }
        }
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
