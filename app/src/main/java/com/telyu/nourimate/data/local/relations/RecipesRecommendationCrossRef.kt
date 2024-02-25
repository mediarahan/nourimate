package com.telyu.nourimate.data.local.relations

import androidx.room.Entity

@Entity(primaryKeys = ["recommendationId", "recipeId"])
data class RecipesRecommendationCrossRef(
    val recommendationId: Int,
    val recipeId: Int,
)
