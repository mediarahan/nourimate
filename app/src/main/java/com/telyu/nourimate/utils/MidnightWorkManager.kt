package com.telyu.nourimate.utils

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.telyu.nourimate.data.local.db.FoodDatabase
import com.telyu.nourimate.data.local.models.RecipeHistory
import kotlinx.coroutines.flow.first


class MidnightWorkManager(appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        // Get the database instance
        val db = FoodDatabase.getInstance(applicationContext)
        val foodDao = db.foodDao()

        val userPreference = UserPreference(applicationContext.dataStore)
        val userId = userPreference.getUserId().first()

        val selectedRecipeIds = foodDao.getAllSelectedRecipeIds()
        val consumedDate = Converters().dateFromTimestamp(GeneralUtil.getYesterdayTimestamp())

        val listOfMealHistory = selectedRecipeIds.map { recipeId ->
            RecipeHistory(0, recipeId, consumedDate, userId)
        }

        foodDao.insertRecipeHistories(listOfMealHistory)

        return Result.success()
    }
}

