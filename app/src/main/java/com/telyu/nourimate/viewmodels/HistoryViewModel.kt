package com.telyu.nourimate.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.telyu.nourimate.data.local.models.History
import com.telyu.nourimate.data.repository.NourimateRepository

class HistoryViewModel(private val repository: NourimateRepository) : ViewModel() {

    val userId = repository.getUserId().asLiveData()

    val userHistories: LiveData<List<History>> = userId.switchMap { id ->
        liveData {
            val listOfHistories = repository.getHistory(id)
            emit(listOfHistories)
        }
    }

}