package com.telyu.nourimate.custom

import android.graphics.drawable.ColorDrawable
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.telyu.nourimate.adapter.date.DateStartProgramAdapter
import android.view.ViewGroup
import android.graphics.Point
import android.view.WindowManager
import android.view.Gravity
import androidx.fragment.app.DialogFragment
import com.telyu.nourimate.databinding.FragmentCustomDateStartProgramBinding
import java.util.*
import java.text.SimpleDateFormat


class CustomDateStartProgramFragment : DialogFragment() {
    private var _binding: FragmentCustomDateStartProgramBinding? = null
    private val binding get() = _binding!!

    private var listener: DateStartProgramDialogListener? = null

    private val monthsArray = arrayOf("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")

    private var displayedMonth: Int = Calendar.getInstance().get(Calendar.MONTH)
    private var displayedYear: Int = Calendar.getInstance().get(Calendar.YEAR)

    private var selectedYear: Int = Calendar.getInstance().get(Calendar.YEAR)
    private var selectedMonth: Int = Calendar.getInstance().get(Calendar.MONTH)
    private var selectedDayOfMonth: Int = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
    private var dates = listOf<DateItem>()


    interface DateStartProgramDialogListener {
        fun onDateSet(year: Int, month: Int, dayOfMonth: Int)
    }

    fun setDateStartProgramDialogListener(listener: DateStartProgramDialogListener) {
        this.listener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCustomDateStartProgramBinding.inflate(inflater, container, false)
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
        val adapter = DateStartProgramAdapter(requireContext(), dates, -1)
        binding.gvCalendar.adapter = adapter

        binding.gvCalendar.setOnItemClickListener { _, _, position, _ ->
            val dateItem = dates[position]
            if (dateItem.isSelectable) {
                // Deselect all previously selected dates
                dates.forEach { it.isSelected = false }

                // Select the new date
                dateItem.isSelected = true
                selectedYear = displayedYear
                selectedMonth = displayedMonth
                selectedDayOfMonth = dateItem.day.toInt()

                // Notify the adapter to update the UI
                (binding.gvCalendar.adapter as DateStartProgramAdapter).notifyDataSetChanged()
            }
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

        // Set to the first day of the given month
        calendar.set(year, month, 1)
        val firstDayOfMonth = calendar.get(Calendar.DAY_OF_WEEK)

        // Add days from previous month to fill the first week
        val daysFromPrevMonth = firstDayOfMonth - calendar.firstDayOfWeek
        calendar.add(Calendar.DAY_OF_MONTH, -daysFromPrevMonth)

        // Current date for comparison
        val today = Calendar.getInstance()

        while (dates.size < 35) {  // Cover up to 6 rows
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val isCurrentMonth = calendar.get(Calendar.MONTH) == month
            val isSelectable = !calendar.before(today)

            dates.add(DateItem(day.toString(), isCurrentMonth, isSelected = false, isSelectable = isSelectable))
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }

        return dates
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

