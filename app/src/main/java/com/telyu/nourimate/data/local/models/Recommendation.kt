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

@Entity(tableName = "recommendations",
//    foreignKeys = [
//        ForeignKey(
//            entity = Recipe::class,
//            parentColumns = ["recipeId"],
//            childColumns = ["recipe_id"],
//            onDelete = ForeignKey.CASCADE
//        ),
//    ]
)

@Parcelize
@TypeConverters(Converters::class)
data class Recommendation(
    @PrimaryKey(autoGenerate = false)
    val recommendationId: Int,
    val date: Date,
    var isSelected: Int = 0,
    @field:ColumnInfo(name = "recipe_id")
    val recipeId: Int,
) : Parcelable