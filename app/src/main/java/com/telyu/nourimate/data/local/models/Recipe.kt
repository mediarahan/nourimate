package com.telyu.nourimate.data.local.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import kotlinx.parcelize.Parcelize

@Entity(tableName = "recipes")
@Parcelize
data class Recipe (
    @PrimaryKey(autoGenerate = true)
    val recipeId: Int,

    val name: String,
    val calories: Double,
    val carbs: Double,
    val fat: Double,
    val protein: Double,
    val ingredients: String,
    val cookingSteps: String,
    val recipePictures: String,
    val mealType: Int,

) : Parcelable
