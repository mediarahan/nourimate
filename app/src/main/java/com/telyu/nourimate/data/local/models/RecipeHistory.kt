package com.telyu.nourimate.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "consumed_recipes",
    indices = [Index(value = ["recipe_id", "consumed_date"], unique = true)]
)
data class RecipeHistory(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "recipe_id") val recipeId: Int,
    @ColumnInfo(name = "consumed_time") val consumedTime: Date,  // Long gapapa di backend /longint / long
    @ColumnInfo(name = "consumed_date") val consumedDate: String,
    @ColumnInfo(name = "user_id") val userId: Int,
)

