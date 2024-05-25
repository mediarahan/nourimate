package com.telyu.nourimate.data.local.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "history")
@Parcelize
data class History(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val programName: String,
    val startDate: String,
    val endDate: String,
    val calories: Int,
    val protein: Int,
    val fat: Int,
    val carbs: Int,
    val startWeight: Int,
    val endWeight: Int,
    val userId: Int
): Parcelable