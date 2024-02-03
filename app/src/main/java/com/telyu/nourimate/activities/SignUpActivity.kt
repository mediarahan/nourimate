package com.telyu.nourimate.activities

import android.os.Bundle
import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.telyu.nourimate.data.local.models.User
import com.telyu.nourimate.databinding.ActivitySignUpBinding
import com.telyu.nourimate.viewmodels.SignUpViewModel
import com.telyu.nourimate.viewmodels.ViewModelFactory

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var signUpViewModel: SignUpViewModel

    private var user: User? = null //entity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        signUpViewModel = obtainViewModel(this@SignUpActivity)

        //setup all ui
        setupButtons()
    }

    private fun setupButtons() {
        binding.buttonRegister.setOnClickListener {
            signup()
        }

        binding.TextViewSignIn.setOnClickListener {
            openLoginPage()
        }
    }

    private fun signup() {
        //call signup function from viewmodel here
        val fullName = binding.editTextFullName.text.toString()
        val phoneNumber = binding.editTextPhone.text.toString().toInt()
        val email = binding.editTextEmail.text.toString()
        val password = binding.editTextPassword.text.toString()
        val confirmPassword = binding.editTextConfirmPassword.text.toString()

        val isPasswordMatch = signUpViewModel.signup(password, confirmPassword)

        if (isPasswordMatch) {
            user = User(fullName, email,phoneNumber , password)

            signUpViewModel.insertUser(user as User)

            val intent = Intent(this, VerificationCode1Activity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
        }
    }

    private fun openLoginPage() {
        // Buat Intent untuk membuka LoginActivity
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun obtainViewModel(activity: AppCompatActivity): SignUpViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[SignUpViewModel::class.java]
    }

}
