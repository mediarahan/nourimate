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
import com.telyu.nourimate.data.remote.response.GetAllUserProgramResponse
import com.telyu.nourimate.data.remote.response.GetUserDetailResponse
import com.telyu.nourimate.data.remote.response.InsertUserDetailResponse
import com.telyu.nourimate.data.remote.response.RecommendationResponse
import com.telyu.nourimate.data.remote.response.SendEmailVerificationResponse
import com.telyu.nourimate.data.remote.response.SigninResponse
import com.telyu.nourimate.data.remote.response.SignupResponse
import com.telyu.nourimate.data.remote.retrofit.ApiService
import com.telyu.nourimate.data.remote.retrofit.ApiService2
import com.telyu.nourimate.data.remote.retrofit.ChangePasswordRequest
import com.telyu.nourimate.data.remote.retrofit.ChangePhoneRequest
import com.telyu.nourimate.data.remote.retrofit.CreateNewProgramRequest
import com.telyu.nourimate.data.remote.retrofit.EmailVerificationRequest
import com.telyu.nourimate.data.remote.retrofit.InsertDetailUserRequest
import com.telyu.nourimate.data.remote.retrofit.PhoneVerificationRequest
import com.telyu.nourimate.data.remote.retrofit.RecommendationRequest
import com.telyu.nourimate.data.remote.retrofit.ResetPasswordRequest
import com.telyu.nourimate.data.remote.retrofit.SendEmailVerificationRequest
import com.telyu.nourimate.data.remote.retrofit.SendForgotPasswordRequest
import com.telyu.nourimate.data.remote.retrofit.SendPhoneVerificationRequest
import com.telyu.nourimate.data.remote.retrofit.SigninRequest
import com.telyu.nourimate.data.remote.retrofit.SignupRequest
import com.telyu.nourimate.data.remote.retrofit.UpdateDetailUserRequest
import com.telyu.nourimate.utils.Converters
import com.telyu.nourimate.utils.GeneralUtil
import com.telyu.nourimate.utils.SettingsModel
import com.telyu.nourimate.utils.SettingsPreference
import com.telyu.nourimate.utils.UserModel
import com.telyu.nourimate.utils.UserPreference
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
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
    ): LiveData<Result<SignupResponse>> = liveData {
        emit(Result.Loading)
        try {
            val signupRequest = SignupRequest(name, email, password, phoneNumber)
            val requestBody = apiService.signup(signupRequest)

            delay(2000)
            emit(Result.Success(requestBody))

        } catch (e: java.lang.Exception) {
            Log.d("UserRepository", "register:${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }

    fun loginBackend(email: String, password: String): LiveData<Result<SigninResponse>> = liveData {
        emit(Result.Loading)
        try {
            val signinRequest = SigninRequest(email, password)
            val response = apiService.signin(signinRequest)
            val id = response.user.userId
            val isVerified = response.user.isVerified
            val isVerifiedBoolean = isVerified == 1
            Log.d("LoginProcess", "id: $id, isVerified: $isVerified")
            Log.d("LoginProcess", "id: $id, isVerified: $isVerifiedBoolean")
            val isDetailFilled = response.user.isDetailFilled
            val isDetailFilledBoolean = isDetailFilled == 1
            Log.d("LoginProcess", "isDetailFilled: $isDetailFilled")
            Log.d("LoginProcess", "isDetailFilled: $isDetailFilledBoolean")
            val accessToken = response.accessToken
            val refreshToken = response.refreshToken
            val name = response.user.name
            val phoneNumber = response.user.phoneNumber

            val userModel = UserModel(
                id,
                email,
                accessToken,
                refreshToken,
                true,
                isVerifiedBoolean,
                isDetailFilledBoolean,
                name,
                phoneNumber,
            )
            userPreference.saveSession(userModel)
            Log.d("LoginProcess", "UserModel: $userModel")

            delay(2000)
            emit(Result.Success(response))
        } catch (e: java.lang.Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    suspend fun sendVerifyEmail() {
        try {
            val userPreferences = getUserEmail().combine(getUserId()) { email, userId ->
                Pair(email, userId)
            }.first()
            val requestBody =
                SendEmailVerificationRequest(userPreferences.second, userPreferences.first)
            val response = apiService.sendEmailVerification(requestBody)
            Log.d("UserRepository", "Email sent successfully")
        } catch (e: Exception) {
            Log.d("UserRepository", "Error sending verification email: ${e.message}")
        }
    }

    fun verifyEmail(emailToken: String): LiveData<Result<Unit>> = liveData {
        emit(Result.Loading)
        try {
            val userId = getUserId().first()
            val requestBody = EmailVerificationRequest(userId, emailToken)
            val response = apiService.verifyEmail(requestBody)
            delay(2000)
            emit(Result.Success(response))
        } catch (e: Exception) {
            Log.d("UserRepository", "Error in email verification: ${e.message}")
            emit(Result.Error(e.message.toString()))
        }
    }

    suspend fun sendVerifyPhone(phoneNumber: Int) {
        try {
            val userId = getUserId().first()
            val requestBody = SendPhoneVerificationRequest(userId, phoneNumber)
            val response = apiService.sendPhoneVerification(requestBody)
            Log.d("UserRepository", "SMS sent successfully")
        } catch (e: Exception) {
            Log.d("UserRepository", "Error sending verification SMS: ${e.message}")
        }
    }

    fun verifyPhone(smsToken: String): LiveData<Result<Unit>> = liveData {
        emit(Result.Loading)
        try {
            val userId = getUserId().first()
            val requestBody = PhoneVerificationRequest(userId, smsToken)
            val response = apiService.verifyPhone(requestBody)
            delay(2000)
            emit(Result.Success(response))
        } catch (e: Exception) {
            Log.d("UserRepository", "Error in phone verification: ${e.message}")
            emit(Result.Error(e.message.toString()))
        }
    }

    suspend fun sendForgotPassword(email: String) {
        try {
            val requestBody = SendForgotPasswordRequest(email)
            val response = apiService.sendForgotPassword(requestBody)
            Log.d("UserRepository", "Forgot password email sent successfully")
        } catch (e: Exception) {
            Log.d("UserRepository", "Error sending forgot password email: ${e.message}")
        }
    }

    fun forgotPassword(
        password: String,
        token: String
    ): LiveData<Result<SendEmailVerificationResponse>> = liveData {
        emit(Result.Loading)
        try {
            val email = getUserEmail().first()
            val requestBody = ResetPasswordRequest(email, password)
            val response = apiService.resetPassword(token, requestBody)
            delay(2000)
            emit(Result.Success(response))
        } catch (e: Exception) {
            Log.d("UserRepository", "Error in forgot password: ${e.message}")
        }
    }

    fun changePassword(
        oldPassword: String,
        password: String,
        confirmPassword: String
    ): LiveData<Result<SendEmailVerificationResponse>> = liveData {
        emit(Result.Loading)
        try {
            val userId = getUserId().first()
            val requestBody = ChangePasswordRequest(userId, oldPassword, password, confirmPassword)
            val response = apiService.changePassword(requestBody)
            delay(2000)
            emit(Result.Success(response))
        } catch (e: Exception) {
            Log.d("UserRepository", "Error in change password: ${e.message}")
        }
    }

    fun changePhoneNumber(
        phoneNumber: String,
        confirmPhoneNumber: String
    ): LiveData<Result<SendEmailVerificationResponse>> = liveData {
        emit(Result.Loading)
        try {
            val userId = getUserId().first()
            val requestBody = ChangePhoneRequest(userId, phoneNumber, confirmPhoneNumber)
            val response = apiService.changePhoneNumber(requestBody)
            delay(2000)
            emit(Result.Success(response))
        } catch (e: Exception) {
            Log.d("UserRepository", "Error in change phone number: ${e.message}")
        }
    }

    suspend fun postUserDetails(
        userId: Int,
        dob: String,
        height: Int,
        waistSize: Int,
        weight: Int,
        gender: String,
        allergen: String,
        disease: String
    ) {
        try {
            val requestBody = InsertDetailUserRequest(
                dob = dob,
                height = height,
                waistSize = waistSize,
                weight = weight,
                gender = gender,
                allergen = allergen,
                disease = disease
            )
            val response = apiService.insertDetailUser(userId, requestBody)
            Log.d("UserDetailRepository", "User details posted successfully")
        } catch (e: Exception) {
            Log.d("UserDetailRepository", "Error posting user details: ${e.message}")
        }
    }

    suspend fun updateUserProfileToBackend(
        userId: Int,
        dob: String,
        height: Int,
        waistSize: Int,
        weight: Int,
        gender: String,
        allergen: String,
        disease: String
    ) {
        val requestBody = UpdateDetailUserRequest(
            dob = dob,
            height = height,
            waistSize = waistSize,
            weight = weight,
            gender = gender,
            allergen = allergen,
            disease = disease
        )
        val response = apiService.updateDetailUser(userId, requestBody)
    }

    suspend fun fetchUserDetails(userId: Int): GetUserDetailResponse? {
        return try {
            val response = apiService.getDetailUser(userId)
            val bmi = GeneralUtil.calculateBMI(response.weight, response.height)
            val formattedDate = Converters().fromStringToDate(response.dob)
            val detail = Detail(
                0,
                formattedDate,
                response.height,
                response.waistSize,
                response.weight,
                response.gender,
                response.allergen,
                response.disease,
                bmi ?: -999f,
                response.userId
            )
            insertDetail(detail)
            Log.d("UserRepository", "User details fetched successfully")
            response
        } catch (e: Exception) {
            Log.e("UserRepository", "Error fetching user details: ${e.message}")
            null
        }
    }

    suspend fun fetchAllUserProgram(): GetAllUserProgramResponse {
        return try {
            val response = apiService.getAllUserProgram()
            Log.d("UserRepository", "All user program fetched successfully")
            response
        } catch (e: Exception) {
            Log.e("UserRepository", "Error fetching all user program: ${e.message}")
            throw e
        }
    }

    fun createNewProgram(ongoingProgram: Int, startDate: String, endDate: String, startWeight: Int, endWeight: Int, editCurrentWeightDate: String): LiveData<Result<SendEmailVerificationResponse>> = liveData {
        emit(Result.Loading)
        try {
            val userId = getUserId().first()
            val requestBody = CreateNewProgramRequest(ongoingProgram, startDate, endDate, startWeight, endWeight, editCurrentWeightDate, userId)
            val response = apiService.createNewProgram(requestBody)
            delay(2000)
            emit(Result.Success(response))
        } catch (e: Exception) {
            Log.d("UserRepository", "Error in create new program: ${e.message}")
        }
    }


    suspend fun saveSession(userModel: UserModel) {
        userPreference.saveSession(userModel)
    }

    suspend fun saveSettingsPreferences(settingsModel: SettingsModel) {
        settingsPreference.saveSettings(settingsModel)
    }

    suspend fun setRecipeTransitionPreference(showTransition: Boolean) {
        settingsPreference.setRecipeTransitionPreference(showTransition)
    }

    fun getRecipeTransitionPreference(): Flow<Boolean> {
        return settingsPreference.getRecipeTransitionPreference()
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



    fun getEmailAndPhoneNumber(): Flow<Pair<String, String>> {
        return userPreference.getEmailAndPhoneNumber()
    }

    fun getUsername(): Flow<String> {
        return userPreference.getUsername()
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

    fun getSettingsPreferences(): Flow<SettingsModel> {
        return settingsPreference.getSettings()
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
    fun getRecipesByMealType(mealId: Int): LiveData<List<Recipe>> {
        return foodDao.getRecipesByMealType(mealId)
    }

    fun getRecommendationsByMealIdSortedAscending(mealId: Int): LiveData<List<Recommendation>> {
        return foodDao.getRecommendationsByMealIdSortedAscending(mealId)
    }

    suspend fun getRecommendationByRecipeAndMealId(recipeId: Int, mealId: Int): Recommendation? {
        return foodDao.getRecommendationByRecipeAndMealId(recipeId, mealId)
    }

    //Query buat home fragment

    suspend fun getCaloriesByMealType(mealType: Int, userId: Int): Int {
        return foodDao.getTotalCaloriesByMealType(mealType, userId)
    }

    suspend fun getNutritionSums(userId: Int): NutritionSum {
        return foodDao.getNutritionSums(userId)
    }

    suspend fun getNutritionSumsInBasketAndHomePerMealType(mealType: Int): NutritionSum {
        return foodDao.getNutritionSumsInBasketAndHomePerMealType(mealType)
    }

    suspend fun getSelectedRecipeCountUsingMealType(mealType: Int, userId: Int): Int {
        return foodDao.getSelectedRecipeCountUsingMealType(mealType, userId)
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

//    fun subscribeToSleepData(pendingIntent: PendingIntent) {
//        val request = SleepSegmentRequest.getDefaultSleepSegmentRequest()
//        activityRecognitionClient.requestSleepSegmentUpdates(pendingIntent, request)
//            .addOnSuccessListener { Log.d("Sleep", "Subscribed to sleep data updates") }
//            .addOnFailureListener { e ->
//                Log.e(
//                    "Sleep",
//                    "Failed to subscribe to sleep data updates",
//                    e
//                )
//            }
//    }

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

    suspend fun getWeightTrackById(id: Int): WeightTrack? {
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

    suspend fun getRecipeIdsByMealType(mealType: Int): List<Int> {
        return foodDao.getRecipeIdsByMealType(mealType)
    }

    suspend fun getRecommendationById(id: Int): Recommendation? {
        return foodDao.getRecommendationById(id)
    }

    fun getRecommendationsByUserId(userId: Int): LiveData<List<Recommendation>> {
        return foodDao.getRecommendationsByUserId(userId)
    }

    fun getAllRecipes(): LiveData<List<Recipe>> {
        return foodDao.getAllRecipes()
    }

    suspend fun checkUserDetailExists(userId: Int): Boolean {
        return userDao.checkUserDetailExists(userId) > 0
    }

    suspend fun getRecipesByNameAndMealType(query: String, mealType: Int): List<Recipe> {
        return foodDao.getRecipesByNameAndMealType(query, mealType)
    }

    suspend fun getRecommendationsByMealId(mealType: Int): List<Recommendation> {
        return foodDao.getRecommendationsByMealId(mealType)
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
            instance ?: NourimateRepository(
                apiService,
                apiService2,
                pref,
                pref2,
                userDao,
                foodDao,
                context
            )
        }.also { instance = it }
    }

}