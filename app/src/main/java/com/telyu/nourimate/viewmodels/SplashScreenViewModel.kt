package com.telyu.nourimate.viewmodels

import androidx.lifecycle.ViewModel
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
}