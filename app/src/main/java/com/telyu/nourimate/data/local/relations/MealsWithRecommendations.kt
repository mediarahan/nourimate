package com.telyu.nourimate.data.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.telyu.nourimate.data.local.models.Meal
import com.telyu.nourimate.data.local.models.Recommendation

data class MealsWithRecommendations (
    @Embedded val meals: Meal,
    @Relation (
        parentColumn = "meal_id",
        entityColumn = "recommendation_id",
    )
    val recommendation: List<Recommendation>
)