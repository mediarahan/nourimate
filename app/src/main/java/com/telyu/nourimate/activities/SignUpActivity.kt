package com.telyu.nourimate.activities

import android.os.Bundle
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import com.telyu.nourimate.R
import com.telyu.nourimate.data.local.models.User
import com.telyu.nourimate.data.remote.Result
import com.telyu.nourimate.databinding.ActivitySignUpBinding
import com.telyu.nourimate.utils.InputValidator
import com.telyu.nourimate.viewmodels.SignUpViewModel
import com.telyu.nourimate.viewmodels.ViewModelFactory

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var signUpViewModel: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStatusBarColor(resources.getColor(R.color.color2, theme))
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        signUpViewModel = obtainViewModel(this@SignUpActivity)
        //setup all ui
        setupButtons()

        checkAllInputsValid()
    }

    private fun setupButtons() {
        //daftar pake lokal
        binding.buttonRegister.setOnClickListener {
            //Backend
            registerWithBackend()
            //Lokal
            //signup()
        }

        binding.TextViewSignIn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        setupTextWatchers()
    }

    private fun setStatusBarColor(color: Int) {
        val window = window
        val insetsController = WindowCompat.getInsetsController(window, window.decorView)

        insetsController.isAppearanceLightStatusBars = true
        insetsController.isAppearanceLightNavigationBars = true

        window.statusBarColor = color
    }

    //=============== Regular Signup ===============

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

        //val user = User(0, fullName, email, phoneNumber, password)

//        signUpViewModel.uiState.observe(this) { result ->
//            when (result) {
//                is Result.Loading -> {
//                    showLoading(true)
//                }
//                is Result.Success -> {
//                    showLoading(false)
//                    val intent = Intent(this, LoginActivity::class.java)
//                    startActivity(intent)
//                    //Perlu pakai finish supaya user tidak dapat kembali ke menu register setelah mengisi data
//                    finish()
//                    Toast.makeText(this, "Signed Up Successfully", Toast.LENGTH_SHORT).show()
//                }
//                is Result.Error -> {
//                    showLoading(false)
//                    Toast.makeText(this, "Failed to Sign Up", Toast.LENGTH_SHORT).show()
//                }
//            }
//        }

       // signUpViewModel.signup(password, confirmPassword, user)
    }

    //=============== Signup w/ Backend ===============

    private fun registerWithBackend() {
        val name =binding.editTextFullName.text.toString()
        val phone =binding.editTextPhone.text.toString()
        val email =binding.editTextEmail.text.toString()
        val password =binding.editTextPassword.text.toString()

        signUpViewModel.registerBackend(name, phone, email, password).observe(this@SignUpActivity){result ->
            if (result != null) {
                when (result) {
                    is Result.Loading -> {
                        showLoading(true)
                    }
                    is Result.Success -> {
                        Toast.makeText(this, "Signed Up Successfully", Toast.LENGTH_SHORT).show()
                        showLoading(false)

                        val intent = Intent(this, VerificationCode1Activity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    is Result.Error -> {
                        showLoading(false)
                        Toast.makeText(this, "Failed to Sign Up", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun obtainViewModel(activity: AppCompatActivity): SignUpViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[SignUpViewModel::class.java]
    }

    //=============== Utility ===============

    private fun setupTextWatchers() {
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkAllInputsValid()
            }

            override fun afterTextChanged(s: Editable?) {
                checkAllInputsValid()
            }
        }

        binding.editTextFullName.addTextChangedListener(textWatcher)
        binding.editTextPhone.addTextChangedListener(textWatcher)
        binding.editTextEmail.addTextChangedListener(textWatcher)
        binding.editTextPassword.addTextChangedListener(textWatcher)
        binding.editTextConfirmPassword.addTextChangedListener(textWatcher)
    }

    private fun checkAllInputsValid() {
        val inputTexts = listOf(binding.editTextFullName.text.toString(), binding.editTextPhone.text.toString(),
            binding.editTextEmail.text.toString(), binding.editTextPassword.text.toString(), binding.editTextConfirmPassword.text.toString())
        val isValid = inputTexts.all{it.isNotEmpty()} && (binding.editTextFullName.error == null
                && binding.editTextPhone.error == null && binding.editTextEmail.error == null && binding.editTextPassword.error == null
                && binding.editTextConfirmPassword.error == null)
        if (isValid){
            enableRegisterButton()
        } else{
            disableRegisterButton()
        }
    }


    private fun disableRegisterButton() {
        binding.buttonRegister.isEnabled = false
        binding.buttonRegister.background = ContextCompat.getDrawable(this, R.drawable.buttonlogin_background_with_shadow_disable)  // Menggunakan background untuk disabled
        binding.buttonRegister.setTextColor(ContextCompat.getColor(this, R.color.color26))  // Set to gray text color
    }

    private fun enableRegisterButton() {
        binding.buttonRegister.isEnabled = true
        binding.buttonRegister.background = ContextCompat.getDrawable(this, R.drawable.buttonlogin_background_with_shadow)  // Menggunakan background untuk enabled
        binding.buttonRegister.setTextColor(ContextCompat.getColor(this, R.color.color23))  // Restore to primary text color
    }

}