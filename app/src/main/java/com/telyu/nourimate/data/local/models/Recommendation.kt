package com.telyu.nourimate.data.local.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.telyu.nourimate.utils.Converters
import kotlinx.parcelize.Parcelize
import java.util.Date

@Entity(tableName = "recommendations")
@Parcelize
@TypeConverters(Converters::class)
data class Recommendation(
    @PrimaryKey(autoGenerate = false)
    @field:ColumnInfo(name = "recommendation_id")
    val recommendationId: Int,

    @field:ColumnInfo(name = "date")
    val date: Date,

    @field:ColumnInfo(name = "isSelected")
    val isSelected: Boolean = false,

    @field:ColumnInfo(name = "meal_type")
    val mealType: Int,

) : Parcelable