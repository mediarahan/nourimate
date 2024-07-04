package com.telyu.nourimate.activities

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import com.telyu.nourimate.R
import com.telyu.nourimate.adapter.date.HintArrayAdapter
import com.telyu.nourimate.custom.CustomDatePickerFragment
import com.telyu.nourimate.custom.StraightRulerView
import com.telyu.nourimate.custom.WaistSizeRulerView
import com.telyu.nourimate.custom.WeightRulerView
import com.telyu.nourimate.databinding.ActivityEditProfileBinding
import com.telyu.nourimate.databinding.DialogHeightPickerBinding
import com.telyu.nourimate.databinding.DialogWaistSizePickerBinding
import com.telyu.nourimate.databinding.DialogWeightPickerBinding
import com.telyu.nourimate.utils.Converters
import com.telyu.nourimate.utils.GeneralUtil
import com.telyu.nourimate.utils.GeneralUtil.calculateBMI
import com.telyu.nourimate.viewmodels.EditProfileViewModel
import com.telyu.nourimate.viewmodels.ViewModelFactory
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

class EditProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditProfileBinding
    private lateinit var viewModel: EditProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStatusBarColor(resources.getColor(R.color.color2, theme))

        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = obtainViewModel(this@EditProfileActivity)

        setupClickListeners()
        setupSpinner()
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

    private fun insertUserDetailsToLocalAndBackend() {
        //all input
        val heightString = binding.editTextHeight.text.toString()
        val weightString = binding.editTextWeight.text.toString()
        val waistSizeString = binding.editTextWaist.text.toString()
        val height = heightString.toInt()
        val weight = weightString.toInt()
        val waistSize = waistSizeString.toInt()

        val gender = binding.spinnerGender.selectedItem.toString()
        val allergies = binding.editTextAllergies.text.toString()
        val disease = binding.editTextDisease.text.toString()

        // Retrieve dob value from the date picker
        val dob = binding.editTextBirth.text.toString()
        val dateFormatter = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
        val date: Date? = dateFormatter.parse(dob)

        // Calculate BMI
        val bmi = calculateBMI(height, weight)
        viewModel.insertDetailToBackend(dob, height, waistSize, weight , gender, allergies, disease)
        viewModel.insertDetail(0, date, height, waistSize, weight, gender, allergies, disease, bmi
            ?: -999f)

        val intent = Intent(this, NavigationBarActivity::class.java)
        startActivity(intent)
    }

    private fun insertProgram() {
        val program = binding.spinnerProgram.selectedItem.toString()
        val dateRange = binding.editTextDateOfProgram.text.toString()
        val weight = binding.editTextWeight.text.toString().toInt()
        Log.d("DateRange", dateRange)

        val (startDate, endDate) = dateRange.split(" to ")
        Log.d("StartDate", startDate)
        Log.d("EndDate", endDate)

        val startDateFormatted = Converters().fromStringToDate(startDate)
        val endDateFormatted = Converters().fromStringToDate(endDate)
        val dateNow = Converters().dateFromTimestamp(System.currentTimeMillis())
        val nextWeek = Converters().dateFromTimestamp(GeneralUtil.calculateOneMinuteLaterTimestamp())

        val programCode = when (program) {
            "Maintain Weight" -> 1
            "Lose Weight" -> 2
            "Gain Weight" -> 3
            else -> 0
        }

        viewModel.insertWeightTrack(0, programCode, startDateFormatted, endDateFormatted, weight, weight, nextWeek)
        if (startDateFormatted != null) {
            viewModel.insertInitialWeightEntry(weight, dateNow)
        }
    }

    //API Related
    private fun fetchDataFromMachineLearning() {
        viewModel.recommendationsLiveData.observe(this) { recommendations ->
            viewModel.insertRecommendations(recommendations)
            Log.d("Recommendations", recommendations.toString())
        }
    }

    private fun showDatePickerDialog() {
        val datePickerFragment = CustomDatePickerFragment().apply {
            setDatePickerDialogListener(object : CustomDatePickerFragment.DatePickerDialogListener {
                override fun onDateSet(year: Int, month: Int, dayOfMonth: Int) {
                    val calendar = Calendar.getInstance().apply {
                        set(year, month, dayOfMonth)
                    }
                    val format = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
                    val formattedDate = format.format(calendar.time)
                    binding.editTextBirth.setText(formattedDate)
                    enableSelectButtonIfReady()
                }
            })
        }
        datePickerFragment.show(supportFragmentManager, "datePicker")
    }

    private fun showDateRangePicker() {
        // Build the date range picker
        val builder = MaterialDatePicker.Builder.dateRangePicker()
            .setTheme(R.style.MaterialCalendarTheme)

        // Set up calendar constraints
        val constraintsBuilder = CalendarConstraints.Builder()
        val today = Calendar.getInstance()

        val yesterdayCalendar = Calendar.getInstance()
        yesterdayCalendar.add(Calendar.DAY_OF_YEAR, -1)

        // Set the minimum date to today to prevent past dates from being selectable
        constraintsBuilder.setStart(today.timeInMillis)
        constraintsBuilder.setValidator(DateValidatorPointForward.from(yesterdayCalendar.timeInMillis))

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

    private fun showWeightRulerPickerDialog(initialSelectedValue: Float) {
        val dialog = Dialog(this)
        val dialogBinding = DialogWeightPickerBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)

        // Mengatur agar latar dialog transparan
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        // Mendapatkan ukuran layar
        val displayMetrics = resources.displayMetrics
        val screenWidth = displayMetrics.widthPixels
        // Mengatur lebar dialog menjadi 85% dari lebar layar
        val dialogWidth = (screenWidth * 0.85).toInt()
        dialog.window?.setLayout(dialogWidth, ViewGroup.LayoutParams.WRAP_CONTENT)

        // Set initial selected value
        dialogBinding.weightRulerView.selectedValue = initialSelectedValue
        dialogBinding.textViewNumber2.text = initialSelectedValue.toInt().toString()
        if (initialSelectedValue.toInt() != 0) {
            binding.editTextWeight.setText(String.format("%d", initialSelectedValue.toInt()))
        } else {
            binding.editTextWeight.setText("")  // Clear EditText or set to a placeholder if zero
        }

        // Listener for changes in the ruler view
        dialogBinding.weightRulerView.listener = object : WeightRulerView.OnValueChangeListener {
            override fun onValueChanged(value: Float) {
                val intValue = value.toInt()
                // Update the TextView and check value before updating EditText
                dialogBinding.textViewNumber2.text = intValue.toString()
                if (intValue != 0) {
                    binding.editTextWeight.setText(String.format("%d", intValue))
                    enableSelectButtonIfReady()
                } else {
                    binding.editTextWeight.setText("")  // Avoid displaying zero in EditText
                }
            }
        }

        // Set action for 'Done' button
        dialogBinding.buttonDone.setOnClickListener {
            dialog.dismiss()
            enableSelectButtonIfReady()
        }

        dialog.show()
    }

    private fun showStraightRulerPickerDialog(initialSelectedValue: Float) {
        val dialog = Dialog(this)
        val dialogBinding = DialogHeightPickerBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)

        // Mengatur agar latar dialog transparan
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        // Mendapatkan ukuran layar
        val displayMetrics = resources.displayMetrics
        val screenWidth = displayMetrics.widthPixels
        // Mengatur lebar dialog menjadi 85% dari lebar layar
        val dialogWidth = (screenWidth * 0.85).toInt()
        dialog.window?.setLayout(dialogWidth, ViewGroup.LayoutParams.WRAP_CONTENT)

        // Set initial selected value
        dialogBinding.straightRulerView.selectedValue = initialSelectedValue
        dialogBinding.textViewNumber.text = initialSelectedValue.toInt().toString()
        if (initialSelectedValue.toInt() != 0) {
            binding.editTextHeight.setText(String.format("%d", initialSelectedValue.toInt()))
        } else {
            binding.editTextHeight.setText("")  // Clear EditText or set to a placeholder if zero
        }

        // Listener for changes in the ruler view
        dialogBinding.straightRulerView.listener = object : StraightRulerView.OnValueChangeListener {
            override fun onValueChanged(value: Float) {
                val intValue = value.toInt()
                // Update the TextView and check value before updating EditText
                dialogBinding.textViewNumber.text = intValue.toString()
                if (intValue != 0) {
                    binding.editTextHeight.setText(String.format("%d", intValue))
                    enableSelectButtonIfReady()
                } else {
                    binding.editTextHeight.setText("")  // Avoid displaying zero in EditText
                }
            }
        }

        // Set action for 'Done' button
        dialogBinding.buttonDone.setOnClickListener {
            dialog.dismiss()
            enableSelectButtonIfReady()
        }

        dialog.show()
    }

    private fun showWaistRulerPickerDialog(initialSelectedValue: Float) {
        val dialog = Dialog(this)
        val dialogBinding = DialogWaistSizePickerBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)

        // Mengatur agar latar dialog transparan
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        // Mendapatkan ukuran layar
        val displayMetrics = resources.displayMetrics
        val screenWidth = displayMetrics.widthPixels
        // Mengatur lebar dialog menjadi 85% dari lebar layar
        val dialogWidth = (screenWidth * 0.85).toInt()
        dialog.window?.setLayout(dialogWidth, ViewGroup.LayoutParams.WRAP_CONTENT)

        // Set initial selected value
        dialogBinding.waistRulerView.selectedValue = initialSelectedValue
        dialogBinding.textViewNumber1.text = initialSelectedValue.toInt().toString()
        if (initialSelectedValue.toInt() != 0) {
            binding.editTextWaist.setText(String.format("%d", initialSelectedValue.toInt()))
        } else {
            binding.editTextWaist.setText("")  // Clear EditText or set to a placeholder if zero
        }

        // Listener for changes in the ruler view
        dialogBinding.waistRulerView.listener = object : WaistSizeRulerView.OnValueChangeListener {
            override fun onValueChanged(value: Float) {
                val intValue = value.toInt()
                // Update the TextView and check value before updating EditText
                dialogBinding.textViewNumber1.text = intValue.toString()
                if (intValue != 0) {
                    binding.editTextWaist.setText(String.format("%d", intValue))
                    enableSelectButtonIfReady()
                } else {
                    binding.editTextWaist.setText("")  // Avoid displaying zero in EditText
                }
            }
        }

        // Action for 'Done' button
        dialogBinding.buttonDone.setOnClickListener {
            // Dismiss the dialog when done
            dialog.dismiss()
            enableSelectButtonIfReady()
        }

        dialog.show()
    }

    private fun showAllergiesDialog() {
        val allergies = arrayOf("None", "Nuts", "Eggs", "Seafood")
        val checkedItems = booleanArrayOf(false, false, false, false)
        val selectedItems = mutableListOf<String>()

        val dialogBuilder = AlertDialog.Builder(this, R.style.CustomAlertDialog)
            .setTitle("Select Allergies")
            .setMultiChoiceItems(allergies, checkedItems) { dialog, which, isChecked ->
                if (which == 0 && isChecked) {  // "None" is at position 0
                    // Deselect all other checkboxes if "None" is selected
                    selectedItems.clear()
                    selectedItems.add(allergies[0])
                    allergies.indices.forEach { index ->
                        if (index != 0) {
                            (dialog as AlertDialog).listView.setItemChecked(index, false)
                        }
                    }
                } else if (isChecked) {
                    // Remove "None" if any other item is selected
                    selectedItems.remove(allergies[0])
                    (dialog as AlertDialog).listView.setItemChecked(0, false)
                    selectedItems.add(allergies[which])
                } else {
                    selectedItems.remove(allergies[which])
                }
            }
            .setPositiveButton("Done") { dialog, _ ->
                // Update the EditText with selected items when "Done" is clicked
                val selectedText = selectedItems.joinToString(",")
                binding.editTextAllergies.setText(selectedText)  // Using ViewBinding to update view
                dialog.dismiss()
            }
            .setNegativeButton("Cancel", null)

        val dialog = dialogBuilder.create()
        dialog.show()

// Modify the OK and Cancel buttons to not use all caps
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).isAllCaps = false
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).isAllCaps = false

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).apply {
            setTextColor(Color.BLACK)  // Set color directly
            textSize = 16f             // Set text size
            typeface =
                Typeface.create(ResourcesCompat.getFont(context, R.font.abel), Typeface.NORMAL)
        }
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).apply {
            setTextColor(Color.BLACK)
            textSize = 16f
            typeface =
                Typeface.create(ResourcesCompat.getFont(context, R.font.abel), Typeface.NORMAL)
        }
    }

        private fun showDiseasesDialog() {
        val diseases = arrayOf("None","Diabetes","Hipertensi","Kolesterol")
        val checkedItems = booleanArrayOf(false, false, false, false)
        val selectedItems = mutableListOf<String>()

        val dialogBuilder = AlertDialog.Builder(this, R.style.CustomAlertDialog)
            .setTitle("Select Diseases")
            .setMultiChoiceItems(diseases, checkedItems) { dialog, which, isChecked ->
                if (which == 0 && isChecked) {  // "None" is at position 0
                    // Deselect all other checkboxes if "None" is selected
                    selectedItems.clear()
                    selectedItems.add(diseases[0])
                    diseases.indices.forEach { index ->
                        if (index != 0) {
                            (dialog as AlertDialog).listView.setItemChecked(index, false)
                        }
                    }
                } else if (isChecked) {
                    // Remove "None" if any other item is selected
                    selectedItems.remove(diseases[0])
                    (dialog as AlertDialog).listView.setItemChecked(0, false)
                    selectedItems.add(diseases[which])
                } else {
                    selectedItems.remove(diseases[which])
                }
            }
            .setPositiveButton("Done") { dialog, _ ->
                // Update the EditText with selected items when "Done" is clicked
                val selectedText = selectedItems.joinToString(",")
                binding.editTextDisease.setText(selectedText)  // Menggunakan ViewBinding untuk mengupdate view
                dialog.dismiss()
            }
            .setNegativeButton("Cancel", null)

        val dialog = dialogBuilder.create()
        dialog.show()

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).isAllCaps = false
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).isAllCaps = false

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).apply {
            setTextColor(Color.BLACK)
            textSize = 16f
            typeface =
                Typeface.create(ResourcesCompat.getFont(context, R.font.abel), Typeface.NORMAL)
        }
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).apply {
            setTextColor(Color.BLACK)
            textSize = 16f
            typeface =
                Typeface.create(ResourcesCompat.getFont(context, R.font.abel), Typeface.NORMAL)
        }

        val displayMetrics = resources.displayMetrics
        val screenWidth = displayMetrics.widthPixels
        dialog.window?.setLayout((screenWidth * 0.85).toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    //=============== Setup Click Listeners ===============

    private fun setupClickListeners() {
        binding.apply {
            setClickListener(editTextBirth) { showDatePickerDialog() }
            setClickListener(editTextDateOfProgram) { showDateRangePicker() }
            setClickListener(editTextWeight) {
                val currentValue = binding.editTextWeight.text.toString().toFloatOrNull() ?: 50f
                showWeightRulerPickerDialog(currentValue)
            }
            setClickListener(editTextHeight) {
                val currentValue = binding.editTextHeight.text.toString().toFloatOrNull() ?: 150f
                showStraightRulerPickerDialog(currentValue)
            }
            setClickListener(editTextWaist) {
                // Get the current value from editTextWaist, if it's not valid, use 70
                val currentValue = binding.editTextWaist.text.toString().toFloatOrNull() ?: 70f
                showWaistRulerPickerDialog(currentValue)
            }

            setClickListener(editTextAllergies) { showAllergiesDialog() }
            setClickListener(editTextDisease) { showDiseasesDialog() }

            // Setup submit data to database button
            buttonNext.setOnClickListener {
                insertProgram()
                insertUserDetailsToLocalAndBackend()
                fetchDataFromMachineLearning()
            }
        }
    }

    private fun setClickListener(editText: EditText, action: () -> Unit) {
        editText.setOnClickListener { action() }
    }

    private fun setupSpinner() {
        val genderOptions = arrayOf("Gender", "Laki-laki", "Perempuan")
        val genderAdapter = HintArrayAdapter(this, android.R.layout.simple_spinner_item, genderOptions)
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerGender.adapter = genderAdapter
        binding.spinnerGender.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                enableSelectButtonIfReady()
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        val programOptions = arrayOf("-- Select Your Program --","Maintain Weight", "Lose Weight", "Gain Weight")
        val programAdapter = HintArrayAdapter(this, android.R.layout.simple_spinner_item, programOptions)
        programAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerProgram.adapter = programAdapter
        binding.spinnerProgram.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                enableSelectButtonIfReady()
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    private fun enableSelectButtonIfReady() {
        val program = binding.spinnerProgram.selectedItem.toString()
        val gender = binding.spinnerGender.selectedItem.toString()
        val dateRange = binding.editTextDateOfProgram.text.toString()
        val date = binding.editTextBirth.text.toString()
        val weight = binding.editTextWeight.text.toString()
        val height = binding.editTextHeight.text.toString()
        val waist = binding.editTextWaist.text.toString()

        binding.buttonNext.isEnabled = program != "-- Select Your Program --" && gender != "Gender" && dateRange.isNotEmpty() && date.isNotEmpty() && weight.isNotEmpty() && height.isNotEmpty() && waist.isNotEmpty()
        if (binding.buttonNext.isEnabled) {
            binding.buttonNext.background = ContextCompat.getDrawable(this, R.drawable.buttonlogin_background_with_shadow)
            binding.buttonNext.setTextColor(ContextCompat.getColor(this, R.color.color23))
        } else {
            binding.buttonNext.background = ContextCompat.getDrawable(this, R.drawable.buttonlogin_background_with_shadow_disable)
            binding.buttonNext.setTextColor(ContextCompat.getColor(this, R.color.color26))
        }
    }



    //=============== Setup ViewModel ===============

    private fun obtainViewModel(activity: AppCompatActivity): EditProfileViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[EditProfileViewModel::class.java]
    }


}