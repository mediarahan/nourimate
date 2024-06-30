package com.telyu.nourimate.data.local.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.Date

@Entity (tableName = "userDetails",
    indices = [Index(value = ["userId"], unique = true)]
)
@Parcelize
    data class Detail(
    @PrimaryKey(autoGenerate = true)
    val detailId: Int,
    val dob: Date?,
    val height: Int,
    val waistSize: Int,
    val weight: Int,
    val gender: String,
    val allergen: String,
    val disease: String,
    val bmi: Float,
    val userId: Int,

    ): Parcelable