package com.telyu.nourimate.utils

import android.content.Context
import com.telyu.nourimate.data.local.dao.UserDao
import com.telyu.nourimate.data.local.db.UserDatabase
import com.telyu.nourimate.data.repository.NourimateRepository

object Injection {
    fun provideRepository(context: Context): NourimateRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        val userDao = provideDao(context)

        return NourimateRepository.getInstance(pref, userDao)
    }
    private fun provideDao(context: Context): UserDao {
        val db = UserDatabase.getInstance(context)
        return db.userDao()
    }
}

