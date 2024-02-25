package com.telyu.nourimate.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.telyu.nourimate.data.local.models.Meal
import com.telyu.nourimate.data.local.models.Recipe
import com.telyu.nourimate.data.local.models.Recommendation
import com.telyu.nourimate.data.local.relations.MealsRecipesCrossRef
import com.telyu.nourimate.data.local.relations.MealsWithRecommendations
import com.telyu.nourimate.data.local.relations.RecipesRecommendationCrossRef

@Dao
interface FoodDao {

    //for prefill
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAllMeal(vararg meals: Meal)

    //query berdasarkan breakfast, lunch, atau dinner (salah satu) pada tanggal tertentu

    @Transaction
    @Query("SELECT * FROM recipes WHERE recipe_id IN (SELECT recipeId FROM MealsRecipesCrossRef WHERE mealId = :mealId) AND recipe_id IN (SELECT recipe_id FROM RecipesRecommendationCrossRef WHERE recommendationId IN (SELECT RecommendationID FROM recommendations WHERE Date = :date))")
    fun getRecipesForMealAndDate(mealId: Int, date: String): LiveData<List<Recipe>>

    //query mingguan, selama tanggalnya 7 hari
    @Transaction
    @Query("SELECT * FROM meals WHERE meal_id = :mealId AND 'Date' BETWEEN :startDate AND :endDate")
    fun getRecommendationsForMealAndDateRange(mealId: String, startDate: String, endDate: String): List<MealsWithRecommendations>


    //tes query pake mealid saja
    @Transaction
    @Query("SELECT * FROM recipes WHERE recipe_id IN (SELECT recipeId FROM MealsRecipesCrossRef WHERE mealId = :mealId)")
    fun getRecipeByMeal(mealId: Int): LiveData<List<Recipe>>




    //Test

    // Insert methods for Recipe, Recommendation, and Meal entities
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: Recipe)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecommendation(recommendation: Recommendation)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeal(meal: Meal)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipeRecommendationCrossRef(crossRef: RecipesRecommendationCrossRef)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMealRecipeCrossRef(crossRef: MealsRecipesCrossRef)
}