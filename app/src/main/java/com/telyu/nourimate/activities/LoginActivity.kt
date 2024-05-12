package com.telyu.nourimate.activities

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.telyu.nourimate.databinding.ActivityLoginBinding
import android.widget.Toast
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts.*
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import com.telyu.nourimate.R
import com.telyu.nourimate.data.remote.Result
import com.telyu.nourimate.utils.GeneralUtil
import com.telyu.nourimate.utils.InputValidator
import com.telyu.nourimate.viewmodels.LoginViewModel
import com.telyu.nourimate.viewmodels.ViewModelFactory
import kotlin.system.exitProcess

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    //firebase auth w/ google account
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginViewModel = obtainViewModel(this@LoginActivity)


        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()
        configureGoogleSignIn()
    }

    private fun setupListeners() {

        binding.TextViewSignUp.setOnClickListener {
            openSignUpPage()
        }
        binding.buttonLogin.setOnClickListener {
            login()
        }
        binding.buttonSignInWithGoogle.setOnClickListener {
            openVerificationPage()
        }
        binding.TextViewForgotPassword.setOnClickListener {
            openVerificationPage()
        }
    }

    private fun configureGoogleSignIn() {
        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        //Initialize firebase auth
        auth = Firebase.auth

        binding.buttonSignInWithGoogle.setOnClickListener {
            signIn()
        }
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        resultLauncher.launch(signInIntent)
    }

    private var resultLauncher = registerForActivityResult(
        StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                //Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    updateUI(null)
                }
            }
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null) {
            startActivity(Intent(this@LoginActivity, VerificationCode1Activity::class.java))
            finish()
        }
    }

    private fun login() {
        val email = binding.emailEditText.text.toString()
        val password = binding.passwordEditText.text.toString()

        if (InputValidator.isValidEmail(email) && InputValidator.isValidPassword(password)) {
            loginViewModel.uiState.observe(this) { result ->
                when (result) {
                    is Result.Loading ->
                        showLoading(true)
                    is Result.Success -> {
                        showLoading(false)
                        observeLoginStatus()
                    }

                    is Result.Error -> {
                        showLoading(false)
                        Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            loginViewModel.login(email, password)
        } else {
            Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
        }
    }


    private fun observeLoginStatus() {
        loginViewModel.userLoginState.observe(this) { state ->
            when (state) {
                1 -> {
                    startActivity(Intent(this, VerificationCode1Activity::class.java))
                    finish()
                }
                2 -> showDetailsNeededDialog()
                3 -> {
                    startActivity(Intent(this, NavigationBarActivity::class.java))
                    finish()
                }

                else -> {
                    // Handle initial state or re-enter credentials
                    Toast.makeText(this, "Please enter your credentials", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun showVerificationNeededDialog() {
        GeneralUtil.showDialog(
            context = this,
            title = "Account Not Verified",
            message = "Please verify your account first.",
            onYes = {
                startActivity(Intent(this, VerificationCode1Activity::class.java))
                finish()
            },
            onNo = {
                validateInputs()
            }
        )
    }

    private fun showDetailsNeededDialog() {
        GeneralUtil.showDialog(
            context = this,
            title = "Account Details Empty",
            message = "Please fill out your account details first.",
            onYes = {
                startActivity(Intent(this, EditProfileActivity::class.java))
                finish()
            },
            onNo = {
                validateInputs()
            }
        )
    }

    private fun openVerificationPage() {
        // Buat Intent untuk membuka SignUpActivity
        val intent = Intent(this, VerificationCode1Activity::class.java)
        startActivity(intent)
        finish()
    }

    private fun openSignUpPage() {
        // Buat Intent untuk membuka SignUpActivity
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }


    private fun obtainViewModel(activity: AppCompatActivity): LoginViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[LoginViewModel::class.java]
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun validateInputs() {
        binding.emailEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //gak dipake
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //gak dipake
            }

            override fun afterTextChanged(p0: Editable?) {
                val email = p0.toString().trim()

                if (InputValidator.isValidEmail(email)) {
                    binding.emailEditText.error = null
                } else {
                    binding.emailEditText.setError("Masukkan email yang valid", null)
                }

            }
        })

        binding.passwordEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //gak dipake
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val password = p0.toString().trim()

                if (InputValidator.isValidPassword(password)) {
                    binding.passwordEditText.error = null
                } else {
                    binding.passwordEditText.setError(
                        "Password tidak boleh kurang dari 8 karakter",
                        null
                    )
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                //gak dipake
            }
        })
    }

    override fun onBackPressed() {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Exit App")
        alertDialogBuilder.setMessage("Are you sure you want to exit the app?")
        alertDialogBuilder.setPositiveButton("Yes") { _, _ ->
            finishAffinity()
        }
        alertDialogBuilder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }
        alertDialogBuilder.show()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    companion object {
        private const val TAG = "LoginActivity"
    }
}

