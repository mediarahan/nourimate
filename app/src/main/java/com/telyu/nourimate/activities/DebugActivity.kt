package com.telyu.nourimate.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import com.telyu.nourimate.R
import com.telyu.nourimate.data.local.dao.UserDao
import com.telyu.nourimate.data.local.db.FoodDatabase
import com.telyu.nourimate.data.local.db.UserDatabase
import com.telyu.nourimate.data.local.models.Recipe
import kotlinx.coroutines.launch

class DebugActivity : AppCompatActivity() {

    private lateinit var dao: UserDao
    private lateinit var deleteAllButton: Button
    private lateinit var queryButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_debug)

        dao = UserDatabase.getInstance(this).userDao()

        deleteAllButton = findViewById(R.id.ButtonDeleteAllUserDb)
        queryButton = findViewById(R.id.querybutton)

        deleteAllButton.setOnClickListener {
            deleteAllRecords()
        }

        queryButton.setOnClickListener {
            deleteAllRecords()
        }
    }

    private fun deleteAllRecords() {
        lifecycleScope.launch {
            dao.deleteAllRecords()
        }
    }

//    private fun query() {
//        lifecycleScope.launch {
//            val foodDatabase = FoodDatabase.getInstance(context = applicationContext)
//            val foodDao = foodDatabase.foodDao()
//
//            // Perform the random recipe insertion query
//            val recipe = Recipe(
//                recipeId = 1,
//                name = "Spaghetti Carbonara",
//                calories = 500.0,
//                carbs = 60.0,
//                fat = 20.0,
//                protein = 25.0,
//                ingredients = "200g spaghetti\n100g pancetta\n2 eggs\n50g grated Parmesan cheese\n2 cloves garlic\nSalt and pepper to taste",
//                cookingSteps = "1. Cook spaghetti according to package instructions.\n2. In a pan, fry pancetta until crispy.\n3. In a bowl, whisk together eggs and Parmesan cheese.\n4. Drain spaghetti and add to the pan with pancetta.\n5. Pour egg mixture over spaghetti and toss until coated.\n6. Serve immediately with additional grated Parmesan cheese and freshly ground black pepper."
//            )
//
//            foodDao.insertAllRecipe(recipe)
//        }
//    }

}