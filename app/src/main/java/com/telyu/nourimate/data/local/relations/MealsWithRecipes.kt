package com.telyu.nourimate.data.local.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.telyu.nourimate.data.local.models.Meal
import com.telyu.nourimate.data.local.models.Recipe

data class MealsWithRecipes (
    @Embedded val meal: Meal,
    @Relation (
        parentColumn = "mealId",
        entityColumn = "recipeId",
        associateBy = Junction(MealsRecipesCrossRef::class)
    )
    val recipes: List<Recipe>
)