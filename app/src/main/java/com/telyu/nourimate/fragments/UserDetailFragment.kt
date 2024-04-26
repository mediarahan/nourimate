package com.telyu.nourimate.fragments

import android.app.DatePickerDialog
import android.content.ContentValues.TAG
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.SeekBar
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.telyu.nourimate.data.local.models.Detail
import com.telyu.nourimate.databinding.FragmentUserDetailBinding
import com.telyu.nourimate.utils.Converters
import com.telyu.nourimate.viewmodels.UserDetailViewModel
import com.telyu.nourimate.viewmodels.ViewModelFactory
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import kotlin.math.max
import kotlin.math.min

class UserDetailFragment : Fragment() {
    private lateinit var binding: FragmentUserDetailBinding
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
                binding.editTextGender.setText(detail.gender)
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
        binding.iconeditnameImageView.setOnClickListener { requestFocusOnEditText(binding.editTextName) }
        binding.iconeditheightImageView.setOnClickListener { requestFocusOnEditText(binding.editTextHeight) }
        binding.iconeditweightImageView.setOnClickListener { requestFocusOnEditText(binding.editTextWeight) }
        binding.iconeditwaistImageView.setOnClickListener { requestFocusOnEditText(binding.editTextWaist) }
        binding.iconeditgenderImageView.setOnClickListener { requestFocusOnEditText(binding.editTextGender) }
        binding.iconeditallergyImageView.setOnClickListener { requestFocusOnEditText(binding.editTextAllergy) }
        binding.iconeditdiseaseImageView.setOnClickListener { requestFocusOnEditText(binding.editTextDisease) }
        //setup date picker
        binding.iconeditbirthImageView.setOnClickListener {
            showDatePicker { selectedDate ->
                selectedDate?.let { date ->
                    val formattedDate =
                        SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(date)
                    binding.editTextBirth.setText(formattedDate)
                }
            }
        }
    }

    private fun requestFocusOnEditText(editText: EditText) {
        editText.requestFocus()
    }

    private fun updateUserProfile() {
        val name = binding.editTextName.text.toString()
        val dob =
            binding.editTextBirth.text.toString() // Assuming this is a String representation of the date
        val height = binding.editTextHeight.text.toString().toFloatOrNull()
        val weight = binding.editTextWeight.text.toString().toFloatOrNull()
        val waistSize = binding.editTextWaist.text.toString().toFloatOrNull()
        val gender = binding.editTextGender.text.toString()
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
        val formattedDob = Converters().fromStringToDate(dob)
        val heightInMeters = height?.div(100)
        val bmi = weight?.div((heightInMeters?.times(heightInMeters)!!))?.toInt()

        if (id != null) {
            val detail = Detail(
                detailId = id,
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

    //retrieve date of birth
    private fun showDatePicker(callback: (Date?) -> Unit) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(),
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

}