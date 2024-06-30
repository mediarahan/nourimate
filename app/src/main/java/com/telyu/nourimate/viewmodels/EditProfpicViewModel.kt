package com.telyu.nourimate.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.telyu.nourimate.data.local.models.Profpic
import com.telyu.nourimate.data.repository.NourimateRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EditProfpicViewModel(private val repository: NourimateRepository): ViewModel() {

    private val _userId = MutableLiveData<Int?>()
    val userId: LiveData<Int?> = _userId

    val userEmail: LiveData<String> = repository.getUserEmail().asLiveData()

    fun getUserIdByEmail(email: String) {
        viewModelScope.launch {
            val id = repository.getUserIdByEmail(email)
            _userId.value = id
        }
    }

    fun insertProfpic (uri: String) {
        viewModelScope.launch {
            val userId = repository.getUserId().first()
            val profpic = Profpic(0, uri, userId)
            repository.insertProfpic(profpic)
        }
    }

}