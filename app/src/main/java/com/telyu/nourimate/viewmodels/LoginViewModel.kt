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

    fun loginBackend(email: String, password: String) = repository.loginBackend(email, password)
    fun googleSignIn(email: String) = repository.performGoogleSignIn(email)

    fun checkUserExists() {
        viewModelScope.launch {
            val userId = repository.getUserId().first()
            val isDetailExists = repository.checkUserDetailExists(userId)
            if (!isDetailExists) {
                repository.fetchUserDetails(userId)
            } else {
                _detailExists.postValue(true)
            }
        }
    }

    fun saveSession(userModel: UserModel) {
        viewModelScope.launch {
            repository.saveSession(userModel)
        }
    }

    private val _userLoginState = MutableLiveData<Int>()
    val userLoginState: LiveData<Int> = _userLoginState

    val isUserVerified: LiveData<Boolean> = repository.getUserVerificationState().asLiveData()
    val isDetailFilled: LiveData<Boolean> = repository.getUserDetailFilled().asLiveData()

    private val _detailExists = MutableLiveData<Boolean>()
    val detailExists: LiveData<Boolean> = _detailExists
}
