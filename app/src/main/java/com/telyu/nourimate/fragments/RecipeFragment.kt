package com.telyu.nourimate.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.telyu.nourimate.R
import com.telyu.nourimate.adapter.recipe.CombinedRecipe
import com.telyu.nourimate.adapter.recipe.RecipeAdapter
import com.telyu.nourimate.adapter.recipe.RecipeAdapter2
import com.telyu.nourimate.adapter.recipe.RecommendationRecipeAdapter
import com.telyu.nourimate.data.local.FakeFoodData
import com.telyu.nourimate.data.local.db.FoodDatabase
import com.telyu.nourimate.data.local.models.Recipe
import com.telyu.nourimate.databinding.FragmentRecipeBinding
import com.telyu.nourimate.viewmodels.RecipeViewModel
import com.telyu.nourimate.viewmodels.ViewModelFactory
import com.telyu.nourimate.custom.RecipeDialog
import com.telyu.nourimate.data.local.models.RecipeHistory
import com.telyu.nourimate.data.local.models.Recommendation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//this code needs lots of fixes, but it'll have to do for now.
class RecipeFragment : Fragment() {

    private lateinit var binding: FragmentRecipeBinding
    private val weeklyRecipeAdapter = RecommendationRecipeAdapter()
    private lateinit var breakfastRecipeAdapter: RecipeAdapter2
    private lateinit var lunchRecipeAdapter: RecipeAdapter2
    private lateinit var dinnerRecipeAdapter: RecipeAdapter2


