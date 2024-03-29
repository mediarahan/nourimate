package com.telyu.nourimate.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.telyu.nourimate.data.local.models.Recipe
import com.telyu.nourimate.data.repository.NourimateRepository
import kotlinx.coroutines.launch
import java.util.Date

class RecipeViewModel(private val repository: NourimateRepository) : ViewModel() {
    private val _searchResult = MutableLiveData<List<Recipe>>()
    val searchResult: LiveData<List<Recipe>> get() = _searchResult

    private val _recipeList = MutableLiveData<List<Recipe>>()
    val recipeList: LiveData<List<Recipe>> get() = _recipeList

    //PERCOBAAN QUERY

    private val _recommendationIds = MutableLiveData<List<Int>>()
    val recommendationIds: LiveData<List<Int>> get() = _recommendationIds

    //PERCOBAAN QUERY

    private val _selectedRecipeCount = MutableLiveData<Int?>()
    val selectedRecipeCount: LiveData<Int?> = _selectedRecipeCount

    private val _selectedRecipeCountByMealType = MutableLiveData<Int?>()
    val selectedRecipeCountByMealType: LiveData<Int?> = _selectedRecipeCountByMealType

    private val _recipeListSelected = MutableLiveData<List<Recipe>>()
    val recipeListSelected: LiveData<List<Recipe>> get() = _recipeListSelected

    private val _userId = MutableLiveData<Int?>()
    val userId: LiveData<Int?> = _userId

    private val _profilePicture = MutableLiveData<String?>()
    val profilePicture: LiveData<String?> = _profilePicture

    private val _userName = MutableLiveData<String?>()
    val userName: LiveData<String?> = _userName

    val userEmail: LiveData<String> = repository.getUserEmail().asLiveData()

    fun getRecipeByName(name: String) {
        viewModelScope.launch {
            val searchResult = repository.getRecipeByName(name)
            _searchResult.value = searchResult
        }
    }

    //PERCOBAAN QUERY

    fun getRecommendationIdsByMealType(mealType: Int) {
        viewModelScope.launch {
            val recommendationIds = repository.getRecommendationIdsByMealType(mealType)
            _recommendationIds.value = recommendationIds
        }
    }

    fun getRecipesByRecommendationIds(recommendationIds: List<Int>) {
        viewModelScope.launch {
            val recipeList = repository.getRecipesByRecommendationIds(recommendationIds)
            _recipeList.value = recipeList
        }
    }

    //PERCOBAAN QUERY

    fun getRecipeByMealTypeAndSelectedRecommendation(mealType: Int) {
        viewModelScope.launch {
            val recipe = repository.getRecipeByMealTypeAndSelectedRecommendation(mealType)
            _recipeListSelected.value = recipe
        }
    }

    fun getSelectedRecipeCount() {
        viewModelScope.launch {
            val count = repository.getSelectedRecipeCount()
            _selectedRecipeCount.value = count
        }
    }

    fun getSelectedRecipeCountByMealType(mealType: Int) {
        viewModelScope.launch {
            val count = repository.getSelectedRecipeCountByMealType(mealType)
            _selectedRecipeCountByMealType.value = count
        }
    }

    fun updateRecommendationSelection(recommendationId: Int, isSelected: Boolean) {
        viewModelScope.launch {
            repository.updateRecommendationSelection(recommendationId, isSelected)
        }
    }

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