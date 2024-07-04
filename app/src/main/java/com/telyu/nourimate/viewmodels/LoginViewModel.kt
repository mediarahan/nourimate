package com.telyu.nourimate.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.telyu.nourimate.data.repository.NourimateRepository
import com.telyu.nourimate.utils.UserModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: NourimateRepository) : ViewModel() {

    val isUserVerified: LiveData<Boolean> = repository.getUserVerificationState().asLiveData()
    val isDetailFilled: LiveData<Boolean> = repository.getUserDetailFilled().asLiveData()

    fun loginBackend(email: String, password: String) = repository.loginBackend(email, password)

    fun checkUserExists() {
        viewModelScope.launch {
            val userId = repository.getUserId().first()
            val isDetailExists = repository.checkUserDetailExists(userId)
            if (!isDetailExists) {
                repository.fetchUserDetails(userId)
            }
        }
    }

    fun checkWeightTrackExists() {
        viewModelScope.launch {
            val userId = repository.getUserId().first()
            val isDetailExists = repository.checkUserWeightTrackExists(userId)
            if (!isDetailExists) {
                repository.fetchAllUserProgram()
            }
        }
    }

    fun checkMealHistoryExists() {
        viewModelScope.launch {
            val userId = repository.getUserId().first()
            val isDetailExists = repository.checkUserMealHistoryExists(userId)
            if (!isDetailExists) {
                repository.fetchAllUserMealHistory()
            }
        }
    }

    fun checkHistoryExists() {
        viewModelScope.launch {
            val userId = repository.getUserId().first()
            val isDetailExists = repository.checkUserHistoryExists(userId)
            if (!isDetailExists) {
                repository.fetchAllUserHistory()
            }
        }
    }

    fun saveSession(userModel: UserModel) {
        viewModelScope.launch {
            repository.saveSession(userModel)
        }
    }

    fun googleSignIn(email: String) = repository.performGoogleSignIn(email)
    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }


}
