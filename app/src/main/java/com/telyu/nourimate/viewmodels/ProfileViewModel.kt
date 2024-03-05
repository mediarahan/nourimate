package com.telyu.nourimate.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.telyu.nourimate.data.local.models.Profpic
import com.telyu.nourimate.data.repository.NourimateRepository
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: NourimateRepository) : ViewModel() {

    private val _userId = MutableLiveData<Int?>()
    val userId: LiveData<Int?> = _userId

    private val _BMI = MutableLiveData<Int?>()
    val BMI: LiveData<Int?> = _BMI

    private val _userName = MutableLiveData<String?>()
    val userName: LiveData<String?> = _userName

    private val _profilePicture = MutableLiveData<String?>()
    val profilePicture: LiveData<String?> = _profilePicture

    val userEmail: LiveData<String> = repository.getUserEmail().asLiveData()

    fun getUserIdByEmail(email: String) {
        viewModelScope.launch {
            val id = repository.getUserIdByEmail(email)
            _userId.value = id
        }
    }

    fun getBMIById (id: Int?) {
        viewModelScope.launch {
            val bmi = repository.getBMIById(id)
            _BMI.value = bmi
        }
    }

    fun getUserNameByEmail (email: String) {
        viewModelScope.launch {
            val name = repository.getUserNameByEmail(email)
            _userName.value = name
        }
    }

    fun getProfpicById(id: Int) {
        viewModelScope.launch {
            val profpic = repository.getProfpicById(id)
            _profilePicture.value = profpic
        }
    }

    fun onSignOutButtonClick() {
        FirebaseAuth.getInstance().signOut()
    }

    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }

}
