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
    val recommendationId: Int,
    val date: String,
    var isSelected: Int = 0,
    @field:ColumnInfo(name = "recipe_id")
    val recipeId: Int,
    val userId: Int,
) : Parcelable