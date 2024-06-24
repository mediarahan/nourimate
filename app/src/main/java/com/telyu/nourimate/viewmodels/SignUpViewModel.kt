package com.telyu.nourimate.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.telyu.nourimate.data.local.models.User
import com.telyu.nourimate.data.repository.NourimateRepository
import kotlinx.coroutines.launch
import com.telyu.nourimate.data.remote.Result
import com.telyu.nourimate.utils.InputValidator
import kotlinx.coroutines.delay

class SignUpViewModel(private val repository: NourimateRepository) : ViewModel() {

    //login dengan backend
    fun registerBackend(name: String, phoneNumber: String, email: String, password: String) =
        repository.registerBackend(name, phoneNumber, email, password)


}