package com.telyu.nourimate.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.telyu.nourimate.databinding.FragmentSettingBinding
import android.widget.AdapterView


class SettingFragment : Fragment() {
    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backButton.setOnClickListener {
            // Navigasi kembali ke ProfileFragment
            requireActivity().supportFragmentManager.popBackStack()
        }

        val themesOptions = arrayOf("Bright", "Dark")
        val themeAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, themesOptions)

        themeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerThemes.adapter = themeAdapter

        binding.spinnerThemes.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedTheme = parent?.getItemAtPosition(position).toString()
                // Di sini Anda dapat melakukan sesuatu dengan tema yang dipilih
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        binding.switchNotification.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Proses untuk menyalakan notifikasi
            } else {
                // Proses untuk mematikan notifikasi
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
