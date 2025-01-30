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
import com.telyu.nourimate.adapter.recipe.CombinedRecipe
import com.telyu.nourimate.data.local.models.Detail
import com.telyu.nourimate.data.local.models.NutritionSum
import com.telyu.nourimate.data.local.models.Recipe
import com.telyu.nourimate.data.local.models.Recommendation
import com.telyu.nourimate.data.local.models.RecommendationRecipe
import com.telyu.nourimate.data.repository.NourimateRepository
import com.telyu.nourimate.utils.GeneralUtil
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import java.lang.Exception

class RecipeViewModel(private val repository: NourimateRepository) : ViewModel() {

    /**
     * Enkapsulasi LiveData
     * Dengan pola dibawah, dipastikan hanya ViewModel yang dapat memodifikasi data.
     * Beda dengan  LiveData biasa yang publik dan dapat diubah oleh Observer mana pun
     */

    //Untuk menampung hasil search
    private val _searchResult = MutableLiveData<List<CombinedRecipe>>()
    val searchResult: LiveData<List<CombinedRecipe>> = _searchResult

    //Digunakan untuk menampilkan data - data terkait pengguna

    val userEmail: LiveData<String> = repository.getUserEmail().asLiveData()

    //========== Fungsi Search ==========

    fun searchRecipes(query: String, mealType: Int) {
        viewModelScope.launch {
            val recipes = repository.getRecipesByNameAndMealType(query, mealType)
            val recommendations = repository.getRecommendationsByMealId(mealType)
            val combinedRecipes = combineRecipesWithRecommendations(recipes, recommendations)
            _searchResult.postValue(combinedRecipes)
        }
    }

