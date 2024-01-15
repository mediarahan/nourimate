package com.telyu.nourimate

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.telyu.nourimate.databinding.ActivityVerificationCode2Binding

class VerificationCode2Activity : AppCompatActivity() {

    private lateinit var binding: ActivityVerificationCode2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerificationCode2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.buttonVerify.setOnClickListener {
            val verificationCode = binding.editTextVerificationCode.text.toString()

            when (verificationCode) {
                "1" -> navigateToProfile()
                "2" -> navigateToHome()
                else -> navigateToVerficationCode1()
            }
        }
    }

    private fun navigateToProfile() {
        startActivity(Intent(this, EditProfileActivity::class.java))
        finish() // Optional, to finish the current activity if going to a different screen
    }

    private fun navigateToHome() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish() // Optional, to finish the current activity if going to a different screen
    }

    private fun navigateToVerficationCode1() {
        startActivity(Intent(this, VerificationCode1Activity::class.java))
        finish() // Optional, to finish the current activity if going to a different screen
    }
}

