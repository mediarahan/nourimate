package com.telyu.nourimate


import android.os.Bundle
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.telyu.nourimate.databinding.ActivityVerificationCode2Binding

class VerificationCode2Activity : AppCompatActivity() {
    private lateinit var binding: ActivityVerificationCode2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerificationCode2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initVerificationCode2()
    }

    private fun initVerificationCode2() {
        binding.buttonVerify.setOnClickListener {
            openHomePage()
        }
    }


    private fun openHomePage() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
}
