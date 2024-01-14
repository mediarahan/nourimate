package com.telyu.nourimate

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.telyu.nourimate.databinding.ActivityVerificationCode1Binding

class VerificationCode1Activity : AppCompatActivity() {
    private lateinit var binding: ActivityVerificationCode1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerificationCode1Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Lakukan inisialisasi atau logika verifikasi kode di sini
    }
}
