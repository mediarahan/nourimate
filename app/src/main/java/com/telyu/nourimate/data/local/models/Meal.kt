package com.telyu.nourimate.data.local.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "meals")
@Parcelize
data class Meal (
    @PrimaryKey(autoGenerate = false)
    @field:ColumnInfo(name = "meal_id")
    val mealId: Int,

    @field:ColumnInfo(name = "name")
    val name: String,
):Parcelable