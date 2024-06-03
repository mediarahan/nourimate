package com.telyu.nourimate.activities

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.app.AlertDialog
import android.view.ViewGroup
import android.graphics.drawable.ColorDrawable
import android.graphics.Color
import android.graphics.Typeface
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import com.telyu.nourimate.databinding.ActivityEditProfileBinding
import com.telyu.nourimate.databinding.DialogWeightPickerBinding
import com.telyu.nourimate.databinding.DialogHeightPickerBinding
import com.telyu.nourimate.databinding.DialogWaistSizePickerBinding
import com.telyu.nourimate.custom.WeightRulerView
import com.telyu.nourimate.custom.StraightRulerView
import com.telyu.nourimate.custom.WaistSizeRulerView
import com.telyu.nourimate.adapter.date.HintArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import com.telyu.nourimate.R
import com.telyu.nourimate.custom.CustomDatePickerFragment
import com.telyu.nourimate.custom.CustomDateStartProgramFragment
import com.telyu.nourimate.data.local.models.Detail
import com.telyu.nourimate.utils.InputValidator
import com.telyu.nourimate.viewmodels.EditProfileViewModel
import com.telyu.nourimate.viewmodels.ViewModelFactory
import java.text.ParseException
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

        setupSpinner()


        binding.editTextBirth.setOnClickListener {
            showDatePickerDialog()
        }

        binding.editTextDateOfProgram.setOnClickListener {
            showDateRangePicker()
        }

        binding.editTextWeight.setOnClickListener {
            val initialSelectedValue = binding.editTextWeight.text.toString().toFloatOrNull() ?: 0f
            showWeightRulerPickerDialog(initialSelectedValue)
        }

        binding.editTextHeight.setOnClickListener {
            val initialSelectedValue = binding.editTextHeight.text.toString().toFloatOrNull() ?: 0f
            showStraightRulerPickerDialog(initialSelectedValue)
        }

        binding.editTextWaist.setOnClickListener {
            val initialSelectedValue = binding.editTextWaist.text.toString().toFloatOrNull() ?: 0f
            showWaistRulerPickerDialog(initialSelectedValue)
        }



        binding.editTextAllergies.setOnClickListener {
            showAllergiesDialog()
        }

        binding.editTextDisease.setOnClickListener {
            showDiseasesDialog()
        }

        disableSelectButton()

        //setup submit data to database button
        binding.buttonNext.setOnClickListener {
            insertUserDetails()
            setupObservers()
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

    private fun insertUserDetails() {
        //all input
        val heightString = binding.editTextHeight.text.toString()
        val weightString = binding.editTextWeight.text.toString()
        val waistSizeString = binding.editTextWaist.text.toString()
        val height = heightString.toFloatOrNull()
        val weight = weightString.toFloatOrNull()
        val waistSize = waistSizeString.toFloatOrNull()

        val gender = binding.spinnerGender.selectedItem.toString()
        val allergies = binding.editTextAllergies.text.toString()
        val disease = binding.editTextDisease.text.toString()

        // Retrieve dob value from the date picker
        val dob = binding.editTextBirth.text.toString()
        val dateFormatter = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
        val date: Date? = dateFormatter.parse(dob)

        // Calculate BMI
        val bmi = calculateBMI(height, weight)?.toInt()
        val id = viewModel.userId.value

        val detail = Detail(0, date, height, weight, waistSize, gender, allergies, disease, bmi?.toFloat())
        viewModel.insertDetail(detail)

        openHomePage()
    }


    private fun openHomePage() {
        // Buat Intent untuk membuka VerificationActivity
        val intent = Intent(this, NavigationBarActivity::class.java)
        startActivity(intent)
    }

    //API Related
    private fun setupObservers() {
        viewModel.recommendationsLiveData.observe(this) { recommendations ->
            if (recommendations.isNotEmpty()) {
                Log.d("ML", "Recommendation from ML successfully inserted")
                viewModel.insertRecommendations(recommendations)
            } else {
                Log.d("ML", "No recommendations received or list is empty")
            }
        }
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

        // Set listener untuk CurvedRulerView
        dialogBinding.weightRulerView.listener = object : WeightRulerView.OnValueChangeListener {
            override fun onValueChanged(value: Float) {
                // Update selected value
                dialogBinding.textViewNumber2.text = value.toInt().toString()
                // Update EditText in real-time
                binding.editTextWeight.setText(String.format("%d", value.toInt()))
                enableSelectButtonIfReady()
            }
        }

        // Set action for 'Done' button
        dialogBinding.buttonDone.setOnClickListener {
            dialog.dismiss()
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

        // Set listener untuk CurvedRulerView
        dialogBinding.straightRulerView.listener =
            object : StraightRulerView.OnValueChangeListener {
                override fun onValueChanged(value: Float) {
                    // Update selected value
                    dialogBinding.textViewNumber.text = value.toInt().toString()
                    // Update EditText in real-time
                    binding.editTextHeight.setText(String.format("%d", value.toInt()))
                    enableSelectButtonIfReady()
                }
            }

        // Set action for 'Done' button
        dialogBinding.buttonDone.setOnClickListener {
            dialog.dismiss()
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

        // Set listener untuk CurvedRulerView
        dialogBinding.waistRulerView.listener = object : WaistSizeRulerView.OnValueChangeListener {
            override fun onValueChanged(value: Float) {
                // Update selected value
                dialogBinding.textViewNumber1.text = value.toInt().toString()
                // Update EditText in real-time
                binding.editTextWaist.setText(String.format("%d", value.toInt()))
                enableSelectButtonIfReady()
            }
        }

        // Set action for 'Done' button
        dialogBinding.buttonDone.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun showAllergiesDialog() {
        val allergies = arrayOf("Nuts", "Eggs", "Seafood")
        val checkedItems = booleanArrayOf(false, false, false)
        val selectedItems = mutableListOf<String>()

        val dialogBuilder = AlertDialog.Builder(this, R.style.CustomAlertDialog)
            .setTitle("Select Allergies")
            .setMultiChoiceItems(allergies, checkedItems) { _, which, isChecked ->
                if (isChecked) {
                    selectedItems.add(allergies[which])
                } else {
                    selectedItems.remove(allergies[which])
                }
            }
            .setPositiveButton("Done") { dialog, _ ->
                // Update the EditText with selected items when "Done" is clicked
                val selectedText = selectedItems.joinToString(", ")
                binding.editTextAllergies.setText(selectedText)  // Menggunakan ViewBinding untuk mengupdate view
                dialog.dismiss()
            }
            .setNegativeButton("Cancel", null)

        val dialog = dialogBuilder.create()
        dialog.show()

        // Mengubah tombol OK dan Cancel agar tidak menggunakan huruf kapital
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).isAllCaps = false
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).isAllCaps = false

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).apply {
            setTextColor(Color.BLACK)  // Mengeset warna secara langsung
            textSize = 16f            // Mengeset ukuran teks
            typeface =
                Typeface.create(ResourcesCompat.getFont(context, R.font.abel), Typeface.NORMAL)
        }
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).apply {
            setTextColor(Color.BLACK)
            textSize = 16f
            typeface =
                Typeface.create(ResourcesCompat.getFont(context, R.font.abel), Typeface.NORMAL)
        }

        // Set dialog width to 85% of the screen width
        val displayMetrics = resources.displayMetrics
        val screenWidth = displayMetrics.widthPixels
        dialog.window?.setLayout((screenWidth * 0.85).toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    private fun showDiseasesDialog() {
        val diseases = arrayOf("Diabetes", "Hipertensi", "Kolesterol")
        val checkedItems = booleanArrayOf(false, false, false)
        val selectedItems = mutableListOf<String>()

        val dialogBuilder = AlertDialog.Builder(this, R.style.CustomAlertDialog)
            .setTitle("Select Diseases")
            .setMultiChoiceItems(diseases, checkedItems) { _, which, isChecked ->
                if (isChecked) {
                    selectedItems.add(diseases[which])
                } else {
                    selectedItems.remove(diseases[which])
                }
            }
            .setPositiveButton("Done") { dialog, _ ->
                // Update the EditText with selected items when "Done" is clicked
                val selectedText = selectedItems.joinToString(", ")
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

//    private fun validateInputs() {
//        val dobLayout = binding.DateOfBirth
//
//        binding.editTextBirth.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
//            override fun afterTextChanged(s: Editable?) {
//                val birth= s.toString()
//                if (birth.isEmpty()) {
//                    dobLayout.error = "Minimum Age 20"
//                } else {
//                    dobLayout.error = null // Tidak ada error, bersihkan pesan
//                }
//                checkAllInputsValid()
//            }
//        })
//
//    }
//
//    private fun checkAllInputsValid() {
//        val isHeightValid = binding.editTextHeight.text.toString().toFloatOrNull()?.let { it > 0 } ?: false
//        val isWeightValid = binding.editTextWeight.text.toString().toFloatOrNull()?.let { it > 0 } ?: false
//        val isWaistSizeValid = binding.editTextWaist.text.toString().toFloatOrNull()?.let { it > 0 } ?: false
//        val isDobValid = validateDate(binding.editTextBirth.text.toString(), false) // Tanggal harus di masa lalu
//        val isDopValid = validateDateRange(binding.editTextDateOfProgram.text.toString())
//        val isGenderValid = binding.spinnerGender.selectedItemPosition > 0
//        val isProgramValid = binding.spinnerProgram.selectedItemPosition > 0
//
//        binding.DateOfProgram.error = if (isDopValid) null else "Date range must be at least 28 days"
//
//        val isAllValid = isHeightValid && isWeightValid && isWaistSizeValid && isDobValid && isDopValid && isGenderValid && isProgramValid
//
//        if (isAllValid) {
//            enableNextButton()
//        } else {
//            disableNextButton()
//        }
//    }
//
//    private fun validateDate(dateStr: String, allowFuture: Boolean): Boolean {
//        val sdf = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
//        sdf.isLenient = false  // Set to false to prevent dates like February 30th from being parsed.
//        try {
//            val date = sdf.parse(dateStr)
//            return if (date != null) {
//                if (!allowFuture) {
//                    // Check if the date is in the past or today.
//                    val today = Calendar.getInstance()
//                    today.set(Calendar.HOUR_OF_DAY, 0)
//                    today.set(Calendar.MINUTE, 0)
//                    today.set(Calendar.SECOND, 0)
//                    today.set(Calendar.MILLISECOND, 0)
//                    !date.after(today.time)  // Returns true if the date is not after today.
//                } else {
//                    true  // If future dates are allowed, just the existence of a valid date is enough.
//                }
//            } else {
//                false
//            }
//        } catch (e: ParseException) {
//            // If the date couldn't be parsed, return false.
//            return false
//        }
//    }
//
//    private fun validateDateRange(dates: String): Boolean {
//        val dateRange = dates.split(" to ")
//        return if (dateRange.size == 2) {
//            val sdf = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
//            sdf.isLenient = false
//            try {
//                val startDate = sdf.parse(dateRange[0])
//                val endDate = sdf.parse(dateRange[1])
//                startDate != null && endDate != null && !endDate.before(startDate) &&
//                        TimeUnit.MILLISECONDS.toDays(endDate.time - startDate.time) >= 28
//            } catch (e: ParseException) {
//                false
//            }
//        } else {
//            false
//        }
//    }

    private fun disableSelectButton() {
        binding.buttonNext.isEnabled = false
        binding.buttonNext.setBackground(ContextCompat.getDrawable(this, R.drawable.buttonlogin_background_with_shadow_disable))  // Menggunakan background untuk enabled
        binding.buttonNext.setTextColor(ContextCompat.getColor(this, R.color.color26))
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
            binding.buttonNext.setBackground(ContextCompat.getDrawable(this, R.drawable.buttonlogin_background_with_shadow))  // Menggunakan background untuk enabled
            binding.buttonNext.setTextColor(ContextCompat.getColor(this, R.color.color23))
        } else {
            binding.buttonNext.setBackground(ContextCompat.getDrawable(this, R.drawable.buttonlogin_background_with_shadow_disable))  // Menggunakan background untuk enabled
            binding.buttonNext.setTextColor(ContextCompat.getColor(this, R.color.color26))
        }
    }

//    private fun disableNextButton() {
//        binding.buttonNext.isEnabled = false
//        binding.buttonNext.setBackground(ContextCompat.getDrawable(this, R.drawable.buttonlogin_background_with_shadow_disable))  // Menggunakan background untuk disabled
//        binding.buttonNext.setTextColor(ContextCompat.getColor(this, R.color.color26))
//    }
//
//    private fun enableNextButton() {
//        binding.buttonNext.isEnabled = true
//        binding.buttonNext.setBackground(ContextCompat.getDrawable(this, R.drawable.buttonlogin_background_with_shadow))  // Menggunakan background untuk enabled
//        binding.buttonNext.setTextColor(ContextCompat.getColor(this, R.color.color23))
//    }

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