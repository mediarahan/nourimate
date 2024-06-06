package com.telyu.nourimate.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.telyu.nourimate.R
import com.telyu.nourimate.activities.ChooseProgramActivity
import com.telyu.nourimate.databinding.FragmentProgramEmptyBinding

class ProgramEmptyFragment : Fragment() {

    private lateinit var binding: FragmentProgramEmptyBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProgramEmptyBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setStatusBarColor(ContextCompat.getColor(requireContext(), R.color.color47))

        binding.buttonChooseProgram.setOnClickListener {
            startActivity(Intent(requireContext(), ChooseProgramActivity::class.java))
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
}