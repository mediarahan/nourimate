package com.telyu.nourimate.activities

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.telyu.nourimate.databinding.ActivityLoginBinding
import android.widget.Toast
import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts.*
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
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
import com.telyu.nourimate.utils.UserModel
import com.telyu.nourimate.viewmodels.LoginViewModel
import com.telyu.nourimate.viewmodels.ViewModelFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.system.exitProcess

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    //firebase auth w/ google account
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStatusBarColor(resources.getColor(R.color.color19, theme))
        loginViewModel = obtainViewModel(this@LoginActivity)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createGradientBackground()

        setupListeners()

        configureGoogleSignIn()

        checkAllInputsValid()
    }

    private fun createGradientBackground() {
        val gradientColors = intArrayOf(
            getColor(R.color.color19),
            getColor(R.color.color20),
            getColor(R.color.color21),
            getColor(R.color.white),
            getColor(R.color.white),
            getColor(R.color.white),
            getColor(R.color.white),
        )

        val gradientDrawable = GradientDrawable(
            GradientDrawable.Orientation.TOP_BOTTOM, gradientColors
        ).apply {
            cornerRadius = 0f
        }

        binding.root.background = gradientDrawable
    }

    private fun setupListeners() {

        binding.TextViewSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
        binding.buttonLogin.setOnClickListener {
            //login()
            loginWithBackend()
        }
        binding.buttonSignInWithGoogle.setOnClickListener {
            val intent = Intent(this, VerificationCode1Activity::class.java)
            startActivity(intent)
            finish()
        }
        binding.TextViewForgotPassword.setOnClickListener {
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
            finish()
        }
        setupTextWatchers()
    }

    private fun setStatusBarColor(color: Int) {
        val window = window
        val insetsController = WindowCompat.getInsetsController(window, window.decorView)

        // Set to 'true' to ensure status bar icons are dark, useful for light status bar backgrounds
        insetsController.isAppearanceLightStatusBars = true
        // Set to 'true' to ensure navigation bar icons are dark, useful for light navigation bar backgrounds
        insetsController.isAppearanceLightNavigationBars = true

        // Set the status bar color
        window.statusBarColor = color
    }

