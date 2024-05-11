package com.telyu.nourimate.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.telyu.nourimate.data.local.models.Detail
import com.telyu.nourimate.data.local.models.NutritionSum
import com.telyu.nourimate.data.local.models.Recipe
import com.telyu.nourimate.data.local.models.Recommendation
import com.telyu.nourimate.data.local.models.RecommendationRecipe
import com.telyu.nourimate.data.repository.NourimateRepository
import com.telyu.nourimate.utils.GeneralUtil
import kotlinx.coroutines.launch
import java.lang.Exception

class RecipeViewModel(private val repository: NourimateRepository) : ViewModel() {

    /**
     * Enkapsulasi LiveData
     * Dengan pola dibawah, dipastikan hanya ViewModel yang dapat memodifikasi data.
     * Beda dengan  LiveData biasa yang publik dan dapat diubah oleh Observer mana pun
     */

    //Untuk menampung hasil search
    private val _searchResult = MutableLiveData<List<Recipe>>()
    val searchResult: LiveData<List<Recipe>> get() = _searchResult

    //Digunakan untuk menampilkan data - data terkait pengguna
    private val _userId = MutableLiveData<Int?>()
    val userId: LiveData<Int?> = _userId

    private val _profilePicture = MutableLiveData<String?>()
    val profilePicture: LiveData<String?> = _profilePicture

    private val _userName = MutableLiveData<String?>()
    val userName: LiveData<String?> = _userName

    val userEmail: LiveData<String> = repository.getUserEmail().asLiveData()

    //========== Fungsi ==========

    fun getRecipeByName(name: String) {
        viewModelScope.launch {
            val searchResult = repository.getRecipeByName(name)
            _searchResult.value = searchResult
        }
    }

//======================= FETCHING RECIPES =====================
    //Semua ini untuk apa? Encapsulation, SSOT, dan separation antara UI dan Data (Model / ViewModel)

    // MutableLiveData for storing the selected meal type
    private val _mealType = MutableLiveData<Int>()      //private and mutable. Only edit in viewModel
    val mealType: LiveData<Int> = _mealType              //public and immutable. Only takes value from _mealType, and used for exposing value to the View

    private val _mealTime = MutableLiveData<String>()
    val mealTime: LiveData<String> = _mealTime

    //the actual function that handles the change of meal type
    fun setSelectedMealType(mealType: Int) {
        _mealType.value = mealType
    }

    fun setSelectedMealTime(mealTime: String) {
        _mealTime.value = mealTime
    }

    val dailyRecipes: LiveData<List<Recipe>> = _mealType.switchMap { mealType ->
        if (mealType != 0) {
            val startDate: Long = GeneralUtil.getDateToday(0, 0, 0, 0)
            val endDate: Long = GeneralUtil.getDateToday(23, 59, 59, 999)
            repository.getRecipesByDateAndMeal(mealType, startDate, endDate)
        } else {
            MutableLiveData(emptyList())
        }
    }

    private val _recommendationRecipes = MediatorLiveData<List<RecommendationRecipe>>()
    val weeklyRecipes: LiveData<List<RecommendationRecipe>> = _recommendationRecipes

    init {
        _recommendationRecipes.addSource(_mealType) { mealTypeId ->
            Log.d("Debug", "Meal Type Changed: $mealTypeId")
            loadData(mealTypeId)
        }
    }


    private fun loadData(mealTypeId: Int) {
        val startDate: Long = GeneralUtil.getDateToday(0, 0, 0, 0)
        val endDate: Long = GeneralUtil.getDateNextWeek()
        Log.d("Debug", "Loading data for Meal Type: $mealTypeId from $startDate to $endDate")

        val recipesLiveData = repository.getRecipesByDateAndMeal(mealTypeId, startDate, endDate)
        val recommendationsLiveData = repository.getRecommendationsByMealIdSortedAscending(mealTypeId)

        _recommendationRecipes.apply {
            removeSource(recipesLiveData)
            removeSource(recommendationsLiveData)
            addSource(recipesLiveData) { recipes ->
                Log.d("Debug", "Recipes loaded: ${recipes.size}")
                combineLatestData(recipes, recommendationsLiveData.value)
            }
            addSource(recommendationsLiveData) { recommendations ->
                Log.d("Debug", "Recommendations loaded: ${recommendations.size}")
                combineLatestData(recipesLiveData.value, recommendations)
            }
        }
    }



