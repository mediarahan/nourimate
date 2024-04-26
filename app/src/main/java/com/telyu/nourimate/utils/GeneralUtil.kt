package com.telyu.nourimate.utils

import android.util.Log
import java.util.Calendar
import java.util.Date

object GeneralUtil {
    fun getDateToday(hour: Int, minute: Int, second: Int, milisecond: Int): Long {
        val calendar = Calendar.getInstance()

        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, second)
        calendar.set(Calendar.MILLISECOND, milisecond)
        val today = calendar.timeInMillis

        return today
    }

    fun getDateNextWeek(): Long {
        val calendar = Calendar.getInstance()

        calendar.add(Calendar.DAY_OF_MONTH, 7)
        calendar.set(Calendar.HOUR_OF_DAY, 23)
        calendar.set(Calendar.MINUTE, 59)
        calendar.set(Calendar.SECOND, 59)
        calendar.set(
            Calendar.MILLISECOND,
            999
        )
        val nextWeek = calendar.timeInMillis
        return nextWeek
    }

    fun calculateAge(dob: Date?): Int {
        if (dob == null) {
            Log.d("Calculate Age", "calculateAge: dob is null")
            return 99
        }

        val dobCalendar = Calendar.getInstance()
        dobCalendar.time = dob  // Set the time of the calendar to the Date object

        val today = Calendar.getInstance()

        Log.d("Calculate Age", "DOB: ${dobCalendar.time}")  // Log the DOB
        Log.d("Calculate Age", "Today: ${today.time}")  // Log the current date

        var age = today.get(Calendar.YEAR) - dobCalendar.get(Calendar.YEAR)

        // Check if the current day of the year is before the birthday to adjust the age
        if (today.get(Calendar.DAY_OF_YEAR) < dobCalendar.get(Calendar.DAY_OF_YEAR)) {
            age--
        }

        Log.d("Calculate Age", "calculateAge: age is $age")
        return age
    }


}