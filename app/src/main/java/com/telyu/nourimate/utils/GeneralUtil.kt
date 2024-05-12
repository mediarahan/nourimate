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

        builder.setPositiveButton("Yes") { _, _ ->
            onYes()
        }

        builder.setNegativeButton("No") { _, _ ->
            onNo()
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    fun showConfirmationDialog(context: Context) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Confirmation")
        builder.setMessage("Your selected recipes have been added to the database.")

        builder.setPositiveButton("Ok") { _, _ ->
            //huhah
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }


    //Step 1: Menghitung BB Ideal + faktor usia
    fun calculateAKEi(
        userHeight: Int,
        userGender: Boolean?,
        userAge: Int
    ): Int {
        val idealWeight = (userHeight - 100) - (0.1 * (userHeight - 100))

        val AKEi = when {
            userAge in 20..29 -> if (userGender == true) ((15.3 * idealWeight + 679) * 1.78).toInt()
            else ((14.7 * idealWeight + 496) * 1.64).toInt()

            userAge in 30..59 -> if (userGender == true) ((11.6 * idealWeight + 879) * 1.78).toInt()
            else ((8.7 * idealWeight + 829) * 1.64).toInt()

            userAge >= 60 -> if (userGender == true) ((13.5 * idealWeight + 487) * 1.78).toInt()
            else ((13.5 * idealWeight + 596) * 1.64).toInt()

            else -> -999
        }

        return (AKEi)
    }

    private fun calculateMealNutrition(akei: Int, conditionCode: Int, mealProportion: Double): Nutrition {
        val dailyCalories = mealProportion * akei
        val (carbMultiplier, protMultiplier, fatMultiplier) = multipliers[conditionCode] ?: Triple(0.55, 0.8, 0.2)
        val nutritionCalculator = if (conditionCode == K) {
            // Special handling for Cholesterol
            { multiplier: Double -> multiplier * akei }
        } else {
            { multiplier: Double -> multiplier * dailyCalories }
        }

        return Nutrition(
            calories = dailyCalories,
            carbohydrates = nutritionCalculator(carbMultiplier) / 4,
            protein = nutritionCalculator(protMultiplier) / 4,
            fat = nutritionCalculator(fatMultiplier) / 9
        )
    }

    fun calculateBreakfastNutrition(akei: Int, conditionCode: Int) =
        calculateMealNutrition(akei = akei, conditionCode, 0.25)

    fun calculateLunchNutrition(akei: Int, conditionCode: Int) =
        calculateMealNutrition(akei = akei, conditionCode, 0.40)

    fun calculateDinnerNutrition(akei: Int, conditionCode: Int) =
        calculateMealNutrition(akei = akei, conditionCode, 0.35)

    data class Nutrition(
        val calories: Double,
        val carbohydrates: Double,
        val protein: Double,
        val fat: Double
    )

    fun convertConditionToCode(condition: String): Int {
        return when (condition) {
            "Diabetes" -> D
            "Hypertension" -> H
            "Cholesterol" -> K
            "Hypertension, Cholesterol" -> HK
            "Diabetes, Cholesterol" -> DK
            "Diabetes, Hypertension" -> DH
            "Diabetes, Hypertension, Cholesterol" -> DHK
            else -> -1
        }
    }

    private const val D = 1  // Diabetes
    private const val H = 2  // Hypertension
    private const val K = 3  // Cholesterol
    private const val HK = 4 // Hypertension + Cholesterol
    private const val DK = 5 // Diabetes + Cholesterol
    private const val DH = 6 // Diabetes + Hypertension
    private const val DHK = 7 // Diabetes + Hypertension + Cholesterol

    private val multipliers = mapOf(
        DHK to Triple(0.55, 0.8, 0.2),
        DH to Triple(0.55, 0.8, 0.225),
        DK to Triple(0.55, 0.8, 0.2),
        HK to Triple(0.6, 0.8, 0.2),
        D to Triple(0.65, 0.125, 0.225),
        H to Triple(0.625, 0.8, 0.25),
        K to Triple(0.65, 0.8, 0.225)
    )

    //Sleep API related
//    fun convertMillisToDateTime(millis: Long): String {
//        val formatter = SimpleDateFormat("dd MMM yyyy, HH:mm:ss", Locale.getDefault())
//        return formatter.format(millis)
//    }

    fun calculateDuration(startTimeMillis: Long, endTimeMillis: Long): String {
        val durationMillis = endTimeMillis - startTimeMillis
        val hours = durationMillis / (1000 * 60 * 60)
        val minutes = (durationMillis / (1000 * 60)) % 60
        return "${hours}h ${minutes}m"
    }
}