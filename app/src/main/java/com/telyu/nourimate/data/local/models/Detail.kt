package com.telyu.nourimate.data.local.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.Date

@Entity (tableName = "userDetails")
@Parcelize
data class Detail(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @field:ColumnInfo(name = "date")
    val dob: Date?,

    @field:ColumnInfo(name = "height")
    val height: Float?,

    @field:ColumnInfo(name = "weight")
    val weight: Float?,

    @field:ColumnInfo(name = "waist_size")
    val waistSize: Float?,

    @field:ColumnInfo(name = "gender")
    val gender: String,

    @field:ColumnInfo(name = "allergen")
    val allergen: String,

    @field:ColumnInfo(name = "disease")
    val disease: String,

    @field:ColumnInfo(name = "bmi")
    val bmi: Int?,

    ): Parcelable