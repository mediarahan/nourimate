package com.telyu.nourimate.data.local.relations

import androidx.room.Entity

@Entity(primaryKeys = ["mealId", "recipeId"])
data class MealsRecipesCrossRef (
    val mealId: Int,
    val recipeId: Int,
    )