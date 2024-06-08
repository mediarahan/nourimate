package com.telyu.nourimate.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "session")

class UserPreference private constructor(private val dataStore: DataStore<Preferences>){

    //Login State:
    //0: Not Logged In
    //1: Logged In, but not verified
    //2: Logged In, Verified, but haven't finished filling personal data
    //3: Logged In, Verified, and have finished filling personal data

    suspend fun saveSession(user: UserModel) {
        dataStore.edit { preferences ->
            preferences[USER_ID_KEY] = user.id ?: 0
            preferences[A_TOKEN_KEY] = user.accessToken ?: ""
            preferences[R_TOKEN_KEY] = user.refreshToken ?: ""
            preferences[EMAIL_KEY] = user.email ?: ""
            preferences[IS_LOGIN_KEY] = user.isLoggedIn
            preferences[IS_VERIFIED_KEY] = user.isVerified
            preferences[IS_DETAIL_FILLED_KEY] = user.isDetailFilled
        }
    }

    fun getSession(): Flow<UserModel> {
        return dataStore.data.map { preferences ->
            UserModel (
                preferences[USER_ID_KEY] ?: 0,
                preferences[EMAIL_KEY] ?: "",
                preferences[A_TOKEN_KEY] ?: "",
                preferences[R_TOKEN_KEY] ?: "",
                preferences[IS_LOGIN_KEY] ?: false,
                preferences[IS_VERIFIED_KEY] ?: false,
                preferences[IS_DETAIL_FILLED_KEY] ?: false
            )
        }
    }

    fun getUserEmail(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[EMAIL_KEY] ?: ""
        }
    }

    fun getUserId(): Flow<Int> {
        return dataStore.data.map { preferences ->
            preferences[USER_ID_KEY] ?: 0
        }
    }

    fun getUserLoginState(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[IS_LOGIN_KEY] ?: false
        }
    }

    fun getUserVerificationState(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[IS_VERIFIED_KEY] ?: false
        }
    }

    fun getUserDetailFilled(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[IS_DETAIL_FILLED_KEY] ?: false
        }
    }

    suspend fun logout() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }



    companion object {
        @Volatile
        private var INSTANCE: UserPreference? = null

        private val USER_ID_KEY = intPreferencesKey("userId")
        private val A_TOKEN_KEY = stringPreferencesKey("access_token")
        private val R_TOKEN_KEY = stringPreferencesKey("refresh_token")

        private val EMAIL_KEY = stringPreferencesKey("email")
        private val IS_LOGIN_KEY = booleanPreferencesKey("loginState")
        private val IS_VERIFIED_KEY = booleanPreferencesKey("isVerified")
        private val IS_DETAIL_FILLED_KEY = booleanPreferencesKey("isDatabaseFilled")

        fun getInstance(dataStore: DataStore<Preferences>): UserPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }

}

data class UserModel (
    val id: Int?,
    val email: String?,
    val accessToken: String?,
    val refreshToken: String?,
    val isLoggedIn: Boolean = false,
    val isVerified: Boolean = false,
    val isDetailFilled: Boolean = false,
)