    private fun combineLatestData(recipes: List<Recipe>?, recommendations: List<Recommendation>?) {
        if (recipes == null || recommendations == null) {
            Log.d("Debug", "One of the lists is null -> Recipes: $recipes, Recommendations: $recommendations")
            return
        }

        Log.d("Debug", "Combining data: Recipes (${recipes.size}), Recommendations (${recommendations.size})")
        val recommendationRecipes = mutableListOf<RecommendationRecipe>()
        val groupByDate = recipes.groupBy { recipe ->
            recommendations.find { it.recipeId == recipe.recipeId }?.date
        }

        for ((date, groupedRecipes) in groupByDate) {
            val recommendation = recommendations.find { it.date == date }
            if (recommendation != null) {
                recommendationRecipes.add(RecommendationRecipe.RecommendationItem(recommendation))
            }
            groupedRecipes.forEach { recipe ->
                recommendationRecipes.add(RecommendationRecipe.RecipeItem(recipe))
            }
        }
        _recommendationRecipes.value = recommendationRecipes
        Log.d("Debug", "Combined list size: ${recommendationRecipes.size}")
    }


    suspend fun getRecommendationByRecipeAndMealId(recipeId: Int, mealId: Int): Recommendation? {
        return repository.getRecommendationByRecipeAndMealId(recipeId, mealId)
    }


//======================= FETCHING RECIPES =====================




    //Digunakan di Recipe Fragment dan RecipeDialogMeal
    //Bagian pertama untuk fitur add / remove resep dengan mendapatkan Recommendation yang di klik
    fun selectRecommendation(recommendation: Recommendation) {
        viewModelScope.launch {
            repository.updateRecommendation(recommendation)
        }
    }

    suspend fun getRecommendationByRecipeIdAndMealType(
        recipeId: Int,
        mealType: Int
    ): Recommendation? {
        return repository.getRecommendationByRecipeIdAndMealType(recipeId, mealType)
    }

    //Digunakan di RecipeDialogMeal dan RecipeDialogMealTutorial
    //2 query ini buat dialog
    fun getAllSelectedRecommendationIdsByMealId(mealType: Int): LiveData<List<Int>> {
        return repository.getAllSelectedRecommendationIdsByMealId(mealType)
    }

    fun getAllConfirmedRecommendationIdsByMealId(mealType: Int): LiveData<List<Int>> {
        return repository.getAllConfirmedRecommendationIdsByMealId(mealType)
    }

    //Digunakan di RecipeDialogMeal dan RecipeDialogMealTutorial
    fun getSelectedRecipesByRecommendationIds(recommendationIds: List<Int>): LiveData<List<Recipe>> {
        return repository.getRecipesByRecommendationIds(recommendationIds)
    }


    //Digunakan di ketiga DialogFragment di RecipeFragment
    fun getSelectedRecipeCount(): LiveData<Int> {
        return repository.getSelectedRecipeCount()
    }

    //Digunakan di ketiga DialogFragment di RecipeFragment
    fun getSelectedRecipeCountByMealType(mealType: Int): LiveData<Int> {
        return repository.getSelectedRecipeCountByMealType(mealType)
    }

    //=============== Untuk di RecipeDialogMeal ===============

    //Today's Meal Related Functions
    //Part 2 of getting each nutrition percentage Values
    //Fetching the user's personal health details
    // Step 1: Fetch user's email

    // Step 2: Fetch user's details based on email
    private val userDetails: LiveData<Detail> = userEmail.switchMap { email ->
        liveData {
            val detail = repository.getUserDetailsByEmail(email)
            emit(detail)
        }
    }

    // Step 3: Get the calorie sum of selected meals
    private val _nutritionSumsInBasket = MutableLiveData<NutritionSum>()

    fun getNutritionSumsInBasketAndHomePerMealType(mealType: Int) {
        viewModelScope.launch {
            val nutritionSums = repository.getNutritionSumsInBasketAndHomePerMealType(mealType)
            _nutritionSumsInBasket.value = nutritionSums
            Log.d("NutritionSums", "Nutrition sums in basket: $nutritionSums")
        }
    }

    // LiveData to track the selected meal
    private val _selectedMeal = MutableLiveData<Int>()
    val selectedMeal: LiveData<Int> = _selectedMeal

