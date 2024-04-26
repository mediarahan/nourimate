package com.telyu.nourimate.fragments

import android.graphics.drawable.ColorDrawable
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.telyu.nourimate.adapter.DateAdapter
import android.view.ViewGroup
import android.graphics.Point
import android.widget.GridView
import android.view.WindowManager
import android.view.Gravity
import androidx.fragment.app.DialogFragment
import com.telyu.nourimate.databinding.FragmentCustomDatePickerBinding
import java.util.*
import java.text.SimpleDateFormat
import android.app.AlertDialog
import android.widget.ArrayAdapter



data class DateItem(val day: String, val isCurrentMonth: Boolean, var isSelected: Boolean = false)


class CustomDatePickerFragment : DialogFragment() {
    private var _binding: FragmentCustomDatePickerBinding? = null
    private val binding get() = _binding!!

    private var listener: DatePickerDialogListener? = null

    private val monthsArray = arrayOf("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")

    private var displayedMonth: Int = Calendar.getInstance().get(Calendar.MONTH)
    private var displayedYear: Int = Calendar.getInstance().get(Calendar.YEAR)

    private var selectedYear: Int = Calendar.getInstance().get(Calendar.YEAR)
    private var selectedMonth: Int = Calendar.getInstance().get(Calendar.MONTH)
    private var selectedDayOfMonth: Int = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
    private var dates = listOf<DateItem>()


    interface DatePickerDialogListener {
        fun onDateSet(year: Int, month: Int, dayOfMonth: Int)
    }

