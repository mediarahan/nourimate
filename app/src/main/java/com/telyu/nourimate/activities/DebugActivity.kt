package com.telyu.nourimate.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.telyu.nourimate.R
import com.telyu.nourimate.data.local.db.FoodDatabase
import com.telyu.nourimate.data.local.db.UserDatabase
import com.telyu.nourimate.data.local.models.Detail
import com.telyu.nourimate.data.local.models.History
import com.telyu.nourimate.data.local.models.RecipeHistory
import com.telyu.nourimate.data.local.models.WeightEntry
import com.telyu.nourimate.data.local.models.WeightTrack
import com.telyu.nourimate.utils.Converters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DebugActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_debug)

        val buttonInsertAlvin = findViewById<View>(R.id.bial)
        val buttonInsertRatika = findViewById<View>(R.id.bira)
        val buttonInsertRionov = findViewById<View>(R.id.biri)

        val db = UserDatabase.getInstance(this).userDao()
        val db2 = FoodDatabase.getInstance(this).foodDao()

        buttonInsertAlvin.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.deleteDetail()
                db.deleteHistories()
                db.deleteWeightEntries()
                db.deleteWeightTrack()
                db2.deleteRecipeHistories()
                db.insertDetail(detailAlvin)
                db.insertHistories(historyAlvin)
                db.insertWeightEntries(grafikAlvin)
                db.insertWeightTrack(programAlvin)
                db2.insertRecipeHistories(mealHistoryAlvin)
                startActivity(Intent(this@DebugActivity, NavigationBarActivity::class.java))
            }
        }

        buttonInsertRatika.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.deleteDetail()
                db.deleteHistories()
                db.deleteWeightEntries()
                db.deleteWeightTrack()
                db2.deleteRecipeHistories()
                db.insertDetail(detailRatika)
                db.insertHistories(historyRatika)
                db.insertWeightEntries(grafikRatika)
                db.insertWeightTrack(programRatika)
                db2.insertRecipeHistories(mealHistoryRatika)
                startActivity(Intent(this@DebugActivity, NavigationBarActivity::class.java))
            }
        }

        buttonInsertRionov.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.deleteDetail()
                db.deleteHistories()
                db.deleteWeightEntries()
                db.deleteWeightTrack()
                db2.deleteRecipeHistories()
                db.insertDetail(detailRionov)
                db.insertHistories(historyRionov)
                db.insertWeightEntries(grafikRionov)
                db.insertWeightTrack(programRionov)
                db2.insertRecipeHistories(mealHistoryRionov)
                startActivity(Intent(this@DebugActivity, NavigationBarActivity::class.java))
            }
        }
    }

    private val detailAlvin = Detail(
        detailId = 0,
        dob = Converters().dateFromTimestamp(1054944000000),
        height = 175,
        weight = 95,
        waistSize = 80,
        gender = "Laki-laki",
        allergen = "Eggs",
        disease = "Diabetes,Hipertensi",
        bmi = 31.03f,
        userId = 2
    )
    private val detailRatika = Detail(
        detailId = 0,
        dob = Converters().dateFromTimestamp(1054944000000),
        height = 152,
        weight = 60,
        waistSize = 80,
        gender = "Perempuan",
        allergen = "Eggs",
        disease = "Kolesterol",
        bmi = 25.95f,
        userId = 2
    )
    private val detailRionov = Detail(
        detailId = 1,
        dob = Converters().dateFromTimestamp(1036886400000),
        height = 172,
        weight = 72,
        waistSize = 80,
        gender = "Laki-laki",
        allergen = "Eggs",
        disease = "Diabetes",
        bmi = 24.39f,
        userId = 2
    )