    // Function to update selected meal from fragment
    fun setSelectedMeal(meal: Int) {
        _selectedMeal.value = meal
    }

    //Step 4: Extract user's health details
    private val maxNutritionsLiveData: LiveData<List<Int>> = selectedMeal.switchMap { meal ->
        userDetails.switchMap { detail ->
            liveData {
                val gender = if (detail.gender == "Laki-laki") true else if (detail.gender == "Perempuan") false else null
                val age = GeneralUtil.calculateAge(detail.dob)

                val akei = GeneralUtil.calculateAKEi(detail.height?.toInt() ?: 9999, gender!!, age)
                val conditionCode = GeneralUtil.convertConditionToCode(detail.disease)

                val nutrition = when (meal) {
                    1 -> akei.let { GeneralUtil.calculateBreakfastNutrition(it, conditionCode) }
                    2 -> akei.let { GeneralUtil.calculateLunchNutrition(it, conditionCode) }
                    3 -> akei.let { GeneralUtil.calculateDinnerNutrition(it, conditionCode) }
                    else -> null  // Handle invalid meal selection
                }

                //Kebutuhan nutrisi berdasarkan mealtime
                nutrition?.let {
                    val calorieNeedsOnMealTime = (it.calories).toInt()
                    val proteinNeedsOnMealTime = (it.protein).toInt()
                    val fatNeedsOnMealTime = (it.fat).toInt()
                    val carbsNeedsOnMealTime = (it.carbohydrates).toInt()

                    emit(listOf(calorieNeedsOnMealTime, proteinNeedsOnMealTime, fatNeedsOnMealTime, carbsNeedsOnMealTime))
                } ?: emit(listOf(0, 0, 0, 0))
            }
        }
    }

    val isNutritionSumWithinNeeds = MediatorLiveData<Boolean>().apply {
        var nutritionSum: NutritionSum? = null
        var maxNutritions: List<Int>? = null

        // Add source for nutrition sums
        addSource(_nutritionSumsInBasket) { sum ->
            nutritionSum = sum
            maxNutritions?.let { max -> value = performCheck(sum, max) }
        }

        // Add source for maximum nutrition values
        addSource(maxNutritionsLiveData) { max ->
            maxNutritions = max
            nutritionSum?.let { sum -> value = performCheck(sum, max) }
        }
    }

    private fun performCheck(nutritionSum: NutritionSum, maxNutritions: List<Int>): Boolean {
        Log.d("NutritionCheck", "Checking nutritional sums against max values")
        Log.d("NutritionCheck", "Calories: ${nutritionSum.totalCalories} / Max: ${maxNutritions[0]}")
        Log.d("NutritionCheck", "Protein: ${nutritionSum.totalProtein} / Max: ${maxNutritions[1]}")
        Log.d("NutritionCheck", "Fat: ${nutritionSum.totalFat} / Max: ${maxNutritions[2]}")
        Log.d("NutritionCheck", "Carbs: ${nutritionSum.totalCarbs} / Max: ${maxNutritions[3]}")

        return nutritionSum.totalCalories <= maxNutritions[0] &&
                nutritionSum.totalProtein <= maxNutritions[1] &&
                nutritionSum.totalFat <= maxNutritions[2] &&
                nutritionSum.totalCarbs <= maxNutritions[3]
    }

    fun updateSelectedRecommendationsPerMealType(mealType: Int) {
        viewModelScope.launch {
            try {
                repository.updateSelectedRecommendationsPerMealType(mealType)
                successMessage.postValue("Update successful")
            } catch (e: Exception) {
                errorMessage.postValue("Failed to update: ${e.message}")
            }
        }
    }

    // LiveData for UI messages
    private val successMessage = MutableLiveData<String>()
    private val errorMessage = MutableLiveData<String>()

    //=============== Untuk di Dialog ===============

    //Digunakan di RecipeFragment. Semua fungsi dibawah user-related
    fun getUserIdByEmail(email: String) {
        viewModelScope.launch {
            val id = repository.getUserIdByEmail(email)
            _userId.value = id
        }
    }

    fun getUserNameByEmail(email: String) {
        viewModelScope.launch {
            val name = repository.getUserNameByEmail(email)
            _userName.value = name
        }
    }

    fun getProfpicById(id: Int) {
        viewModelScope.launch {
            val profpic = repository.getProfpicById(id)
            _profilePicture.value = profpic
        }
    }


}