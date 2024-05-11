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

    //untuk simulasi State loading kalau registernya udah beneran pake API
    val uiState = MutableLiveData<Result<Unit>>()

    fun signup(password: String, confirmPassword: String, user: User) {
        if (password != confirmPassword) {
            uiState.value = Result.Error("Passwords do not match")
            return //return disini maksudnya lebih mirip ke break / pass kalau di Python. Untuk flow control, bukan returnnya fungsi
        }

        viewModelScope.launch {
            uiState.value = Result.Loading
            delay(2000)

            try {
                repository.insertUser(user)
                uiState.value = Result.Success(Unit)
            } catch (e: Exception) {
                uiState.value = Result.Error("Failed to insert user to database.")
            }
        }
    }

    //login dengan backend
    fun register(name: String, phoneNumber: String, email: String, password: String) =
        repository.register(name, phoneNumber, email, password)


}