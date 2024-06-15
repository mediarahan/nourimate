package com.telyu.nourimate.viewmodels

import androidx.lifecycle.ViewModel
import com.telyu.nourimate.data.repository.NourimateRepository

class DetailChangeViewModel(private val repository: NourimateRepository) : ViewModel() {

    fun changePhoneNumber(phoneNumber: String, confirmPhoneNumber: String) =
        repository.changePhoneNumber(phoneNumber, confirmPhoneNumber)

    fun changePassword(oldPassword: String, password: String, newPassword: String) =
        repository.changePassword(oldPassword, password, newPassword)

}