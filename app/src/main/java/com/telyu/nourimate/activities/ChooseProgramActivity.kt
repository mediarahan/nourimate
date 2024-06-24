package com.telyu.nourimate.activities

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import com.telyu.nourimate.R
import com.telyu.nourimate.adapter.date.HintArrayAdapter
import com.telyu.nourimate.data.local.models.WeightEntry
import com.telyu.nourimate.data.local.models.WeightTrack
import com.telyu.nourimate.databinding.ActivityChooseProgramBinding
import com.telyu.nourimate.utils.Converters
import com.telyu.nourimate.viewmodels.ChooseProgramViewModel
import com.telyu.nourimate.viewmodels.ViewModelFactory
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

class ChooseProgramActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChooseProgramBinding
    private lateinit var viewModel: ChooseProgramViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStatusBarColor(resources.getColor(R.color.color2, theme))

        viewModel = obtainViewModel(this@ChooseProgramActivity)

        binding = ActivityChooseProgramBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupSpinner()
        setupDateEditText()
        disableSelectButton()

        binding.buttonSelectProgram.setOnClickListener {
            Log.d("ChooseProgramActivity", "Click!")
            insertProgramDetails()
        }

        binding.backButton.setOnClickListener {
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

    //setup untuk masukkin weight entry untuk tampilan grafik
    private fun setupWeightEntry() {
        val today = Converters().dateFromTimestamp(System.currentTimeMillis())
        viewModel.userDetails.observe(this) {detail ->
            val weightEntry =
                detail?.let { WeightEntry(0, detail?.weight?.toInt() ?: -999, today, it.detailId ) }
            if (weightEntry != null) {
                viewModel.insertWeightEntry(weightEntry)
            }
            Log.d("ProgramFilledFragment", "InsertedWeightEntry: $weightEntry")
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

        val dop = binding.editTextDateOfProgram.text.toString()
        val dates = dop.split(" to ")
        val startDateString = dates[0]
        val endDateString = dates[1]
        val startDateParsed = Converters().fromStringToDate(startDateString)
        val endDateParsed = Converters().fromStringToDate(endDateString)
        val dateFormatter = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
        val today = System.currentTimeMillis()
        val todayDate = Converters().dateFromTimestamp(today)

        viewModel.userDetails.observe(this) { detail ->
            val weightTrack = WeightTrack(
                0,
                selectedProgramInt,
                startDateParsed,
                endDateParsed,
                detail?.weight ?: -999,
                detail?.weight ?: -999,
                todayDate,
                detail?.detailId ?: -999
            )
            viewModel.insertWeightTrack(weightTrack)
            Log.d("ChooseProgramActivity", "InsertedWeightTrack: $weightTrack")
            setupWeightEntry()
            finish()
        }
    }

    private fun setupSpinner() {
        val programOptions = arrayOf("-- Select Your Program --", "Maintain Weight", "Lose Weight", "Gain Weight")
        val programAdapter = HintArrayAdapter(this, android.R.layout.simple_spinner_item, programOptions)
        programAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerProgram.adapter = programAdapter

        binding.spinnerProgram.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                enableSelectButtonIfReady()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                enableSelectButtonIfReady()
            }
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun disableSelectButton() {
        binding.buttonSelectProgram.isEnabled = false
        binding.buttonSelectProgram.setBackgroundColor(R.color.color26)  // Menggunakan background untuk enabled
        binding.buttonSelectProgram.setTextColor(ContextCompat.getColor(this, R.color.white))
    }

    private fun enableSelectButtonIfReady() {
        val program = binding.spinnerProgram.selectedItem.toString()
        val dateRange = binding.editTextDateOfProgram.text.toString()
        binding.buttonSelectProgram.isEnabled = program != "-- Select Your Program --" && dateRange.isNotEmpty()
        updateButtonAppearance()
    }


    private fun updateButtonAppearance() {
        if (binding.buttonSelectProgram.isEnabled) {
            val colorInt = Color.parseColor("#FFBA38") // Active button color
            binding.buttonSelectProgram.setBackgroundColor(colorInt)
            binding.buttonSelectProgram.setTextColor(ContextCompat.getColor(this, R.color.white))
        } else {
            binding.buttonSelectProgram.setBackgroundColor(ContextCompat.getColor(this, R.color.color26)) // Disabled button color
            binding.buttonSelectProgram.setTextColor(ContextCompat.getColor(this, R.color.white))
        }
    }

    private fun setupDateEditText() {
        binding.editTextDateOfProgram.setOnClickListener {
            showDateRangePicker()
        }
        binding.editTextDateOfProgram.addTextChangedListener {
            enableSelectButtonIfReady()
        }
    }

    private fun showDateRangePicker() {
        // Build the date range picker
        val builder = MaterialDatePicker.Builder.dateRangePicker()
            .setTheme(R.style.MaterialCalendarTheme)

        // Set up calendar constraints
        val constraintsBuilder = CalendarConstraints.Builder()
        val today = Calendar.getInstance()

        // Set the minimum date to today to prevent past dates from being selectable
        constraintsBuilder.setStart(today.timeInMillis)
        constraintsBuilder.setValidator(DateValidatorPointForward.from(today.timeInMillis))

        builder.setCalendarConstraints(constraintsBuilder.build())
        builder.setTitleText("Select Dates (min 28 days range)")


        val dateRangePicker = builder.build()
        dateRangePicker.addOnPositiveButtonClickListener { dateRange ->
            // Ensure that the selected end date is at least 28 days after the selected start date
            if (dateRange.second - dateRange.first >= TimeUnit.DAYS.toMillis(28)) {
                val startDate = dateRange.first
                val endDate = dateRange.second
                val startDateString = formatDate(Date(startDate))
                val endDateString = formatDate(Date(endDate))
                val rangeString = "$startDateString to $endDateString"
                // Save the valid date range to editTextDateOfProgram
                binding.editTextDateOfProgram.setText(rangeString)
                enableSelectButtonIfReady()
            } else {
                Toast.makeText(this, "The selected date range is not valid. The end date must be at least 28 days after the start date.", Toast.LENGTH_LONG).show()
            }
        }

        dateRangePicker.show(supportFragmentManager, dateRangePicker.toString())
    }

    private fun formatDate(date: Date): String {
        val format = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
        return format.format(date)
    }
    private fun obtainViewModel(activity: AppCompatActivity): ChooseProgramViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[ChooseProgramViewModel::class.java]
    }

}