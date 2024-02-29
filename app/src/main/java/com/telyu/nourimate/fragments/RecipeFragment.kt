package com.telyu.nourimate.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.os.Build
import androidx.fragment.app.viewModels
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
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date


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
                cookingSteps = recipe.cookingSteps
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

//        selectMealTypeAndDate { mealId, date ->
//            viewModel.getRecipeByMealAndDate(mealId, date)
//                .observe(viewLifecycleOwner) { recipes ->
//                    recipeAdapter.submitList(recipes)
//                }
//        }
    }

//    private fun selectMealTypeAndDate(onMealTypeAndDateSelected: (Int, Date) -> Unit) {
//        val today = Calendar.getInstance().time
//        binding.radioGroupMealtype.setOnCheckedChangeListener { group, checkedId ->
//            val mealId = when (checkedId) {
//                R.id.button_breakfast -> 1
//                R.id.button_lunch -> 2
//                R.id.button_dinner -> 3
//                else -> -1
//            }
//            onMealTypeAndDateSelected(mealId, today)
//        }
//    }

        private fun selectMealType(onMealTypeSelected: (Int) -> Unit) {

        binding.radioGroupMealtype.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.button_breakfast -> onMealTypeSelected(1)
                R.id.button_lunch -> onMealTypeSelected(2)
                R.id.button_dinner -> onMealTypeSelected(3)
            }
        }
    }

}


//    private val allFoodData = mutableListOf<Food>().apply {
//        repeat(12) {
//            add(breakfastData)
//            add(lunchData)
//            add(dinnerData)
//        }
//
//    }

//
//    private var dailyBreakfastClicked = false
//    private var dailyLunchClicked = false
//    private var dailyDinnerClicked = false
//
//    private var weeklyBreakfastClicked = false
//    private var weeklyLunchClicked = false
//    private var weeklyDinnerClicked = false
//
//    private var isBreakfastAdded = false
//    private var isLunchAdded = false
//    private var isDinnerAdded = false
//
//    private var addedBreakfast: Food? = null
//    private var addedLunch: Food? = null
//    private var addedDinner: Food? = null

//    private fun updateRecommendations(foodList: List<Food>) {
//        val adapter = RecipeAdapter(foodList, showAddButton = false) { /* Empty lambda */ }
//        binding.recommendationRecyclerView.adapter = adapter
//        updateButtonState(binding.btnBreakfast)
//        updateButtonState(binding.btnLunch)
//        updateButtonState(binding.btnDinner)
//    }


//    private fun displayRecommendations(foodList: List<Food>) {
//        val adapter = RecipeAdapter(foodList.take(2), showAddButton = dailyOrWeeklyClicked) { food: Food ->
//            onAddButtonClick(food)
//        }
//        binding.recommendationRecyclerView.adapter = adapter
//        updateButtonState(binding.btnBreakfast)
//        updateButtonState(binding.btnLunch)
//        updateButtonState(binding.btnDinner)
//    }

//    private fun displayWeeklyRecommendations(foodList: List<Food>) {
//        val filteredList = mutableListOf<Food>()
//
//        for ((index, food) in foodList.withIndex()) {
//            // Menambahkan elemen tanggal
//            if (index % 2 == 0) {
//                val dateFood = Food("Date ${index / 2 + 1}", 0, "", "", food.date ?: "")
//                filteredList.add(dateFood)
//            }
//
//            // Menambahkan dua elemen makanan setelah setiap tanggal
//            if (index % 2 == 1) {
//                filteredList.add(food.copy(isAdded = isFoodAdded(food)))
//                if (index < foodList.size - 1) {
//                    filteredList.add(foodList[index + 1].copy(isAdded = isFoodAdded(foodList[index + 1])))
//                }
//            }
//        }

//        val adapter = RecipeAdapter(filteredList, dailyOrWeeklyClicked) { food: Food ->
//            onAddButtonClick(food)
//        }
//        binding.recommendationRecyclerView.adapter = adapter
//        updateButtonState(binding.btnBreakfast)
//        updateButtonState(binding.btnLunch)
//        updateButtonState(binding.btnDinner)
//    }

//    private fun isFoodAdded(food: Food): Boolean {
//        return when {
//            food == breakfastData && isBreakfastAdded -> true
//            food == lunchData && isLunchAdded -> true
//            food == dinnerData && isDinnerAdded -> true
//            else -> false
//        }
//    }

