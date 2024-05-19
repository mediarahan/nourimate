package com.telyu.nourimate.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.telyu.nourimate.adapter.date.HintArrayAdapter
import com.telyu.nourimate.custom.CustomDatePickerFragment
import com.telyu.nourimate.custom.CustomDateStartProgramFragment
import com.telyu.nourimate.data.local.models.WeightTrack
import com.telyu.nourimate.databinding.ActivityChooseProgramBinding
import com.telyu.nourimate.utils.Converters
import com.telyu.nourimate.viewmodels.ChooseProgramViewModel
import com.telyu.nourimate.viewmodels.ViewModelFactory
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class ChooseProgramActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChooseProgramBinding
    private lateinit var viewModel: ChooseProgramViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = obtainViewModel(this@ChooseProgramActivity)

        binding = ActivityChooseProgramBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupSpinner()
        setupDateEditText()

        binding.buttonSelectProgram.setOnClickListener {
            insertProgramDetails()
        }
    }

    private fun insertProgramDetails() {
        val selectedProgram = binding.spinnerProgram.selectedItem.toString()
        val selectedProgramInt = when (selectedProgram) {
            "Maintain Weight" -> 1
            "Lose Weight" -> 2
            "Gain Weight" -> 3
            else -> 0
        }

        val startDate = binding.editTextDateStartProgram.text.toString()
        val endDate = binding.editTextDateEndProgram.text.toString()
        val dateFormatter = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
        val startDateParsed = dateFormatter.parse(startDate)
        val endDateParsed = dateFormatter.parse(endDate)

        val today = System.currentTimeMillis()
        val todayDate = Converters().dateFromTimestamp(today)

        viewModel.userDetails.observe(this) { detail ->
            // Insert program details into the database
            val weightTrack = WeightTrack(
                0,
                selectedProgramInt,
                startDateParsed,
                endDateParsed,
                detail.weight?.toInt() ?: -999,
                detail.weight?.toInt() ?: -999,
                todayDate,
                detail.detailId
            )
            viewModel.insertWeightTrack(weightTrack)
            finish()
        }
    }

    private fun setupSpinner() {
        val programOptions = arrayOf("-- Select Your Program --","Maintain Weight", "Lose Weight", "Gain Weight")
        val programAdapter =
            HintArrayAdapter(this, android.R.layout.simple_spinner_item, programOptions)
        programAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerProgram.adapter = programAdapter
    }

    private fun setupDateEditText() {
        binding.editTextDateStartProgram.setOnClickListener {
            showDateStartProgramDialog()
        }
        binding.editTextDateEndProgram.setOnClickListener {
            showDateEndProgramDialog()
        }
    }

    private fun showDatePickerDialog(editText: EditText, tag: String) {
        val datePickerFragment = CustomDateStartProgramFragment().apply {
            setDateStartProgramDialogListener(object : CustomDateStartProgramFragment.DateStartProgramDialogListener {
                override fun onDateSet(year: Int, month: Int, dayOfMonth: Int) {
                    val calendar = Calendar.getInstance().apply {
                        set(year, month, dayOfMonth)
                    }
                    val format = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
                    editText.setText(format.format(calendar.time))
                }
            })
        }
        datePickerFragment.show(supportFragmentManager, tag)
    }

    private fun showDateStartProgramDialog() {
        showDatePickerDialog(binding.editTextDateStartProgram, "datePickerStart")
    }

    private fun showDateEndProgramDialog() {
        showDatePickerDialog(binding.editTextDateEndProgram, "datePickerEnd")
    }


    private fun obtainViewModel(activity: AppCompatActivity): ChooseProgramViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[ChooseProgramViewModel::class.java]
    }

}