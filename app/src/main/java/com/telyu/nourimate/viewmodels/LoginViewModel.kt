package com.telyu.nourimate.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.telyu.nourimate.data.repository.NourimateRepository
import kotlinx.coroutines.launch

class LoginViewModel (private val repository: NourimateRepository): ViewModel() {

    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> get() = _loginResult


    fun login(email: String, password: String) {
        viewModelScope.launch {
            val result = repository.login(email, password)
            _loginResult.value = result
        }
    }
}