    private val viewModel by viewModels<RecipeViewModel> {
        ViewModelFactory.getInstance(
            requireContext().applicationContext
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecipeBinding.inflate(inflater, container, false)
        fillDatabaseWithFakeData()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //supaya ilang dulu
        binding.radioGroupMealtype.visibility = View.GONE
        binding.searchBar.visibility = View.GONE

        setupRecyclerViewAdapter()
        setupRecyclerView()
        displayUserNameAndProfpic()
        setupSearchBarAndSearchView()
        setupDraggableSelectedItem()

        selectMealType()
        selectMealTime()

        viewModel.weeklyRecipes.observe(viewLifecycleOwner) {weeklyRecipes ->
            weeklyRecipeAdapter.submitList(weeklyRecipes)
        }

        viewModel.searchResult.observe(viewLifecycleOwner) { combinedRecipes ->
            when (viewModel.mealType.value) {
                1 -> breakfastRecipeAdapter.submitList(combinedRecipes)
                2 -> lunchRecipeAdapter.submitList(combinedRecipes)
                3 -> dinnerRecipeAdapter.submitList(combinedRecipes)
                else -> {  }
            }
        }
    }

    private fun setupRecyclerViewAdapter() {
        breakfastRecipeAdapter = RecipeAdapter2 { combinedRecipe ->
            toggleSelection(combinedRecipe)
        }
        lunchRecipeAdapter = RecipeAdapter2 { combinedRecipe ->
            toggleSelection(combinedRecipe)
        }
        dinnerRecipeAdapter = RecipeAdapter2 { combinedRecipe ->
            toggleSelection(combinedRecipe)
        }
    }

    private fun toggleSelection(combinedRecipe: CombinedRecipe) {
        CoroutineScope(Dispatchers.Main).launch {
            val recommendation = viewModel.getRecommendationById(combinedRecipe.recommendation.recommendationId)
            recommendation?.let { rec ->
                rec.isSelected = if (combinedRecipe.recommendation.isSelected == 0) 1 else 0
                viewModel.selectRecommendation(rec)
            }
        }
    }

    private fun setupRecyclerView() {
        binding.breakfastRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.lunchRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.dinnerRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.weeklyRecommendationRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        binding.breakfastRecyclerView.adapter = breakfastRecipeAdapter
        binding.lunchRecyclerView.adapter = lunchRecipeAdapter
        binding.dinnerRecyclerView.adapter = dinnerRecipeAdapter
        binding.weeklyRecommendationRecyclerView.adapter = weeklyRecipeAdapter
    }

    private fun selectMealType() {
        binding.radioGroupMealtype.setOnCheckedChangeListener { _, checkedId ->
            hideAllDailyRecyclerViews()

            val mealId = when (checkedId) {
                R.id.button_breakfast -> {
                    if (!binding.buttonWeekly.isChecked) {
                        viewModel.breakfastRecipes.observe(viewLifecycleOwner) {
                            breakfastRecipeAdapter.submitList(it)
                        }
                        binding.breakfastRecyclerView.visibility = View.VISIBLE
                    }
                    1
                }
                R.id.button_lunch -> {
                    if (!binding.buttonWeekly.isChecked) {
                        viewModel.lunchRecipes.observe(viewLifecycleOwner) {
                            lunchRecipeAdapter.submitList(it)
                        }
                        binding.lunchRecyclerView.visibility = View.VISIBLE
                    }
                    2
                }
                R.id.button_dinner -> {
                    if (!binding.buttonWeekly.isChecked) {
                        viewModel.dinnerRecipes.observe(viewLifecycleOwner) {
                            dinnerRecipeAdapter.submitList(it)
                        }
                        binding.dinnerRecyclerView.visibility = View.VISIBLE
                    }
                    3
                }
                else -> 0
            }
            viewModel.setSelectedMealType(mealId)
            binding.searchBar.visibility = if (binding.buttonWeekly.isChecked) View.GONE else View.VISIBLE
        }
    }




    private fun selectMealTime() {
        binding.radioGroupMealtime.setOnCheckedChangeListener { _, checkedId ->
            val mealTime = when (checkedId) {
                R.id.button_daily -> "Daily"
                R.id.button_weekly -> "Weekly"
                else -> "Invalid"
            }
            viewModel.setSelectedMealTime(mealTime)
            binding.radioGroupMealtype.visibility = View.VISIBLE
            updateRecyclerViewVisibility(mealTime)
        }
    }

    private fun updateRecyclerViewVisibility(mealTime: String) {
        if (mealTime == "Daily") {
            showDailyRecyclerViewsBasedOnMealType()
            binding.weeklyRecommendationRecyclerView.visibility = View.GONE
        } else {
            hideAllDailyRecyclerViews()
            binding.weeklyRecommendationRecyclerView.visibility = View.VISIBLE
        }
    }

    private fun showDailyRecyclerViewsBasedOnMealType() {
        val checkedId = binding.radioGroupMealtype.checkedRadioButtonId
        binding.breakfastRecyclerView.visibility = if (checkedId == R.id.button_breakfast) View.VISIBLE else View.GONE
        binding.lunchRecyclerView.visibility = if (checkedId == R.id.button_lunch) View.VISIBLE else View.GONE
        binding.dinnerRecyclerView.visibility = if (checkedId == R.id.button_dinner) View.VISIBLE else View.GONE
    }

    private fun hideAllDailyRecyclerViews() {
        binding.breakfastRecyclerView.visibility = View.GONE
        binding.lunchRecyclerView.visibility = View.GONE
        binding.dinnerRecyclerView.visibility = View.GONE
    }

    //popup. Mulai untuk profile feature branch
    private fun showPopupMenu() {
        val dialogFragment = RecipeDialog()
        dialogFragment.show(
            parentFragmentManager,
            "com.telyu.nourimate.custom.RecipeDialog"
        )
    }

    private fun setupSearchBarAndSearchView() {
        with(binding) {
            searchView.setupWithSearchBar(searchBar)

            searchView.editText.setOnEditorActionListener { _, _, _ ->
                searchBar.text = searchView.text
                searchView.hide()

                val query = searchBar.text.toString()
                val mealType = viewModel.mealType.value ?: 0
                viewModel.searchRecipes(query, mealType)
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

    //FAB selected meal
    @SuppressLint("ClickableViewAccessibility")
    private fun setupDraggableSelectedItem() {
        var dX = 0f
        var dY = 0f
        var lastAction = 0

        binding.selecteditem.setOnTouchListener { view, event ->
            when (event.actionMasked) {
                MotionEvent.ACTION_DOWN -> {
                    dX = view.x - event.rawX
                    dY = view.y - event.rawY
                    lastAction = MotionEvent.ACTION_DOWN
                    true
                }

                MotionEvent.ACTION_MOVE -> {
                    view.y = event.rawY + dY
                    view.x = event.rawX + dX
                    lastAction = MotionEvent.ACTION_MOVE
                    true
                }

                MotionEvent.ACTION_UP -> {
                    if (lastAction == MotionEvent.ACTION_DOWN) {
                        // Logika untuk menampilkan dialog pop-up
                        showPopupMenu()
                    }
                    true
                }

                else -> false
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

        viewModel.userName.observe(viewLifecycleOwner) { userName ->
            //dari username, ambil kata paling depan
            binding.usernameTextView.text = userName

        }

        viewModel.userId.observe(viewLifecycleOwner) { userId ->
            if (userId != null) {
                viewModel.getProfpicById(userId)
            }
        }

        viewModel.profilePicture.observe(viewLifecycleOwner) { uriString ->
            uriString?.let { uriStr ->
                val uri = Uri.parse(uriStr)
                binding.recipeProfileImageView.setImageURI(uri)
            }

        }

    }

    private fun fillDatabaseWithFakeData() {
        val prefs = requireActivity().getSharedPreferences("AppPrefs", Activity.MODE_PRIVATE)
        val isDataFilled = prefs.getBoolean("isDataFilled", false)

        if (!isDataFilled) {
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
                    recipePictures = recipe.recipePictures,
                    mealType = recipe.mealType,
                    cookTime = recipe.cookTime,
                    prepTime = recipe.prepTime,
                    totalTime = recipe.totalTime,
                    portion = recipe.portion,
                )
            }

            val mappedRecommendations = fakeFoodData.recommendations.map { recommendation ->
                Recommendation(
                    recommendationId = recommendation.recommendationId,
                    date = recommendation.date,
                    isSelected = recommendation.isSelected,
                    recipeId = recommendation.recipeId,
                    userId = recommendation.userId,
                )
            }

            val mappedRecipeHistory = fakeFoodData.recipeHistory.map { recipeHistory ->
                RecipeHistory(
                    id = recipeHistory.id,
                    recipeId = recipeHistory.recipeId,
                    consumedDate = recipeHistory.consumedDate,
                    userId = recipeHistory.userId,
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
                mappedRecipeHistory.forEach { recipeHistory ->
                    dao.insertRecipeHistory(recipeHistory)
                }
            }
            prefs.edit().putBoolean("isDataFilled", true).apply()
        }
    }
}