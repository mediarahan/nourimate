package com.telyu.nourimate.fragments


import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.telyu.nourimate.R
import com.telyu.nourimate.data.local.models.Detail
import com.telyu.nourimate.databinding.FragmentUserDetailBinding
import com.telyu.nourimate.utils.Converters
import com.telyu.nourimate.viewmodels.UserDetailViewModel
import com.telyu.nourimate.viewmodels.ViewModelFactory
import com.telyu.nourimate.databinding.DialogWeightPickerBinding
import com.telyu.nourimate.databinding.DialogHeightPickerBinding
import com.telyu.nourimate.databinding.DialogWaistSizePickerBinding
import com.telyu.nourimate.databinding.DialogNameChangeBinding
import com.telyu.nourimate.custom.WeightRulerView
import com.telyu.nourimate.custom.StraightRulerView
import com.telyu.nourimate.custom.WaistSizeRulerView
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.math.max
import kotlin.math.min
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.app.AlertDialog
import android.graphics.Typeface
import androidx.core.content.res.ResourcesCompat
import com.telyu.nourimate.adapter.date.HintArrayAdapter
import com.telyu.nourimate.custom.CustomDatePickerFragment


class UserDetailFragment : Fragment() {
    private lateinit var binding: FragmentUserDetailBinding
    private lateinit var genderAdapter: HintArrayAdapter
    private val viewModel by viewModels<UserDetailViewModel> {
        ViewModelFactory.getInstance(
            requireContext().applicationContext
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backButton.setOnClickListener {
            // Navigasi kembali ke ProfileFragment
            requireActivity().supportFragmentManager.popBackStack()
        }

        getAllData()
        mapAllDataToView()
        setupSeekbar()
        bindEditTextButtons()

        binding.buttonSaveEditProfile.setOnClickListener {
            updateUserProfile()
            requireActivity().supportFragmentManager.popBackStack()
        }

        // Inisialisasi genderSpinner menggunakan View Binding
        binding = FragmentUserDetailBinding.bind(view)
        val genderOptions = arrayOf("Gender", "Laki-laki", "Perempuan")

        // Set adapter untuk spinner
        genderAdapter =
            HintArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, genderOptions)
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerGender.adapter = genderAdapter

    }

    private fun getAllData() {
        //dapatkan value email dari user preference
        //gunakan email untuk mendapatkan id pengguna di database
        //gunakan email untuk mendapatkan nama pengguna di database
        //, dipisah karena entity nya pun terpisan
        viewModel.userEmail.observe(viewLifecycleOwner) { userEmail ->
            userEmail?.let {
                viewModel.getUserIdByEmail(it)
                viewModel.getUserNameByEmail(it)
            }
        }

        //gunakan id pengguna untuk mendapatkan detail pengguna dari database
        viewModel.userId.observe(viewLifecycleOwner) { userId ->
            if (userId != null)
                viewModel.getUserDetailsById(userId)
        }
    }

    private fun mapAllDataToView() {
        //mapping setiap atribut detail ke edittext
        viewModel.userDetails.observe(viewLifecycleOwner) { userDetails ->
            userDetails?.let { detail ->
                val formattedDate = Converters().formatDateToString(detail.dob)
                binding.editTextBirth.setText(formattedDate)
                binding.editTextHeight.setText(detail.height?.toInt().toString())
                binding.editTextWeight.setText(detail.weight?.toInt().toString())
                binding.editTextWaist.setText(detail.waistSize?.toInt().toString())

                // Jika genderAdapter telah diinisialisasi, lakukan seleksi
                if (::genderAdapter.isInitialized) {
                    val position = genderAdapter.getPosition(detail.gender)
                    if (position != -1) {
                        binding.spinnerGender.setSelection(position)
                    }
                }

                binding.editTextAllergy.setText(detail.allergen)
                binding.editTextDisease.setText(detail.disease)

            }
        }

        //mapping nama dari entity user, terpisah
        viewModel.userName.observe(viewLifecycleOwner) { userName ->
            binding.editTextName.setText(userName)
        }
    }

