package com.telyu.nourimate.data.repository

import android.app.Application
import com.telyu.nourimate.data.local.db.UserDatabase
import com.telyu.nourimate.data.local.models.User

class NourimateRepository(application: Application) {

    val userDao = UserDatabase.getInstance(application).userDao()

    fun signup(
        password:String,
        confirmPassword:String,
    ): Boolean {
        return password == confirmPassword
    }

    suspend fun insertUser (user: User) {
        userDao.insertUser(user)
    }

    suspend fun login(
        email: String,
        password: String
    ): Boolean {
        val user = userDao.getUserByEmail(email)
        return user != null && user.password == password
    }
}