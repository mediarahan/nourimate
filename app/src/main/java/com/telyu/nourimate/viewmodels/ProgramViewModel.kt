package com.telyu.nourimate.viewmodels

import MealHistoryWorkManager
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.telyu.nourimate.data.local.models.Detail
import com.telyu.nourimate.data.local.models.History
import com.telyu.nourimate.data.local.models.NutritionSum
import com.telyu.nourimate.data.local.models.Recipe
import com.telyu.nourimate.data.local.models.RecipeHistory
import com.telyu.nourimate.data.local.models.RecipeHistoryData
import com.telyu.nourimate.data.local.models.WeightEntry
import com.telyu.nourimate.data.local.models.WeightTrack
import com.telyu.nourimate.data.repository.NourimateRepository
import com.telyu.nourimate.utils.Converters
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import java.util.Date

class ProgramViewModel(private val repository: NourimateRepository) : ViewModel() {

    val userId = repository.getUserId().asLiveData()

    val userWeightTrack: LiveData<WeightTrack> = userId.switchMap { id ->
        Log.d("ProgramViewModel", "userWeightTrack: $id")
        liveData {
            val weightTrack = repository.getWeightTrackById(id)
            if (weightTrack != null) {
                emit(weightTrack)
            }
        }
    }

    val userEndDate: LiveData<Long> = userId.switchMap { id ->
        liveData {
            val weightTrack = repository.getWeightTrackById(id)
            val endDate = Converters().dateToTimestamp(weightTrack?.endDate)
            if (endDate != null) {
                emit(endDate)
            }
        }
    }

    val userDetails: LiveData<Detail> = userId.switchMap {
        liveData {
            val detail = repository.getUserDetailsById(it)
            if (detail != null) {
                emit(detail)
            }
        }
    }

    val userMealHistory: LiveData<List<RecipeHistory>> = userId.switchMap { id ->
        liveData {
            val recipeHistory = repository.getRecipeHistoryById(id)
            emit(recipeHistory)
        }
    }

    //=============== ProgramFragment ===============

    fun deleteWeightTrackByUserId() {
        viewModelScope.launch {
            repository.deleteWeightTrackByUserId(userId.value)
        }
    }

    private val _historyNutritionSum = MutableLiveData<NutritionSum>()
    val historyNutritionSum: LiveData<NutritionSum> = _historyNutritionSum

    fun getNutritionSumsForHistory() {
        viewModelScope.launch {
            val nutritionSum = repository.getNutritionSumsForHistory()
            _historyNutritionSum.value = nutritionSum
        }
    }

    fun insertHistory(
        programName: String,
        startDate: String,
        endDate: String,
        calories: Int,
        protein: Int,
        fat: Int,
        carbs: Int,
        startWeight: Int,
        endWeight: Int,
    ) {
        viewModelScope.launch {
            val userId = repository.getUserId().first()
            val history = History(
                id = 0,
                programName = programName,
                startDate = startDate,
                endDate = endDate,
                calories = calories,
                protein = protein,
                fat = fat,
                carbs = carbs,
                startWeight = startWeight,
                endWeight = endWeight,
                userId = userId,
                createdAt = System.currentTimeMillis()
            )
            repository.insertHistory(history)
        }
    }

    fun insertDetail(
        dob: Date?,
        height: Int,
        waistSize: Int,
        weight: Int,
        gender: String,
        allergen: String,
        disease: String,
        bmi: Float,
    ) {
        viewModelScope.launch {
            val userId = repository.getUserId().first()
            val newDetail =
                Detail(
                    0,
                    dob,
                    height,
                    waistSize,
                    weight,
                    gender,
                    allergen,
                    disease,
                    bmi,
                    userId
                )
            repository.insertDetail(newDetail)

        }
    }

    fun createNewHistory(
        programName: String,
        startDate: String,
        endDate: String,
        calories: Int,
        protein: Int,
        fat: Int,
        carbs: Int,
        startWeight: Int,
        endWeight: Int
    ) {
        viewModelScope.launch {
            repository.createNewHistory(
                programName,
                startDate,
                endDate,
                calories,
                protein,
                fat,
                carbs,
                startWeight,
                endWeight,
                System.currentTimeMillis()
            )
        }
    }

