package com.telyu.nourimate.utils

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.Date

class Converters {

    private val dateFormat = SimpleDateFormat("yyyy/MM/dd")

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }

    fun formatDateToString(date: Date?): String {
        return date?.let { dateFormat.format(it) } ?: ""
    }
}
