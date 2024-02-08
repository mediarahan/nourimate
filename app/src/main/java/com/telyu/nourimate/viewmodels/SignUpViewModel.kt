package com.telyu.nourimate.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.telyu.nourimate.data.local.models.User
import com.telyu.nourimate.data.repository.NourimateRepository
import kotlinx.coroutines.launch

class SignUpViewModel (private val repository: NourimateRepository): ViewModel() {

    fun signup(password: String, confirmPassword:String): Boolean {
        return repository.signup(password,confirmPassword)
    }

    val isUserLoggedIn: LiveData<Boolean?> = repository.observeUserLoginStatus()

    fun insertUser(user: User) {
        viewModelScope.launch {
            repository.insertUser(user)
        }
    }
}