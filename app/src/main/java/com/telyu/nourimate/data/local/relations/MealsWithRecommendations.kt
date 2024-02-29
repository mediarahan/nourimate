package com.telyu.nourimate.data.local.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.telyu.nourimate.data.local.models.Meal
import com.telyu.nourimate.data.local.models.Recipe
import com.telyu.nourimate.data.local.models.Recommendation

data class MealsWithRecommendations (
    @Embedded val meal: Meal,
    @Relation (
        parentColumn = "mealId",
        entityColumn = "recommendationId",
        associateBy = Junction(MealsRecommendationsCrossRef::class)
    )
    val recommendation: List<Recommendation>
)