//        Day 1 (11 June 2024): 1718064000000 ms, formatted as "2024/06/11"
//        Day 2 (12 June 2024): 1718150400000 ms, formatted as "2024/06/12"
//        Day 3 (13 June 2024): 1718236800000 ms, formatted as "2024/06/13"
//        Day 4 (14 June 2024): 1718323200000 ms, formatted as "2024/06/14"
//        Day 5 (15 June 2024): 1718409600000 ms, formatted as "2024/06/15"
//        Day 6 (16 June 2024): 1718496000000 ms, formatted as "2024/06/16"
//        Day 7 (17 June 2024): 1718582400000 ms, formatted as "2024/06/17"

    private val mealHistoryAlvin = listOf(
        RecipeHistory(id = 0, recipeId = 31, consumedTime = Converters().dateFromTimestamp(1718064000000), consumedDate = "2024/06/11", userId = 2), // breakfast 1 day 1
        RecipeHistory(id = 0, recipeId = 292, consumedTime = Converters().dateFromTimestamp(1718064000000), consumedDate = "2024/06/11", userId = 2), // lunch 1 day 1
        RecipeHistory(id = 0, recipeId = 242, consumedTime = Converters().dateFromTimestamp(1718064000000), consumedDate = "2024/06/11", userId = 2), // lunch 2 day 1
        RecipeHistory(id = 0, recipeId = 255, consumedTime = Converters().dateFromTimestamp(1718064000000), consumedDate = "2024/06/11", userId = 2), // dinner 1 day 1
        RecipeHistory(id = 0, recipeId = 282, consumedTime = Converters().dateFromTimestamp(1718150400000), consumedDate = "2024/06/12", userId = 2), // breakfast 1 day 2
        RecipeHistory(id = 0, recipeId = 262, consumedTime = Converters().dateFromTimestamp(1718150400000), consumedDate = "2024/06/12", userId = 2), // lunch 1 day 2
        RecipeHistory(id = 0, recipeId = 126, consumedTime = Converters().dateFromTimestamp(1718150400000), consumedDate = "2024/06/12", userId = 2), // lunch 2 day 2
        RecipeHistory(id = 0, recipeId = 80, consumedTime = Converters().dateFromTimestamp(1718150400000), consumedDate = "2024/06/12", userId = 2), // dinner 1 day 2
        RecipeHistory(id = 0, recipeId = 65, consumedTime = Converters().dateFromTimestamp(1718236800000), consumedDate = "2024/06/13", userId = 2), // breakfast 1 day 3
        RecipeHistory(id = 0, recipeId = 98, consumedTime = Converters().dateFromTimestamp(1718236800000), consumedDate = "2024/06/13", userId = 2), // lunch 1 day 3
        RecipeHistory(id = 0, recipeId = 178, consumedTime = Converters().dateFromTimestamp(1718236800000), consumedDate = "2024/06/13", userId = 2), // lunch 2 day 3
        RecipeHistory(id = 0, recipeId = 102, consumedTime = Converters().dateFromTimestamp(1718236800000), consumedDate = "2024/06/13", userId = 2), // dinner 1 day 3
        RecipeHistory(id = 0, recipeId = 125, consumedTime = Converters().dateFromTimestamp(1718323200000), consumedDate = "2024/06/14", userId = 2), // breakfast 1 day 4
        RecipeHistory(id = 0, recipeId = 87, consumedTime = Converters().dateFromTimestamp(1718323200000), consumedDate = "2024/06/14", userId = 2), // lunch 1 day 4
        RecipeHistory(id = 0, recipeId = 117, consumedTime = Converters().dateFromTimestamp(1718323200000), consumedDate = "2024/06/14", userId = 2), // lunch 2 day 4
        RecipeHistory(id = 0, recipeId = 161, consumedTime = Converters().dateFromTimestamp(1718323200000), consumedDate = "2024/06/14", userId = 2), // dinner 1 day 4
        RecipeHistory(id = 0, recipeId = 141, consumedTime = Converters().dateFromTimestamp(1718409600000), consumedDate = "2024/06/15", userId = 2), // breakfast 1 day 5
        RecipeHistory(id = 0, recipeId = 118, consumedTime = Converters().dateFromTimestamp(1718409600000), consumedDate = "2024/06/15", userId = 2), // lunch 1 day 5
        RecipeHistory(id = 0, recipeId = 30, consumedTime = Converters().dateFromTimestamp(1718409600000), consumedDate = "2024/06/15", userId = 2), // lunch 2 day 5
        RecipeHistory(id = 0, recipeId = 142, consumedTime = Converters().dateFromTimestamp(1718409600000), consumedDate = "2024/06/15", userId = 2), // dinner 1 day 5
        RecipeHistory(id = 0, recipeId = 46, consumedTime = Converters().dateFromTimestamp(1718496000000), consumedDate = "2024/06/16", userId = 2), // breakfast 1 day 6
        RecipeHistory(id = 0, recipeId = 292, consumedTime = Converters().dateFromTimestamp(1718496000000), consumedDate = "2024/06/16", userId = 2), // lunch 1 day 6
        RecipeHistory(id = 0, recipeId = 242, consumedTime = Converters().dateFromTimestamp(1718496000000), consumedDate = "2024/06/16", userId = 2), // lunch 2 day 6
        RecipeHistory(id = 0, recipeId = 51, consumedTime = Converters().dateFromTimestamp(1718496000000), consumedDate = "2024/06/16", userId = 2), // dinner 1 day 6
        RecipeHistory(id = 0, recipeId = 134, consumedTime = Converters().dateFromTimestamp(1718582400000), consumedDate = "2024/06/17", userId = 2), // breakfast 1 day 7
        RecipeHistory(id = 0, recipeId = 262, consumedTime = Converters().dateFromTimestamp(1718582400000), consumedDate = "2024/06/17", userId = 2), // lunch 1 day 7
        RecipeHistory(id = 0, recipeId = 126, consumedTime = Converters().dateFromTimestamp(1718582400000), consumedDate = "2024/06/17", userId = 2), // lunch 2 day 7
        RecipeHistory(id = 0, recipeId = 127, consumedTime = Converters().dateFromTimestamp(1718582400000), consumedDate = "2024/06/17", userId = 2), // dinner 1 day 7
    )

    private val mealHistoryRatika = listOf(
        RecipeHistory(id = 0, recipeId = 282, consumedTime = Converters().dateFromTimestamp(1718064000000), consumedDate = "2024/06/11", userId = 2), // breakfast 1 day 1
        RecipeHistory(id = 0, recipeId = 238, consumedTime = Converters().dateFromTimestamp(1718064000000), consumedDate = "2024/06/11", userId = 2), // lunch 1 day 1
        RecipeHistory(id = 0, recipeId = 292, consumedTime = Converters().dateFromTimestamp(1718064000000), consumedDate = "2024/06/11", userId = 2), // lunch 2 day 1
        RecipeHistory(id = 0, recipeId = 232, consumedTime = Converters().dateFromTimestamp(1718064000000), consumedDate = "2024/06/11", userId = 2), // dinner 1 day 1
        RecipeHistory(id = 0, recipeId = 31, consumedTime = Converters().dateFromTimestamp(1718150400000) , consumedDate = "2024/06/12", userId = 2), // breakfast 1 day 2
        RecipeHistory(id = 0, recipeId = 262, consumedTime = Converters().dateFromTimestamp(1718150400000), consumedDate = "2024/06/12", userId = 2), // lunch 1 day 2
        RecipeHistory(id = 0, recipeId = 87, consumedTime = Converters().dateFromTimestamp(1718150400000), consumedDate = "2024/06/12", userId = 2), // lunch 2 day 2
        RecipeHistory(id = 0, recipeId = 246, consumedTime = Converters().dateFromTimestamp(1718150400000), consumedDate = "2024/06/12", userId = 2), // dinner 1 day 2
        RecipeHistory(id = 0, recipeId = 181, consumedTime = Converters().dateFromTimestamp(1718236800000), consumedDate = "2024/06/13", userId = 2), // breakfast 1 day 3
        RecipeHistory(id = 0, recipeId = 242, consumedTime = Converters().dateFromTimestamp(1718236800000), consumedDate = "2024/06/13", userId = 2), // lunch 1 day 3
        RecipeHistory(id = 0, recipeId = 98, consumedTime = Converters().dateFromTimestamp(1718236800000), consumedDate = "2024/06/13", userId = 2), // lunch 2 day 3
        RecipeHistory(id = 0, recipeId = 201, consumedTime = Converters().dateFromTimestamp(1718236800000), consumedDate = "2024/06/13", userId = 2), // dinner 1 day 3
        RecipeHistory(id = 0, recipeId = 65, consumedTime = Converters().dateFromTimestamp(1718323200000), consumedDate = "2024/06/14", userId = 2), // breakfast 1 day 4
        RecipeHistory(id = 0, recipeId = 117, consumedTime = Converters().dateFromTimestamp(1718323200000), consumedDate = "2024/06/14", userId = 2), // lunch 1 day 4
        RecipeHistory(id = 0, recipeId = 178, consumedTime = Converters().dateFromTimestamp(1718323200000), consumedDate = "2024/06/14", userId = 2), // lunch 2 day 4
        RecipeHistory(id = 0, recipeId = 255, consumedTime = Converters().dateFromTimestamp(1718323200000), consumedDate = "2024/06/14", userId = 2), // dinner 1 day 4
        RecipeHistory(id = 0, recipeId = 36, consumedTime = Converters().dateFromTimestamp(1718409600000), consumedDate = "2024/06/15", userId = 2), // breakfast 1 day 5
        RecipeHistory(id = 0, recipeId = 271, consumedTime = Converters().dateFromTimestamp(1718409600000), consumedDate = "2024/06/15", userId = 2), // lunch 1 day 5
        RecipeHistory(id = 0, recipeId = 126, consumedTime = Converters().dateFromTimestamp(1718409600000), consumedDate = "2024/06/15", userId = 2), // lunch 2 day 5
        RecipeHistory(id = 0, recipeId = 102, consumedTime = Converters().dateFromTimestamp(1718409600000), consumedDate = "2024/06/15", userId = 2), // dinner 1 day 5
        RecipeHistory(id = 0, recipeId = 44, consumedTime = Converters().dateFromTimestamp(1718496000000), consumedDate = "2024/06/16", userId = 2), // breakfast 1 day 6
        RecipeHistory(id = 0, recipeId = 87, consumedTime = Converters().dateFromTimestamp(1718496000000), consumedDate = "2024/06/16", userId = 2), // lunch 1 day 6
        RecipeHistory(id = 0, recipeId = 242, consumedTime = Converters().dateFromTimestamp(1718496000000), consumedDate = "2024/06/16", userId = 2), // lunch 2 day 6
        RecipeHistory(id = 0, recipeId = 98, consumedTime = Converters().dateFromTimestamp(1718496000000), consumedDate = "2024/06/16", userId = 2), // lunch 3 day 6
        RecipeHistory(id = 0, recipeId = 161, consumedTime = Converters().dateFromTimestamp(1718496000000), consumedDate = "2024/06/16", userId = 2), // dinner 1 day 6
        RecipeHistory(id = 0, recipeId = 204, consumedTime = Converters().dateFromTimestamp(1718582400000), consumedDate = "2024/06/17", userId = 2), // breakfast 1 day 7
        RecipeHistory(id = 0, recipeId = 292, consumedTime = Converters().dateFromTimestamp(1718582400000), consumedDate = "2024/06/17", userId = 2), // lunch 1 day 7
        RecipeHistory(id = 0, recipeId = 117, consumedTime = Converters().dateFromTimestamp(1718582400000), consumedDate = "2024/06/17", userId = 2), // lunch 2 day 7
        RecipeHistory(id = 0, recipeId = 126, consumedTime = Converters().dateFromTimestamp(1718582400000), consumedDate = "2024/06/17", userId = 2), // lunch 3 day 7
        RecipeHistory(id = 0, recipeId = 137, consumedTime = Converters().dateFromTimestamp(1718582400000), consumedDate = "2024/06/17", userId = 2), // dinner 1 day 7
    )

    private val mealHistoryRionov = listOf(
        RecipeHistory(id = 0, recipeId = 282, consumedTime = Converters().dateFromTimestamp(1718064000000), consumedDate = "2024/06/11", userId = 2), // breakfast 1 day 1
        RecipeHistory(id = 0, recipeId = 31, consumedTime = Converters().dateFromTimestamp(1718064000000), consumedDate = "2024/06/11", userId = 2), // breakfast 2 day 1
        RecipeHistory(id = 0, recipeId = 242, consumedTime = Converters().dateFromTimestamp(1718064000000), consumedDate = "2024/06/11", userId = 2), // lunch 1 day 1
        RecipeHistory(id = 0, recipeId = 292, consumedTime = Converters().dateFromTimestamp(1718064000000), consumedDate = "2024/06/11", userId = 2), // lunch 2 day 1
        RecipeHistory(id = 0, recipeId = 232, consumedTime = Converters().dateFromTimestamp(1718064000000), consumedDate = "2024/06/11", userId = 2), // dinner 1 day 1
        RecipeHistory(id = 0, recipeId = 255, consumedTime = Converters().dateFromTimestamp(1718064000000), consumedDate = "2024/06/11", userId = 2), // dinner 2 day 1
        RecipeHistory(id = 0, recipeId = 181, consumedTime = Converters().dateFromTimestamp(1718150400000), consumedDate = "2024/06/12", userId = 2), // breakfast 1 day 2
        RecipeHistory(id = 0, recipeId = 65, consumedTime = Converters().dateFromTimestamp(1718150400000), consumedDate ="2024/06/12" , userId = 2), // breakfast 2 day 2
        RecipeHistory(id = 0, recipeId = 238, consumedTime = Converters().dateFromTimestamp(1718150400000), consumedDate = "2024/06/12", userId = 2), // lunch 1 day 2
        RecipeHistory(id = 0, recipeId = 117, consumedTime = Converters().dateFromTimestamp(1718150400000), consumedDate = "2024/06/12", userId = 2), // lunch 2 day 2
        RecipeHistory(id = 0, recipeId = 137, consumedTime = Converters().dateFromTimestamp(1718150400000), consumedDate = "2024/06/12", userId = 2), // dinner 1= day 2
        RecipeHistory(id = 0, recipeId = 227, consumedTime = Converters().dateFromTimestamp(1718150400000), consumedDate = "2024/06/12", userId = 2), // dinner 2 day 2
        RecipeHistory(id = 0, recipeId = 36, consumedTime = Converters().dateFromTimestamp(1718236800000), consumedDate = "2024/06/13", userId = 2), // breakfast 1 day 3
        RecipeHistory(id = 0, recipeId = 44, consumedTime = Converters().dateFromTimestamp(1718236800000), consumedDate = "2024/06/13", userId = 2), // breakfast 2 day 3
        RecipeHistory(id = 0, recipeId = 98, consumedTime = Converters().dateFromTimestamp(1718236800000), consumedDate = "2024/06/13", userId = 2), // lunch 1 day 3
        RecipeHistory(id = 0, recipeId = 262, consumedTime = Converters().dateFromTimestamp(1718236800000), consumedDate = "2024/06/13", userId = 2), // lunch 2 day 3
        RecipeHistory(id = 0, recipeId = 80, consumedTime = Converters().dateFromTimestamp(1718236800000), consumedDate = "2024/06/13", userId = 2), // dinner 1 day 3
        RecipeHistory(id = 0, recipeId = 175, consumedTime = Converters().dateFromTimestamp(1718236800000), consumedDate = "2024/06/13", userId = 2), // dinner 2 day 3
        RecipeHistory(id = 0, recipeId = 91, consumedTime = Converters().dateFromTimestamp(1718323200000), consumedDate = "2024/06/14", userId = 2), // breakfast 1 day 4
        RecipeHistory(id = 0, recipeId = 212, consumedTime = Converters().dateFromTimestamp(1718323200000), consumedDate = "2024/06/14", userId = 2), // breakfast 2 day 4
        RecipeHistory(id = 0, recipeId = 126, consumedTime = Converters().dateFromTimestamp(1718323200000), consumedDate = "2024/06/14", userId = 2), // lunch 1 day 4
        RecipeHistory(id = 0, recipeId = 87, consumedTime = Converters().dateFromTimestamp(1718323200000), consumedDate = "2024/06/14", userId = 2), // lunch 2 day 4
        RecipeHistory(id = 0, recipeId = 284, consumedTime = Converters().dateFromTimestamp(1718323200000), consumedDate = "2024/06/14", userId = 2), // dinner 1 day 4
        RecipeHistory(id = 0, recipeId = 270, consumedTime = Converters().dateFromTimestamp(1718323200000), consumedDate = "2024/06/14", userId = 2), // dinner 2 day 4
        RecipeHistory(id = 0, recipeId = 311, consumedTime = Converters().dateFromTimestamp(1718409600000), consumedDate = "2024/06/15", userId = 2), // breakfast 1 day 5
        RecipeHistory(id = 0, recipeId = 215, consumedTime = Converters().dateFromTimestamp(1718409600000), consumedDate = "2024/06/15", userId = 2), // breakfast 2 day 5
        RecipeHistory(id = 0, recipeId = 214, consumedTime = Converters().dateFromTimestamp(1718409600000), consumedDate = "2024/06/15", userId = 2), // lunch 1 day 5
        RecipeHistory(id = 0, recipeId = 271, consumedTime = Converters().dateFromTimestamp(1718409600000), consumedDate = "2024/06/15", userId = 2), // lunch 2 day 5
        RecipeHistory(id = 0, recipeId = 64, consumedTime = Converters().dateFromTimestamp(1718409600000), consumedDate = "2024/06/15", userId = 2), // dinner 1 day 5
        RecipeHistory(id = 0, recipeId = 102, consumedTime = Converters().dateFromTimestamp(1718409600000), consumedDate = "2024/06/15", userId = 2), // dinner 2 day 5
        RecipeHistory(id = 0, recipeId = 282, consumedTime = Converters().dateFromTimestamp(1718496000000), consumedDate = "2024/06/16", userId = 2), // breakfast 1 day 6
        RecipeHistory(id = 0, recipeId = 181, consumedTime = Converters().dateFromTimestamp(1718496000000), consumedDate = "2024/06/16", userId = 2), // breakfast 2 day 6
        RecipeHistory(id = 0, recipeId = 292, consumedTime = Converters().dateFromTimestamp(1718496000000), consumedDate = "2024/06/16", userId = 2), // lunch 1 day 6
        RecipeHistory(id = 0, recipeId = 238, consumedTime = Converters().dateFromTimestamp(1718496000000), consumedDate = "2024/06/16", userId = 2), // lunch 2 day 6
        RecipeHistory(id = 0, recipeId = 98, consumedTime = Converters().dateFromTimestamp(1718496000000), consumedDate = "2024/06/16", userId = 2), // lunch 3 day 6
        RecipeHistory(id = 0, recipeId = 232, consumedTime = Converters().dateFromTimestamp(1718496000000), consumedDate = "2024/06/16", userId = 2), // dinner 1 day 6
        RecipeHistory(id = 0, recipeId = 255, consumedTime = Converters().dateFromTimestamp(1718496000000), consumedDate = "2024/06/16", userId = 2), // dinner 2 day 6
        RecipeHistory(id = 0, recipeId = 65, consumedTime = Converters().dateFromTimestamp(1718582400000), consumedDate ="2024/06/17" , userId = 2), // breakfast 1 day 7
        RecipeHistory(id = 0, recipeId = 36, consumedTime = Converters().dateFromTimestamp(1718582400000), consumedDate ="2024/06/17", userId = 2), // breakfast 2 day 7
        RecipeHistory(id = 0, recipeId = 117, consumedTime = Converters().dateFromTimestamp(1718582400000), consumedDate = "2024/06/17", userId = 2), // lunch 1 day 7
        RecipeHistory(id = 0, recipeId = 262, consumedTime = Converters().dateFromTimestamp(1718582400000), consumedDate = "2024/06/17", userId = 2), // lunch 2 day 7
        RecipeHistory(id = 0, recipeId = 126, consumedTime = Converters().dateFromTimestamp(1718582400000), consumedDate = "2024/06/17" , userId = 2), // lunch 2 day 7
        RecipeHistory(id = 0, recipeId = 137, consumedTime = Converters().dateFromTimestamp(1718582400000), consumedDate = "2024/06/17", userId = 2), // dinner 1 day 7
        RecipeHistory(id = 0, recipeId = 227, consumedTime = Converters().dateFromTimestamp(1718582400000), consumedDate = "2024/06/17", userId = 2), // dinner 2 day 7
        // continue similarly...
    )


    private val historyAlvin = listOf(
        History(
            id = 0,
            programName = "Lose Weight",
            startDate = "2024-05-27",
            endDate = "2024-06-03",
            calories = 11710,
            protein = 491,
            fat = 349,
            carbs = 2160,
            startWeight = 97,
            endWeight = 95,
            userId = 2,
            createdAt = System.currentTimeMillis()
        ), History(
            id = 0,
            programName = "Maintain Weight",
            startDate = "2024-06-04",
            endDate = "2024-06-10",
            calories = 13746,
            protein = 429,
            fat = 305,
            carbs = 1890,
            startWeight = 95,
            endWeight = 95,
            userId = 2,
            createdAt = System.currentTimeMillis()
        )
    )
    private val historyRatika = listOf(
        History(
            id = 0,
            programName = "Maintain Weight",
            startDate = "2024-05-21",
            endDate = "2024-06-02",
            calories = 16627,
            protein = 511,
            fat = 415,
            carbs = 2701,
            startWeight = 60,
            endWeight = 61,
            userId = 2,
            createdAt = System.currentTimeMillis()
        ),
        History(
            id = 0,
            programName = "Lose Weight",
            startDate = "2024-06-03",
            endDate = "2024-06-10",
            calories = 6232,
            protein = 314,
            fat = 255,
            carbs = 1662,
            startWeight = 61,
            endWeight = 60,
            userId = 2,
            createdAt = System.currentTimeMillis(),
        )
    )
    private val historyRionov = listOf(
        History(
            id = 0,
            programName = "Gain Weight",
            startDate = "2024-05-19",
            endDate = "2024-05-26",
            calories = 17560,
            protein = 762,
            fat = 376,
            carbs = 2203,
            startWeight = 70,
            endWeight = 71,
            userId = 2,
            createdAt = System.currentTimeMillis()
        ), History(
            id = 0,
            programName = "Gain Weight",
            startDate = "2024-05-27",
            endDate = "2024-06-02",
            calories = 15365,
            protein = 667,
            fat = 329,
            carbs = 1927,
            startWeight = 71,
            endWeight = 72,
            userId = 2,
            createdAt = System.currentTimeMillis()
        )
    )

    private val programAlvin = WeightTrack(
        id = 0,
        ongoingProgram = 2,
        startDate = Converters().fromTimestamp(1718064000000),
        endDate = Converters().fromTimestamp(1722470400000),
        startWeight = 95,
        endWeight = 95,
        editCurrentWeightDate = Converters().dateFromTimestamp(System.currentTimeMillis()),
        userId = 2
    )
    private val programRatika = WeightTrack(
        id = 0,
        ongoingProgram = 1,
        startDate = Converters().fromTimestamp(1718064000000),
        endDate = Converters().fromTimestamp(1025481600000),
        startWeight = 60,
        endWeight = 60,
        editCurrentWeightDate = Converters().dateFromTimestamp(System.currentTimeMillis()),
        userId = 2
    )
    private val programRionov = WeightTrack(
        id = 0,
        ongoingProgram = 3,
        startDate = Converters().fromTimestamp(1718064000000),
        endDate = Converters().fromTimestamp(1719792000000),
        startWeight = 72,
        endWeight = 72,
        editCurrentWeightDate = Converters().dateFromTimestamp(System.currentTimeMillis()),
        userId = 2
    )

    private val grafikAlvin = listOf(
        WeightEntry(
            id = 0,
            weight = 95,
            date = Converters().dateFromTimestamp(1718064000000), // 11 Juni
            userId = 2
        ),
        WeightEntry(
            id = 0,
            weight = 94,
            date = Converters().dateFromTimestamp(1718323200000), // 14 Juni
            userId = 2
        ),
        WeightEntry(
            id = 0,
            weight = 95,
            date = Converters().dateFromTimestamp(1718582400000), // 17 Juni
            userId = 2
        ),
    )
    private val grafikRatika = listOf(
        WeightEntry(
            id = 0,
            weight = 60,
            date = Converters().dateFromTimestamp(1718064000000), // 11 Juni
            userId = 2
        ),
        WeightEntry(
            id = 0,
            weight = 59,
            date = Converters().dateFromTimestamp(1718323200000), // 14 Juni
            userId = 2
        ),
        WeightEntry(
            id = 0,
            weight = 59,
            date = Converters().dateFromTimestamp(1718582400000), // 17 Juni
            userId = 2
        ),
    )
    private val grafikRionov = listOf(
        WeightEntry(
            id = 0,
            weight = 72,
            date = Converters().dateFromTimestamp(1718064000000), // 11 Juni
            userId = 2
        ),
        WeightEntry(
            id = 0,
            weight = 73,
            date = Converters().dateFromTimestamp(1718409600000), // 15 Juni
            userId = 2
        ),
        WeightEntry(
            id = 0,
            weight = 73,
            date = Converters().dateFromTimestamp(1718668800000), // 18 Juni
            userId = 2
        ),
    )

}
