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
        initSignUp()

        val fullName = binding.editTextFullName.text.toString()
        val phoneNumber = binding.editTextPhone.text.toString()
        val email = binding.editTextEmail.text.toString()
        val password = binding.editTextPassword.text.toString()
        val confirmPassword = binding.editTextConfirmPassword.text.toString()

        binding.TextViewSignIn.setOnClickListener {
            openLoginPage()
        }
    }

    private fun initSignUp() {
        binding.buttonRegister.setOnClickListener {
            openVerificationPage()
        }
    }

    private fun openVerificationPage() {
        val intent = Intent(this, VerificationCode1Activity::class.java)
        startActivity(intent)
    }

    private fun openLoginPage() {
        // Buat Intent untuk membuka SignUpActivity
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}
