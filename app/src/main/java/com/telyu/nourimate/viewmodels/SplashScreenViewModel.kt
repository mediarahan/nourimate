package com.telyu.nourimate.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.telyu.nourimate.data.local.models.Detail
import com.telyu.nourimate.data.local.models.User
import com.telyu.nourimate.data.repository.NourimateRepository
import kotlinx.coroutines.launch

class SplashScreenViewModel(private val repository: NourimateRepository): ViewModel() {

    fun insertUser(user: User) {
        viewModelScope.launch {
            repository.insertUser(user)

        }
    }

    fun insertDetail(detail: Detail) {
        viewModelScope.launch {
            repository.insertDetail(detail)
        }
    }

    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }

    val recipeCount: LiveData<Boolean> = liveData {
        val rCount = repository.checkIfRecipeDatabaseIsFilled()
        if (rCount == 0) {
            emit(true)
        }
        else {
            emit(false)
        }
    }

    val recommendationCount: LiveData<Boolean> = liveData {
        val rCount = repository.checkIfRecommendationDatabaseIsFilled()
        if (rCount == 0) {
            emit(true)
        }
        else {
            emit(false)
        }
    }

}