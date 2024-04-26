package com.telyu.nourimate.data.local.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "meal")
@Parcelize
data class Meal (
    @PrimaryKey(autoGenerate = false)
    val mealId: Int,
) : Parcelable