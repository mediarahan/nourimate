package com.telyu.nourimate.data.local.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.telyu.nourimate.utils.DateConverter
import kotlinx.parcelize.Parcelize
import java.util.Date

@Entity(tableName = "recommendations")
@Parcelize
@TypeConverters(DateConverter::class)
data class Recommendation(
    @PrimaryKey(autoGenerate = false)
    @field:ColumnInfo(name = "recommendation_id")
    val recommendationId: Int,

    @field:ColumnInfo(name = "date")
    val date: Date,

) : Parcelable