package com.telyu.nourimate.fragments

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.telyu.nourimate.R
import com.telyu.nourimate.adapter.RecipeAdapter
import com.telyu.nourimate.data.local.FakeFoodData
import com.telyu.nourimate.data.local.db.FoodDatabase
import com.telyu.nourimate.data.local.models.Meal
import com.telyu.nourimate.data.local.models.Recipe
import com.telyu.nourimate.data.local.models.Recommendation
import com.telyu.nourimate.data.local.relations.MealsRecipesCrossRef
import com.telyu.nourimate.data.local.relations.RecipesRecommendationCrossRef
import com.telyu.nourimate.databinding.FragmentRecipeBinding
import com.telyu.nourimate.viewmodels.RecipeViewModel
import com.telyu.nourimate.viewmodels.ViewModelFactory
import kotlinx.coroutines.launch

class RecipeFragment : Fragment() {

    private lateinit var binding: FragmentRecipeBinding

    private val viewModel by viewModels<RecipeViewModel> {
        ViewModelFactory.getInstance(
            requireContext().applicationContext
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecipeBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fillDatabaseWithFakeData()

        displayRecipes()

        displayUserNameAndProfpic()

        setupSearchBarAndSearchView()

    }

    private fun selectMealType(onMealTypeSelected: (Int) -> Unit) {

        binding.radioGroupMealtype.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.button_breakfast -> onMealTypeSelected(1)
                R.id.button_lunch -> onMealTypeSelected(2)
                R.id.button_dinner -> onMealTypeSelected(3)
            }
        }
    }

    //popup. Mulai untuk profile feature branch
    private fun showPopupMenu() {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.popup_layout)

        // Adjust dialog properties as needed
        val layoutParams = dialog.window?.attributes
        layoutParams?.apply {
            width = WindowManager.LayoutParams.MATCH_PARENT
            height = WindowManager.LayoutParams.WRAP_CONTENT
            gravity = Gravity.CENTER
        }

        dialog.window?.attributes = layoutParams
        dialog.show()
    }

    private fun displayRecipes() {
        //rv adapter stuff
        val recipeAdapter = RecipeAdapter()
        binding.recommendationRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recommendationRecyclerView.adapter = recipeAdapter

        selectMealType { selectedMealType ->
            viewModel.getRecipeByMeal(selectedMealType)
                .observe(viewLifecycleOwner) { recipes ->
                    recipeAdapter.submitList(recipes)
                }
        }

        viewModel.searchResult.observe(viewLifecycleOwner) { recipes ->
            recipeAdapter.submitList(recipes)
        }
    }

    private fun setupSearchBarAndSearchView() {
        with(binding) {
            searchView.setupWithSearchBar(searchBar)

            searchView.editText.setOnEditorActionListener { _, _, _ ->
                searchBar.text = searchView.text
                searchView.hide()

                val query = searchBar.text.toString()
                viewModel.getRecipeByName(query)
                true // Return true to indicate that the action has been handled
            }
            searchBar.inflateMenu(R.menu.selected_meal_menu)
            searchBar.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.btn_selected_meal -> {
                        showPopupMenu()
                        true
                    }

                    else -> false
                }
            }
        }
    }

    private fun displayUserNameAndProfpic() {
        viewModel.userEmail.observe(viewLifecycleOwner) { userEmail ->
            userEmail.let {
                viewModel.getUserIdByEmail(it)
                viewModel.getUserNameByEmail(it)
            }
        }

        viewModel.userName.observe(viewLifecycleOwner) {userName ->
            binding.usernameTextView.text = userName
        }

        viewModel.userId.observe(viewLifecycleOwner) {userId ->
            if (userId != null) {
                viewModel.getProfpicById(userId)
            }
        }

        viewModel.profilePicture.observe(viewLifecycleOwner) {uriString ->
            uriString?.let { uriStr ->
                val uri = Uri.parse(uriStr)
                binding.recipeProfileImageView.setImageURI(uri)
            }

        }

    }

    private fun fillDatabaseWithFakeData(){

        // Untuk isi database dengan fake data
        val dao = FoodDatabase.getInstance(requireContext()).foodDao()
        val fakeFoodData = FakeFoodData()

        val mappedRecipes = fakeFoodData.recipes.map { recipe ->
            Recipe(
                recipeId = recipe.recipeId,
                name = recipe.name,
                calories = recipe.calories,
                carbs = recipe.carbs,
                fat = recipe.fat,
                protein = recipe.protein,
                ingredients = recipe.ingredients,
                cookingSteps = recipe.cookingSteps,
                recipePictures = recipe.recipePictures
            )
        }
        val mappedMeals = fakeFoodData.meals.map { meal ->
            Meal(
                mealId = meal.mealId,
                name = meal.name
            )
        }

        val mappedRecommendations = fakeFoodData.recommendations.map { recommendation ->
            Recommendation(
                recommendationId = recommendation.recommendationId,
                date = recommendation.date
            )
        }

        val mappedRelationRR = fakeFoodData.recipeRecommendationCrossRef.map { crossRef ->
            RecipesRecommendationCrossRef(
                recipeId = crossRef.recipeId,
                recommendationId = crossRef.recommendationId,
            )
        }

        val mappedRelationMR = fakeFoodData.mealRecipeCrossRef.map { crossRef ->
            MealsRecipesCrossRef(
                mealId = crossRef.mealId,
                recipeId = crossRef.recipeId,
            )
        }

        // Insert data into database within coroutine scope
        lifecycleScope.launch {
            // Insert each recipe individually
            mappedRecipes.forEach { recipe ->
                dao.insertRecipe(recipe)
            }
            mappedMeals.forEach { meals ->
                dao.insertMeal(meals)
            }
            mappedRecommendations.forEach { recommendation ->
                dao.insertRecommendation(recommendation)
            }
            mappedRelationRR.forEach { crossRef ->
                dao.insertRecipeRecommendationCrossRef(crossRef)
            }
            mappedRelationMR.forEach { crossRef ->
                dao.insertMealRecipeCrossRef(crossRef)
            }
        }
    }
}