package com.telyu.nourimate.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sleep_segments")
data class SleepSegmentEventEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val startTimeMillis: Long,
    val endTimeMillis: Long
)