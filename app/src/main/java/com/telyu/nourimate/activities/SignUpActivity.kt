package com.telyu.nourimate.activities

import android.os.Bundle
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.telyu.nourimate.data.local.models.User
import com.telyu.nourimate.databinding.ActivitySignUpBinding
import com.telyu.nourimate.utils.InputValidator
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
        val phoneNumber = binding.editTextPhone.text.toString().toLong()
        val email = binding.editTextEmail.text.toString()
        val password = binding.editTextPassword.text.toString()
        val confirmPassword = binding.editTextConfirmPassword.text.toString()

        val isPasswordMatch = signUpViewModel.signup(password, confirmPassword)

        if (InputValidator.isValidEmail(email) && InputValidator.isValidPassword(password)) {

            if (isPasswordMatch) {
                user = User(0,fullName, email, phoneNumber, password)

                signUpViewModel.insertUser(user as User)

                //TODO: Jangan lupa balikin lagi ke verifikasi kalau udah di implementasiin
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            }
        } else {
            // Show a Toast message indicating the validation error
            Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
        }
    }

    private fun openLoginPage() {
        // Buat Intent untuk membuka LoginActivity
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }



    private fun validateInputs() {
        binding.editTextEmail.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //gak dipake
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //gak dipake
            }

            override fun afterTextChanged(p0: Editable?) {
                val email = p0.toString().trim()

                if (InputValidator.isValidEmail(email)) {
                    binding.editTextEmail.error = null
                } else {
                    binding.editTextEmail.setError("Masukkan email yang valid", null)
                }

            }
        })

        binding.editTextPassword.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //gak dipake
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val password = p0.toString().trim()

                if (InputValidator.isValidPassword(password)) {
                    binding.editTextPassword.error = null
                } else {
                    binding.editTextPassword.setError("Password tidak boleh kurang dari 8 karakter", null)
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                //gak dipake
            }
        })
    }

    private fun obtainViewModel(activity: AppCompatActivity): SignUpViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[SignUpViewModel::class.java]
    }

}
