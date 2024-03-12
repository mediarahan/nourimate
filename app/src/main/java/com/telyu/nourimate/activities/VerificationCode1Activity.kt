package com.telyu.nourimate.activities


import android.os.Bundle
import android.content.Intent
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.telyu.nourimate.R
import com.telyu.nourimate.databinding.ActivityVerificationCode1Binding

class VerificationCode1Activity : AppCompatActivity() {
    private lateinit var binding: ActivityVerificationCode1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerificationCode1Binding.inflate(layoutInflater)
        window.statusBarColor = ContextCompat.getColor(this, R.color.color25)
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

        // Tambahkan logika sign-up jika diperlukan
        initVerificationCode1()
    }

    private fun initVerificationCode1() {
        binding.buttonVerifEmail.setOnClickListener {
            openVerification2Page()
        }
        binding.buttonVerifNumber.setOnClickListener {
            openVerification3Page()
        }
    }


    private fun openVerification2Page() {
        // Buat Intent untuk membuka VerificationActivity
        val intent = Intent(this, VerificationCode2Activity::class.java)
        startActivity(intent)
    }

    private fun openVerification3Page() {
        // Buat Intent untuk membuka VerificationActivity
        val intent = Intent(this, VerificationCode3Activity::class.java)
        startActivity(intent)
    }
}
