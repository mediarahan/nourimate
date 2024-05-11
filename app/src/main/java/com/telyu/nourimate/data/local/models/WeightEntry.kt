package com.telyu.nourimate.data.local.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.Date

@Entity (tableName = "weight_entries")
@Parcelize
data class WeightEntry(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val weight: Int = 0,
    val date: Date,
    val userId: Int = 0,
): Parcelable
