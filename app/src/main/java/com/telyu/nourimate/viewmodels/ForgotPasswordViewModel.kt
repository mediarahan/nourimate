package com.telyu.nourimate.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.telyu.nourimate.data.repository.NourimateRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ForgotPasswordViewModel(private val repository: NourimateRepository): ViewModel() {
    fun sendEmailVerification(email: String) {
        viewModelScope.launch {
            repository.sendForgotPassword(email)
        }
    }

    fun forgotPassword(password: String, token: String) = repository.forgotPassword(password, token)


}