package com.telyu.nourimate.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.telyu.nourimate.data.local.models.Recommendation
import com.telyu.nourimate.data.repository.NourimateRepository
import kotlinx.coroutines.launch

class MachineLearningViewModel(private val repository: NourimateRepository): ViewModel() {

    val allRecipeNames: LiveData<List<String>> = repository.allRecipeNames

    private val _mealId = MutableLiveData<Int?>()
    val mealId: LiveData<Int?> = _mealId

    private val _recipeId = MutableLiveData<Int?>()
    val recipeId: LiveData<Int?> = _recipeId

    fun insertRecommendation(recommendation: Recommendation) {
        viewModelScope.launch {
            repository.insertRecommendation(recommendation)
        }
    }

}