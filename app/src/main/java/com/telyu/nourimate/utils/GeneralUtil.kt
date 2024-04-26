package com.telyu.nourimate.utils

import java.util.Calendar

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
}