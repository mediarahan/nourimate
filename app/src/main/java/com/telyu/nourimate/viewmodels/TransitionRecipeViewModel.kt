package com.telyu.nourimate.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.telyu.nourimate.data.repository.NourimateRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class TransitionRecipeViewModel(private val repository: NourimateRepository) : ViewModel() {

    fun setRecipeTransitionPreference(showTransition: Boolean) {
        viewModelScope.launch {
            repository.setRecipeTransitionPreference(showTransition)
        }
    }
}