    private fun combineRecipesWithRecommendations(recipes: List<Recipe>, recommendations: List<Recommendation>): List<CombinedRecipe> {
        return recipes.mapNotNull { recipe ->
            recommendations.find { it.recipeId == recipe.recipeId }?.let { recommendation ->
                CombinedRecipe(recipe, recommendation)
            }
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

    val breakfastRecipes = MediatorLiveData<List<CombinedRecipe>>()
    val lunchRecipes = MediatorLiveData<List<CombinedRecipe>>()
    val dinnerRecipes = MediatorLiveData<List<CombinedRecipe>>()

    private fun setupMediatorLiveData(userId: Int) {
        val liveDataRecipes = repository.getAllRecipes()
        val liveDataRecommendations = repository.getRecommendationsByUserId(userId)

        setupMealTypeLiveData(liveDataRecipes, liveDataRecommendations, 1, breakfastRecipes)
        setupMealTypeLiveData(liveDataRecipes, liveDataRecommendations, 2, lunchRecipes)
        setupMealTypeLiveData(liveDataRecipes, liveDataRecommendations, 3, dinnerRecipes)
    }

    private fun setupMealTypeLiveData(
        liveDataRecipes: LiveData<List<Recipe>>,
        liveDataRecommendations: LiveData<List<Recommendation>>,
        mealType: Int,
        mealLiveData: MediatorLiveData<List<CombinedRecipe>>
    ) {
        mealLiveData.addSource(liveDataRecipes) { recipes -> combineData(recipes, liveDataRecommendations.value, mealType) }
        mealLiveData.addSource(liveDataRecommendations) { recommendations -> combineData(liveDataRecipes.value, recommendations, mealType) }
    }

    private fun combineData(
        recipes: List<Recipe>?,
        recommendations: List<Recommendation>?,
        mealType: Int
    ) {
        if (recipes == null || recommendations == null) {
            if (recipes == null) { Log.w("HUHAH", "Recipes list is null") }
            if (recommendations == null) { Log.w("HUHAH", "Recommendations list is null") }
            return
        }

        val combinedList = recipes.filter { it.mealType == mealType }
            .mapNotNull { recipe ->
                recommendations.find { it.recipeId == recipe.recipeId }?.let {
                    CombinedRecipe(recipe, it)
                }
            }

        when (mealType) {
            1 -> { breakfastRecipes.value = combinedList }
            2 -> { lunchRecipes.value = combinedList }
            3 -> { dinnerRecipes.value = combinedList }
        }
    }

    private val _recommendationRecipes = MediatorLiveData<List<RecommendationRecipe>>()
    val weeklyRecipes: LiveData<List<RecommendationRecipe>> = _recommendationRecipes

    init {
        _recommendationRecipes.addSource(_mealType) { mealTypeId ->
            loadData(mealTypeId)
        }
        viewModelScope.launch {
            repository.getUserId().collect { id ->
                setupMediatorLiveData(id)
            }
        }
    }

    private fun loadData(mealTypeId: Int) {
        viewModelScope.launch {
            val userId = repository.getUserId().first()
            val startDate: Long = GeneralUtil.getDateToday(0, 0, 0, 0)
            val endDate: Long = GeneralUtil.getDateNextWeek()
            Log.d("Debug", "Loading data for Meal Type: $mealTypeId from $startDate to $endDate")

            val recipesLiveData = repository.getRecipesByMealType(mealTypeId)
            val recommendationsLiveData = repository.getRecommendationsByMealIdSortedAscending(mealTypeId, userId)

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
    }


    private fun combineLatestData(recipes: List<Recipe>?, recommendations: List<Recommendation>?) {
        if (recipes == null || recommendations == null) {
            Log.d(
                "Debug",
                "One of the lists is null -> Recipes: $recipes, Recommendations: $recommendations"
            )
            return
        }

        Log.d(
            "Debug",
            "Combining data: Recipes (${recipes.size}), Recommendations (${recommendations.size})"
        )
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

    suspend fun getRecommendationById(recommendationId: Int): Recommendation? {
        return repository.getRecommendationById(recommendationId)
    }


//======================= FETCHING RECIPES =====================

    //Digunakan di Recipe Fragment dan com.telyu.nourimate.custom.RecipeDialogMeal
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

    //=============== DialogMeal dan DialogPilihResep ===============
    //2 query ini buat dialog
    fun getAllSelectedRecommendationIdsByMealId(mealType: Int): LiveData<List<Int>> {
        return repository.getAllSelectedRecommendationIdsByMealId(mealType)
    }

    fun getAllConfirmedRecommendationIdsByMealId(mealType: Int): LiveData<List<Int>> {
        return repository.getAllConfirmedRecommendationIdsByMealId(mealType)
    }

    //Digunakan di com.telyu.nourimate.custom.RecipeDialogMeal dan RecipeDialogMealTutorial
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

    //=============== Untuk di com.telyu.nourimate.custom.RecipeDialogMeal ===============

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
                val gender =
                    if (detail.gender == "Laki-laki") true else if (detail.gender == "Perempuan") false else null
                val age = GeneralUtil.calculateAge(detail.dob)

                val akei = GeneralUtil.calculateAKEi(detail.height, gender!!, age)
                val conditionCode = GeneralUtil.convertConditionToCode(detail.disease)

                val id = repository.getUserId().first()
                val weightTrack = repository.getWeightTrackById(id)
                val program = weightTrack?.ongoingProgram ?: 1  // Default to 1 if null

                val nutrition = when (meal) {
                    1 -> akei.let { GeneralUtil.calculateBreakfastNutrition(it, conditionCode, program) }
                    2 -> akei.let { GeneralUtil.calculateLunchNutrition(it, conditionCode, program) }
                    3 -> akei.let { GeneralUtil.calculateDinnerNutrition(it, conditionCode, program) }
                    else -> null  // Handle invalid meal selection
                }

                //Kebutuhan nutrisi berdasarkan mealtime
                nutrition?.let {
                    val calorieNeedsOnMealTime = (it.calories).toInt()
                    val proteinNeedsOnMealTime = (it.protein).toInt()
                    val fatNeedsOnMealTime = (it.fat).toInt()
                    val carbsNeedsOnMealTime = (it.carbohydrates).toInt()

                    emit(
                        listOf(
                            calorieNeedsOnMealTime,
                            proteinNeedsOnMealTime,
                            fatNeedsOnMealTime,
                            carbsNeedsOnMealTime
                        )
                    )
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
        Log.d(
            "NutritionCheck",
            "Calories: ${nutritionSum.totalCalories} / Max: ${maxNutritions[0]}"
        )
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

    private val userId = repository.getUserId().asLiveData()

    private var _username = MutableLiveData<String>()
    val username: LiveData<String> = _username

    val profpic: LiveData<String> = userId.switchMap { id ->
        liveData {
            val profpic = repository.getProfpicById(id)
            if (profpic != null) {
                emit(profpic)
            }
        }
    }

    fun getUsername() {
        viewModelScope.launch {
            val username = repository.getUsername().first()
            _username.value = username
        }
    }



    private val _recipeTransitionPreference = MutableLiveData<Boolean>()
    val recipeTransitionPreference = _recipeTransitionPreference

    fun getRecipeTransitionPreference() {
        viewModelScope.launch {
            val preference = repository.getRecipeTransitionPreference().first()
            _recipeTransitionPreference.value = preference
        }
    }

}