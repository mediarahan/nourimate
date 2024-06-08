package com.telyu.nourimate.data.repository

import android.app.PendingIntent
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.google.android.gms.location.ActivityRecognition
import com.google.android.gms.location.SleepSegmentRequest
import com.telyu.nourimate.data.local.dao.FoodDao
import com.telyu.nourimate.data.local.dao.UserDao
import com.telyu.nourimate.data.local.models.Detail
import com.telyu.nourimate.data.local.models.History
import com.telyu.nourimate.data.local.models.NutritionSum
import com.telyu.nourimate.data.local.models.Profpic
import com.telyu.nourimate.data.local.models.Recipe
import com.telyu.nourimate.data.local.models.RecipeHistory
import com.telyu.nourimate.data.local.models.Recommendation
import com.telyu.nourimate.data.local.models.SleepSegmentEventEntity
import com.telyu.nourimate.data.local.models.User
import com.telyu.nourimate.data.local.models.WeightEntry
import com.telyu.nourimate.data.local.models.WeightTrack
import com.telyu.nourimate.data.remote.Result
import com.telyu.nourimate.data.remote.response.LoginResponse
import com.telyu.nourimate.data.remote.response.RecommendationResponse
import com.telyu.nourimate.data.remote.response.RegisterResponse
import com.telyu.nourimate.data.remote.retrofit.ApiService
import com.telyu.nourimate.data.remote.retrofit.ApiService2
import com.telyu.nourimate.data.remote.retrofit.EmailVerificationBody
import com.telyu.nourimate.data.remote.retrofit.LoginRequest
import com.telyu.nourimate.data.remote.retrofit.RecommendationRequest
import com.telyu.nourimate.data.remote.retrofit.RegisterRequest
import com.telyu.nourimate.data.remote.retrofit.SignUpBody
import com.telyu.nourimate.utils.SettingsPreference
import com.telyu.nourimate.utils.UserModel
import com.telyu.nourimate.utils.UserPreference
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import java.util.Date

