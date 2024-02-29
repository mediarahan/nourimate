package com.telyu.nourimate.data.local.relations

import androidx.room.Entity

@Entity(primaryKeys = ["mealId", "recommendationId"])
data class MealsRecommendationsCrossRef(
    val mealId: Int,
    val recommendationId: Int,
)

