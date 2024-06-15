package com.telyu.nourimate.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import com.telyu.nourimate.R
import com.telyu.nourimate.databinding.ActivityRecipeDetailBinding
import com.telyu.nourimate.databinding.LayoutRecipeHistoryHomeBinding
import com.telyu.nourimate.databinding.LayoutRecipeHomeBinding
import com.telyu.nourimate.viewmodels.HomeViewModel
import com.telyu.nourimate.viewmodels.RecipeDetailViewModel
import com.telyu.nourimate.viewmodels.ViewModelFactory

class RecipeDetailActivity : AppCompatActivity() {

    private lateinit var binding: LayoutRecipeHomeBinding
    private lateinit var viewModel: RecipeDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = obtainViewModel(this@RecipeDetailActivity)

        binding = LayoutRecipeHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setStatusBarColor(resources.getColor(R.color.color48, theme))

        val recipeId = intent.getIntExtra("RECIPE_ID", -1)
        if (recipeId != -1) {
            fetchRecipeDetails(recipeId)
        } else {
            Toast.makeText(this, "Recipe not found!", Toast.LENGTH_LONG).show()
        }

        initializeUI()
    }

    private fun initializeUI() {
        binding.backButton.setOnClickListener {
            onBackPressed()
        }

    }

    private fun setStatusBarColor(color: Int) {
        val window = window
        val insetsController = WindowCompat.getInsetsController(window, window.decorView)

        // Set to 'true' to ensure status bar icons are dark, useful for light status bar backgrounds
        insetsController.isAppearanceLightStatusBars = true
        // Set to 'true' to ensure navigation bar icons are dark, useful for light navigation bar backgrounds
        insetsController.isAppearanceLightNavigationBars = true

        // Set the status bar color
        window.statusBarColor = color
    }

    private fun fetchRecipeDetails(recipeId: Int) {
        // Example: Use a ViewModel to get recipe details
        viewModel.getRecipeDetailByRecipeId(recipeId)
        viewModel.recipe.observe(this) { recipe ->
            binding.tvNamefood.text = recipe.name
            val resourceId = resources.getIdentifier(
                recipe.recipePictures,
                "drawable",
                packageName
            )
            binding.ivRecipe.setImageResource(resourceId)
            binding.tvIngredients.text = recipe.ingredients
            binding.tvCookingstepsbystep.text = recipe.cookingSteps
            binding.tvPreparationtime.text = recipe.prepTime
            binding.tvCooktime.text = recipe.cookTime
        }
    }

    private fun obtainViewModel(activity: AppCompatActivity): RecipeDetailViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[RecipeDetailViewModel::class.java]
    }


}