package com.telyu.nourimate.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.telyu.nourimate.data.local.dao.FoodDao
import com.telyu.nourimate.data.local.dao.UserDao
import com.telyu.nourimate.data.local.models.Detail
import com.telyu.nourimate.data.local.models.Recipe
import com.telyu.nourimate.data.local.models.User
import com.telyu.nourimate.utils.UserModel
import com.telyu.nourimate.utils.UserPreference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Date

class NourimateRepository(
    private val userPreference: UserPreference,
    private val userDao: UserDao,
    private val foodDao: FoodDao,
) {

    suspend fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    suspend fun insertDetail(detail: Detail) {
        userDao.insertDetail(detail)
    }

    // === AUTHENTICATION RELATED QUERIES ===

    fun signup(
        password: String,
        confirmPassword: String,
    ): Boolean {
        return password == confirmPassword
    }

    suspend fun login(
        email: String,
        password: String,
    ): Boolean {
        val user = userDao.getUserByEmail(email)
        val isLoginSuccessful = user != null && user.password == password

        Log.d("Login", "Email: $email, IsLoginSuccessful: $isLoginSuccessful")

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

        //=== UserPreferences Related ===

    //dapetin id dari shared/userpreferences
    suspend fun getUserIdByEmail(email: String): Int? {
        return userDao.getUserIdByEmail(email)
    }

    //dapetin id userdetails yang terasosiasi dengan id user tertentu
    suspend fun getUserDetailsById (id: Int): Detail? {
        return userDao.getUserDetailsById(id)
        //abis ini, map masing masing atribut dari Detail ke EditText
    }

    suspend fun getUserNameByEmail (email: String): String? {
        return userDao.getUserNameByEmail(email)
    }

    suspend fun getBMIById (id: Int?): Int? {
        return userDao.getBMIById(id)
    }

    fun getUserEmail(): Flow<String> {
        return userPreference.getUserEmail()
    }
        //=== UserPreferences Related ===

    // === AUTHENTICATION RELATED QUERIES ===

    //=== QUERY FOOD ===

    fun getRecipeByMeal(mealId: Int): LiveData<List<Recipe>> {
        return foodDao.getRecipeByMeal(mealId)
    }

    fun getRecipeByMealAndDate (mealId: Int, date: Date): LiveData<List<Recipe>> {
        return foodDao.getRecipesForMealAndDate(mealId, date)
    }

    suspend fun getRecipeByName (name: String): List<Recipe> {
        return foodDao.getRecipeByName(name)
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