package com.telyu.nourimate.data.local.models

import androidx.room.Embedded
import androidx.room.Relation

//wrapper class
sealed class RecommendationRecipe {
    data class RecipeItem(val recipe: Recipe) : RecommendationRecipe()
    data class RecommendationItem(val recommendation: Recommendation) : RecommendationRecipe()
}