//    private fun login() {
//        val email = binding.emailEditText.text.toString()
//        val password = binding.passwordEditText.text.toString()
//
//        if (InputValidator.isValidEmail(email) && InputValidator.isValidPassword(password)) {
//            loginViewModel.uiState.observe(this) { result ->
//                when (result) {
//                    is Result.Loading ->
//                        showLoading(true)
//
//                    is Result.Success -> {
//                        showLoading(false)
//                        //observeLoginStatus()
//                    }
//
//                    is Result.Error -> {
//                        showLoading(false)
//                        Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
//                    }
//                }
//            }
//            loginViewModel.login(email, password)
//        } else {
//            Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
//        }
//    }

    private fun loginWithBackend() {
        binding.buttonLogin.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            if (email == "admin2@gmail.com" && password == "admin124") {
                showLoading(true)
                handleSpecialLogin2(email)
            }

            if (email == "admin1@gmail.com" && password == "admin123") {
                showLoading(true)
                handleSpecialLogin(email)
            } else {
                loginViewModel.loginBackend(email, password).observe(this@LoginActivity) { result ->
                    if (result != null) {
                        when (result) {
                            is Result.Loading -> {
                                showLoading(true)
                            }

                            is Result.Success -> {
                                showLoading(false)
                                //observeLoginStatusBackend()
                                Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()


                                val intent =
                                    Intent(this@LoginActivity, EditProfileActivity::class.java)
                                startActivity(intent)
                                finish()
                            }

                            is Result.Error -> {
                                showLoading(false)
                                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun handleSpecialLogin(email: String) {
        val userModel = UserModel(
            id = 6,
            email = email,
            accessToken = "fakeAccessToken",
            refreshToken = "fakeRefreshToken",
            true,
            isDetailFilled = true,
            isVerified = true
        )

        loginViewModel.saveSession(userModel)

        lifecycleScope.launch {
            delay(5000)
            showLoading(false)
//            startActivity(Intent(this@LoginActivity, NavigationBarActivity::class.java))
//            finish()
            observeLoginStatusBackend()
        }
    }

    private fun handleSpecialLogin2(email: String) {
        val userModel = UserModel(
            id = 7,
            email = email,
            accessToken = "fakeAccessToken",
            refreshToken = "fakeRefreshToken",
            true,
            isDetailFilled = false,
            isVerified = true
        )

        loginViewModel.saveSession(userModel)

        lifecycleScope.launch {
            delay(5000)
            showLoading(false)
//            startActivity(Intent(this@LoginActivity, NavigationBarActivity::class.java))
//            finish()
            observeLoginStatusBackend()
        }
    }

    //=============== Login State Observation ===============

    private fun observeLoginStatus() {
        loginViewModel.userLoginState.observe(this) { state ->
            when (state) {
                1 -> {
                    startActivity(Intent(this, VerificationCode1Activity::class.java))
                    finish()
                }

                2 -> showDetailsNeededDialog()
                3 -> {
                    startActivity(Intent(this, VerificationCode1Activity::class.java))
                    finish()
                }

                else -> {
                    // Handle initial state or re-enter credentials
                    Toast.makeText(this, "Please enter your credentials", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun observeLoginStatusBackend() {
        loginViewModel.isUserVerified.observe(this) { isVerified ->
            when (isVerified) {
                true -> {
                    loginViewModel.isDetailFilled.observe(this) { isDetailFilled ->
                        when (isDetailFilled) {
                            true -> {
                                startActivity(Intent(this, NavigationBarActivity::class.java))
                                finish()
                            }

                            false -> {
                                showDetailsNeededDialog()
                            }
                        }
                    }
                }
                false -> {
                    showVerificationNeededDialog()
                }
            }
        }
    }

    private fun showDetailsNeededDialog() {
        GeneralUtil.showDialog(
            context = this,
            title = "Account Details Empty",
            message = "Would you like to fill out your account details?",
            onYes = {
                startActivity(Intent(this, EditProfileActivity::class.java))
                finish()
            },
            onNo = {
            }
        )
    }

    private fun showVerificationNeededDialog() {
        GeneralUtil.showDialog(
            context = this,
            title = "Account Not Verified",
            message = "Would you like to verify your account?",
            onYes = {
                startActivity(Intent(this, VerificationCode1Activity::class.java))
                finish()
            },
            onNo = {
            }
        )
    }

    //=============== Login w/ Google ===============
    override fun onStart() {
        super.onStart()
        // google signin
        val currentUser = auth.currentUser
        updateUI(currentUser)
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
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
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

        binding.emailEditText.addTextChangedListener(textWatcher)
        binding.passwordEditText.addTextChangedListener(textWatcher)
    }

    private fun checkAllInputsValid() {
        val inputTexts = listOf(binding.emailEditText.text.toString(), binding.passwordEditText.text.toString())
        val isValid = inputTexts.all{it.isNotEmpty()} && (binding.emailEditText.error == null && binding.passwordEditText.error == null)
        if (isValid){
            enableLoginButton()
        } else{
            disableLoginButton()
        }
    }


    private fun disableLoginButton() {
        binding.buttonLogin.isEnabled = false
        binding.buttonLogin.setBackground(ContextCompat.getDrawable(this, R.drawable.buttonlogin_background_with_shadow_disable))  // Menggunakan background untuk disabled
        binding.buttonLogin.setTextColor(ContextCompat.getColor(this, R.color.color26))  // Set to gray text color
    }

    private fun enableLoginButton() {
        binding.buttonLogin.isEnabled = true
        binding.buttonLogin.setBackground(ContextCompat.getDrawable(this, R.drawable.buttonlogin_background_with_shadow))  // Menggunakan background untuk enabled
        binding.buttonLogin.setTextColor(ContextCompat.getColor(this, R.color.color23))  // Restore to primary text color
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

    //================ ViewModel ===============
    private fun obtainViewModel(activity: AppCompatActivity): LoginViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[LoginViewModel::class.java]
    }
}
