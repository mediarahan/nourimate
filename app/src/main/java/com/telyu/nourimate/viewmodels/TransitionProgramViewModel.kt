package com.telyu.nourimate.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.telyu.nourimate.data.local.models.History
import com.telyu.nourimate.data.repository.NourimateRepository
import kotlinx.coroutines.launch

class TransitionProgramViewModel(private val repository: NourimateRepository): ViewModel() {

    private var _history: MutableLiveData<History> = MutableLiveData()
    val history: LiveData<History> = _history

    fun getLatestHistory() {
        viewModelScope.launch {
            _history.value = repository.getLatestHistory()
        }
    }
}