//    private fun enableMealButtons() {
//        binding.btnBreakfast.apply {
//            setBackgroundColor(resources.getColor(android.R.color.darker_gray))
//            isClickable =
//                dailyOrWeeklyClicked && !dailyBreakfastClicked || !dailyOrWeeklyClicked && !weeklyBreakfastClicked
//            dailyBreakfastClicked = false
//            weeklyBreakfastClicked = false
//        }
//
//        binding.btnLunch.apply {
//            setBackgroundColor(resources.getColor(android.R.color.darker_gray))
//            isClickable =
//                dailyOrWeeklyClicked && !dailyLunchClicked || !dailyOrWeeklyClicked && !weeklyLunchClicked
//            dailyLunchClicked = false
//            weeklyLunchClicked = false
//        }
//
//        binding.btnDinner.apply {
//            setBackgroundColor(resources.getColor(android.R.color.darker_gray))
//            isClickable =
//                dailyOrWeeklyClicked && !dailyDinnerClicked || !dailyOrWeeklyClicked && !weeklyDinnerClicked
//            dailyDinnerClicked = false
//            weeklyDinnerClicked = false
//        }
//    }


//    private fun updateButtonState(button: View) {
//        val selectedColor = "#FFC745"
//        val unselectedColor = "#F1EFEF"
//
//        if (button == binding.btnDaily || button == binding.btnWeekly) {
//            binding.btnDaily.setBackgroundColor(
//                if (dailyOrWeeklyClicked) android.graphics.Color.parseColor(selectedColor)
//                else android.graphics.Color.parseColor(unselectedColor)
//            )
//            binding.btnWeekly.setBackgroundColor(
//                if (!dailyOrWeeklyClicked) android.graphics.Color.parseColor(selectedColor)
//                else android.graphics.Color.parseColor(unselectedColor)
//            )
//        }
//
//        if (button == binding.btnBreakfast) {
//            binding.btnBreakfast.setBackgroundColor(
//                if (dailyOrWeeklyClicked && dailyBreakfastClicked || !dailyOrWeeklyClicked && weeklyBreakfastClicked)
//                    android.graphics.Color.parseColor(selectedColor)
//                else
//                    android.graphics.Color.parseColor(unselectedColor)
//            )
//        }
//
//        if (button == binding.btnLunch) {
//            binding.btnLunch.setBackgroundColor(
//                if (dailyOrWeeklyClicked && dailyLunchClicked || !dailyOrWeeklyClicked && weeklyLunchClicked)
//                    android.graphics.Color.parseColor(selectedColor)
//                else
//                    android.graphics.Color.parseColor(unselectedColor)
//            )
//        }
//
//        if (button == binding.btnDinner) {
//            binding.btnDinner.setBackgroundColor(
//                if (dailyOrWeeklyClicked && dailyDinnerClicked || !dailyOrWeeklyClicked && weeklyDinnerClicked)
//                    android.graphics.Color.parseColor(selectedColor)
//                else
//                    android.graphics.Color.parseColor(unselectedColor)
//            )
//        }
//    }

//    class SpacingItemDecoration(private val spacingInPixels: Int) : RecyclerView.ItemDecoration() {
//
//        override fun getItemOffsets(
//            outRect: Rect,
//            view: View,
//            parent: RecyclerView,
//            state: RecyclerView.State
//        ) {
//            outRect.left = spacingInPixels
//            outRect.right = spacingInPixels
//            outRect.bottom = spacingInPixels
//            outRect.top = spacingInPixels
//        }
//    }


//    private fun showPopup() {
//        if (dailyOrWeeklyClicked) {
//            val inflater = LayoutInflater.from(requireContext())
//            val dialogView = inflater.inflate(R.layout.popup_layout, null)
//            val binding = PopupLayoutBinding.bind(dialogView)
//
//            val dialogBuilder = AlertDialog.Builder(requireContext())
//                .setView(binding.root)
//                .setTitle("Selected Meal")
//
//            val alertDialog = dialogBuilder.create()
//            alertDialog.show()
//
//            // Set text and visibility based on added meals
//            binding.breakfastTextView.text = addedBreakfast?.name ?: "No breakfast added"
//            binding.lunchTextView.text = addedLunch?.name ?: "No lunch added"
//            binding.dinnerTextView.text = addedDinner?.name ?: "No dinner added"
//
//            // Set click listeners for each button
//            binding.breakfastImageView.setOnClickListener {
//                showToast("Breakfast selected")
//                alertDialog.dismiss()
//            }
//
//            binding.lunchImageView.setOnClickListener {
//                showToast("Lunch selected")
//                alertDialog.dismiss()
//            }
//
//            binding.dinnerImageView.setOnClickListener {
//                showToast("Dinner selected")
//                alertDialog.dismiss()
//            }
//        }
//    }


