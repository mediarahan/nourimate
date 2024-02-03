package com.telyu.nourimate.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.telyu.nourimate.databinding.ActivityVerificationCode2Binding
import android.app.Activity


class VerificationCode2Activity : AppCompatActivity() {

    private lateinit var binding: ActivityVerificationCode2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerificationCode2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

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

}

