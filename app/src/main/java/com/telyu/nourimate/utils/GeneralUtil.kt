package com.telyu.nourimate.utils

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import java.util.Calendar
import java.util.Date

object GeneralUtil {
    fun getDateToday(hour: Int, minute: Int, second: Int, milisecond: Int): Long {
        val calendar = Calendar.getInstance()

        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, second)
        calendar.set(Calendar.MILLISECOND, milisecond)
        val today = calendar.timeInMillis

        return today
    }

    fun getDateNextWeek(): Long {
        val calendar = Calendar.getInstance()

        calendar.add(Calendar.DAY_OF_MONTH, 7)
        calendar.set(Calendar.HOUR_OF_DAY, 23)
        calendar.set(Calendar.MINUTE, 59)
        calendar.set(Calendar.SECOND, 59)
        calendar.set(
            Calendar.MILLISECOND,
            999
        )
        val nextWeek = calendar.timeInMillis
        return nextWeek
    }

    fun calculateAge(dob: Date?): Int {
        if (dob == null) {
            Log.d("Calculate Age", "calculateAge: dob is null")
            return 99
        }

        val dobCalendar = Calendar.getInstance()
        dobCalendar.time = dob  // Set the time of the calendar to the Date object

        val today = Calendar.getInstance()

        Log.d("Calculate Age", "DOB: ${dobCalendar.time}")  // Log the DOB
        Log.d("Calculate Age", "Today: ${today.time}")  // Log the current date

        var age = today.get(Calendar.YEAR) - dobCalendar.get(Calendar.YEAR)

        // Check if the current day of the year is before the birthday to adjust the age
        if (today.get(Calendar.DAY_OF_YEAR) < dobCalendar.get(Calendar.DAY_OF_YEAR)) {
            age--
        }

        Log.d("Calculate Age", "calculateAge: age is $age")
        return age
    }

    fun showDialog(context: Context, title: String, message: String, onYes: () -> Unit, onNo: () -> Unit) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(message)

        builder.setPositiveButton("Yes") { dialog, which ->
            onYes()
        }

        builder.setNegativeButton("No") { dialog, which ->
            onNo()
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    fun showConfirmationDialog(context: Context) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Confirmation")
        builder.setMessage("Your selected recipes have been added to the database.")

        builder.setPositiveButton("Ok") { dialog, which ->
            //huhah
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }


    //Menghitung Nutrisi
    fun calculateDailyCalorieNeeds(
        userHeight: Int,
        userGender: Boolean,
        userAge: Int
    ): Int {
        val idealWeight = (userHeight - 100) - (0.1 * (userHeight - 100))

        val dailyCalorieNeeds = when {
            userAge in 20..29 -> if (userGender) ((15.3 * idealWeight + 679) * 1.78).toInt()
            else ((14.7 * idealWeight + 496) * 1.64).toInt()

            userAge in 30..59 -> if (userGender) ((11.6 * idealWeight + 879) * 1.78).toInt()
            else ((8.7 * idealWeight + 829) * 1.64).toInt()

            userAge >= 60 -> if (userGender) ((13.5 * idealWeight + 487) * 1.78).toInt()
            else ((13.5 * idealWeight + 596) * 1.64).toInt()

            else -> -999
        }

        return (dailyCalorieNeeds * 0.2).toInt()
    }

    fun calculateDailyNutritionNeeds(dailyCalories: Int, nutritionMultiplier: Double): Int {
        return (dailyCalories * nutritionMultiplier).toInt()
    }


}