package com.telyu.nourimate.data.local.models

sealed class RecipeHistoryData {
    data class RecipeItem(val recipe: Recipe) : RecipeHistoryData()
    data class RecipeHistoryItem(val recipeHistory: RecipeHistory) : RecipeHistoryData()
}