    fun insertDetailBackend(
        dob: String,
        height: Int,
        waistSize: Int,
        weight: Int,
        gender: String,
        allergen: String,
        disease: String
    ) {
        viewModelScope.launch {
            val userId = repository.getUserId().first()
            repository.postUserDetails(
                userId,
                dob,
                height,
                waistSize,
                weight,
                gender,
                allergen,
                disease
            )
        }
    }

    //enqueue MealHistoryUpdate
    fun scheduleMealHistoryUpdate(recipeId: Int, consumedTime: String, context: Context) {
        val data = workDataOf(
            "recipeId" to recipeId,
            "consumedTime" to consumedTime,
            "consumedDate" to consumedTime
        )
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        // Create a one-time work request for the MealHistoryWorker
        val mealHistoryRequest = OneTimeWorkRequestBuilder<MealHistoryWorkManager>()
            .setInputData(data)
            .setConstraints(constraints) // Remove or change constraints as needed
            .build()

        // Enqueue the work using WorkManager
        WorkManager.getInstance(context).enqueue(mealHistoryRequest)
    }

    //=============== MealHistoryFragment ===============

    private val _recipeHistoryData = MediatorLiveData<List<RecipeHistoryData>>()
    val recipeHistoryData: LiveData<List<RecipeHistoryData>> = _recipeHistoryData

    private val _selectedMealTime = MutableLiveData<Int>()
    val selectedMealTime: LiveData<Int> get() = _selectedMealTime

    fun selectMealTime(mealTime: Int) {
        _selectedMealTime.value = mealTime
        fetchRecipeAndHistoryLiveData(mealTime)
    }

    private fun fetchRecipeAndHistoryLiveData(mealTime: Int) {
        viewModelScope.launch {
            val userId = repository.getUserId().first()

            val recipesLiveData = repository.getAllRecipesByMealType(mealTime)
            val historyLiveData = repository.getRecipeHistorySortedAscending(userId)

            _recipeHistoryData.apply {
                removeSource(recipesLiveData)
                removeSource(historyLiveData)
                addSource(recipesLiveData) { recipes ->
                    Log.d("MealHistory", "Recipes loaded: ${recipes.size}")
                    combineLatestData(recipes, historyLiveData.value)
                }
                addSource(historyLiveData) { history ->
                    Log.d("MealHistory", "RecipeHistories loaded: ${history.size}")
                    combineLatestData(recipesLiveData.value, history)
                }
            }
        }
    }

    private fun combineLatestData(recipes: List<Recipe>?, history: List<RecipeHistory>?) {
        if (recipes == null || history == null) {
            Log.d(
                "MealHistory",
                "One of the lists is null -> Recipes: $recipes, Recommendations: $history"
            )
            return
        }

        // Filter recipes to include only those with a corresponding history entry
        val filteredRecipes = recipes.filter { recipe ->
            history.any { it.recipeId == recipe.recipeId }
        }.sortedBy { recipe ->
            history.find { it.recipeId == recipe.recipeId }?.consumedDate
        }

        // Group by consumed date after filtering and sorting
        val groupByDate = filteredRecipes.groupBy { recipe ->
            history.find { it.recipeId == recipe.recipeId }?.consumedDate
        }

        val recipeHistoryData = mutableListOf<RecipeHistoryData>()

        for ((date, groupedRecipes) in groupByDate) {
            val recipeHistory = history.find { it.consumedDate == date }
            if (recipeHistory != null) {
                recipeHistoryData.add(RecipeHistoryData.RecipeHistoryItem(recipeHistory))
            }
            groupedRecipes.forEach { recipe ->
                recipeHistoryData.add(RecipeHistoryData.RecipeItem(recipe))
            }
        }

        _recipeHistoryData.value = recipeHistoryData
        Log.d("MealHistory", "Combined list size: ${recipeHistoryData.size}")
    }


