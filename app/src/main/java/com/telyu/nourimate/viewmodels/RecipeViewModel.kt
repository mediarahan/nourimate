package com.telyu.nourimate.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.telyu.nourimate.data.local.models.Recipe
import com.telyu.nourimate.data.repository.NourimateRepository
import kotlinx.coroutines.launch
import java.util.Date

class RecipeViewModel (private val repository: NourimateRepository) : ViewModel() {
    private val _searchResult = MutableLiveData<List<Recipe>>()
    val searchResult: LiveData<List<Recipe>> get() = _searchResult

    fun getRecipeByName(name: String) {
        viewModelScope.launch {
            val searchResult = repository.getRecipeByName(name)
            _searchResult.value = searchResult
        }
    }

    fun getRecipeByMeal(mealId: Int): LiveData<List<Recipe>> {
        return repository.getRecipeByMeal(mealId)
    }

    fun getRecipeByMealAndDate(mealId: Int, date: Date): LiveData<List<Recipe>> {
        return repository.getRecipeByMealAndDate(mealId, date)
    }
}