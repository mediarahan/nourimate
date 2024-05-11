package com.telyu.nourimate.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.telyu.nourimate.data.local.models.Detail
import com.telyu.nourimate.data.local.models.User
import com.telyu.nourimate.data.local.models.WeightEntry
import com.telyu.nourimate.data.local.models.WeightTrack
import com.telyu.nourimate.data.repository.NourimateRepository
import kotlinx.coroutines.launch
import java.util.Date

class ProgramViewModel(private val repository: NourimateRepository) : ViewModel() {

    var selectedProgram: MutableLiveData<String> = MutableLiveData()
    val userId = repository.getUserId().asLiveData()

    //Enforce the rule that the first weight input becomes
    // startWeight and is immutable unless the program is reset

    private var _earliestWeightEntryDate: MutableLiveData<Date> = MutableLiveData()
    val earliestWeightEntryDate: LiveData<Date> get() = _earliestWeightEntryDate

    private var _latestWeightEntryDate: MutableLiveData<Date> = MutableLiveData()
    val latestWeightEntryDate: LiveData<Date> get() = _latestWeightEntryDate

    private var _latestWeightEntry: MutableLiveData<Int> = MutableLiveData()
    val latestWeightEntry: LiveData<Int> get() = _latestWeightEntry

    fun insertWeightEntry(weightEntry: WeightEntry) {
        viewModelScope.launch {
            repository.insertWeightEntry(weightEntry)
        }
    }

    fun deleteWeightEntryById(entryId: Int) {
        viewModelScope.launch {
            repository.deleteWeightEntryById(entryId)
        }
    }

    fun getLatestWeightEntryByUserId(userId: Int) {
        viewModelScope.launch {
            val weightEntry = repository.getLatestWeightEntryByUserId(userId)
                _latestWeightEntry.value = weightEntry.weight.toInt()
        }
    }

    fun getLatestWeightEntryDateByUserId(userId: Int) {
        viewModelScope.launch {
            val date = repository.getLatestWeightEntryDateByUserId(userId)
                _latestWeightEntryDate.value = date
        }
    }

    fun getEarliestWeightEntryDateByUserId(userId: Int) {
        viewModelScope.launch {
            val date = repository.getEarliestWeightEntryDateByUserId(userId)
                _earliestWeightEntryDate.value = date
        }
    }

    fun updateWeight(detailId: Int, weight: Int) {
        viewModelScope.launch {
            repository.updateWeight(detailId, weight)
        }
    }

    fun insertWeightTrack(weightTrack: WeightTrack) {
        viewModelScope.launch {
            repository.insertWeightTrack(weightTrack)
        }
    }

    fun insertDetail(detail: Detail) {
        viewModelScope.launch {
            repository.insertDetail(detail)
        }
    }

    val userDetails: LiveData<Detail> = userId.switchMap { id -> //fetching of id is the trigger.
        liveData {
            val detail = repository.getUserDetailsById(id)
            if (detail != null) {
                emit(detail)
            } else {
                Log.d("ProgramViewModel", "User details not found")
            }
        }
    }

    val userName: LiveData<String> = userId.switchMap { id ->
        liveData {
            val user = repository.getUserNameById(id)
            Log.d("Id: ", "$id")
            Log.d("User: ", "$user")
            if (user != null) {
                emit(user)
            }
        }
    }

    val weightEntryDate: LiveData<List<Long>> = userId.switchMap { id ->
        liveData {
            val weightEntry = repository.getWeightEntriesByUserIdAsc(id)
            weightEntry.let {
                val dates = weightEntry.map { it.date }
                val datesInLong = dates.map { it.time }
                emit(datesInLong)
            }
        }
    }

    val weightEntryWeight: LiveData<List<Int>> = userId.switchMap { id ->
        liveData {
            val weightEntry = repository.getWeightEntriesByUserIdAsc(id)
            weightEntry.let {
                val weight = weightEntry.map { it.weight }
                emit(weight)
            }
        }
    }

    val weightEntries: LiveData<List<WeightEntry>> = userId.switchMap { id ->
        liveData {
            val weightEntries = repository.getWeightEntriesByUserIdAsc(id)
            if (weightEntries.isNotEmpty()) {
                emit(weightEntries)
            } else {
                Log.d("ProgramViewModel", "Weight entries not found")
            }
        }
    }




}
