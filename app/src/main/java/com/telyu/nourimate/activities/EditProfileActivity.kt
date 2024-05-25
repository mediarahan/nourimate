package com.telyu.nourimate.activities

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.app.AlertDialog
import android.view.ViewGroup
import android.graphics.drawable.ColorDrawable
import android.graphics.Color
import android.graphics.Typeface
import android.util.Log
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
import androidx.lifecycle.ViewModelProvider
import com.telyu.nourimate.R
import com.telyu.nourimate.custom.CustomDatePickerFragment
import com.telyu.nourimate.custom.CustomDateStartProgramFragment
import com.telyu.nourimate.data.local.models.Detail
import com.telyu.nourimate.viewmodels.EditProfileViewModel
import com.telyu.nourimate.viewmodels.ViewModelFactory
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class EditProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditProfileBinding
    private lateinit var viewModel: EditProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this, R.color.color2)

        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = obtainViewModel(this@EditProfileActivity)

        binding.editTextBirth.setOnClickListener {
            showDatePickerDialog()
        }

        binding.editTextDateStartProgram.setOnClickListener {
            showDateStartProgramDialog()
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

        val genderOptions = arrayOf("Gender", "Laki-laki", "Perempuan")
        val genderAdapter =
            HintArrayAdapter(this, android.R.layout.simple_spinner_item, genderOptions)
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerGender.adapter = genderAdapter



        binding.editTextAllergies.setOnClickListener {
            showAllergiesDialog()
        }

        binding.editTextDisease.setOnClickListener {
            showDiseasesDialog()
        }

        //setup submit data to database button
        binding.buttonNext.setOnClickListener {
            insertUserDetails()
            setupObservers()
            viewModel.setAccountStateAsCompleted()
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
        val allergies = binding.editTextAllergies.text.toString()
        val disease = binding.editTextDisease.text.toString()

        // Retrieve dob value from the date picker
        val dob = binding.editTextBirth.text.toString()
        val dateFormatter = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
        val date: Date? = dateFormatter.parse(dob)

        // Calculate BMI
        val bmi = calculateBMI(height, weight)?.toInt()
        val id = viewModel.userId.value

        val detail = Detail(0, date, height, weight, waistSize, gender, allergies, disease, bmi)
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
                }
            })
        }
        datePickerFragment.show(supportFragmentManager, "datePicker")
    }

    private fun showDateStartProgramDialog() {
        val datePickerFragment = CustomDateStartProgramFragment().apply {
            setDateStartProgramDialogListener(object :
                CustomDateStartProgramFragment.DateStartProgramDialogListener {
                override fun onDateSet(year: Int, month: Int, dayOfMonth: Int) {
                    val calendar = Calendar.getInstance().apply {
                        set(year, month, dayOfMonth)
                    }
                    val format = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
                    val formattedDate = format.format(calendar.time)
                    binding.editTextDateStartProgram.setText(formattedDate)
                }
            })
        }
        datePickerFragment.show(supportFragmentManager, "datePicker")
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