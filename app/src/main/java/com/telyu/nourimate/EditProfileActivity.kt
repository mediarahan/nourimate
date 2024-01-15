package com.telyu.nourimate

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.DatePicker
import com.telyu.nourimate.databinding.ActivityEditProfileBinding
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class EditProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Date of Birth
        binding.editTextDateOfBirth.setOnClickListener {
            showDatePicker()
        }

        // Gender Dropdown
        val genderOptions = arrayOf("Laki-laki", "Perempuan")
        val genderAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, genderOptions)
        binding.spinnerGender.adapter = genderAdapter

        // Allergies Dropdown
        val allergiesOptions = arrayOf("Nuts", "Egg", "Seafood")
        val allergiesAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, allergiesOptions)
        binding.spinnerAllergies.adapter = allergiesAdapter

        // Personal Disease Dropdown
        val diseaseOptions = arrayOf("High Blood Pressure", "Diabetes", "Cholesterol")
        val diseaseAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, diseaseOptions)
        binding.spinnerPersonalDisease.adapter = diseaseAdapter

        // Next Button
        binding.buttonNext.setOnClickListener {
            // Tambahkan logika untuk berpindah ke halaman Home
        openHomePage()}
    }

    private fun openHomePage() {
        // Buat Intent untuk membuka VerificationActivity
        val intent = Intent(this, HomeActivity::class.java)
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
