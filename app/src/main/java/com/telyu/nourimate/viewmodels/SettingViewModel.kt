package com.telyu.nourimate.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.telyu.nourimate.data.repository.NourimateRepository
import com.telyu.nourimate.utils.SettingsModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class SettingViewModel(private val repository: NourimateRepository): ViewModel() {

    fun saveSettingsPreferences(settingsModel: SettingsModel) {
        viewModelScope.launch {
            repository.saveSettingsPreferences(settingsModel)
        }
    }

    fun getSettingsPreferences(): Flow<SettingsModel> {
        return repository.getSettingsPreferences()
    }


}