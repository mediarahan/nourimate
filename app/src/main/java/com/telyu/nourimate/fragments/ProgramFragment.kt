package com.telyu.nourimate.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.DatePicker
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.telyu.nourimate.viewmodels.ProgramViewModel
import com.telyu.nourimate.databinding.FragmentProgramBinding
import java.util.Calendar

class ProgramFragment : Fragment() {

    private lateinit var viewModel: ProgramViewModel
    private lateinit var binding: FragmentProgramBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProgramBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ViewModel initialization moved to onViewCreated
        viewModel = ViewModelProvider(this).get(ProgramViewModel::class.java)

        // Spinner (Dropdown) data
        val programOptions = arrayOf("Maintain Health", "Maintain Weight", "Loss Weight", "Gain Weight")
        val programAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, programOptions)
        binding.programSpinner.adapter = programAdapter

        // Date Picker
        binding.editTextDateOfProgram.setOnClickListener {
            showDatePicker()
        }

        // Menu Icon Click
        binding.menuIcon.setOnClickListener {
            // Implement menu icon click action here
        }

        // Notification Icon Click
        binding.notificationIcon.setOnClickListener {
            // Implement notification icon click action here
        }

        // Observing currentWeight
        viewModel.currentWeight.observe(viewLifecycleOwner, Observer {
            binding.weightTextView.text = "Current Weight: $it kg"
        })

        // Implement logic for editing weight (e.g., through EditText)
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                val selectedDate = "$dayOfMonth/${month + 1}/$year"
                binding.editTextDateOfProgram.setText(selectedDate)
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }

}
