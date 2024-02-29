package com.telyu.nourimate.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.telyu.nourimate.R
import com.telyu.nourimate.data.local.dao.FoodDao
import com.telyu.nourimate.data.local.models.Meal
import com.telyu.nourimate.data.local.models.Recipe
import com.telyu.nourimate.data.local.models.Recommendation
import com.telyu.nourimate.data.local.relations.MealsRecipesCrossRef
import com.telyu.nourimate.data.local.relations.MealsRecommendationsCrossRef
import com.telyu.nourimate.data.local.relations.RecipesRecommendationCrossRef
import com.telyu.nourimate.utils.Converters
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.util.concurrent.Executors

@Database(
    entities = [Meal::class, Recipe::class,
        Recommendation::class, MealsRecipesCrossRef::class,
        RecipesRecommendationCrossRef::class, MealsRecommendationsCrossRef::class],
    version = 1, exportSchema = false
)
@TypeConverters(Converters::class)
abstract class FoodDatabase : RoomDatabase() {

    abstract fun foodDao(): FoodDao

    companion object {
        @Volatile
        private var INSTANCE: FoodDatabase? = null

        @JvmStatic
        fun getInstance(context: Context): FoodDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FoodDatabase::class.java,
                    "food"
                )
                    //prepopulate database
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            //new thread
                            Executors.newSingleThreadExecutor().execute {
                                val dao = getInstance(context).foodDao()
                                fillWithStartingData(context, dao)
                            }
                        }
                    })
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private fun fillWithStartingData(context: Context, dao: FoodDao) {
            val jsonArray = loadJsonArray(context)
            try {
                if (jsonArray != null) {
                    for (i in 0 until jsonArray.length()) {
                        val item = jsonArray.getJSONObject(i)
                        dao.insertAllMeal(
                            Meal(
                                mealId = item.getInt("mealId"),
                                name = item.getString("name")
                            )
                        )
                    }
                }
            } catch (exception: JSONException) {
                exception.printStackTrace()
            }
        }

        private fun loadJsonArray(context: Context): JSONArray? {
            return try {
                val inputStream = context.resources.openRawResource(R.raw.meal)
                val jsonString = inputStream.bufferedReader().use { it.readText() }
                JSONObject(jsonString).getJSONArray("meal")
            } catch (exception: IOException) {
                exception.printStackTrace()
                null
            } catch (exception: JSONException) {
                exception.printStackTrace()
                null
            }
        }

    }
}

//        private fun fillWithStartingData(context: Context, foodDao: FoodDao) {
//            val mealsJsonArray = loadJsonArray(context, R.raw.meal, "meal")
//            val recipesJsonArray = loadJsonArray(context, R.raw.recipe, "recipe")
//            val recommendationJsonArray = loadJsonArray(context, R.raw.recommendation, "recommendation")
//
//            mealsJsonArray?.let { jsonArray ->
//                for (i in 0 until jsonArray.length()) {
//                    val item = jsonArray.getJSONObject(i)
//                    foodDao.insertAllMeal(
//                        Meal(
//                            mealId = item.getInt("mealId"),
//                            name = item.getString("name")
//                        )
//                    )
//                }
//
//                recipesJsonArray?.let { jsonArray ->
//                    for (i in 0 until jsonArray.length()) {
//                        val item = jsonArray.getJSONObject(i)
//                        foodDao.insertAllRecipe(
//                            Recipe(
//                                recipeId = item.getInt("recipeId"),
//                                name = item.getString("name"),
//                                calories = item.getDouble("calories"),
//                                carbs = item.getDouble("carbs"),
//                                fat = item.getDouble("fat"),
//                                protein = item.getDouble("protein"),
//                                ingredients = item.getString("ingredients"),
//                                cookingSteps = item.getString("cookingSteps")
//                            )
//                        )
//                    }
//                }
//
//                recommendationJsonArray?.let { jsonArray ->
//                    for (i in 0 until jsonArray.length()) {
//                        val item = jsonArray.getJSONObject(i)
//                        foodDao.insertAllRecommendation(
//                            Recommendation(
//                                recommendationId = item.getInt("recommendationId"),
//                                date = Date(item.getLong("date"))
//                            )
//                        )
//                    }
//                }
//            }
//        }
//
//        private fun loadJsonArray(context: Context, resourceId: Int, jsonKey: String): JSONArray? {
//            val builder = StringBuilder()
//            val inputStream = context.resources.openRawResource(resourceId)
//            val reader = BufferedReader(InputStreamReader(inputStream))
//            var line: String?
//
//            try {
//                while (reader.readLine().also { line = it } != null) {
//                    builder.append(line)
//                }
//                val json = JSONObject(builder.toString())
//                return json.getJSONArray(jsonKey)
//            } catch (exception: IOException) {
//                exception.printStackTrace()
//            } catch (exception: JSONException) {
//                exception.printStackTrace()
//            } finally {
//                reader.close()
//                inputStream.close()
//            }
//            return null
//        }
//    }
