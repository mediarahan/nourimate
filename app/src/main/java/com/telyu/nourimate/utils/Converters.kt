package com.telyu.nourimate.utils

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class Converters {

    private val dateFormat = SimpleDateFormat("yyyy/MM/dd")
    private val dateFormatProgram = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    private val dayAndDateFormat = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault())

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    //ini 2 dibawah buat di program aja

    fun toTimestamp(date: Date): Long {
        return date.time
    }

    fun formatDate(date: Date?): String {
        return date?.let { dateFormatProgram.format(it) } ?: ""
    }

    fun dateFromTimestamp(value: Long): Date {
        return Date(value)
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