    //=============== ProgramFilledFragment ===============
    private val _breakfastCalories = MutableLiveData<Int>()
    val breakfastCalories: LiveData<Int> = _breakfastCalories

    private val _lunchCalories = MutableLiveData<Int>()
    val lunchCalories: LiveData<Int> = _lunchCalories

    private val _dinnerCalories = MutableLiveData<Int>()
    val dinnerCalories: LiveData<Int> = _dinnerCalories

    fun getTotalCaloriesByMealTypeHistory(mealType: Int) {
        viewModelScope.launch {
            val userId = repository.getUserId().first()
            val calories = repository.getTotalCaloriesByMealTypeHistory(mealType, userId)
            when (mealType) {
                1 -> _breakfastCalories.value = calories
                2 -> _lunchCalories.value = calories
                3 -> _dinnerCalories.value = calories
            }
        }
    }

    fun insertWeightEntry(weightEntry: WeightEntry) {
        viewModelScope.launch {
            repository.insertWeightEntry(weightEntry)
        }
    }

    fun changeRecommendationFromConsumedToExpired() {
        viewModelScope.launch {
            repository.changeRecommendationFromConsumedToExpired()
        }
    }

    //===== Untuk grafik =====

    val weightEntries: LiveData<List<WeightEntry>> = userId.switchMap { id ->
        repository.getWeightEntriesByUserIdAsc(id)
    }


//    val weightEntries: LiveData<List<WeightEntry>> = liveData {
//        emitSource(repository.getWeightEntriesLiveData())
//    }

//    private var _weightEntries: MutableLiveData<List<WeightEntry>> = MutableLiveData()
//    val weightEntries: LiveData<List<WeightEntry>> = _weightEntries
//
//    fun setWeightEntries(userId: Int) {
//        viewModelScope.launch {
//            val weightEntries = repository.getWeightEntriesByUserIdAsc(userId)
//            _weightEntries.value = weightEntries
//        }
//    }

    //===== Untuk current dan start weight =====

//    val latestWeightEntry: LiveData<WeightEntry> = userId.switchMap { id ->
//        liveData {
//            emitSource(repository.getLatestWeightEntryByUserId2(id))
//        }
//    }

    private var _latestWeightEntryWeight: MutableLiveData<Int> = MutableLiveData()
    val latestWeightEntryWeight: LiveData<Int> = _latestWeightEntryWeight

//    val latestWeightEntry: LiveData<Int?> = userId.switchMap { id ->
//        liveData {
//            val latestWeightEntry = repository.getLatestWeightEntryByUserId(id)
//            emit(latestWeightEntry.weight)
//        }
//    }

    fun setLatestWeightEntryWeight(userId: Int) {
        viewModelScope.launch {
            val latestWeightEntry = repository.getLatestWeightEntryByUserId(userId)
            _latestWeightEntryWeight.value = latestWeightEntry.weight
        }
    }


    val userWeightDetail: LiveData<Int> = userId.switchMap { id ->
        liveData {
            val detail = repository.getUserDetailsById(id)
            if (detail != null) {
                emit(detail.weight)
            }
        }
    }

    //===== Untuk insert weight =====
    fun insertWeightTrack(weightTrack: WeightTrack) {
        viewModelScope.launch {
            repository.insertWeightTrack(weightTrack)
            fetchCooldownTime()
        }
    }

    //===== Setup Input Weight 1 minggu sekali =====
    private val _remainingTime = MutableLiveData<Long>()
    val remainingTime: LiveData<Long> = _remainingTime

    fun fetchCooldownTime() {
        viewModelScope.launch {
            val userId = repository.getUserId().first()
            val endDate = repository.getCooldownEndDate(userId)
            endDate.let {
                val remaining = it.time - System.currentTimeMillis()
                _remainingTime.value = if (remaining > 0) remaining else 0
            }
        }
    }

    fun deleteWeightEntriesById() {
        viewModelScope.launch {
            val userId = repository.getUserId().firstOrNull() ?: -1
            repository.deleteWeightEntriesById(userId)
        }
    }


}



