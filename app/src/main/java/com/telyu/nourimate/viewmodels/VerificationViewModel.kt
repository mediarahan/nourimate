package com.telyu.nourimate.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.telyu.nourimate.data.repository.NourimateRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import com.telyu.nourimate.data.remote.Result

class VerificationViewModel(private val repository: NourimateRepository) : ViewModel() {

    fun sendEmailVerification() {
        viewModelScope.launch {
            repository.sendVerifyEmail()
        }
    }

    fun sendPhoneVerification() {
        viewModelScope.launch {
            repository.sendVerifyPhone(0)
        }
    }

    fun verifyEmail(emailToken: String) = repository.verifyEmail(emailToken)
    fun verifyPhone(smsToken: String) = repository.verifyPhone(smsToken)

}
