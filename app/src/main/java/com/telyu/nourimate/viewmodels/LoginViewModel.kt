package com.telyu.nourimate.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.telyu.nourimate.data.remote.Result
import com.telyu.nourimate.data.repository.NourimateRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: NourimateRepository) : ViewModel() {

    private val _userLoginState = MutableLiveData<Int>()
    val userLoginState: LiveData<Int> = _userLoginState

    val uiState = MutableLiveData<Result<Unit>>()

    fun login(email: String, password: String) {
        viewModelScope.launch {
            uiState.value = Result.Loading
            delay(2000)  // simulate network delay

            val currentState = repository.login(email, password)
            if (currentState != -1) {  // assuming -1 indicates a failed login
                _userLoginState.postValue(currentState)
                uiState.value = Result.Success(Unit)
            } else {
                uiState.value = Result.Error("Login failed. Incorrect email or password.")
            }
        }
    }

}
