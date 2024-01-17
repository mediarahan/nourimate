package com.telyu.nourimate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.telyu.nourimate.databinding.ActivityLoginBinding
import android.widget.Toast
import android.content.Intent

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initLogin()

        binding.TextViewSignUp.setOnClickListener {
            openSignUpPage()
        }
        binding.buttonLogin.setOnClickListener {
            openNavBarPage()
        }
        binding.buttonSignInWithGoogle.setOnClickListener {
            openVerificationPage()
        }
        binding.TextViewForgotPassword.setOnClickListener {
            openVerificationPage()
        }
    }

    private fun initLogin() {
        binding.buttonLogin.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            showToast("Login clicked. Username: $email, Password: $password")
        }
    }

    private fun openSignUpPage() {
        // Buat Intent untuk membuka SignUpActivity
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }
    private fun openNavBarPage() {
        val intent = Intent(this, NavigationBarActivity::class.java)
        startActivity(intent)
    }
    private fun openVerificationPage() {
        // Buat Intent untuk membuka SignUpActivity
        val intent = Intent(this, VerificationCode1Activity::class.java)
        startActivity(intent)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

