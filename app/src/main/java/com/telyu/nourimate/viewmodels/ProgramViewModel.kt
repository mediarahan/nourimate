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
import com.telyu.nourimate.data.local.models.History
import com.telyu.nourimate.data.local.models.NutritionSum
import com.telyu.nourimate.data.local.models.Recipe
import com.telyu.nourimate.data.local.models.RecipeHistory
import com.telyu.nourimate.data.local.models.RecipeHistoryData
import com.telyu.nourimate.data.local.models.WeightEntry
import com.telyu.nourimate.data.local.models.WeightTrack
import com.telyu.nourimate.data.repository.NourimateRepository
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

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

    fun insertHistory(history: History) {
        viewModelScope.launch {
            repository.insertHistory(history)
        }
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

        val recipesLiveData = repository.getConsumedRecipesByMealType(mealTime)
        val historyLiveData = repository.getRecipeHistorySortedAscending()

        _recipeHistoryData.apply {
            removeSource(recipesLiveData)
            removeSource(historyLiveData)
            addSource(recipesLiveData) { recipes ->
                Log.d("Debug", "Recipes loaded: ${recipes.size}")
                combineLatestData(recipes, historyLiveData.value)
            }
            addSource(historyLiveData) { history ->
                Log.d("Debug", "RecipeHistories loaded: ${history.size}")
                combineLatestData(recipesLiveData.value, history)
            }
        }
    }

    private fun combineLatestData(recipes: List<Recipe>?, history: List<RecipeHistory>?) {
        if (recipes == null || history == null) {
            Log.d(
                "Debug",
                "One of the lists is null -> Recipes: $recipes, Recommendations: $history"
            )
            return
        }
        Log.d(
            "Debug",
            "Combining data: Recipes (${recipes.size}), Recommendations (${history.size})"
        )
        val recipeHistoryData = mutableListOf<RecipeHistoryData>()
        val groupByDate = recipes.groupBy { recipe ->
            history.find { it.recipeId == recipe.recipeId }?.consumedDate
        }
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
        Log.d("Debug", "Combined list size: ${recipeHistoryData.size}")
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
            val calories = repository.getTotalCaloriesByMealTypeHistory(mealType)
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
            val userId = repository.getUserId().firstOrNull() ?: -1
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



