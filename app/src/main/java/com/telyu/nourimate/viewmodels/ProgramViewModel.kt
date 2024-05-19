package com.telyu.nourimate.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.telyu.nourimate.data.local.models.WeightTrack
import com.telyu.nourimate.data.repository.NourimateRepository

class ProgramViewModel(private val repository: NourimateRepository) : ViewModel() {

    private val userId = repository.getUserId().asLiveData()

    val userWeightTrack: LiveData<WeightTrack> = userId.switchMap { id ->
        Log.d("ProgramViewModel", "userWeightTrack: $id")
        liveData {
            val weightTrack = repository.getWeightTrackById(id)
            emit(weightTrack)
        }
    }

}
