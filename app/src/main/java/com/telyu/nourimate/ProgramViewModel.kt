package com.telyu.nourimate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProgramViewModel : ViewModel() {
    private val _currentWeight = MutableLiveData<Int>()
    val currentWeight: LiveData<Int>
        get() = _currentWeight

    init {
        // Initialize with default weight
        _currentWeight.value = 70 // Set your default weight here
    }

    fun updateWeight(newWeight: Int) {
        _currentWeight.value = newWeight
    }
}
