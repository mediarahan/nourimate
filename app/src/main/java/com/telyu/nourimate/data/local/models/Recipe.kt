package com.telyu.nourimate.data.local.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity (tableName = "recipes")
@Parcelize
data class Recipe (
    @PrimaryKey(autoGenerate = true)
    @field:ColumnInfo(name = "recipe_id")
    val recipeId: Int,

    @field:ColumnInfo(name = "name")
    val name: String,

    @field:ColumnInfo(name = "calories")
    val calories: Double,

    @field:ColumnInfo(name = "carbs")
    val carbs: Double,

    @field:ColumnInfo(name = "fat")
    val fat: Double,

    @field:ColumnInfo(name = "protein")
    val protein: Double,

    @field:ColumnInfo(name = "ingredients")
    val ingredients: String,

    @field:ColumnInfo(name = "cooking_steps")
    val cookingSteps: String,

    @field:ColumnInfo(name = "recipe_pictures")
    val recipePictures: String,

): Parcelable