    fun setDatePickerDialogListener(listener: DatePickerDialogListener) {
        this.listener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCustomDatePickerBinding.inflate(inflater, container, false)
        val view = binding.root

        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        val currentMonth = Calendar.getInstance().get(Calendar.MONTH)
        updateCalendar(currentMonth, currentYear)


        binding.Year.setOnClickListener {
            val decadeViewFragment = DecadeViewFragment()
            decadeViewFragment.setOnYearSelectedListener { selectedYear ->
                displayedYear = selectedYear
                binding.Year.text = selectedYear.toString()
                // Refresh the calendar view with the new year.
                updateCalendar(displayedMonth, displayedYear)
            }
            decadeViewFragment.show(parentFragmentManager, "decadeView")
        }

        binding.Month.setOnClickListener {
            val monthViewFragment = MonthViewFragment().apply {
                setOnMonthSelectedListener { selectedMonth ->
                    displayedMonth = selectedMonth
                    binding.Month.text = monthsArray[selectedMonth] // Assuming monthsArray is defined in this fragment
                    updateCalendar(displayedMonth, displayedYear)
                }
            }
            monthViewFragment.show(parentFragmentManager, "monthView")
        }



        binding.btnCancel.setOnClickListener {
            dismiss()
        }


        binding.leftArrow.setOnClickListener {
            navigateMonth(-1)
        }

        binding.rightArrow.setOnClickListener {
            navigateMonth(1)
        }

        binding.doubleleftArrow.setOnClickListener {
            changeYear(-1)
        }

        binding.doublerightArrow.setOnClickListener {
            changeYear(1)
        }

        binding.btnDone.setOnClickListener {
            // Check if a date is selected
            if (selectedYear != 0 && selectedMonth != 0 && selectedDayOfMonth != 0) {
                // Panggil listener dan kirimkan tanggal yang dipilih
                listener?.onDateSet(selectedYear, selectedMonth, selectedDayOfMonth)

                // Dismiss the dialog
                dismiss()
            } else {
                // Optionally, show a message to the user to select a date first
            }
        }




        return view
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.apply {
            // Set the size of the dialog to match the parent width and wrap the content in height
            setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            // Set the background of the window to transparent to enable rounded corners
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    override fun onResume() {
        super.onResume()
        // Obtain the current window attributes
        val params: WindowManager.LayoutParams? = dialog?.window?.attributes

        // Set the desired layout parameters
        params?.width = WindowManager.LayoutParams.MATCH_PARENT
        params?.height = WindowManager.LayoutParams.WRAP_CONTENT

        // Apply the changes to the dialog window
        dialog?.window?.attributes = params

        // Set the dialog size based on the window size
        val window = dialog?.window
        val size = Point()

        // Get display size
        val display = window?.windowManager?.defaultDisplay
        display?.getSize(size)

        // Define width and height
        val width = (size.x * 0.85).toInt() // 85% of screen width, for example
        val height = WindowManager.LayoutParams.WRAP_CONTENT // Height to wrap the content

        // Set the layout parameters
        window?.setLayout(width, height)
        window?.setGravity(Gravity.CENTER)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) // Make the background transparent
    }



    private fun updateCalendar(month: Int, year: Int) {
        val calendar = Calendar.getInstance().apply {
            set(Calendar.YEAR, year)
            set(Calendar.MONTH, month)
            set(Calendar.DAY_OF_MONTH, 1)
        }

        binding.Year.text = year.toString()
        binding.Month.text = SimpleDateFormat("MMMM", Locale.getDefault()).format(calendar.time)

        dates = generateDatesForMonth(month, year)
        val adapter = DateAdapter(requireContext(), dates, 99)
        binding.gvCalendar.adapter = adapter

        // Set listener for date selection
        binding.gvCalendar.setOnItemClickListener { _, view, position, _ ->
            // Update the selected state for the clicked item
            val newSelectedDate = dates[position] // Assuming 'dates' is your data list
            dates.forEach { it.isSelected = false } // Deselect all
            newSelectedDate.isSelected = true // Select the new one

            // Save the selected date
            selectedYear = year
            selectedMonth = month
            selectedDayOfMonth = Integer.parseInt(newSelectedDate.day)

            // Notify the adapter to update the UI
            adapter.setSelectedPosition(position) // Update the selected position
        }

    }



    private fun navigateMonth(monthIncrement: Int) {
        // Adjust the month
        val calendar = Calendar.getInstance()
        calendar.set(displayedYear, displayedMonth, 1)
        calendar.add(Calendar.MONTH, monthIncrement)

        // Update the displayed month and year
        displayedMonth = calendar.get(Calendar.MONTH)
        displayedYear = calendar.get(Calendar.YEAR)

        // Now update the calendar to reflect the change
        updateCalendar(displayedMonth, displayedYear)
    }

    private fun changeYear(yearIncrement: Int) {
        displayedYear += yearIncrement
        updateCalendar(displayedMonth, displayedYear)
    }

    // Example method within CustomDatePickerFragment



    fun onDateSelected(year: Int, month: Int, dayOfMonth: Int) {
        selectedYear = year
        selectedMonth = month
        selectedDayOfMonth = dayOfMonth
    }


    fun generateDatesForMonth(month: Int, year: Int): List<DateItem> {
        val dates = mutableListOf<DateItem>()
        val calendar = Calendar.getInstance()

        // Determine the length of the previous month
        calendar.set(year, month-1, 1)
        val daysInPrevMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

        // Set to the first day of the month and year passed in
        calendar.set(year, month, 1)
        val firstDayOfMonth = calendar.get(Calendar.DAY_OF_WEEK)

        // Calculate the previous month's days to display
        var day = daysInPrevMonth - (firstDayOfMonth - 1) + calendar.firstDayOfWeek
        if (day > daysInPrevMonth) day -= daysInPrevMonth
        for (i in 1 until firstDayOfMonth) {
            dates.add(DateItem(day++.toString(), isCurrentMonth = false))
            if (day > daysInPrevMonth) day = 1
        }

        // Number of days in the current month
        val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        for (i in 1..daysInMonth) {
            dates.add(DateItem(i.toString(), isCurrentMonth = true))
        }

        // Calculate the next month's days to display if needed
        day = 1
        while (dates.size % 7 != 0) {
            dates.add(DateItem(day++.toString(), isCurrentMonth = false))
        }

        return dates
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

