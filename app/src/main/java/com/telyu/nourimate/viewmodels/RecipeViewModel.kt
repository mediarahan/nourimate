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

class RecipeViewModel (private val repository: NourimateRepository) : ViewModel() {
    private val _searchResult = MutableLiveData<List<Recipe>>()
    val searchResult: LiveData<List<Recipe>> get() = _searchResult

    private val _recipeList = MutableLiveData<List<Recipe>>()
    val recipeList: LiveData<List<Recipe>> get() = _recipeList

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

    fun getRecipeByMealType(mealType: Int) {
        viewModelScope.launch {
            val recipe = repository.getRecipesByMealType(mealType)
            _recipeList.value = recipe
        }
    }

    fun getUserIdByEmail(email: String) {
        viewModelScope.launch {
            val id = repository.getUserIdByEmail(email)
            _userId.value = id
        }
    }

    fun getUserNameByEmail (email: String) {
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