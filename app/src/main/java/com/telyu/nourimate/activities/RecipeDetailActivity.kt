package com.telyu.nourimate.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
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

        val recipeId = intent.getIntExtra("RECIPE_ID", -1)
        if (recipeId != -1) {
            fetchRecipeDetails(recipeId)
        } else {
            Toast.makeText(this, "Recipe not found!", Toast.LENGTH_LONG).show()
        }
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
        }
    }

    private fun obtainViewModel(activity: AppCompatActivity): RecipeDetailViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[RecipeDetailViewModel::class.java]
    }

}