package com.telyu.nourimate.fragments

import android.annotation.SuppressLint
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
import com.telyu.nourimate.adapter.RecipeAdapter
import com.telyu.nourimate.adapter.RecommendationRecipeAdapter
import com.telyu.nourimate.data.local.FakeFoodData
import com.telyu.nourimate.data.local.db.FoodDatabase
import com.telyu.nourimate.data.local.models.Meal
import com.telyu.nourimate.data.local.models.Recipe
import com.telyu.nourimate.data.local.models.RecipeMeal
import com.telyu.nourimate.data.local.models.Recommendation
import com.telyu.nourimate.databinding.FragmentRecipeBinding
import com.telyu.nourimate.viewmodels.RecipeViewModel
import com.telyu.nourimate.viewmodels.ViewModelFactory
import com.telyu.nourimate.views.custom.RecipeDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//this code needs lots of fixes, but it'll have to do for now.
class RecipeFragment : Fragment(), RecipeAdapter.OnAddClickListener, RecommendationRecipeAdapter.OnAddClickListener {

    private lateinit var binding: FragmentRecipeBinding
    private val recipeAdapter = RecipeAdapter(this)
    private val weeklyRecipeAdapter = RecommendationRecipeAdapter(this)

    private val viewModel by viewModels<RecipeViewModel> {
        ViewModelFactory.getInstance(
            requireContext().applicationContext
        )
    }

    private var isDatabaseFilled = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecipeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!isDatabaseFilled) {
            fillDatabaseWithFakeData()
            isDatabaseFilled = true
        }
        setupRecyclerView()
        displayUserNameAndProfpic()
        setupSearchBarAndSearchView()
        setupDraggableSelectedItem()

        selectMealType()
        selectMealTime()

        viewModel.dailyRecipes.observe(viewLifecycleOwner) { recipes ->
            recipeAdapter.submitList(recipes)
        }

        viewModel.weeklyRecipes.observe(viewLifecycleOwner) { recipes ->
            Log.d("RecipeFragment", "weeklyRecipes: $recipes")
            weeklyRecipeAdapter.submitList(recipes)
        }

    }

    override fun onAddClick(recipe: Recipe) {
        CoroutineScope(Dispatchers.Main).launch {
            val mealType = viewModel.mealType.value
            val recommendation =
                viewModel.getRecommendationByRecipeAndMealId(recipe.recipeId, mealType!!)

            recommendation?.let { rec ->
                rec.isSelected = !rec.isSelected
                viewModel.selectRecommendation(rec)
                Toast.makeText(requireContext(), "Recommendation updated", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun setupRecyclerView() {
        binding.recommendationRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.weeklyRecommendationRecyclerView.layoutManager =
            LinearLayoutManager(requireContext())
        binding.recommendationRecyclerView.adapter = recipeAdapter
        binding.weeklyRecommendationRecyclerView.adapter = weeklyRecipeAdapter
    }

    private fun selectMealType() {
        binding.radioGroupMealtype.setOnCheckedChangeListener { _, checkedId ->
            val mealId = when (checkedId) {
                R.id.button_breakfast -> 1
                R.id.button_lunch -> 2
                R.id.button_dinner -> 3
                else -> 0
            }
            viewModel.setSelectedMealType(mealId)
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

            when (checkedId) {
                R.id.button_daily -> showDailyRecyclerView()
                R.id.button_weekly -> showWeeklyRecyclerView()
            }
        }
    }

    private fun showDailyRecyclerView() {
        binding.recommendationRecyclerView.visibility = View.VISIBLE
        binding.weeklyRecommendationRecyclerView.visibility = View.GONE
    }

    private fun showWeeklyRecyclerView() {
        binding.recommendationRecyclerView.visibility = View.GONE
        binding.weeklyRecommendationRecyclerView.visibility = View.VISIBLE
    }

    //popup. Mulai untuk profile feature branch
    private fun showPopupMenu() {
        val dialogFragment = RecipeDialog()
        dialogFragment.show(
            parentFragmentManager,
            "com.telyu.nourimate.views.custom.RecipeDialog"
        )
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

        val mappedMeals = fakeFoodData.meals.map { meals ->
            Meal(mealId = meals.mealId)
        }

        val mappedRecipeMeals = fakeFoodData.recipeMeal.map { recipeMeals ->
            RecipeMeal(
                mealId = recipeMeals.mealId,
                recipeId = recipeMeals.recipeId
            )
        }

        val mappedRecommendations = fakeFoodData.recommendations.map { recommendation ->
            Recommendation(
                recommendationId = recommendation.recommendationId,
                date = recommendation.date,
                isSelected = recommendation.isSelected,
                mealId = recommendation.mealId,
                recipeId = recommendation.recipeId,
            )
        }

        // Insert data into database within coroutine scope
        lifecycleScope.launch {
            // Insert each recipe individually
            mappedRecipes.forEach { recipe ->
                dao.insertRecipe(recipe)
            }
            mappedMeals.forEach { meal ->
                dao.insertMeal(meal)
            }
            mappedRecipeMeals.forEach { recipeMeal ->
                dao.insertRecipeMeal(recipeMeal)
            }
            mappedRecommendations.forEach { recommendation ->
                dao.insertRecommendation(recommendation)
            }
        }
    }
}