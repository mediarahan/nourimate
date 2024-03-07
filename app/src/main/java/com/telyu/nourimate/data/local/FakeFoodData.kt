package com.telyu.nourimate.data.local

import com.telyu.nourimate.data.local.relations.MealsRecipesCrossRef
import com.telyu.nourimate.data.local.relations.MealsRecommendationsCrossRef
import com.telyu.nourimate.data.local.relations.RecipesRecommendationCrossRef
import com.telyu.nourimate.utils.Converters
import java.text.SimpleDateFormat
import java.util.Date

class FakeFoodData {
    data class Recipe(
        val recipeId: Int,
        val name: String,
        val calories: Double,
        val carbs: Double,
        val fat: Double,
        val protein: Double,
        val ingredients: String,
        val cookingSteps: String,
        val recipePictures: String
    )

    data class Recommendation(
        val recommendationId: Int,
        val date: Date
    )

    data class Meal(
        val mealId: Int,
        val name: String
    )

    val recipes = listOf(
        Recipe(
            recipeId = 1,
            name = "Omelette",
            calories = 200.0,
            carbs = 8.0,
            fat = 15.0,
            protein = 12.0,
            ingredients = "2 eggs\n1/2 cup tomatoes\n1/4 cup bell peppers\n1/4 cup spinach\n1 teaspoon olive oil\nSalt and pepper to taste",
            cookingSteps = "Pecah telor huhu hahah\n",
            recipePictures = "drawable/vegetable_omelette"
        ),
        Recipe(
            recipeId = 2,
            name = "Chicken Stir-Fry",
            calories = 350.0,
            carbs = 20.0,
            fat = 18.0,
            protein = 25.0,
            ingredients = "1 cup rice\n200g chicken breast, diced\n1/2 cup broccoli\n1/4 cup carrots, sliced\n2 tbsp soy sauce\n1 tbsp sesame oil\nSalt and pepper to taste",
            cookingSteps = "Cook rice according to package instructions. In a pan, heat sesame oil over medium heat. Add chicken and cook until browned. Add vegetables and soy sauce, cook until vegetables are tender. Serve over rice.",
            recipePictures = "drawable/chicken_stir_fry"

        ),
        Recipe(
            recipeId = 3,
            name = "Overnight Oats",
            calories = 300.0,
            carbs = 25.0,
            fat = 12.0,
            protein = 20.0,
            ingredients = "1 cup oats\n1 cup almond milk\n1 banana, sliced\n1/4 cup almonds, chopped\n1 tbsp honey\n1 tsp cinnamon\n",
            cookingSteps = "In a bowl, mix oats, almond milk, banana, almonds, honey, and cinnamon. Let it sit in the fridge overnight. In the morning, serve cold or heat in the microwave.",
            recipePictures = "drawable/overnight_oats"
        ),
        Recipe(
            recipeId = 4,
            name = "Penne Bolognese",
            calories = 400.0,
            carbs = 30.0,
            fat = 22.0,
            protein = 28.0,
            ingredients = "200g penne pasta\n1/2 cup marinara sauce\n200g ground beef\n1/4 cup onion, diced\n1/4 cup bell pepper, diced\n1/4 cup grated Parmesan cheese\n",
            cookingSteps = "Cook pasta according to package instructions. In a pan, brown ground beef with onion and bell pepper. Add marinara sauce and cooked pasta, stir until heated through. Serve with Parmesan cheese.",
            recipePictures = "drawable/penne_bolognese"
        ),
        Recipe(
            recipeId = 5,
            name = "Turkey Sandwich",
            calories = 250.0,
            carbs = 15.0,
            fat = 10.0,
            protein = 18.0,
            ingredients = "2 slices whole wheat bread\n100g turkey breast\n1/4 avocado, sliced\n1/4 cup lettuce\n1 tbsp mayonnaise\n1 tsp mustard\nSalt and pepper to taste",
            cookingSteps = "Spread mayonnaise and mustard on bread slices. Layer turkey, avocado, and lettuce on one slice. Season with salt and pepper. Top with the other slice of bread. Slice in half and serve.",
            recipePictures = "drawable/turkey_avocado_sandwich"
        )
    )

    val converters = Converters()

    val recommendations = listOf(
        Recommendation(
            recommendationId = 1,
            date = Date(System.currentTimeMillis())
        ),
        Recommendation(
            recommendationId = 2,
            date = Date(System.currentTimeMillis() - 86400000)
        ),
        Recommendation(
            recommendationId = 3,
            date = Date(System.currentTimeMillis() + 86400000)
        ),
        Recommendation(
            recommendationId = 4,
            date = Date(System.currentTimeMillis() + 86400000 * 2)
        )
    )


    val meals = listOf(
        Meal(
            mealId = 1,
            name = "Breakfast"
        ),
        Meal(
            mealId = 2,
            name = "Lunch"
        ),
        Meal(
            mealId = 3,
            name = "Dinner"
        )
    )

    val mealRecipeCrossRef = listOf(
        MealsRecipesCrossRef(
            mealId = 1,
            recipeId = 1
        ),
        MealsRecipesCrossRef(
            mealId = 2,
            recipeId = 2
        ),
        MealsRecipesCrossRef(
            mealId = 3,
            recipeId = 5
        ),
        MealsRecipesCrossRef(
            mealId = 1,
            recipeId = 3
        ),
        MealsRecipesCrossRef(
            mealId = 1,
            recipeId = 4
        ),
    )
    val recipeRecommendationCrossRef = listOf(
        RecipesRecommendationCrossRef(
            recipeId = 1,
            recommendationId = 1
        ),
        RecipesRecommendationCrossRef(
            recipeId = 2,
            recommendationId = 2
        ),
        RecipesRecommendationCrossRef(
            recipeId = 2,
            recommendationId = 3
        ),
        RecipesRecommendationCrossRef(
            recipeId = 3,
            recommendationId = 4
        ),
        RecipesRecommendationCrossRef(
            recipeId = 4,
            recommendationId = 5
        ),
    )

    val mealsRecommendationCrossRef = listOf(
        MealsRecommendationsCrossRef(
            mealId = 1,
            recommendationId = 1
        ),
        MealsRecommendationsCrossRef(
            mealId = 2,
            recommendationId = 2
        ),
        MealsRecommendationsCrossRef(
            mealId = 3,
            recommendationId = 3
        ),
        MealsRecommendationsCrossRef(
            mealId = 1,
            recommendationId = 4
        ),
        MealsRecommendationsCrossRef(
            mealId = 1,
            recommendationId = 5
        )
    )
}
