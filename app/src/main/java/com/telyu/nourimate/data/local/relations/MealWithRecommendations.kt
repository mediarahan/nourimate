package com.telyu.nourimate.data.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.telyu.nourimate.data.local.models.Meal
import com.telyu.nourimate.data.local.models.Recommendation

//Relationship antara Meal dan Recommendations, One to Many. Satu meal
// bisa memiliki beberapa (Many) rekomendasi, tapi satu rekomendasi hanya
// bisa memiliki satu meal.
data class MealWithRecommendations(
    @Embedded val meal: Meal,
    @Relation(
        parentColumn = "meal_id",
        entityColumn = "meal_id"
    )
    val recommendations: List<Recommendation>
)