package com.telyu.nourimate.activities

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.view.View
import android.widget.Toast
import com.telyu.nourimate.databinding.ActivityEditProfileBinding
import com.telyu.nourimate.databinding.DialogRulerPickerBinding
import com.telyu.nourimate.custom.CurvedRulerView
import com.telyu.nourimate.fragments.CustomDatePickerFragment
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.telyu.nourimate.R
import com.telyu.nourimate.data.local.models.Detail
import com.telyu.nourimate.data.remote.Result
import com.telyu.nourimate.viewmodels.EditProfileViewModel
import com.telyu.nourimate.viewmodels.ViewModelFactory
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class EditProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditProfileBinding
    private lateinit var viewModel : EditProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this, R.color.color2)

        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewModel = obtainViewModel(this@EditProfileActivity)

        //array opsi
        val genderOptions = arrayOf("Laki-laki", "Perempuan")
        val allergiesOptions = arrayOf("Kacang", "Gluten")
        val diseaseOptions = arrayOf("Hipertensi", "Diabetes", "Kolesterol")

        binding.editTextHeight

        binding.editTextWaist

        binding.editTextBirth.setOnClickListener {
            showDatePickerDialog()
        }

        binding.editTextWeight.setOnClickListener {
            showRulerPickerDialog()
        }


        //setup spinner
        val genderAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, genderOptions)
        binding.spinnerGender.adapter = genderAdapter
        val allergiesAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, allergiesOptions)
        binding.spinnerAllergies.adapter = allergiesAdapter
        val diseaseAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, diseaseOptions)
        binding.spinnerPersonalDisease.adapter = diseaseAdapter

        //Activity onCreate
        binding.buttonNext.setOnClickListener {
            viewModel.setAccountStateAsCompleted()
            insertUserDetails()
            setupObservers()
        }
    }

    private fun insertUserDetails() {
        //all input
        val heightString = binding.editTextHeight.text.toString()
        val weightString = binding.editTextWeight.text.toString()
        val waistSizeString = binding.editTextWaist.text.toString()
        val height = heightString.toFloatOrNull()
        val weight = weightString.toFloatOrNull()
        val waistSize = waistSizeString.toFloatOrNull()

        val gender = binding.spinnerGender.selectedItem.toString()
        val allergies = binding.spinnerAllergies.selectedItem.toString()
        val disease = binding.spinnerPersonalDisease.selectedItem.toString()

        // Retrieve dob value from the date picker
        val dob = binding.editTextBirth.text.toString()
        val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date: Date? = dateFormatter.parse(dob)

        // Calculate BMI
        val bmi = calculateBMI(height, weight)?.toInt()

        // Insert details into the database inside the callback
        val detail = Detail(0 ,date, height, weight, waistSize, gender, allergies, disease, bmi)
        viewModel.insertDetail(detail)

        openHomePage()
    }


    //Fungsi dibawah untuk masukin rekomendasi yang didapatkan dari ML ke device

    private fun setupObservers() {
        viewModel.recommendationsLiveData.observe(this) {recommendations ->
            if (recommendations.isNotEmpty()) {
                viewModel.insertRecommendations(recommendations)
            } else {
                handleLoadingOrErrorState()
            }
        }
    }

    //Fungsi dibawah buat ganti state akun dari verified jadi completed, beres. Sudah isi semua profil akun

    private fun handleLoadingOrErrorState() {
        viewModel.recommendationData.observe(this) { result ->
            when (result) {
                is Result.Loading -> showLoading(true)
                is Result.Error -> {
                    showLoading(false)
                    Toast.makeText(this, "Error Fetching Recipes", Toast.LENGTH_SHORT).show()
                }
                else -> showLoading(false)
            }
        }
    }

    private fun openHomePage() {
        // Buat Intent untuk membuka VerificationActivity
        val intent = Intent(this, NavigationBarActivity::class.java)
        startActivity(intent)
    }

    private fun showDatePickerDialog() {
        val datePickerFragment = CustomDatePickerFragment().apply {
            setDatePickerDialogListener(object : CustomDatePickerFragment.DatePickerDialogListener {
                override fun onDateSet(year: Int, month: Int, dayOfMonth: Int) {
                    val calendar = Calendar.getInstance().apply {
                        set(year, month, dayOfMonth)
                    }
                    val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                    val formattedDate = format.format(calendar.time)
                    binding.editTextBirth.setText(formattedDate)
                }
            })
        }
        datePickerFragment.show(supportFragmentManager, "datePicker")
    }

    private fun showRulerPickerDialog() {
        val dialog = Dialog(this)
        val dialogBinding = DialogRulerPickerBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)

        // Atur listener untuk perubahan nilai pada CurvedRulerView di dalam dialog
        dialogBinding.curvedRulerView.listener = object : CurvedRulerView.OnValueChangeListener {
            override fun onValueChanged(value: Float) {
                // Sementara simpan nilai yang dipilih di tag
                dialogBinding.curvedRulerView.tag = value
            }
        }

        // Atur tindakan untuk tombol 'Done'
        dialogBinding.buttonDone.setOnClickListener {
            // Ketika 'Done' ditekan, update EditTextWeight dengan nilai yang dipilih
            val selectedValue = dialogBinding.curvedRulerView.tag as Float
            binding.editTextWeight.setText(selectedValue.toInt().toString())
            dialog.dismiss()
        }

        dialog.show()
    }



    private fun calculateBMI(height: Float?, weight: Float?): Float? {
        if (height == null || weight == null || height == 0f) {
            return null
        }

        // Convert height from cm to meters
        val heightInMeters = height / 100

        // Calculate BMI
        return weight / (heightInMeters * heightInMeters)
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun obtainViewModel(activity: AppCompatActivity): EditProfileViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[EditProfileViewModel::class.java]
    }
}