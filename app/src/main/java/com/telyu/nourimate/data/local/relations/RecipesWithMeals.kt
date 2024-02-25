package com.telyu.nourimate.data.local.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.telyu.nourimate.data.local.models.Meal
import com.telyu.nourimate.data.local.models.Recipe

data class RecipesWithMeals(
    @Embedded val recipe: Recipe,
    @Relation(
        parentColumn = "recipeId",
        entityColumn = "mealId",
        associateBy = Junction(MealsRecipesCrossRef::class)
    )
    val meals: List<Meal>
)
