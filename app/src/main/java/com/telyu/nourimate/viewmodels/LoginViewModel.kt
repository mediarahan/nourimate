package com.telyu.nourimate.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.telyu.nourimate.data.repository.NourimateRepository
import kotlinx.coroutines.launch

class LoginViewModel (application: Application): ViewModel() {

    private val mNourimateRepository : NourimateRepository = NourimateRepository(application)

    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> get() = _loginResult


    fun login(email: String, password: String) {
        viewModelScope.launch {
            val result = mNourimateRepository.login(email, password)
            _loginResult.value = result
        }
    }
}