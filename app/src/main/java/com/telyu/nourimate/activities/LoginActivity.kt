package com.telyu.nourimate.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.telyu.nourimate.R
import com.telyu.nourimate.data.remote.Result
import com.telyu.nourimate.databinding.ActivityLoginBinding
import com.telyu.nourimate.utils.GeneralUtil
import com.telyu.nourimate.utils.InputValidator
import com.telyu.nourimate.utils.MealHistoryWorkManager
import com.telyu.nourimate.utils.MidnightWorkManager
import com.telyu.nourimate.utils.NotificationWorkManager
import com.telyu.nourimate.utils.UserModel
import com.telyu.nourimate.viewmodels.LoginViewModel
import com.telyu.nourimate.viewmodels.ViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

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

    private fun loginWithBackend() {
        val email = binding.emailEditText.text.toString()
        val password = binding.passwordEditText.text.toString()
        scheduleMidnightRecipeUpdate(this)
        scheduleNotifications(this)

        if (email == "admin2@gmail.com" && password == "admin124") {
            showLoading(true)
            handleSpecialLogin2(email)
        } else if (email == "admin1@gmail.com" && password == "admin123") {
            showLoading(true)
            handleSpecialLogin(email)
        } else {
            loginViewModel.loginBackend(email, password).observe(this@LoginActivity) { result ->
                when (result) {
                    is Result.Loading -> showLoading(true)
                    is Result.Success -> {
                        showLoading(false)
                        observeLoginStatusBackend()

                    }
                    is Result.Error -> {
                        showLoading(false)
                        Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun handleSpecialLogin(email: String) {
        val userModel = UserModel(
            id = 100,
            email = email,
            accessToken = "fakeAccessToken",
            refreshToken = "fakeRefreshToken",
            true,
            isDetailFilled = true,
            isVerified = true,
            name = "Admin1",
            phoneNumber = "1231231231"
        )

        loginViewModel.saveSession(userModel)

        lifecycleScope.launch {
            delay(1000)
            showLoading(false)
//            startActivity(Intent(this@LoginActivity, NavigationBarActivity::class.java))
//            finish()
            observeLoginStatusBackend()
        }
    }

    private fun handleSpecialLogin2(email: String) {
        val userModel = UserModel(
            id = 101,
            email = email,
            accessToken = "fakeAccessToken",
            refreshToken = "fakeRefreshToken",
            true,
            isDetailFilled = false,
            isVerified = true,
            name = "admin2",
            phoneNumber = "3213213213"
        )

        loginViewModel.saveSession(userModel)

        lifecycleScope.launch {
            delay(1000)
            showLoading(false)
//            startActivity(Intent(this@LoginActivity, NavigationBarActivity::class.java))
//            finish()
            observeLoginStatusBackend()
        }
    }

    //=============== Login State Observation ===============

    private fun observeLoginStatusBackend() {
        loginViewModel.isUserVerified.observe(this) { isVerified ->
            Log.d("isVerified", isVerified.toString())
            when (isVerified) {
                true -> {
                    loginViewModel.isDetailFilled.observe(this) { isDetailFilled ->
                        Log.d("isDetailFilled", isDetailFilled.toString())
                        when (isDetailFilled) {
                            true -> {
                                lifecycleScope.launch {
                                    try {
                                        // Perform all checks and wait for all to complete before moving to the next activity.
                                        coroutineScope {
                                            // Fetch and insert necessary data, and wait for completion
                                            val userDetails = async { loginViewModel.checkUserExists() }
                                            val userPrograms = async { loginViewModel.checkWeightTrackExists() }
                                            val userMealHistory = async { loginViewModel.checkMealHistoryExists() }
                                            val userHistory = async { loginViewModel.checkHistoryExists() }

                                            // Await all async operations
                                            userDetails.await()
                                            userPrograms.await()
                                            userMealHistory.await()
                                            userHistory.await()
                                        }

                                        // Proceed to the next activity only after all operations are complete
                                        navigateToNextActivity()

                                    } catch (e: Exception) {
                                        // Handle errors if any of the fetch operations fail
                                        Log.e("LoginActivity", "Error during login sequence: ${e.message}")
                                        Toast.makeText(this@LoginActivity, "Error during login sequence", Toast.LENGTH_SHORT).show()
                                    }
                                }
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

    private fun navigateToNextActivity() {
        Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this@LoginActivity, NavigationBarActivity::class.java))
        finish()
    }


    private fun showDetailsNeededDialog() {
        GeneralUtil.showDialog(
            context = this,
            title = "Account Details Empty",
            message = "Would you like to fill out your account details?",
            onYes = {
                loginViewModel.logout()
                startActivity(Intent(this, EditProfileActivity::class.java))
                finish()
            },
            onNo = {
                validateInputs()
            }
        )
    }

    private fun showVerificationNeededDialog() {
        GeneralUtil.showDialog(
            context = this,
            title = "Account Not Verified",
            message = "Would you like to verify your account?",
            onYes = {
                loginViewModel.logout()
                startActivity(Intent(this, VerificationCode1Activity::class.java))
                finish()
            },
            onNo = {
                validateInputs()
            }
        )
    }

    //=============== Login w/ Google ===============

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
                // Google Sign In was successful, extract necessary info
                val account = task.getResult(ApiException::class.java)!!
                val email = account.email
                val idToken = account.idToken

                // You can now use the email and ID token to send to your backend
                sendLoginToBackend(email, idToken)
            } catch (e: ApiException) {
                // Handle Google Sign In failure
            }
        }
    }

    private fun sendLoginToBackend(email: String?, idToken: String?) {
        if (email != null && idToken != null) {
            loginViewModel.googleSignIn(email).observe(this) { result ->
                when (result) {
                    is Result.Loading -> showLoading(true)
                    is Result.Success -> {
                        lifecycleScope.launch {
                            try {
                                // Initiate and wait for all checks to complete before proceeding.
                                coroutineScope {
                                    // Launch all check functions concurrently
                                    val userExists = async { loginViewModel.checkUserExists() }
                                    val weightTrackExists = async { loginViewModel.checkWeightTrackExists() }
                                    val mealHistoryExists = async { loginViewModel.checkMealHistoryExists() }
                                    val historyExists = async { loginViewModel.checkHistoryExists() }

                                    // Await all operations to complete
                                    userExists.await()
                                    weightTrackExists.await()
                                    mealHistoryExists.await()
                                    historyExists.await()
                                }

                                // Navigate to the next activity only after all operations complete
                                navigateToNextActivity()

                            } catch (e: Exception) {
                                // Handle errors if any of the check operations fail
                                showLoading(false)
                                Toast.makeText(this@LoginActivity, "Login sequence error: ${e.message}", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                    is Result.Error -> {
                        showLoading(false)
                        Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        } else {
            Toast.makeText(this, "Email or token is missing", Toast.LENGTH_SHORT).show()
        }
    }

    //=============== Utility ===============

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

    //================ Skejuled Tasks ===============
    private fun scheduleMidnightRecipeUpdate(context: Context) {
        val constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .build()

        val midnightDelay = GeneralUtil.calculateInitialDelayForMidnight()

        val updateRequest = PeriodicWorkRequestBuilder<MidnightWorkManager>(1, TimeUnit.DAYS)
            .setInitialDelay(midnightDelay, TimeUnit.MILLISECONDS)
            .setConstraints(constraints)
            .build()

        val updateRequest2 = PeriodicWorkRequestBuilder<MealHistoryWorkManager>(1, TimeUnit.DAYS)
            .setInitialDelay(midnightDelay, TimeUnit.MILLISECONDS)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(context).enqueue(updateRequest)
        WorkManager.getInstance(context).enqueue(updateRequest2)
    }



    //===== Notif Makanan and Stuff
    private fun scheduleNotifications(context: Context) {
        val times = listOf(
            Pair(Pair(7, "Good Morning"), "Time to start your day with a healthy breakfast!"),
            Pair(Pair(12, "Lunch Time"), "Don't forget to have some nutritious lunch!"),
            Pair(Pair(18, "Dinner Time"), "Time to wind down with a light dinner.")
        )

        times.forEach { (timePair, message) ->
            val (hour, title) = timePair

            val notificationData = Data.Builder()
                .putString("title", title)
                .putString("message", message)
                .build()

            val initialDelay = GeneralUtil.calculateDelayForNotification(hour)

            val dailyWorkRequest = PeriodicWorkRequestBuilder<NotificationWorkManager>(24, TimeUnit.HOURS)
                .setInitialDelay(initialDelay, TimeUnit.MILLISECONDS)
                .setInputData(notificationData)
                .build()

            WorkManager.getInstance(context).enqueue(dailyWorkRequest)
        }
    }


    //================ ViewModel ===============
    private fun obtainViewModel(activity: AppCompatActivity): LoginViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[LoginViewModel::class.java]
    }
}