    private fun setupSeekbar() {
        val seekBar = binding.seekBar

        val minValue = 15
        val maxValue = 40
        seekBar.max = maxValue - minValue

        // Observe the BMI attribute of the Detail object
        viewModel.userDetails.observe(viewLifecycleOwner) { userDetails ->
            userDetails?.let { detail ->
                val bmi = detail.bmi ?: 0.0
                binding.textViewBmi.text = bmi.toString()
                // Convert BMI to an integer value within the range of the SeekBar
                val progress = max(0, min((maxValue - minValue), (bmi.toInt() - minValue)))
                seekBar.progress = progress
            }
        }
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                val currentBmi = minValue + p1
                binding.textViewBmi.text = currentBmi.toString()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                //empty implementation
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                //empty implementation
            }
        })
    }

    private fun bindEditTextButtons() {
        binding.iconeditnameImageView.setOnClickListener {
            showNameChangeDialog()
        }


        binding.iconeditheightImageView.setOnClickListener {
            val currentWeight = binding.editTextHeight.text.toString().toFloatOrNull() ?: 0f
            showHeightRulerPickerDialog(currentWeight)
        }

        binding.iconeditweightImageView.setOnClickListener {
            val currentWeight = binding.editTextWeight.text.toString().toFloatOrNull() ?: 0f
            showWeightRulerPickerDialog(currentWeight)
        }

        binding.iconeditwaistImageView.setOnClickListener {
            val currentWeight = binding.editTextWaist.text.toString().toFloatOrNull() ?: 0f
            showWaistRulerPickerDialog(currentWeight)
        }

        binding.iconeditgenderImageView.setOnClickListener {
            binding.spinnerGender.performClick()
        }


        binding.iconeditallergyImageView.setOnClickListener {
            showAllergiesDialog()
        }
        binding.iconeditdiseaseImageView.setOnClickListener {
            showDiseasesDialog()
        }
        binding.iconeditbirthImageView.setOnClickListener {
            showDatePickerDialog()
        }

    }

    private fun requestFocusOnEditText(editText: EditText) {
        editText.requestFocus()
    }

    private fun updateUserProfile() {
        val name = binding.editTextName.text.toString()
        val dob = binding.editTextBirth.text.toString() // Assuming this is a String representation of the date
        val height = binding.editTextHeight.text.toString().toFloatOrNull()
        val weight = binding.editTextWeight.text.toString().toFloatOrNull()
        val waistSize = binding.editTextWaist.text.toString().toFloatOrNull()
        val gender = binding.spinnerGender.selectedItem.toString()
        val allergen = binding.editTextAllergy.text.toString()
        val disease = binding.editTextDisease.text.toString()

        // Logging variables before creating Detail object
        Log.d(TAG, "Name: $name")
        Log.d(TAG, "Date of Birth: $dob")
        Log.d(TAG, "Height: $height")
        Log.d(TAG, "Weight: $weight")
        Log.d(TAG, "Waist Size: $waistSize")
        Log.d(TAG, "Gender: $gender")
        Log.d(TAG, "Allergen: $allergen")
        Log.d(TAG, "Disease: $disease")

        val id = viewModel.userId.value
        Log.d("UserDetailFragment", "User ID: $id")
        val formattedDob = Converters().fromStringToDate(dob)
        val heightInMeters = height?.div(100)
        val bmi = weight?.div((heightInMeters?.times(heightInMeters)!!))

        viewModel.userDetails.observe(viewLifecycleOwner) { userDetails ->
            if (id != null) {
                val detail = Detail(
                    detailId = userDetails?.detailId ?: -999,
                    dob = formattedDob,
                    height = height,
                    weight = weight,
                    waistSize = waistSize,
                    gender = gender,
                    allergen = allergen,
                    disease = disease,
                    bmi = bmi,
                )
                viewModel.userId.observe(viewLifecycleOwner) { userId ->
                    userId?.let {
                        viewModel.updateUserName(it, name)
                        viewModel.updateUserProfile(detail)
                    }
                }
            }
        }
    }


    //retrieve date of birth
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
        datePickerFragment.show(childFragmentManager, "datePicker")
    }

    private fun showWeightRulerPickerDialog(initialSelectedValue: Float) {
        val dialog = Dialog(requireContext())
        val dialogBinding = DialogWeightPickerBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)

        // Mengatur agar latar dialog transparan
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // Mendapatkan ukuran layar
        val displayMetrics = requireContext().resources.displayMetrics
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

    private fun showHeightRulerPickerDialog(initialSelectedValue: Float) {
        val dialog = Dialog(requireContext())
        val dialogBinding = DialogHeightPickerBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)

        // Mengatur agar latar dialog transparan
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // Mendapatkan ukuran layar
        val displayMetrics = requireContext().resources.displayMetrics
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
        val dialog = Dialog(requireContext())
        val dialogBinding = DialogWaistSizePickerBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)

        // Mengatur agar latar dialog transparan
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // Mendapatkan ukuran layar
        val displayMetrics = requireContext().resources.displayMetrics
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
        val allergies = arrayOf("Kacang, Gluten")
        val checkedItems = booleanArrayOf(false, false)
        val selectedItems = mutableListOf<String>()

        val dialogBuilder = AlertDialog.Builder(requireContext(), R.style.CustomAlertDialog)
            .setTitle("Select Allergies")
            .setMultiChoiceItems(allergies, checkedItems) { _, which, isChecked ->
                if (isChecked) {
                    selectedItems.add(allergies[which])
                } else {
                    selectedItems.remove(allergies[which])
                }
            }
            .setPositiveButton("Done") { dialog, _ ->
                val selectedText = selectedItems.joinToString(", ")
                binding.editTextAllergy.setText(selectedText)  // Menggunakan ViewBinding untuk mengupdate view
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
            typeface = Typeface.create(
                ResourcesCompat.getFont(requireContext(), R.font.abel),
                Typeface.NORMAL
            )
        }
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).apply {
            setTextColor(Color.BLACK)
            textSize = 16f
            typeface = Typeface.create(
                ResourcesCompat.getFont(requireContext(), R.font.abel),
                Typeface.NORMAL
            )
        }

        val displayMetrics = requireContext().resources.displayMetrics
        val screenWidth = displayMetrics.widthPixels
        dialog.window?.setLayout((screenWidth * 0.85).toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    private fun showDiseasesDialog() {
        val diseases = arrayOf("Diabetes", "Kolesterol", "Hipertensi")
        val checkedItems = booleanArrayOf(false, false, false)
        val selectedItems = mutableListOf<String>()

        val dialogBuilder = AlertDialog.Builder(requireContext(), R.style.CustomAlertDialog)
            .setTitle("Select Diseases")
            .setMultiChoiceItems(diseases, checkedItems) { _, which, isChecked ->
                if (isChecked) {
                    selectedItems.add(diseases[which])
                } else {
                    selectedItems.remove(diseases[which])
                }
            }
            .setPositiveButton("Done") { dialog, _ ->
                val selectedText = selectedItems.joinToString(", ")
                binding.editTextDisease.setText(selectedText)
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
            typeface = Typeface.create(
                ResourcesCompat.getFont(requireContext(), R.font.abel),
                Typeface.NORMAL
            )
        }
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).apply {
            setTextColor(Color.BLACK)
            textSize = 16f
            typeface = Typeface.create(
                ResourcesCompat.getFont(requireContext(), R.font.abel),
                Typeface.NORMAL
            )
        }

        val displayMetrics = requireContext().resources.displayMetrics
        val screenWidth = displayMetrics.widthPixels
        dialog.window?.setLayout((screenWidth * 0.85).toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    private fun showNameChangeDialog() {
        val dialog = Dialog(requireContext())
        val dialogBinding = DialogNameChangeBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)

        // Setting initial value
        dialogBinding.editTextNameChange.setText(binding.editTextName.text.toString())

        // Configure the dialog width
        val displayMetrics = requireContext().resources.displayMetrics
        val screenWidth = displayMetrics.widthPixels
        val dialogWidth = (screenWidth * 0.85).toInt()
        dialog.window?.setLayout(dialogWidth, ViewGroup.LayoutParams.WRAP_CONTENT)

        // Set actions for "Done" and "Cancel" buttons
        dialogBinding.buttonDone.setOnClickListener {
            val newName = dialogBinding.editTextNameChange.text.toString()
            binding.editTextName.setText(newName)
            dialog.dismiss()
        }

        dialogBinding.buttonCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

}