package com.telyu.nourimate.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.telyu.nourimate.data.local.models.Meal
import com.telyu.nourimate.data.local.models.Recipe
import com.telyu.nourimate.data.local.models.RecipeMeal
import com.telyu.nourimate.data.local.models.Recommendation
import com.telyu.nourimate.data.local.models.RecommendationRecipe
import java.util.Date

@Dao
interface FoodDao {

    @Query("SELECT * FROM recipes WHERE name LIKE '%' || :name || '%'")
    suspend fun getRecipeByName(name: String): List<Recipe>

    // Insert methods for Recipe, Recommendation, and Meal entities
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: Recipe)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecommendation(recommendation: Recommendation)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeal(meal: Meal)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipeMeal(recipeMeal: RecipeMeal)

    //2 Query utama untuk nampilin Resep di RecipeFragment
    @Query("SELECT recipe_id FROM recipe_meal WHERE meal_id = :mealId")
    fun getRecipeIdsByMealId(mealId: Int): LiveData<List<Int>>

    @Query("SELECT * FROM recipes WHERE recipeId IN (:recipeIds)")
    fun getRecipesById(recipeIds: List<Int>): LiveData<List<Recipe>>

    //^^ Versi Weekly

    //BISMILLAHHHHHHHHHHHH

    @Query("""
    SELECT DISTINCT recipes.* FROM recipes
    INNER JOIN recommendations ON recipes.recipeId = recommendations.recipe_id
    WHERE recommendations.meal_id = :mealId
    AND recommendations.date BETWEEN :startDate AND :endDate
""")
    fun getRecipesByDateAndMealType(mealId: Int, startDate: Long, endDate: Long): LiveData<List<Recipe>>

    //COBA COBA QUERY BUAT UPDATE RECOMMENDATION
    @Query("""
        SELECT DISTINCT recommendations.* FROM recommendations
        INNER JOIN recipes ON recommendations.recipe_id = recipes.recipeId
        WHERE recommendations.recipe_id = :recipeId
        AND recommendations.meal_id = :mealId
    """)
    suspend fun getRecommendationByRecipeAndMealId(recipeId: Int, mealId: Int): Recommendation?



    @Query("SELECT * FROM recommendations WHERE meal_id = :mealId ORDER BY date ASC")
    fun getRecommendationsByMealIdSortedAscending(mealId: Int): LiveData<List<Recommendation>>


    @Query("SELECT * FROM recommendations ORDER BY date ASC")
    fun getAllRecommendationByDate(): LiveData<List<Recommendation>>

    //Query pertama untuk tampilin resep juga, tapi di DialogFragment
    @Query("SELECT recommendationId FROM recommendations WHERE meal_id = :mealId AND isSelected = 1")
    fun getAllSelectedRecommendationIdsByMealId(mealId: Int): LiveData<List<Int>>

    //Query kedua untuk nampilin Resep juga, tapi di DialogFragment
    @Query("SELECT * FROM recipes WHERE recipeId IN (SELECT recipe_id FROM recommendations WHERE recommendationId IN (:recommendationIds))")
    fun getRecipesByRecommendationIds(recommendationIds: List<Int>): LiveData<List<Recipe>>

    //Query untuk update rekomendasi
    @Update
    suspend fun updateRecommendation(recommendation: Recommendation)

    //QUERY YANG INI BUAT NANDAIN REKOMENDASI YANG MANA YANG MAU DIPILIH
    @Query("SELECT  * FROM recommendations WHERE recipe_id = :recipeId AND meal_id = :mealType")
    suspend fun getRecommendationByRecipeIdAndMealType(recipeId: Int, mealType: Int): Recommendation?

    //Query untuk menentukan jumlah rekomendasi yang dipilih
    @Query("SELECT COUNT(*) FROM recommendations WHERE isSelected = 1")
    fun getSelectedRecipeCount(): LiveData<Int>

    //Query untuk menentukan jumlah rekomendasi yang dipilih berdasarkan meal type
    @Query("SELECT COUNT(*) FROM recommendations WHERE isSelected = 1 AND meal_id = :mealType")
    fun getSelectedRecipeCountByMealType(mealType: Int): LiveData<Int>

    //=== query untuk mock machine learning activity ===
    @Query("SELECT name FROM recipes")
    fun getAllRecipeNames(): LiveData<List<String>>

    //dapetin recipe id berdasarkan nama resep yang dipilih di spinner
    @Query("SELECT recipeId FROM recipes WHERE name = :name ")
    fun getRecipeIdByName(name: String): Int?









}