package com.telyu.nourimate.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.telyu.nourimate.data.local.dao.FoodDao
import com.telyu.nourimate.data.local.dao.UserDao
import com.telyu.nourimate.data.local.models.Detail
import com.telyu.nourimate.data.local.models.Profpic
import com.telyu.nourimate.data.local.models.Recipe
import com.telyu.nourimate.data.local.models.Recommendation
import com.telyu.nourimate.data.local.models.User
import com.telyu.nourimate.utils.UserModel
import com.telyu.nourimate.utils.UserPreference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

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

    suspend fun insertProfpic(profpic: Profpic) {
        userDao.insertProfpic(profpic)
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
    suspend fun getUserDetailsById(id: Int): Detail? {
        return userDao.getUserDetailsById(id)
        //abis ini, map masing masing atribut dari Detail ke EditText
    }

    suspend fun getUserNameByEmail(email: String): String? {
        return userDao.getUserNameByEmail(email)
    }

    suspend fun getBMIById(id: Int?): Int? {
        return userDao.getBMIById(id)
    }

    suspend fun getProfpicById(id: Int): String? {
        return userDao.getProfpicById(id)
    }

    fun getUserEmail(): Flow<String> {
        return userPreference.getUserEmail()
    }

    //Update Query Functions

    suspend fun updateUserProfile(detail: Detail) {
        return userDao.updateUserProfile(detail)
    }

    suspend fun updateUserName(id:Int, name: String) {
        return userDao.updateUserName(id, name)
    }

    //=== QUERY FOOD ===
    suspend fun getRecipesByMealType(mealType: Int): List<Recipe> {
        return foodDao.getRecipesByMealType(mealType)
    }

    suspend fun getRecipeByName(name: String): List<Recipe> {
        return foodDao.getRecipeByName(name)
    }

    //query untuk mock machine learning activity

    val allRecipeNames: LiveData<List<String>> = foodDao.getAllRecipeNames()

    suspend fun insertRecommendation(recommendation: Recommendation) {
        foodDao.insertRecommendation(recommendation)
    }

    fun getRecipeIdByName(recipeName: String): Int? {
        return foodDao.getRecipeIdByName(recipeName)
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