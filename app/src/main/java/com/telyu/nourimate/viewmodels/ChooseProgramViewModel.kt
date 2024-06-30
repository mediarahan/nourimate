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
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.util.Date

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

    fun insertWeightTrack(
        selectedProgramInt: Int,
        startDate: Date?,
        endDate: Date?,
        startWeight: Int,
        endWeight: Int,
        editCurrentWeightDate: Date
    ) {
        viewModelScope.launch {
            val userId = repository.getUserId().first()
            val weightTrack = WeightTrack(
                0,
                selectedProgramInt,
                startDate,
                endDate,
                startWeight,
                endWeight,
                editCurrentWeightDate,
                userId
            )
            repository.insertWeightTrack(weightTrack)
        }
    }

    fun insertWeightEntry(weight: Int, date: Date) {
        viewModelScope.launch {
            val userId = repository.getUserId().first()
            val weightEntry = WeightEntry(0, weight, date, userId)
            repository.insertWeightEntry(weightEntry)
        }
    }

    fun createNewProgram(
        ongoingProgram: Int,
        startDate: String,
        endDate: String,
        startWeight: Int,
        endWeight: Int,
        editCurrentWeightDate: String
    ) {
        viewModelScope.launch {
            repository.createNewProgram(
                ongoingProgram,
                startDate,
                endDate,
                startWeight,
                endWeight,
                editCurrentWeightDate
            )
        }
    }
}