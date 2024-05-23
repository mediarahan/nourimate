package com.telyu.nourimate.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.telyu.nourimate.R
import com.telyu.nourimate.databinding.ActivitySignUpBinding
import com.telyu.nourimate.databinding.ActivityTransitionProgramBinding
import com.telyu.nourimate.viewmodels.SignUpViewModel
import com.telyu.nourimate.viewmodels.TransitionProgramViewModel
import com.telyu.nourimate.viewmodels.ViewModelFactory

class TransitionProgramActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTransitionProgramBinding
    private lateinit var viewModel: TransitionProgramViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransitionProgramBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = obtainViewModel(this@TransitionProgramActivity)

        binding.textViewDetailCongrats.setOnClickListener {
            Log.d("huhahh", "Click! ")
            val intent = Intent(this, NavigationBarActivity::class.java).apply {
                putExtra("loadFragment", "HistoryFragment")
            }
            startActivity(intent)
        }


    }

    private fun obtainViewModel(activity: AppCompatActivity): TransitionProgramViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[TransitionProgramViewModel::class.java]
    }

}