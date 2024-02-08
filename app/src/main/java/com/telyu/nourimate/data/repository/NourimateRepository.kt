package com.telyu.nourimate.data.repository

import android.app.Application
import androidx.datastore.dataStore
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.telyu.nourimate.data.local.dao.UserDao
import com.telyu.nourimate.data.local.db.UserDatabase
import com.telyu.nourimate.data.local.models.User
import com.telyu.nourimate.utils.UserModel
import com.telyu.nourimate.utils.UserPreference
import kotlinx.coroutines.flow.map

class NourimateRepository(
    private val userPreference: UserPreference,
    private val userDao: UserDao
) {

    fun signup(
        password: String,
        confirmPassword: String,
    ): Boolean {
        return password == confirmPassword
    }

    suspend fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    suspend fun login(
        email: String,
        password: String
    ): Boolean {
        val user = userDao.getUserByEmail(email)
        val isLoginSuccessful = user != null && user.password == password

        //redundan, jangan lupa ubah logikanya kalau udah pake API
        val userModel = UserModel(email, isLoginSuccessful)
        userPreference.saveSession(userModel)

        return isLoginSuccessful
    }

    fun observeUserLoginStatus(): LiveData<Boolean?> {
        return userPreference.getSession().map { userModel ->
            userModel.isLogin
        }.asLiveData()
    }

    suspend fun logout() {
        userPreference.logout()
    }

    companion object {
        @Volatile
        private var instance: NourimateRepository? = null
        fun getInstance(
            pref: UserPreference, dao: UserDao
        ): NourimateRepository = instance ?: synchronized(this) {
            instance ?: NourimateRepository(pref, dao)
        }.also { instance = it }
    }

}