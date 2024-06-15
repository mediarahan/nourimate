package com.telyu.nourimate.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.settingsDataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingsPreference private constructor(private val dataStore: DataStore<Preferences>) {

    suspend fun saveSettings(settings: SettingsModel) {
        dataStore.edit { preferences ->
            preferences[NIGHT_MODE_KEY] = settings.isNightModeEnabled
            preferences[NOTIFICATIONS_ENABLED_KEY] = settings.areNotificationsEnabled
            preferences[RECIPE_TRANSITION_KEY] = settings.isRecipeTransition
        }
    }

    fun getSettings(): Flow<SettingsModel> {
        return dataStore.data.map { preferences ->
            SettingsModel(
                preferences[NIGHT_MODE_KEY] ?: false,
                preferences[NOTIFICATIONS_ENABLED_KEY] ?: true,
                preferences[RECIPE_TRANSITION_KEY] ?: true // Default text size
            )
        }
    }

    suspend fun clearSettings() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    suspend fun saveWaterIntake (amount: Int) {
        dataStore.edit { preferences ->
            preferences[WATER_INTAKE_KEY] = amount
        }
    }

    fun getWaterIntake(): Flow<Int> {
        return dataStore.data.map { preferences ->
            preferences[WATER_INTAKE_KEY] ?: 0
        }
    }

    suspend fun clearWaterIntake() {
        dataStore.edit { preferences ->
            preferences[WATER_INTAKE_KEY] = 0
        }
    }

    suspend fun setRecipeTransitionPreference(showTransition: Boolean) {
        dataStore.edit {preferences ->
            preferences[RECIPE_TRANSITION_KEY] = showTransition
        }
    }

    fun getRecipeTransitionPreference(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[RECIPE_TRANSITION_KEY] ?: true
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: SettingsPreference? = null

        private val NIGHT_MODE_KEY = booleanPreferencesKey("nightMode")
        private val NOTIFICATIONS_ENABLED_KEY = booleanPreferencesKey("notificationsEnabled")
        private val RECIPE_TRANSITION_KEY = booleanPreferencesKey("recipeTransition")

        private val WATER_INTAKE_KEY = intPreferencesKey("water_intake")

        fun getInstance(dataStore: DataStore<Preferences>): SettingsPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = SettingsPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }

}

data class SettingsModel(
    val isNightModeEnabled: Boolean,
    val areNotificationsEnabled: Boolean,
    val isRecipeTransition: Boolean,
)
