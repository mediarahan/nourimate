package com.telyu.nourimate.fragments

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
import androidx.fragment.app.Fragment
import com.telyu.nourimate.R
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

        binding.backButton.setOnClickListener {
            // Navigasi kembali ke ProfileFragment
            requireActivity().supportFragmentManager.popBackStack()
        }

        binding.changenumberIcon.setOnClickListener {
            onChangePhoneNumberClicked(view)
        }

        binding.changepwIcon.setOnClickListener {
            onChangePasswordClicked(view)
        }
    }

    private fun onChangePhoneNumberClicked(view: View) {
        val inflater = requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView = inflater.inflate(R.layout.popup_change_phone_number, null)

        val popupWindow = PopupWindow(
            popupView,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            popupWindow.elevation = 20f
        }

        popupWindow.setBackgroundDrawable(ColorDrawable(Color.WHITE))

        popupWindow.width = LinearLayout.LayoutParams.MATCH_PARENT
        popupWindow.height = LinearLayout.LayoutParams.WRAP_CONTENT

        popupWindow.isFocusable = true

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0)
    }

    private fun onChangePasswordClicked(view: View) {
        val inflater = requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView = inflater.inflate(R.layout.popup_change_password, null)

        val popupWindow = PopupWindow(
            popupView,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            popupWindow.elevation = 20f
        }

        popupWindow.setBackgroundDrawable(ColorDrawable(Color.WHITE))

        popupWindow.width = LinearLayout.LayoutParams.MATCH_PARENT
        popupWindow.height = LinearLayout.LayoutParams.WRAP_CONTENT

        popupWindow.isFocusable = true

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
