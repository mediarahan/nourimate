package com.telyu.nourimate.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.telyu.nourimate.data.local.models.Profpic
import com.telyu.nourimate.data.repository.NourimateRepository
import com.telyu.nourimate.utils.GeneralUtil
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: NourimateRepository) : ViewModel() {

    private val userId = repository.getUserId().asLiveData()

    private var _username = MutableLiveData<String>()
    val username: LiveData<String> = _username

    val BMI: LiveData<Float> = userId.switchMap { id ->
        liveData {
            val detail = repository.getUserDetailsById(id)
            val bmi = GeneralUtil.calculateBMI(detail?.height, detail?.weight)
            Log.d("VERIFIKASI BMI", "BMI: $bmi")
            if (bmi != null) {
                emit(bmi)
            }
        }
    }

    val profpic: LiveData<String> = userId.switchMap { id ->
        liveData {
            val profpic = repository.getProfpicById(id)
            if (profpic != null) {
                emit(profpic)
            }
        }
    }

    fun getUsername() {
        viewModelScope.launch {
            val username = repository.getUsername().first()
            _username.value = username
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
