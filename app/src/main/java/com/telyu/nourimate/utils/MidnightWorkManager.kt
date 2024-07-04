package com.telyu.nourimate.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.telyu.nourimate.R
import com.telyu.nourimate.activities.NavigationBarActivity
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

        //=============== AddRecipeToMealHistory ===============
        val userId = userPreference.getUserId().first()
        val selectedRecipeIds = foodDao.getAllSelectedRecipeIds()
        val consumedTime = Converters().dateFromTimestamp(GeneralUtil.getYesterdayTimestamp())
        val consumedDate = Converters().formatDateToString(consumedTime)

        val listOfMealHistory = selectedRecipeIds.map { recipeId ->
            RecipeHistory(0, recipeId, consumedTime, consumedDate, userId)
        }

        foodDao.insertRecipeHistories(listOfMealHistory)

        //=============== DeselectRecipes ===============
        foodDao.deselectSelectedRecipes()

        showNotification("Meal History Updated", "Your meal history has been updated for yesterday.")
        return Result.success()
    }
    private fun showNotification(title: String, content: String) {
        val intent = Intent(applicationContext, NavigationBarActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent = PendingIntent.getActivity(
            applicationContext,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val builder = NotificationCompat.Builder(applicationContext, "1")
            .setSmallIcon(R.drawable.baseline_notifications_24)
            .setContentTitle(title)
            .setContentText(content)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setContentIntent(pendingIntent) // Attach the PendingIntent to the notification
            .setAutoCancel(true) // Automatically remove the notification when it is tapped

        // For Android Oreo and above, create a notification channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "1",
                "Reset Meal Notification",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(channel)
            builder.setChannelId("1")
        }

        // Show the notification
        notificationManager.notify(1, builder.build())
    }
}