package com.telyu.nourimate.utils

import android.content.Context
import com.telyu.nourimate.data.local.dao.FoodDao
import com.telyu.nourimate.data.local.dao.UserDao
import com.telyu.nourimate.data.local.db.FoodDatabase
import com.telyu.nourimate.data.local.db.UserDatabase
import com.telyu.nourimate.data.remote.retrofit.ApiConfig
import com.telyu.nourimate.data.repository.NourimateRepository

object Injection {
    fun provideRepository(context: Context): NourimateRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        val apiService = ApiConfig.getApiService()
        val userDao = provideUserDao(context)
        val foodDao = provideFoodDao(context)

        return NourimateRepository.getInstance(apiService, pref, userDao, foodDao)
    }
    private fun provideUserDao(context: Context): UserDao {
        val db = UserDatabase.getInstance(context)
        return db.userDao()
    }

    private fun provideFoodDao(context: Context): FoodDao {
        val db = FoodDatabase.getInstance(context)
        return db.foodDao()
    }
}

