package com.telyu.nourimate.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.telyu.nourimate.databinding.ActivityVerificationCode2Binding
import android.app.Activity
import android.graphics.drawable.GradientDrawable
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.telyu.nourimate.R
import com.telyu.nourimate.viewmodels.VerificationViewModel
import com.telyu.nourimate.viewmodels.ViewModelFactory


class VerificationCode2Activity : AppCompatActivity() {

    private lateinit var binding: ActivityVerificationCode2Binding
    private lateinit var viewModel: VerificationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerificationCode2Binding.inflate(layoutInflater)
        window.statusBarColor = ContextCompat.getColor(this, R.color.color25)
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
                "1" -> navigateToProfile()
                "2" -> navigateToNavBar()
                "3" -> navigateToPasswordPopupPage()
                else -> setResult(Activity.RESULT_OK)
            }
        }
    }

    private fun navigateToProfile() {
        viewModel.setAccountStateAsVerified()
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
        // Buat Intent untuk membuka VerificationActivity
        val intent = Intent(this, PasswordPopupActivity::class.java)
        startActivity(intent)
    }
    private fun obtainViewModel(activity: AppCompatActivity): VerificationViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[VerificationViewModel::class.java]
    }


}

