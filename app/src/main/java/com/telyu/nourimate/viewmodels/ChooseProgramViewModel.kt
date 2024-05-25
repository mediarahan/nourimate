package com.telyu.nourimate.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.telyu.nourimate.data.local.models.Detail
import com.telyu.nourimate.data.local.models.WeightEntry
import com.telyu.nourimate.data.local.models.WeightTrack
import com.telyu.nourimate.data.repository.NourimateRepository
import kotlinx.coroutines.launch

class ChooseProgramViewModel(private val repository: NourimateRepository) : ViewModel() {

    private val userId = repository.getUserId().asLiveData()

    val userDetails: LiveData<Detail> = userId.switchMap { id ->
        liveData {
            val detail = repository.getUserDetailsById(id)
            if (detail != null) {
                emit(detail)
            }
        }
    }

    fun insertWeightTrack(weightTrack: WeightTrack) {
        viewModelScope.launch {
            repository.insertWeightTrack(weightTrack)
        }
    }

    fun insertWeightEntry(weightEntry: WeightEntry) {
        viewModelScope.launch {
            repository.insertWeightEntry(weightEntry)
        }
    }
}