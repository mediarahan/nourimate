package com.telyu.nourimate.custom

import android.graphics.drawable.ColorDrawable
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.graphics.Point
import android.view.WindowManager
import android.view.Gravity
import androidx.fragment.app.DialogFragment
import com.telyu.nourimate.adapter.DateAdapter
import com.telyu.nourimate.databinding.FragmentCustomDatePickerBinding
import com.telyu.nourimate.custom.DateItem
import com.telyu.nourimate.fragments.DecadeViewFragment
import com.telyu.nourimate.fragments.MonthViewFragment
import java.text.SimpleDateFormat
import java.util.*

class CustomDatePickerFragment : DialogFragment() {
    private var _binding: FragmentCustomDatePickerBinding? = null
    private val binding get() = _binding!!

    private var listener: DatePickerDialogListener? = null

    private val monthsArray = arrayOf("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")

    private var displayedMonth: Int = 0
    private var displayedYear: Int = 0

    private var selectedYear: Int = 0
    private var selectedMonth: Int = 0
    private var selectedDayOfMonth: Int = 0
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

        setInitialDateTo20YearsAgo()

        binding.Year.setOnClickListener {
            val decadeViewFragment = DecadeViewFragment()
            decadeViewFragment.setOnYearSelectedListener { selectedYear ->
                displayedYear = selectedYear
                binding.Year.text = selectedYear.toString()
                updateCalendar(displayedMonth, displayedYear)
            }
            decadeViewFragment.show(parentFragmentManager, "decadeView")
        }

        binding.Month.setOnClickListener {
            val monthViewFragment = MonthViewFragment().apply {
                setOnMonthSelectedListener { selectedMonth ->
                    displayedMonth = selectedMonth
                    binding.Month.text = monthsArray[selectedMonth]
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
            if (selectedYear != 0 && selectedDayOfMonth != 0) {
                listener?.onDateSet(selectedYear, selectedMonth, selectedDayOfMonth)
            } else {
                setDateTo20YearsAgo()
            }
            dismiss()
        }

        return view
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.apply {
            setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    override fun onResume() {
        super.onResume()
        val params: WindowManager.LayoutParams? = dialog?.window?.attributes
        params?.width = WindowManager.LayoutParams.MATCH_PARENT
        params?.height = WindowManager.LayoutParams.WRAP_CONTENT
        dialog?.window?.attributes = params

        val window = dialog?.window
        val size = Point()
        val display = window?.windowManager?.defaultDisplay
        display?.getSize(size)

        val width = (size.x * 0.85).toInt()
        val height = WindowManager.LayoutParams.WRAP_CONTENT
        window?.setLayout(width, height)
        window?.setGravity(Gravity.CENTER)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    private fun setInitialDateTo20YearsAgo() {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.YEAR, -20)
        displayedYear = calendar.get(Calendar.YEAR)
        displayedMonth = calendar.get(Calendar.MONTH)
        selectedYear = displayedYear
        selectedMonth = displayedMonth
        selectedDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
        updateCalendar(displayedMonth, displayedYear)
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
        val adapter = DateAdapter(requireContext(), dates, -1)
        binding.gvCalendar.adapter = adapter

        binding.gvCalendar.setOnItemClickListener { _, _, position, _ ->
            val selectedDate = dates[position]

            if (selectedDate.day.isNotEmpty()) {
                val selectedCalendar = Calendar.getInstance()
                if (selectedDate.isPreviousMonth) {
                    selectedCalendar.set(Calendar.YEAR, if (month == 0) year - 1 else year)
                    selectedCalendar.set(Calendar.MONTH, if (month == 0) 11 else month - 1)
                } else if (selectedDate.isNextMonth) {
                    selectedCalendar.set(Calendar.YEAR, if (month == 11) year + 1 else year)
                    selectedCalendar.set(Calendar.MONTH, if (month == 11) 0 else month + 1)
                } else {
                    selectedCalendar.set(Calendar.YEAR, year)
                    selectedCalendar.set(Calendar.MONTH, month)
                }
                selectedCalendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(selectedDate.day))
                val age = calculateAge(selectedCalendar.time)

                if (age < 20) {
                    selectedYear = 0
                    selectedMonth = 0
                    selectedDayOfMonth = 0
                    return@setOnItemClickListener
                }

                selectedYear = selectedCalendar.get(Calendar.YEAR)
                selectedMonth = selectedCalendar.get(Calendar.MONTH)
                selectedDayOfMonth = selectedCalendar.get(Calendar.DAY_OF_MONTH)

                dates.forEach { it.isSelected = false }
                selectedDate.isSelected = true

                adapter.setSelectedPosition(position)
            }
        }
    }

    private fun setDateTo20YearsAgo() {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.YEAR, -20)
        selectedYear = calendar.get(Calendar.YEAR)
        selectedMonth = calendar.get(Calendar.MONTH)
        selectedDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
        listener?.onDateSet(selectedYear, selectedMonth, selectedDayOfMonth)
    }

    private fun calculateAge(birthDate: Date): Int {
        val dob = Calendar.getInstance()
        dob.time = birthDate
        val today = Calendar.getInstance()
        var age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR)
        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
            age--
        }
        return age
    }

    private fun navigateMonth(monthIncrement: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(displayedYear, displayedMonth, 1)
        calendar.add(Calendar.MONTH, monthIncrement)

        displayedMonth = calendar.get(Calendar.MONTH)
        displayedYear = calendar.get(Calendar.YEAR)

        updateCalendar(displayedMonth, displayedYear)
    }

    private fun changeYear(yearIncrement: Int) {
        displayedYear += yearIncrement
        updateCalendar(displayedMonth, displayedYear)
    }

    fun generateDatesForMonth(month: Int, year: Int): List<DateItem> {
        val dates = mutableListOf<DateItem>()
        val calendar = Calendar.getInstance()

        // Set the calendar to the start of the month
        calendar.set(year, month, 1)
        val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        val firstDayOfMonth = calendar.get(Calendar.DAY_OF_WEEK)

        // Adjust for previous month's days
        calendar.add(Calendar.MONTH, -1)
        val daysInPrevMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        var day = daysInPrevMonth - (firstDayOfMonth - 2)
        for (i in 1 until firstDayOfMonth) {
            dates.add(DateItem(day++.toString(), isCurrentMonth = false, isPreviousMonth = true))
            if (day > daysInPrevMonth) day = 1
        }

        // Reset calendar for the current month
        calendar.set(year, month, 1)
        for (i in 1..daysInMonth) {
            val date = Calendar.getInstance().apply {
                set(year, month, i)
            }.time
            val age = calculateAge(date)
            val isUnderAge = age < 20

            dates.add(DateItem(i.toString(), isCurrentMonth = true, isUnderAge = isUnderAge))
        }

        // Fill the rest of the calendar row with next month's days
        day = 1
        while (dates.size % 7 != 0) {
            dates.add(DateItem(day++.toString(), isCurrentMonth = false, isNextMonth = true))
        }

        return dates
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}