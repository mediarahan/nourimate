package com.telyu.nourimate.utils

import android.util.Log
import androidx.room.TypeConverter
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class Converters {

    private val dateFormat = SimpleDateFormat("yyyy/MM/dd")
    private val shortDateFormat = SimpleDateFormat("MM/dd")
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

    fun longToString(long: Long): String {
        return long.let { dateFormat.format(it) } ?: ""
    }

    fun formatDate(date: Date?): String {
        return date?.let { dateFormatProgram.format(it) } ?: ""
    }

    fun dateFromTimestamp(value: Long): Date {
        return Date(value)
    }

    fun formatDateToStringShort(date: Date?): String {
        return date?.let { shortDateFormat.format(it) } ?: ""
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

    private val iso8601Format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault()).apply {
        timeZone = TimeZone.getTimeZone("UTC")
    }

    fun fromStringToDateISO(isoDate: String): Date? {
        return try {
            iso8601Format.parse(isoDate)
        } catch (e: ParseException) {
            Log.e("Converters", "Error parsing date: ${e.message}")
            null
        }
    }

    fun fromStringToDayDate(value: String?): Date? {
        return value?.let { dayAndDateFormat.parse(it) }
    }



}
