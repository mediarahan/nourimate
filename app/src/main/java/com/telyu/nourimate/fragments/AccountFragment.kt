package com.telyu.nourimate.fragments

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import com.telyu.nourimate.R
import com.telyu.nourimate.databinding.DialogNameChangeBinding
import com.telyu.nourimate.databinding.FragmentAccountBinding

class AccountFragment : Fragment() {
    private var _binding: FragmentAccountBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setStatusBarColor(ContextCompat.getColor(requireContext(), R.color.color54))

        binding.backButton.setOnClickListener {
            // Navigasi kembali ke ProfileFragment
            requireActivity().supportFragmentManager.popBackStack()
        }

        binding.changenumberIcon.setOnClickListener {
            onChangePhoneNumberClicked()
        }

        binding.changepwIcon.setOnClickListener {
            onChangePasswordClicked(view)
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

    private fun onChangePhoneNumberClicked() {
        val dialogFragment = ChangeNumberProfileFragment.newInstance()
        dialogFragment.show(parentFragmentManager, "changeNumberDialog") // or use childFragmentManager
    }



    private fun onChangePasswordClicked(view: View) {
        val dialogFragment = ChangePasswordProfileFragment.newInstance()
        dialogFragment.show(parentFragmentManager, "changeNumberDialog") // or use childFragmentManager
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
