package com.telyu.nourimate.activities

import android.content.Intent
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import com.telyu.nourimate.R
import com.telyu.nourimate.databinding.ActivityForgotPassword2Binding
import com.telyu.nourimate.databinding.PopupChangePasswordBinding
import com.telyu.nourimate.fragments.ChangePasswordDialogFragment

class ForgotPassword2Activity : AppCompatActivity() {
    private lateinit var binding: ActivityForgotPassword2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPassword2Binding.inflate(layoutInflater)
        setStatusBarColor(resources.getColor(R.color.color25, theme))
        val view = binding.root
        setContentView(view)

        val gradientColors = intArrayOf(
            getColor(R.color.color25),
            getColor(R.color.color20),
            getColor(R.color.white),
            getColor(R.color.white),
            getColor(R.color.white),
        )

        // Buat objek GradientDrawable
        val gradientDrawable = GradientDrawable(
            GradientDrawable.Orientation.TOP_BOTTOM,
            gradientColors
        )

        // Set corner radius jika diinginkan
        gradientDrawable.cornerRadius = 0f

        // Terapkan drawable ke root view
        binding.root.background = gradientDrawable

        setupButton()

        checkAllInputsValid()
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

    private fun setupButton() {
        binding.TextViewOtherMethod.setOnClickListener {
            openForgotPasswordPage()
        }
        binding.buttonNext.setOnClickListener {
            openChangePasswordPage()
        }

        setupTextWatchers()
    }

    private fun setupTextWatchers() {
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkAllInputsValid()
            }

            override fun afterTextChanged(s: Editable?) {
                checkAllInputsValid()
            }
        }

        binding.verifyPhoneNumberEditText.addTextChangedListener(textWatcher)
    }

    private fun checkAllInputsValid() {
        val phoneNumber = binding.verifyPhoneNumberEditText.text.toString()
        val isValid = (phoneNumber.isNotEmpty()) && binding.verifyPhoneNumberEditText.error == null
        if (isValid){
            enableNextButton()
        } else{
            disableNextButton()
        }
    }

    private fun disableNextButton() {
        binding.buttonNext.isEnabled = false
        binding.buttonNext.setBackground(ContextCompat.getDrawable(this, R.drawable.buttonlogin_background_disable))
        binding.buttonNext.setTextColor(ContextCompat.getColor(this, R.color.color26))// Set to gray color
    }

    private fun enableNextButton() {
        binding.buttonNext.isEnabled = true
        binding.buttonNext.setBackground(ContextCompat.getDrawable(this, R.drawable.buttonlogin_background))  // Restore to primary color
        binding.buttonNext.setTextColor(ContextCompat.getColor(this, R.color.color23))
    }


    private fun openForgotPasswordPage() {
        // Buat Intent untuk membuka VerificationActivity
        val intent = Intent(this, ForgotPasswordActivity::class.java)
        startActivity(intent)
    }

    private fun openChangePasswordPage() {
        val dialogFragment = ChangePasswordDialogFragment.newInstance()
        dialogFragment.show(supportFragmentManager, "changePasswordDialog")
    }
}