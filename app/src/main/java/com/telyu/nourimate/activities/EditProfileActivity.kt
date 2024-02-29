package com.telyu.nourimate.activities

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import com.telyu.nourimate.databinding.ActivityEditProfileBinding
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.telyu.nourimate.data.local.models.Detail
import com.telyu.nourimate.viewmodels.EditProfileViewModel
import com.telyu.nourimate.viewmodels.ViewModelFactory
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class EditProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditProfileBinding
    private lateinit var editProfileViewModel: EditProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        editProfileViewModel = obtainViewModel(this@EditProfileActivity)

        //array opsi
        val genderOptions = arrayOf("Laki-laki", "Perempuan")
        val allergiesOptions = arrayOf("Nuts", "Egg", "Seafood")
        val diseaseOptions = arrayOf("High Blood Pressure", "Diabetes", "Cholesterol")

        //setup edittext
        binding.editTextDateOfBirth
        binding.editTextHeight
        binding.editTextWeight
        binding.editTextWaistSize

        //setup date picker
        binding.editTextDateOfBirth.setOnClickListener {
            showDatePicker { selectedDate ->
                selectedDate?.let { date ->
                    val formattedDate =
                        SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(date)
                    binding.editTextDateOfBirth.setText(formattedDate)
                }
            }
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

        //setup submit data to database button
        binding.buttonFinish.setOnClickListener {
            insertUserDetails()
        }

    }

    private fun insertUserDetails() {
        //all input
        val heightString = binding.editTextHeight.text.toString()
        val weightString = binding.editTextWeight.text.toString()
        val waistSizeString = binding.editTextWaistSize.text.toString()
        val height = heightString.toFloatOrNull()
        val weight = weightString.toFloatOrNull()
        val waistSize = waistSizeString.toFloatOrNull()

        val gender = binding.spinnerGender.selectedItem.toString()
        val allergies = binding.spinnerAllergies.selectedItem.toString()
        val disease = binding.spinnerPersonalDisease.selectedItem.toString()

        // Retrieve dob value from the date picker
        val dob = binding.editTextDateOfBirth.text.toString()
        val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date: Date? = dateFormatter.parse(dob)

        // Calculate BMI
        val bmi = calculateBMI(height, weight)?.toInt()

        // Insert details into the database inside the callback
        val detail = Detail(0 ,date, height, weight, waistSize, gender, allergies, disease, bmi)
        editProfileViewModel.insertDetail(detail)

        openHomePage()
    }


        private fun openHomePage() {
        // Buat Intent untuk membuka VerificationActivity
        val intent = Intent(this, NavigationBarActivity::class.java)
        startActivity(intent)
    }

    //retrieve date of birth
    private fun showDatePicker(callback: (Date?) -> Unit) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, month, dayOfMonth)
                // Convert Calendar to Date
                val date = selectedDate.time
                // Pass the selected date via callback
                callback(date)
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
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


    private fun obtainViewModel(activity: AppCompatActivity): EditProfileViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[EditProfileViewModel::class.java]
    }
}