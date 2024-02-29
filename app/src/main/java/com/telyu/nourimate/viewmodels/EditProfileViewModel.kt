package com.telyu.nourimate.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.telyu.nourimate.data.local.models.Detail
import com.telyu.nourimate.data.repository.NourimateRepository
import kotlinx.coroutines.launch

class EditProfileViewModel (private val repository: NourimateRepository): ViewModel() {

    fun insertDetail(detail: Detail) {
        viewModelScope.launch {
            repository.insertDetail(detail)
        }
    }



}