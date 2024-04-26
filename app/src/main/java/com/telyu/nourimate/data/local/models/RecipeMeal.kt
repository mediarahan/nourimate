package com.telyu.nourimate.data.local.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "recipe_meal",
    primaryKeys = ["recipe_id", "meal_id"],
    foreignKeys = [
        ForeignKey(
            entity = Recipe::class,
            parentColumns = ["recipeId"],
            childColumns = ["recipe_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Meal::class,
            parentColumns = ["mealId"],
            childColumns = ["meal_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
@Parcelize
data class RecipeMeal(
    @field:ColumnInfo(name = "recipe_id")
    val recipeId: Int,

    @field:ColumnInfo(name = "meal_id")
    val mealId: Int,
) : Parcelable
