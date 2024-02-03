package com.telyu.nourimate.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.telyu.nourimate.data.local.models.User
import com.telyu.nourimate.data.repository.NourimateRepository
import kotlinx.coroutines.launch

class SignUpViewModel (application: Application): ViewModel() {

    private val mNourimateRepository : NourimateRepository = NourimateRepository(application)

    fun signup(password: String, confirmPassword:String): Boolean {
        return mNourimateRepository.signup(password,confirmPassword)
    }

    fun insertUser(user: User) {
        viewModelScope.launch {
            mNourimateRepository.insertUser(user)
        }
    }
}