class NourimateRepository(
    private val apiService: ApiService,
    private val apiService2: ApiService2,
    private val userPreference: UserPreference,
    private val settingsPreference: SettingsPreference,
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

//    fun register(
//        name: String,
//        phoneNumber: String,
//        email: String,
//        password: String
//    ): LiveData<Result<Unit>> = liveData {
//        emit(Result.Loading)
//        try {
//            val registerRequest = SignUpBody(name, phoneNumber, email, password)
//            val requestBody = apiService2.signup(registerRequest)
//
//            delay(2000)
//            emit(Result.Success(requestBody))
//
//        } catch (e: java.lang.Exception) {
//            Log.d("UserRepository", "register:${e.message.toString()}")
//            emit(Result.Error(e.message.toString()))
//        }
//    }

        fun registerBackend(
        name: String,
        phoneNumber: String,
        email: String,
        password: String
    ): LiveData<Result<RegisterResponse>> = liveData {
        emit(Result.Loading)
        try {
            val registerRequest = RegisterRequest(name, phoneNumber, email, password)
            val requestBody = apiService.register(registerRequest)

            delay(2000)
            emit(Result.Success(requestBody))

        } catch (e: java.lang.Exception) {
            Log.d("UserRepository", "register:${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }

    fun sendVerifyEmail(userId: Int, email: String): LiveData<Result<Unit>> = liveData {
        emit(Result.Loading)
        try {
            val verifyRequest = EmailVerificationBody(userId, email)
            val requestBody = apiService2.sendEmailVerification(verifyRequest)
            delay(2000)
            emit(Result.Success(requestBody))

        } catch (e: java.lang.Exception) {
            Log.d("UserRepository", "register:${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }

    fun verifyEmail(userId: Int, email: String): LiveData<Result<Unit>> = liveData {
        emit(Result.Loading)
        try {
            val verifyRequest = EmailVerificationBody(userId, email)
            val requestBody = apiService2.verifyEmail(verifyRequest)
            delay(2000)
            emit(Result.Success(requestBody))
        } catch (e: java.lang.Exception) {
            Log.d("UserRepository", "register:${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }

    fun postDetails(detailId:Int, dob: String, height: Int, waistSize: Int, gender: String, allergen: String, disease: String, userId: Int): LiveData<Result<Unit>> = liveData {
       emit(Result.Loading)

    }

    fun loginBackend(email: String, password: String): LiveData<Result<LoginResponse>> = liveData {
        emit(Result.Loading)
        try {
            val loginRequest = LoginRequest(email, password)
            val requestBody = apiService.login(loginRequest)
            val id = requestBody.user.userId
            val isVerified = requestBody.user.emailVerified
            val isVerifiedBoolean = isVerified == 1
            val isDetailFilled = requestBody.user.phoneVerified
            val isDetailFilledBoolean = isDetailFilled == 1
            val accessToken = requestBody.accessToken
            val refreshToken = requestBody.refreshToken

            val userModel = UserModel(
                id,
                email,
                accessToken,
                refreshToken,
                true,
                isDetailFilledBoolean,
                isVerifiedBoolean
            )
            userPreference.saveSession(userModel)

            delay(2000)
            emit(Result.Success(requestBody))
        } catch (e: java.lang.Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    suspend fun saveSession(userModel: UserModel) {
        userPreference.saveSession(userModel)
    }


//    fun signup(password: String, confirmPassword: String, ): Boolean {
//        return password == confirmPassword
//    }

    //inimah lancar, masukin ke userPreference aman2 aja
//    suspend fun login(email: String, password: String): Int {
//        val user = userDao.getUserByEmail(email)
//        if (user != null && user.password == password && email == user.email) {
//            val currentAccountState = userDao.getAccountStateByUserId(user.userId)
//
//            userPreference.logout()
//            val userModel = UserModel(
//                user.userId, email, null, null,
//                isLoggedIn = true,
//                isVerified = false,
//                isDetailFilled = false
//            )
//            userPreference.saveSession(userModel)
//            Log.d("Login", "Email: $email, userId: ${user.userId} IsLoginSuccessful: true")
//            return currentAccountState
//        } else {
//            return -1
//        }
//    }

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

    suspend fun getBMIById(id: Int?): Float? {
        return userDao.getBMIById(id)
    }

    suspend fun getProfpicById(id: Int): String? {
        return userDao.getProfpicById(id)
    }

    fun getUserEmail(): Flow<String> {
        return userPreference.getUserEmail()
    }

    fun getUserId(): Flow<Int> {
        return userPreference.getUserId()
    }

    fun getUserLoginState(): Flow<Boolean> {
        return userPreference.getUserLoginState()
    }

    fun getUserVerificationState(): Flow<Boolean> {
        return userPreference.getUserVerificationState()
    }

    fun getUserDetailFilled(): Flow<Boolean> {
        return userPreference.getUserDetailFilled()

    }

    suspend fun getUserDetailsByEmail(email: String): Detail {
        return userDao.getUserDetailsByEmail(email)
    }

    //Update Query Functions

    suspend fun updateUserProfile(detail: Detail) {
        return userDao.updateUserProfile(detail)
    }

    suspend fun updateUserName(id: Int, name: String) {
        return userDao.updateUserName(id, name)
    }

    suspend fun updateRecommendation(recommendation: Recommendation) {
        foodDao.updateRecommendation(recommendation)
    }

    suspend fun updateSelectedRecommendationsPerMealType(mealType: Int) {
        foodDao.updateSelectedRecommendationsPerMealType(mealType)
    }

//    suspend fun updateAccountState(userId: Int, loginState: Int) {
//        userDao.updateAccountState(userId, loginState)
//    }

    //=== QUERY FOOD ===

    //query weekly
    fun getRecipesByDateAndMeal(mealId: Int): LiveData<List<Recipe>> {
        return foodDao.getRecipesByDateAndMealType(mealId)
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
    suspend fun getRecommendationByRecipeIdAndMealType(
        recipeId: Int,
        mealType: Int
    ): Recommendation? {
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
            val requestBody = apiService2.getRecommendedRecipes(recommendationRequest)
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
        val idMakanMalam = response.makanMalam.map { it.recipeID }

        return ListOfIds(idSarapan, idMakanSiang, idMakanMalam)
    }

    //RecipeViewModel and HomeViewModel related

    //========== Sleep API Related ==========
    private val activityRecognitionClient = ActivityRecognition.getClient(context)

    fun subscribeToSleepData(pendingIntent: PendingIntent) {
        val request = SleepSegmentRequest.getDefaultSleepSegmentRequest()
        activityRecognitionClient.requestSleepSegmentUpdates(pendingIntent, request)
            .addOnSuccessListener { Log.d("Sleep", "Subscribed to sleep data updates") }
            .addOnFailureListener { e ->
                Log.e(
                    "Sleep",
                    "Failed to subscribe to sleep data updates",
                    e
                )
            }
    }

    fun unsubscribeToSleepData(pendingIntent: PendingIntent) {
        activityRecognitionClient.removeSleepSegmentUpdates(pendingIntent)
            .addOnSuccessListener { Log.d("SleepRepository", "Unsubscribed from sleep data.") }
            .addOnFailureListener { exception ->
                Log.e(
                    "SleepRepository",
                    "Failed to unsubscribe.",
                    exception
                )
            }
    }

    fun getAllSleepSegments(): LiveData<List<SleepSegmentEventEntity>> {
        return userDao.getAllSleepSegments()
    }

    //special program related
    suspend fun insertWeightEntry(entry: WeightEntry) {
        userDao.insertWeightEntry(entry)
    }

    suspend fun deleteWeightEntriesById(entryId: Int) {
        userDao.deleteWeightEntriesById(entryId)
    }

    fun getWeightEntriesByUserIdAsc(userId: Int): LiveData<List<WeightEntry>> {
        return userDao.getWeightEntriesByUserIdAsc(userId)
    }

    suspend fun getLatestWeightEntryByUserId(userId: Int): WeightEntry {
        return userDao.getLatestWeightEntryByUserId(userId)
    }

    fun getLatestWeightEntryByUserId2(userId: Int): LiveData<WeightEntry> {
        return userDao.getLatestWeightEntryByUserId2(userId)
    }

    suspend fun getLatestWeightEntryDateByUserId(userId: Int): Date {
        return userDao.getLatestWeightEntryDateByUserId(userId)
    }

    suspend fun getEarliestWeightEntryDateByUserId(userId: Int): Date {
        return userDao.getEarliestWeightEntryDateByUserId(userId)
    }

    suspend fun updateWeight(detailId: Int, weight: Int) {
        userDao.updateWeight(detailId, weight)
    }

    suspend fun getWeightTrackById(id: Int): WeightTrack {
        return userDao.getWeightTrackById(id)
    }

    suspend fun getUserNameById(id: Int): String? {
        return userDao.getUserNameById(id)
    }

    suspend fun insertWeightTrack(weightTrack: WeightTrack) {
        userDao.insertWeightTrack(weightTrack)
    }

    suspend fun changeRecommendationFromConsumedToExpired() {
        foodDao.changeRecommendationFromConsumedToExpired()
    }

    fun getConsumedRecipesByMealType(mealType: Int): LiveData<List<Recipe>> {
        return foodDao.getConsumedRecipesByMealType(mealType)
    }

    fun getRecipeHistorySortedAscending(): LiveData<List<RecipeHistory>> {
        return foodDao.getRecipeHistorySortedAscending()
    }

    suspend fun getTotalCaloriesByMealTypeHistory(mealType: Int): Int {
        return foodDao.getTotalCaloriesByMealTypeHistory(mealType)
    }

    suspend fun deleteWeightTrackByUserId(userId: Int?) {
        return userDao.deleteWeightTrackByUserId(userId)
    }

    suspend fun getNutritionSumsForHistory(): NutritionSum {
        return foodDao.getNutritionSumsForHistory()
    }

    suspend fun insertHistory(history: History) {
        return userDao.insertHistory(history)
    }

    suspend fun getHistory(userId: Int): List<History> {
        return userDao.getHistory(userId)
    }

    fun getSelectedRecipesByMealType(mealType: Int): LiveData<List<Recipe>> {
        return foodDao.getSelectedRecipesByMealType(mealType)
    }

    suspend fun getRecipeDetailByRecipeId(recipeId: Int): Recipe {
        return foodDao.getRecipeDetailByRecipeId(recipeId)
    }

    suspend fun getAllRecipe(): List<Recipe> {
        return foodDao.getAllRecipe()
    }

    suspend fun getAllInactiveRecommendations(): List<Recommendation> {
        return foodDao.getAllInactiveRecommendations()
    }

    fun getWeightEntriesLiveData(): LiveData<List<WeightEntry>> {
        return userDao.getWeightEntriesLiveData()
    }

    suspend fun getLatestHistory(userId: Int): History {
        return userDao.getLatestHistory(userId)
    }

    suspend fun getCooldownEndDate(userId: Int): Date {
        return userDao.getEditCurrentWeightDate(userId)
    }

    suspend fun saveWaterIntake(newAmount: Int) {
        settingsPreference.saveWaterIntake(newAmount)
    }

    suspend fun getWaterIntake(): Flow<Int> {
        return settingsPreference.getWaterIntake()
    }

    companion object {
        @Volatile
        private var instance: NourimateRepository? = null
        fun getInstance(
            apiService: ApiService,
            apiService2: ApiService2,
            pref: UserPreference,
            pref2: SettingsPreference,
            userDao: UserDao,
            foodDao: FoodDao,
            context: Context
        ): NourimateRepository = instance ?: synchronized(this) {
            instance ?: NourimateRepository(apiService, apiService2, pref, pref2, userDao, foodDao, context)
        }.also { instance = it }
    }

}