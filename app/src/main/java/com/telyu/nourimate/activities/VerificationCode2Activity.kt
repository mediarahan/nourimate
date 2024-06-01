package com.telyu.nourimate.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.telyu.nourimate.databinding.ActivityVerificationCode2Binding
import android.app.Activity
import android.graphics.drawable.GradientDrawable
import android.text.Editable
import android.text.TextWatcher
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import com.telyu.nourimate.R
import com.telyu.nourimate.fragments.ChangePasswordDialogFragment
import com.telyu.nourimate.utils.InputValidator
import com.telyu.nourimate.viewmodels.VerificationViewModel
import com.telyu.nourimate.viewmodels.ViewModelFactory


class VerificationCode2Activity : AppCompatActivity() {

    private lateinit var binding: ActivityVerificationCode2Binding
    private lateinit var viewModel: VerificationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerificationCode2Binding.inflate(layoutInflater)
        setStatusBarColor(resources.getColor(R.color.color25, theme))
        val view = binding.root
        setContentView(view)

        viewModel = obtainViewModel(this@VerificationCode2Activity)

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

        binding.TextViewReceiveCode.setOnClickListener {
            openVerification1Page()
        }

        binding.buttonVerify.setOnClickListener {
            val verificationCode = binding.verifyEditText.text.toString()

            when (verificationCode) {
                "123456" -> navigateToProfile()
                "234567" -> navigateToNavBar()
                "345678" -> navigateToPasswordPopupPage()
                else -> setResult(Activity.RESULT_OK)
            }
        }

        setupTextWatchers()

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

        binding.verifyEditText.addTextChangedListener(textWatcher)
    }

    private fun checkAllInputsValid() {
        val verify = binding.verifyEditText.text.toString()
        val isValid = (verify.isNotEmpty()) && binding.verifyEditText.error == null
        if (isValid){
            enableVerifyButton()
        } else{
            disableVerifyButton()
        }
    }

    private fun disableVerifyButton() {
        binding.buttonVerify.isEnabled = false
        binding.buttonVerify.setBackground(ContextCompat.getDrawable(this, R.drawable.buttonlogin_background_disable))
        binding.buttonVerify.setTextColor(ContextCompat.getColor(this, R.color.color26))// Set to gray color
    }

    private fun enableVerifyButton() {
        binding.buttonVerify.isEnabled = true
        binding.buttonVerify.setBackground(ContextCompat.getDrawable(this, R.drawable.buttonlogin_background))  // Restore to primary color
        binding.buttonVerify.setTextColor(ContextCompat.getColor(this, R.color.color23))
    }

    private fun navigateToProfile() {
        //viewModel.setAccountStateAsVerified()
        startActivity(Intent(this, EditProfileActivity::class.java))
        finish() // Optional, to finish the current activity if going to a different screen
    }

    private fun navigateToNavBar() {
        startActivity(Intent(this, NavigationBarActivity::class.java))
        finish() // Optional, to finish the current activity if going to a different screen
    }


    private fun openVerification1Page() {
        // Buat Intent untuk membuka VerificationActivity
        val intent = Intent(this, VerificationCode1Activity::class.java)
        startActivity(intent)
    }

    private fun navigateToPasswordPopupPage() {
        val dialogFragment = ChangePasswordDialogFragment.newInstance()
        dialogFragment.show(supportFragmentManager, "changePasswordDialog")
    }
    private fun obtainViewModel(activity: AppCompatActivity): VerificationViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[VerificationViewModel::class.java]
    }


}

