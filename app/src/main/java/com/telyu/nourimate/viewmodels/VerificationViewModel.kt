package com.telyu.nourimate.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.telyu.nourimate.data.local.models.User
import com.telyu.nourimate.data.repository.NourimateRepository
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class VerificationViewModel(private val repository: NourimateRepository): ViewModel() {

    val userEmail: LiveData<String> = repository.getUserEmail().asLiveData()
    val userId: LiveData<Int> = repository.getUserId().asLiveData()

    fun setAccountStateAsVerified() {
        viewModelScope.launch {
            val email = repository.getUserEmail().firstOrNull() ?: ""
            val userId = repository.getUserId().firstOrNull() ?: -1

            if (userId != -1 && email.isNotEmpty()) {
                repository.changeAccountState(userId, email, 2)
                repository.updateAccountState(userId, 2)
            } else {
                Log.e("ViewModel", "Invalid userId or email: userId=$userId, email=$email")
            }
        }
    }

    fun setAccountStateAsLoggedIn() {
        viewModelScope.launch {
            val email = repository.getUserEmail().firstOrNull() ?: ""
            val userId = repository.getUserId().firstOrNull() ?: -1

            if (userId != -1 && email.isNotEmpty()) {
                repository.changeAccountState(userId, email, 1)
                repository.updateAccountState(userId, 1)
                Log.d("ViewModel", "Account state changed to logged in")
            } else {
                Log.e("ViewModel", "Invalid userId or email: userId=$userId, email=$email")
            }
        }
    }

}