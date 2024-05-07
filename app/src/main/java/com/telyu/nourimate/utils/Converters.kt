package com.telyu.nourimate.utils

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class Converters {

    private val dateFormat = SimpleDateFormat("yyyy/MM/dd")
    private val dayAndDateFormat = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault())

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    fun formatDateToString(date: Date?): String {
        return date?.let { dateFormat.format(it) } ?: ""
    }

    fun formatDayDateToString(date: Date?): String {
        return date?.let { dayAndDateFormat.format(it) } ?: ""
    }

    @TypeConverter
    fun fromStringToDate(value: String?): Date? {
        return value?.let { dateFormat.parse(it) }
    }
    fun fromStringToDayDate(value: String?): Date? {
        return value?.let { dayAndDateFormat.parse(it) }
    }



}
