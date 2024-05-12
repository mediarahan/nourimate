package com.telyu.nourimate.activities

import android.os.Bundle
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.telyu.nourimate.data.local.models.User
import com.telyu.nourimate.data.remote.Result
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

        if (!InputValidator.isValidEmail(email) || !InputValidator.isValidPassword(password)) {
            Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
            return //return disini maksudnya lebih mirip ke break / pass kalau di Python. Untuk flow control, bukan returnnya fungsi
        }

        val user = User(0, fullName, email, phoneNumber, password, 1)

        signUpViewModel.uiState.observe(this) {result ->
            when (result) {
                is Result.Loading -> {
                    showLoading(true)
                }
                is Result.Success -> {
                    showLoading(false)
                    openLoginPage()
                    //Perlu pakai finish supaya user tidak kembali ke menu register setelah mengisi data
                    finish()
                    Toast.makeText(this, "Signed Up Successfully", Toast.LENGTH_SHORT).show()
                }
                is Result.Error -> {
                    showLoading(false)
                    Toast.makeText(this, "Failed to Sign Up", Toast.LENGTH_SHORT).show()
                }
            }
        }

        signUpViewModel.signup(password, confirmPassword, user)
    }

    private fun openLoginPage() {
        // Buat Intent untuk membuka LoginActivity. Tidak perlu pakai finish
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun obtainViewModel(activity: AppCompatActivity): SignUpViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[SignUpViewModel::class.java]
    }

//    private fun validateInputs() {
//        binding.editTextEmail.addTextChangedListener(object: TextWatcher {
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                //gak dipake
//            }
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                //gak dipake
//            }
//
//            override fun afterTextChanged(p0: Editable?) {
//                val email = p0.toString().trim()
//
//                if (InputValidator.isValidEmail(email)) {
//                    binding.editTextEmail.error = null
//                } else {
//                    binding.editTextEmail.setError("Masukkan email yang valid", null)
//                }
//
//            }
//        })
//
//        binding.editTextPassword.addTextChangedListener(object: TextWatcher {
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                //gak dipake
//            }
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                val password = p0.toString().trim()
//
//                if (InputValidator.isValidPassword(password)) {
//                    binding.editTextPassword.error = null
//                } else {
//                    binding.editTextPassword.setError("Password tidak boleh kurang dari 8 karakter", null)
//                }
//            }
//
//            override fun afterTextChanged(p0: Editable?) {
//                //gak dipake
//            }
//        })
//    }

}
