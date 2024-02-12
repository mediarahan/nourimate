package com.telyu.nourimate.activities

import android.app.DatePickerDialog
import android.content.Context
import android.content.SharedPreferences
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.DatePicker
import com.telyu.nourimate.databinding.ActivityEditProfileBinding
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class EditProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditProfileBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        sharedPreferences = getSharedPreferences("ProfileData", Context.MODE_PRIVATE)
        binding.editTextDateOfBirth.setOnClickListener {
            showDatePicker()
        }
        val name= binding.editTextName.text.toString()
        val height = binding.editTextHeight.text.toString()
        val weight = binding.editTextWeight.text.toString()
        val waistSize = binding.editTextWaistSize.text.toString()
        val genderOptions = arrayOf("Laki-laki", "Perempuan")
        val genderAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, genderOptions)
        binding.spinnerGender.adapter = genderAdapter
        val allergiesOptions = arrayOf("Nuts", "Egg", "Seafood")
        val allergiesAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, allergiesOptions)
        binding.spinnerAllergies.adapter = allergiesAdapter
        val diseaseOptions = arrayOf("High Blood Pressure", "Diabetes", "Cholesterol")
        val diseaseAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, diseaseOptions)
        binding.spinnerPersonalDisease.adapter = diseaseAdapter
        binding.buttonNext.setOnClickListener {
            // Simpan nilai tinggi dan berat ke SharedPreferences saat pengguna menekan tombol Simpan
            val height = binding.editTextHeight.text.toString().toFloatOrNull()
            val weight = binding.editTextWeight.text.toString().toFloatOrNull()

            if (height != null && weight != null) {
                with(sharedPreferences.edit()) {
                    putFloat("height", height)
                    putFloat("weight", weight)
                    apply()
                }
            }

            openHomePage()}
    }

    private fun openHomePage() {
        // Buat Intent untuk membuka VerificationActivity
        val intent = Intent(this, NavigationBarActivity::class.java)
        startActivity(intent)}

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val datePickerDialog = DatePickerDialog(
            this,
            { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                val selectedDate = "$dayOfMonth/${month + 1}/$year"
                binding.editTextDateOfBirth.setText(selectedDate)
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }
}
