package com.telyu.nourimate


import android.os.Bundle
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.telyu.nourimate.databinding.ActivityVerificationCode1Binding

class VerificationCode1Activity : AppCompatActivity() {
    private lateinit var binding: ActivityVerificationCode1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerificationCode1Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Tambahkan logika sign-up jika diperlukan
        initVerificationCode1()
    }

    private fun initVerificationCode1() {
        binding.buttonVerifEmail.setOnClickListener {
            openVerification2Page()
        }
        binding.buttonVerifNumber.setOnClickListener {
            openVerification2Page()
        }
    }


    private fun openVerification2Page() {
        // Buat Intent untuk membuka VerificationActivity
        val intent = Intent(this, VerificationCode2Activity::class.java)
        startActivity(intent)
    }
}
