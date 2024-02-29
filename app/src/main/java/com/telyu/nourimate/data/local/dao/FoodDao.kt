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
import java.util.Date

@Dao
interface FoodDao {

    //for prefill
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAllMeal(vararg meals: Meal)

    //query mingguan, selama tanggalnya 7 hari
//    @Transaction
//    @Query("SELECT * FROM meals WHERE mealId = :mealId AND 'Date' BETWEEN :startDate AND :endDate")
//    fun getRecommendationsForMealAndDateRange(mealId: String, startDate: String, endDate: String): List<MealsWithRecommendations>

    //tes query pake mealid saja
    @Transaction
    @Query("SELECT * FROM recipes WHERE recipe_id IN (SELECT recipeId FROM MealsRecipesCrossRef WHERE mealId = :mealId)")
    fun getRecipeByMeal(mealId: Int): LiveData<List<Recipe>>

//    @Transaction
//    @Query("SELECT * FROM recipes WHERE recipe_id IN (SELECT recipeId FROM MealsRecipesCrossRef WHERE mealId = :mealId) AND recipe_id IN (SELECT recipe_id FROM RecipesRecommendationCrossRef WHERE recommendationId IN (SELECT recommendationId FROM Recommendations WHERE date = :date))")
//    fun getRecipesForMealAndDate(mealId: Int, date: Date): LiveData<List<Recipe>>

//    @Transaction
//    @Query("SELECT * FROM recipes WHERE recipe_id IN (SELECT recipeId FROM MealsRecipesCrossRef WHERE mealId = :mealId) AND recipe_id IN (SELECT recipe_id FROM RecipesRecommendationCrossRef WHERE mealId = :mealId AND recommendationId = (SELECT recommendationId FROM Recommendations WHERE date = :date))")
//    fun getRecipesForMealAndDate(mealId: Int, date: Date): LiveData<List<Recipe>>

    @Transaction
    @Query("SELECT * FROM recipes WHERE recipe_id IN (SELECT recipeId FROM MealsRecipesCrossRef WHERE mealId = :mealId) AND recipe_id IN (SELECT recipe_id FROM MealsRecommendationsCrossRef WHERE mealId = :mealId AND recommendationId = (SELECT recommendationId FROM Recommendations WHERE date = :date))")
    fun getRecipesForMealAndDate(mealId: Int, date: Date): LiveData<List<Recipe>>


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