package com.telyu.nourimate.data.local.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.telyu.nourimate.data.local.models.Recipe
import com.telyu.nourimate.data.local.models.Recommendation

data class RecommendationsWithRecipes(
    @Embedded val recommendation: Recommendation,
    @Relation(
        parentColumn = "recommendationId",
        entityColumn = "recipeId",
        associateBy = Junction(RecipesRecommendationCrossRef::class)
    )
    val recipes: List<Recipe>
)