//    private fun onAddButtonClick(food: Food) {
//        when {
//            food == breakfastData && addedBreakfast == null -> {
//                addedBreakfast = food
//                showToast("Breakfast added")
//            }
//            food == lunchData && addedLunch == null -> {
//                addedLunch = food
//                showToast("Lunch added")
//            }
//            food == dinnerData && addedDinner == null -> {
//                addedDinner = food
//                showToast("Dinner added")
//            }
//        }
//    }


//    private fun showToast(message: String) {
//        // Implementasi tampilan pesan toast di sini
//        // Misalnya, menggunakan Toast.makeText
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }


//        binding.selectedMealButton.visibility = if (dailyOrWeeklyClicked) View.VISIBLE else View.GONE
//
//        binding.btnBreakfast.isEnabled = false
//        binding.btnLunch.isEnabled = false
//        binding.btnDinner.isEnabled = false
//
//
//        binding.btnDaily.setOnClickListener {
//            dailyOrWeeklyClicked = true
//            binding.selectedMealButton.visibility = View.VISIBLE
//            updateButtonState(binding.btnDaily)
//            updateButtonState(binding.btnWeekly)
//            enableMealButtons()
//            updateRecommendations(emptyList())
//
//            // Aktifkan tombol-tombol setelah tombol "Daily" diklik
//            isButtonsEnabled = true
//            binding.btnBreakfast.isEnabled = true
//            binding.btnLunch.isEnabled = true
//            binding.btnDinner.isEnabled = true
//        }
//
//
//        binding.btnWeekly.setOnClickListener {
//            dailyOrWeeklyClicked = false
//            binding.selectedMealButton.visibility = View.GONE
//            updateButtonState(binding.btnWeekly)
//            updateButtonState(binding.btnDaily)
//            enableMealButtons()
//            weeklyBreakfastClicked = false
//            weeklyLunchClicked = false
//            weeklyDinnerClicked = false
//            updateRecommendations(emptyList())
//
//            // Aktifkan tombol-tombol setelah tombol "Weekly" diklik
//            isButtonsEnabled = true
//            binding.btnBreakfast.isEnabled = true
//            binding.btnLunch.isEnabled = true
//            binding.btnDinner.isEnabled = true
//        }
//
//
//        binding.recommendationRecyclerView.layoutManager = LinearLayoutManager(context)
//
//        binding.btnBreakfast.setOnClickListener {
//            if (dailyOrWeeklyClicked) {
//                dailyBreakfastClicked = true
//                dailyLunchClicked = false
//                dailyDinnerClicked = false
//                displayRecommendations(allFoodData.filterIndexed { index, _ -> index % 3 == 0 })
//            } else {
//                weeklyBreakfastClicked = true
//                weeklyLunchClicked = false
//                weeklyDinnerClicked = false
//                displayWeeklyRecommendations(allFoodData.filterIndexed { index, _ -> index % 3 == 0 })
//            }
//            updateButtonState(binding.btnBreakfast)
//            updateButtonState(binding.btnLunch)
//            updateButtonState(binding.btnDinner)
//        }
//
//        binding.btnLunch.setOnClickListener {
//            if (dailyOrWeeklyClicked) {
//                dailyBreakfastClicked = false
//                dailyLunchClicked = true
//                dailyDinnerClicked = false
//                displayRecommendations(allFoodData.filterIndexed { index, _ -> index % 3 == 1 })
//            } else {
//                weeklyBreakfastClicked = false
//                weeklyLunchClicked = true
//                weeklyDinnerClicked = false
//                displayWeeklyRecommendations(allFoodData.filterIndexed { index, _ -> index % 3 == 1 })
//            }
//            updateButtonState(binding.btnBreakfast)
//            updateButtonState(binding.btnLunch)
//            updateButtonState(binding.btnDinner)
//        }
//
//        binding.btnDinner.setOnClickListener {
//            if (dailyOrWeeklyClicked) {
//                dailyBreakfastClicked = false
//                dailyLunchClicked = false
//                dailyDinnerClicked = true
//                displayRecommendations(allFoodData.filterIndexed { index, _ -> index % 3 == 2 })
//            } else {
//                weeklyBreakfastClicked = false
//                weeklyLunchClicked = false
//                weeklyDinnerClicked = true
//                displayWeeklyRecommendations(allFoodData.filterIndexed { index, _ -> index % 3 == 2 })
//            }
//            updateButtonState(binding.btnBreakfast)
//            updateButtonState(binding.btnLunch)
//            updateButtonState(binding.btnDinner)
//        }
//
//        binding.selectedMealButton.setOnClickListener {
//            showPopup()
//        }