package com.telyu.nourimate.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.telyu.nourimate.data.local.models.Recipe
import com.telyu.nourimate.data.repository.NourimateRepository
import kotlinx.coroutines.launch

class RecipeDetailViewModel(private val repository: NourimateRepository): ViewModel() {

    private val _recipe = MutableLiveData<Recipe>()
    val recipe: LiveData<Recipe> get() = _recipe

    fun getRecipeDetailByRecipeId(recipeId: Int) {
        viewModelScope.launch {
            _recipe.value = repository.getRecipeDetailByRecipeId(recipeId)
        }
    }

}
