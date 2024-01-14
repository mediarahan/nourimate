package com.telyu.nourimate


import android.os.Bundle
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.telyu.nourimate.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Tambahkan logika sign-up jika diperlukan
        initSignUp()
    }

    private fun initSignUp() {
        binding.buttonRegister.setOnClickListener {
            // Handle sign-up logic here

            // Setelah berhasil sign-up, buka halaman verifikasi kode
            openVerificationPage()
        }
    }

    private fun openVerificationPage() {
        // Buat Intent untuk membuka VerificationActivity
        val intent = Intent(this, VerificationCode1Activity::class.java)
        startActivity(intent)
    }
}
