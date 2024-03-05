package com.telyu.nourimate.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.telyu.nourimate.data.local.models.Detail
import com.telyu.nourimate.data.repository.NourimateRepository
import com.telyu.nourimate.utils.UserPreference
import kotlinx.coroutines.launch

class UserDetailViewModel(private val repository: NourimateRepository): ViewModel() {

    private val _userId = MutableLiveData<Int?>()
    val userId: LiveData<Int?> = _userId

    private val _userDetails = MutableLiveData<Detail?>()
    val userDetails: LiveData<Detail?> = _userDetails

    private val _userName = MutableLiveData<String?>()
    val userName: LiveData<String?> = _userName

    val userEmail: LiveData<String> = repository.getUserEmail().asLiveData()

    fun getUserIdByEmail(email: String) {
        viewModelScope.launch {
            val id = repository.getUserIdByEmail(email)
            _userId.value = id
        }
    }

    fun getUserDetailsById (id: Int) {
        viewModelScope.launch {
            val detail = repository.getUserDetailsById(id)
            _userDetails.value = detail
        }
    }

    fun getUserNameByEmail (email: String) {
        viewModelScope.launch {
            val name = repository.getUserNameByEmail(email)
            _userName.value = name
        }
    }

    fun updateUserProfile(detail: Detail) {
        viewModelScope.launch {
            repository.updateUserProfile(detail)
        }
    }

    fun updateUserName(id: Int, name: String) {
        viewModelScope.launch {
            repository.updateUserName(id, name)
        }
    }

}