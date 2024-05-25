package com.telyu.nourimate.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.telyu.nourimate.data.remote.Result
import com.telyu.nourimate.data.repository.NourimateRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: NourimateRepository) : ViewModel() {

    private val _userLoginState = MutableLiveData<Int>()
    val userLoginState: LiveData<Int> = _userLoginState

    val uiState = MutableLiveData<Result<Unit>>()
    val isLoggedInState: LiveData<Boolean> = repository.getUserLoginState().asLiveData()
    val isUserVerified: LiveData<Boolean> = repository.getUserVerificationState().asLiveData()
    val isDetailFilled: LiveData<Boolean> = repository.getUserDetailFilled().asLiveData()

    fun login(email: String, password: String) {
        viewModelScope.launch {
            uiState.value = Result.Loading
            delay(2000)  // simulate network delay

            val currentState = repository.login(email, password)
            if (currentState != -1) {
                _userLoginState.postValue(currentState)
                uiState.value = Result.Success(Unit)
            } else {
                uiState.value = Result.Error("Login failed. Incorrect email or password.")
            }
        }
    }

    fun loginBackend(email: String, password: String) = repository.loginBackend(email, password)

}
