package com.telyu.nourimate.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.telyu.nourimate.data.local.models.Recipe
import com.telyu.nourimate.data.local.models.Recommendation

@Dao
interface FoodDao {

    @Query("SELECT * FROM recipes WHERE name LIKE '%' || :name || '%'")
    suspend fun getRecipeByName(name: String): List<Recipe>

    @Query("SELECT * FROM recommendations WHERE isSelected = :isSelected")
    suspend fun getSelectedRecommendations(isSelected: Boolean = true): List<Recommendation>

    // Insert methods for Recipe, Recommendation, and Meal entities
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: Recipe)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecommendation(recommendation: Recommendation)

    //querynya salah cok.
    @Query("SELECT recommendation_id FROM recommendations WHERE meal_type = :mealType")
    suspend fun getRecommendationIdsByMealType(mealType: Int): List<Int>

    @Query("SELECT * FROM recipes WHERE recipe_id IN (SELECT recipe_id FROM recommendations WHERE recommendation_id IN (:recommendationIds))")
    suspend fun getRecipesByRecommendationIds(recommendationIds: List<Int>): List<Recipe>

    //=== query untuk tampilin resep di RecipeFragment

    @Query(
        """ 
        SELECT * FROM recipes
        INNER JOIN recommendations ON recipes.recipe_id = recommendations.recommendation_id
        WHERE recommendations.meal_type = :mealType AND isSelected = 1
        """
    )
    suspend fun getRecipeByMealTypeAndSelectedRecommendation(mealType: Int): List<Recipe>


    @Query("SELECT COUNT(*) FROM recommendations WHERE isSelected = 1")
    suspend fun getSelectedRecipeCount(): Int

    @Query("SELECT COUNT(*) FROM recommendations WHERE isSelected = 1 AND meal_type = :mealType")
    suspend fun getSelectedRecipeCountByMealType(mealType: Int): Int

    @Query("UPDATE recommendations SET isSelected = :isSelected WHERE recommendation_id = :recommendationId")
    suspend fun updateRecommendationSelection(recommendationId: Int, isSelected: Boolean)

    //=== query untuk mock machine learning activity ===
    @Query("SELECT name FROM recipes")
    fun getAllRecipeNames(): LiveData<List<String>>

    //dapetin recipe id berdasarkan nama resep yang dipilih di spinner
    @Query("SELECT recipe_id FROM recipes WHERE name = :name ")
    fun getRecipeIdByName(name: String): Int?

    //pake query insert recommendation diatas
    //pake query insertMealRecipeCrossRef diatas

}