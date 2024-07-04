package com.telyu.nourimate.utils

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.telyu.nourimate.data.remote.retrofit.ApiConfig
import com.telyu.nourimate.data.remote.retrofit.CreateNewMealHistoryRequest
import kotlinx.coroutines.flow.first

class MealHistoryWorkManager(context: Context, workerParams: WorkerParameters):
    CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        // Extract the parameters
        val recipeId = inputData.getInt("recipeId", 0)
        val consumedTime = inputData.getString("consumedTime") ?: ""
        val consumedDate = inputData.getString("consumedDate") ?: ""

        val userPreference = UserPreference(applicationContext.dataStore)
        val apiService = ApiConfig.getApiService(applicationContext)

        return try {
            val userId = userPreference.getUserId().first()

            val requestBody = CreateNewMealHistoryRequest(
                recipeId,
                consumedTime,
                consumedDate,
                userId
            )
            val response = apiService.createNewMealHistory(requestBody)
            if (response.isSuccessful) { // Response object from Retrofit
                Result.success()
            } else {
                Result.retry()
            }
        } catch (e: Exception) {
            Log.e("MealHistoryWorker", "Failed to create new meal history", e)
            Result.failure()
        }
    }
}
