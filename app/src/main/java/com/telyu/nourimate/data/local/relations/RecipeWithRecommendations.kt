package com.telyu.nourimate.data.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.telyu.nourimate.data.local.models.Recipe
import com.telyu.nourimate.data.local.models.Recommendation

//Relationship antara Recipe dan Recommendations, One to Many.
// Satu recipe bisa memiliki beberapa (Many) rekomendasi, tapi
// satu rekomendasi hanya bisa memiliki satu recipe
data class RecipeWithRecommendations(
    @Embedded val recipe: Recipe,
    @Relation(
        parentColumn = "recipe_id",
        entityColumn = "recipe_id"
    )
    val recommendations: List<Recommendation>
)