package com.telyu.nourimate.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.telyu.nourimate.data.remote.Result
import com.telyu.nourimate.data.repository.NourimateRepository
import com.telyu.nourimate.utils.UserModel
import kotlinx.coroutines.delay
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

    fun saveSession(userModel: UserModel) {
        viewModelScope.launch {
            repository.saveSession(userModel)
        }
    }
}
