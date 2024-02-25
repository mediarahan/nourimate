package com.telyu.nourimate.data.local.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.telyu.nourimate.data.local.models.Recipe
import com.telyu.nourimate.data.local.models.Recommendation

data class RecipesWithRecommendation(
    @Embedded val recipe: Recipe,
    @Relation(
        parentColumn = "recipeId",
        entityColumn = "recommendationId",
        associateBy = Junction(RecipesRecommendationCrossRef::class)
    )
    val recommendations: List<Recommendation>
)
