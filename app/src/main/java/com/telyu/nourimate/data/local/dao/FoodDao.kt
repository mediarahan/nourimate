package com.telyu.nourimate.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.telyu.nourimate.data.local.models.NutritionSum
import com.telyu.nourimate.data.local.models.Recipe
import com.telyu.nourimate.data.local.models.Recommendation

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
    suspend fun insertRecommendations(recommendations: List<Recommendation>)


    //2 Query utama untuk nampilin Resep di RecipeFragment


    //BISMILLAHHHHHHHHHHHH

    @Query(
        """
    SELECT DISTINCT recipes.* FROM recipes
    INNER JOIN recommendations ON recipes.recipeId = recommendations.recipe_id
    WHERE recipes.mealType = :mealId
    AND recommendations.date BETWEEN :startDate AND :endDate
"""
    )
    fun getRecipesByDateAndMealType(
        mealId: Int,
        startDate: Long,
        endDate: Long
    ): LiveData<List<Recipe>>

    //COBA COBA QUERY BUAT UPDATE RECOMMENDATION
    @Query(
        """
        SELECT DISTINCT recommendations.* FROM recommendations
        INNER JOIN recipes ON recommendations.recipe_id = recipes.recipeId
        WHERE recommendations.recipe_id = :recipeId
        AND recipes.mealType = :mealId
    """
    )
    suspend fun getRecommendationByRecipeAndMealId(recipeId: Int, mealId: Int): Recommendation?

    @Query("""
        SELECT * FROM recommendations
        INNER JOIN recipes ON recommendations.recipe_id = recipes.recipeId
        WHERE mealType = :mealId ORDER BY date ASC
    """)
    fun getRecommendationsByMealIdSortedAscending(mealId: Int): LiveData<List<Recommendation>>

    @Query("SELECT * FROM recommendations ORDER BY date ASC")
    fun getAllRecommendationByDate(): LiveData<List<Recommendation>>

    //Query pertama untuk tampilin resep juga, tapi di DialogFragment
    @Query("""
        SELECT recommendationId FROM recommendations
        INNER JOIN recipes ON recommendations.recipe_id = recipes.recipeId
        WHERE mealType = :mealId AND isSelected = 1
    """)
    fun getAllSelectedRecommendationIdsByMealId(mealId: Int): LiveData<List<Int>>

    @Query("""
        SELECT recommendationId FROM recommendations
        INNER JOIN recipes ON recommendations.recipe_id = recipes.recipeId
        WHERE mealType = :mealId AND isSelected = 2
    """)
    fun getAllConfirmedRecommendationIdsByMealId(mealId: Int): LiveData<List<Int>>

    //Query kedua untuk nampilin Resep juga, tapi di DialogFragment
    @Query("SELECT * FROM recipes WHERE recipeId IN (SELECT recipe_id FROM recommendations WHERE recommendationId IN (:recommendationIds))")
    fun getRecipesByRecommendationIds(recommendationIds: List<Int>): LiveData<List<Recipe>>

    //Query untuk update rekomendasi
    @Update
    suspend fun updateRecommendation(recommendation: Recommendation)

    //QUERY YANG INI BUAT NANDAIN REKOMENDASI YANG MANA YANG MAU DIPILIH
    @Query("""
        SELECT  * FROM recommendations 
        INNER JOIN recipes ON recommendations.recipe_id = recipes.recipeId
        WHERE recipe_id = :recipeId 
        AND mealType = :mealType
    """)
    suspend fun getRecommendationByRecipeIdAndMealType(
        recipeId: Int,
        mealType: Int
    ): Recommendation?

    //Query untuk menentukan jumlah rekomendasi yang dipilih
    @Query("SELECT COUNT(*) FROM recommendations WHERE isSelected = 1")
    fun getSelectedRecipeCount(): LiveData<Int>

    //Query untuk menentukan jumlah rekomendasi yang dipilih berdasarkan meal type
    @Query("""
        SELECT COUNT(*) FROM recommendations 
        INNER JOIN recipes ON recommendations.recipe_id = recipes.recipeId
        WHERE isSelected = 1 AND mealType = :mealType
    """)
    fun getSelectedRecipeCountByMealType(mealType: Int): LiveData<Int>

    //=== query untuk mock machine learning activity ===
    @Query("SELECT name FROM recipes")
    fun getAllRecipeNames(): LiveData<List<String>>

    //dapetin recipe id berdasarkan nama resep yang dipilih di spinner
    @Query("SELECT recipeId FROM recipes WHERE name = :name ")
    fun getRecipeIdByName(name: String): Int?

    //== Query untuk HomeFragment
    //dapetin total kalori berdasarkan mealid
    @Query(
        """
            SELECT SUM(calories) FROM recipes
            INNER JOIN recommendations ON recipes.recipeId = recommendations.recipe_id 
            WHERE mealType = :mealType 
            AND isSelected = 2"""
    )
    suspend fun getTotalCaloriesByMealType(mealType: Int): Int

    //Query untuk menentukan jumlah rekomendasi yang dipilih berdasarkan meal type (Yang bener)
    //Kalau ada waktu, ubah yang di Dialog2 untuk pakai query ini juga
    @Query("""
        SELECT COUNT(*) FROM recommendations 
        INNER JOIN recipes ON recommendations.recipe_id = recipes.recipeId
        WHERE isSelected = 2 AND mealType = :mealType
    """)
    suspend fun getSelectedRecipeCountUsingMealType(mealType: Int): Int

    @Query(
        """
        SELECT 
            SUM(recipes.calories) AS totalCalories, 
            SUM(recipes.carbs) AS totalCarbs, 
            SUM(recipes.fat) AS totalFat, 
            SUM(recipes.protein) AS totalProtein
        FROM recipes
        INNER JOIN recommendations ON recipes.recipeId = recommendations.recipe_id
        WHERE recommendations.isSelected = 2
        """
    )
    suspend fun getNutritionSums(): NutritionSum

    @Query(
        """
    SELECT 
        SUM(recipes.calories) AS totalCalories, 
        SUM(recipes.carbs) AS totalCarbs, 
        SUM(recipes.fat) AS totalFat, 
        SUM(recipes.protein) AS totalProtein
    FROM recipes
    INNER JOIN recommendations ON recipes.recipeId = recommendations.recipe_id
    WHERE (recommendations.isSelected = 1 OR recommendations.isSelected = 2)
    AND recipes.mealType = :mealType
    """
    )
    suspend fun getNutritionSumsInBasketAndHomePerMealType(mealType: Int): NutritionSum


    @Query("""
    UPDATE recommendations
    SET isSelected = 2
    WHERE recipe_id IN (
        SELECT recipeId
        FROM recipes
        WHERE mealType = :mealType
    )
    AND isSelected = 1
""")
    suspend fun updateSelectedRecommendationsPerMealType(mealType: Int)



}