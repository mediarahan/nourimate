package com.telyu.nourimate.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.telyu.nourimate.data.local.models.Recipe
import com.telyu.nourimate.data.repository.NourimateRepository
import java.util.Date

class RecipeViewModel (private val repository: NourimateRepository) : ViewModel() {
//    private val _greetingMessage = MutableLiveData<String>()
//    val greetingMessage: LiveData<String>
//        get() = _greetingMessage

    // Mungkin ada lebih banyak fungsi dan data lain di sini sesuai kebutuhan

    fun getRecipeByMeal(mealId: Int): LiveData<List<Recipe>> {
        return repository.getRecipeByMeal(mealId)
    }

    fun getRecipeByMealAndDate(mealId: Int, date: Date): LiveData<List<Recipe>> {
        return repository.getRecipeByMealAndDate(mealId, date)
    }
}
