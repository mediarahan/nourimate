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
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.telyu.nourimate.R
import com.telyu.nourimate.adapter.RecipeAdapter
import com.telyu.nourimate.data.local.FakeFoodData
import com.telyu.nourimate.data.local.db.FoodDatabase
import com.telyu.nourimate.data.local.models.Recipe
import com.telyu.nourimate.data.local.models.Recommendation
import com.telyu.nourimate.databinding.FragmentRecipeBinding
import com.telyu.nourimate.viewmodels.RecipeViewModel
import com.telyu.nourimate.viewmodels.ViewModelFactory
import com.telyu.nourimate.views.custom.RecipeDialog
import kotlinx.coroutines.launch

class RecipeFragment : Fragment() {

    private lateinit var binding: FragmentRecipeBinding

    private val viewModel by viewModels<RecipeViewModel> {
        ViewModelFactory.getInstance(
            requireContext().applicationContext
        )
    }

    private var isDatabaseFilled = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecipeBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //jank as fuck database insertion check
        if (!isDatabaseFilled) {
            fillDatabaseWithFakeData()
            isDatabaseFilled = true
        }
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


    private fun displayRecipes() {
        //rv adapter stuff
        val recipeAdapter = RecipeAdapter()
        binding.recommendationRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recommendationRecyclerView.adapter = recipeAdapter

        selectMealType { mealType ->
            viewModel.getRecipeByMealType(mealType)
        }

        viewModel.recipeList.observe(viewLifecycleOwner) { recipes ->
            recipeAdapter.submitList(recipes)
        }

        viewModel.searchResult.observe(viewLifecycleOwner) { recipes ->
            recipeAdapter.submitList(recipes)
        }
    }

    //popup. Mulai untuk profile feature branch
    private fun showPopupMenu() {
        val customDialog = RecipeDialog(requireContext(), R.layout.popup_layout)
        customDialog.show()

        // Example: Modify dialog views
        customDialog.setDialogTitle("Custom Dialog Title")
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

        val mappedRecommendations = fakeFoodData.recommendations.map { recommendation ->
            Recommendation(
                recommendationId = recommendation.recommendationId,
                date = recommendation.date,
                isSelected = recommendation.isSelected,
                mealType = recommendation.mealType,
            )
        }

        // Insert data into database within coroutine scope
        lifecycleScope.launch {
            // Insert each recipe individually
            mappedRecipes.forEach { recipe ->
                dao.insertRecipe(recipe)
            }
            mappedRecommendations.forEach { recommendation ->
                dao.insertRecommendation(recommendation)
            }
        }
    }
}