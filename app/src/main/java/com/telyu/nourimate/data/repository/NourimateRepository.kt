package com.telyu.nourimate.data.repository

import android.app.PendingIntent
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.test.core.app.ApplicationProvider
import com.google.android.gms.location.ActivityRecognition
import com.google.android.gms.location.SleepSegmentRequest
import com.telyu.nourimate.data.local.dao.FoodDao
import com.telyu.nourimate.data.local.dao.UserDao
import com.telyu.nourimate.data.local.models.Detail
import com.telyu.nourimate.data.local.models.NutritionSum
import com.telyu.nourimate.data.local.models.Profpic
import com.telyu.nourimate.data.local.models.Recipe
import com.telyu.nourimate.data.local.models.Recommendation
import com.telyu.nourimate.data.local.models.RecommendationRecipe
import com.telyu.nourimate.data.local.models.SleepSegmentEventEntity
import com.telyu.nourimate.data.local.models.User
import com.telyu.nourimate.data.remote.response.RecommendationResponse
import com.telyu.nourimate.data.remote.retrofit.ApiService
import com.telyu.nourimate.data.remote.retrofit.RecommendationRequest
import com.telyu.nourimate.utils.UserModel
import com.telyu.nourimate.utils.UserPreference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Date

class NourimateRepository(
    private val apiService: ApiService,
    private val userPreference: UserPreference,
    private val userDao: UserDao,
    private val foodDao: FoodDao,
    context: Context
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

    fun observeDatabaseFilledStatus(): LiveData<Boolean?> {
        return userPreference.getDatabaseFilled().map { filled ->
            filled
        }.asLiveData()
    }

    suspend fun setDatabaseFilled() {
        userPreference.setDatabaseFilled()
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

    suspend fun getUserDetailsByEmail(email: String): Detail {
        return userDao.getUserDetailsByEmail(email)
    }

    //Update Query Functions

    suspend fun updateUserProfile(detail: Detail) {
        return userDao.updateUserProfile(detail)
    }

    suspend fun updateUserName(id:Int, name: String) {
        return userDao.updateUserName(id, name)
    }

    suspend fun updateRecommendation(recommendation: Recommendation) {
        foodDao.updateRecommendation(recommendation)
    }

    suspend fun updateSelectedRecommendationsPerMealType(mealType: Int) {
        foodDao.updateSelectedRecommendationsPerMealType(mealType)
    }

    //=== QUERY FOOD ===

    //query weekly
    fun getRecipesByDateAndMeal(mealId: Int, startDate: Long, endDate: Long): LiveData<List<Recipe>> {
        return foodDao.getRecipesByDateAndMealType(mealId, startDate, endDate)
    }

    fun getRecommendationsByMealIdSortedAscending(mealId: Int): LiveData<List<Recommendation>> {
        return foodDao.getRecommendationsByMealIdSortedAscending(mealId)
    }

    suspend fun getRecommendationByRecipeAndMealId(recipeId: Int, mealId: Int): Recommendation? {
        return foodDao.getRecommendationByRecipeAndMealId(recipeId, mealId)
    }

    //Query buat home fragment

    suspend fun getCaloriesByMealType(mealType: Int): Int {
        return foodDao.getTotalCaloriesByMealType(mealType)
    }

    suspend fun getNutritionSums(): NutritionSum {
        return foodDao.getNutritionSums()
    }

    suspend fun getNutritionSumsInBasketAndHomePerMealType(mealType: Int): NutritionSum {
        return foodDao.getNutritionSumsInBasketAndHomePerMealType(mealType)
    }

    suspend fun getSelectedRecipeCountUsingMealType(mealType: Int): Int {
        return foodDao.getSelectedRecipeCountUsingMealType(mealType)
    }

    //query utama

    fun getRecipesByRecommendationIds(recommendationIds: List<Int>): LiveData<List<Recipe>> {
        return foodDao.getRecipesByRecommendationIds(recommendationIds)
    }

    fun getAllSelectedRecommendationIdsByMealId(mealType: Int): LiveData<List<Int>> {
        return foodDao.getAllSelectedRecommendationIdsByMealId(mealType)
    }

    fun getAllConfirmedRecommendationIdsByMealId(mealType: Int): LiveData<List<Int>> {
        return foodDao.getAllConfirmedRecommendationIdsByMealId(mealType)
    }

    //query utama
    suspend fun getRecommendationByRecipeIdAndMealType(recipeId: Int, mealType: Int): Recommendation? {
        return foodDao.getRecommendationByRecipeIdAndMealType(recipeId, mealType)
    }

    fun getSelectedRecipeCount(): LiveData<Int> {
        return foodDao.getSelectedRecipeCount()
    }

    fun getSelectedRecipeCountByMealType(mealType: Int): LiveData<Int> {
        return foodDao.getSelectedRecipeCountByMealType(mealType)
    }

    suspend fun getRecipeByName(name: String): List<Recipe> {
        return foodDao.getRecipeByName(name)
    }

    //query untuk mock machine learning activity

    val allRecipeNames: LiveData<List<String>> = foodDao.getAllRecipeNames()

    suspend fun insertRecommendation(recommendation: Recommendation) {
        foodDao.insertRecommendation(recommendation)
    }

    suspend fun insertRecommendations(recommendations: List<Recommendation>) {
        foodDao.insertRecommendations(recommendations)
    }

    suspend fun fetchRecommendationData(recommendationRequest: RecommendationRequest): ListOfIds {
        try {
            val requestBody = apiService.getRecommendedRecipes(recommendationRequest)
            Log.d("Recommendation", "Response: $requestBody")
            val mappedResponse = mapResponseToRecipeIds(requestBody)

            return mappedResponse
        } catch (e: Exception) {
            throw e
        }
    }

    data class ListOfIds(
        val recipeIdsSarapan: List<Int>,
        val recipeIdsMakanSiang: List<Int>,
        val recipeIdsMakanMalam: List<Int>
    )

    private fun mapResponseToRecipeIds(response: RecommendationResponse): ListOfIds {
        val idSarapan = response.sarapan.map { it.recipeID }
        val idMakanSiang = response.makanSiang.map { it.recipeID }
        val idMakanMalam = response.makanMalam.map {it.recipeID}

        return ListOfIds(idSarapan, idMakanSiang, idMakanMalam)
    }

    //RecipeViewModel and HomeViewModel related

    //========== Sleep API Related ==========
    private val activityRecognitionClient = ActivityRecognition.getClient(context)

    fun subscribeToSleepData(pendingIntent: PendingIntent) {
        val request = SleepSegmentRequest.getDefaultSleepSegmentRequest()
        activityRecognitionClient.requestSleepSegmentUpdates(pendingIntent, request)
            .addOnSuccessListener { Log.d("Sleep", "Subscribed to sleep data updates") }
            .addOnFailureListener { e -> Log.e("Sleep", "Failed to subscribe to sleep data updates", e) }
    }

    fun unsubscribeToSleepData(pendingIntent: PendingIntent) {
        activityRecognitionClient.removeSleepSegmentUpdates(pendingIntent)
            .addOnSuccessListener { Log.d("SleepRepository", "Unsubscribed from sleep data.") }
            .addOnFailureListener { exception -> Log.e("SleepRepository", "Failed to unsubscribe.", exception) }
    }

    fun getAllSleepSegments(): LiveData<List<SleepSegmentEventEntity>> {
        return userDao.getAllSleepSegments()
    }


    companion object {
        @Volatile
        private var instance: NourimateRepository? = null
        fun getInstance(
            apiService: ApiService, pref: UserPreference, userDao: UserDao, foodDao: FoodDao, context: Context
        ): NourimateRepository = instance ?: synchronized(this) {
            instance ?: NourimateRepository(apiService, pref, userDao, foodDao, context)
        }.also { instance = it }
    }

}