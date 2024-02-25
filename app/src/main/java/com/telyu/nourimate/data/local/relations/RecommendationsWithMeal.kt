package com.telyu.nourimate.data.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.telyu.nourimate.data.local.models.Meal
import com.telyu.nourimate.data.local.models.Recommendation

data class RecommendationsWithMeal (
    @Embedded val recommendation: Recommendation,
    @Relation(
        parentColumn = "mealId",
        entityColumn = "mealId"
    )
    val meal: Meal
)
