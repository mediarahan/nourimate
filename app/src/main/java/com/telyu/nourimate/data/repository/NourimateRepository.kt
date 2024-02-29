package com.telyu.nourimate.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.telyu.nourimate.data.local.dao.FoodDao
import com.telyu.nourimate.data.local.dao.UserDao
import com.telyu.nourimate.data.local.models.Recipe
import com.telyu.nourimate.data.local.models.User
import com.telyu.nourimate.data.local.relations.MealsWithRecommendations
import com.telyu.nourimate.utils.UserModel
import com.telyu.nourimate.utils.UserPreference
import kotlinx.coroutines.flow.map
import java.util.Date

class NourimateRepository(
    private val userPreference: UserPreference,
    private val userDao: UserDao,
    private val foodDao: FoodDao,
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

    //=== QUERY ===

    fun getRecipeByMeal(mealId: Int): LiveData<List<Recipe>> {
        return foodDao.getRecipeByMeal(mealId)
    }

    fun getRecipeByMealAndDate (mealId: Int, date: Date): LiveData<List<Recipe>> {
        return foodDao.getRecipesForMealAndDate(mealId, date)
    }

    companion object {
        @Volatile
        private var instance: NourimateRepository? = null
        fun getInstance(
            pref: UserPreference, userDao: UserDao, foodDao: FoodDao
        ): NourimateRepository = instance ?: synchronized(this) {
            instance ?: NourimateRepository(pref, userDao, foodDao)
        }.also { instance = it }
    }

}