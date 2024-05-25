package com.telyu.nourimate.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "consumed_recipes")
data class RecipeHistory(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "recipe_id")
    val recipeId: Int,
    @ColumnInfo(name = "consumed_date")
    val consumedDate: Date,
    @ColumnInfo(name = "user_id")
    val userId: Int,
)

