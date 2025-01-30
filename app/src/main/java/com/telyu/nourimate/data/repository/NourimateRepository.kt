package com.telyu.nourimate.data.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.telyu.nourimate.data.local.dao.FoodDao
import com.telyu.nourimate.data.local.dao.UserDao
import com.telyu.nourimate.data.local.models.Detail
import com.telyu.nourimate.data.local.models.History
import com.telyu.nourimate.data.local.models.NutritionSum
import com.telyu.nourimate.data.local.models.Profpic
import com.telyu.nourimate.data.local.models.Recipe
import com.telyu.nourimate.data.local.models.RecipeHistory
import com.telyu.nourimate.data.local.models.Recommendation
import com.telyu.nourimate.data.local.models.User
import com.telyu.nourimate.data.local.models.WeightEntry
import com.telyu.nourimate.data.local.models.WeightTrack
import com.telyu.nourimate.data.remote.Result
import com.telyu.nourimate.data.remote.response.GetAllUserProgramResponse
import com.telyu.nourimate.data.remote.response.GetUserDetailResponse
import com.telyu.nourimate.data.remote.response.GoogleSigninStep1Response
import com.telyu.nourimate.data.remote.response.GoogleSigninStep3Response
import com.telyu.nourimate.data.remote.response.RecommendationResponse
import com.telyu.nourimate.data.remote.response.SendEmailVerificationResponse
import com.telyu.nourimate.data.remote.response.SigninResponse
import com.telyu.nourimate.data.remote.retrofit.ApiService
import com.telyu.nourimate.data.remote.retrofit.ApiService2
import com.telyu.nourimate.data.remote.retrofit.ChangePasswordRequest
import com.telyu.nourimate.data.remote.retrofit.ChangePhoneNumberRequest
import com.telyu.nourimate.data.remote.retrofit.CreateNewProgramRequest
import com.telyu.nourimate.data.remote.retrofit.CustomTokenRequest
import com.telyu.nourimate.data.remote.retrofit.EmailVerificationRequest
import com.telyu.nourimate.data.remote.retrofit.GoogleApiService
import com.telyu.nourimate.data.remote.retrofit.GoogleSigninTokenRequest
import com.telyu.nourimate.data.remote.retrofit.InsertDetailUserRequest
import com.telyu.nourimate.data.remote.retrofit.PhoneVerificationRequest
import com.telyu.nourimate.data.remote.retrofit.RecommendationRequest
import com.telyu.nourimate.data.remote.retrofit.ResetPasswordRequest
import com.telyu.nourimate.data.remote.retrofit.SendEmailVerificationRequest
import com.telyu.nourimate.data.remote.retrofit.SendForgotPasswordRequest
import com.telyu.nourimate.data.remote.retrofit.SendGoogleSigninVerificationRequest
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import java.util.Date

class NourimateRepository(
    private val apiService: ApiService,
    private val apiService2: ApiService2,
    private val googleApiService: GoogleApiService,
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

    fun registerBackend(
        name: String,
        phoneNumber: String,
        email: String,
        password: String
    ): LiveData<Result<SigninResponse>> = liveData {
        emit(Result.Loading)
        try {
            val requestBody = SignupRequest(name, email, password, phoneNumber)
            val response = apiService.signup(requestBody)

            if (response.message == "User registered successfully") {
                delay(1000) // Optional: delay to mimic network latency
                emit(Result.Loading) // Notify observers we are now logging in
                // Automatically call login after successful registration
                val signinRequest = SigninRequest(email, password)
                val loginResponse = apiService.signin(signinRequest)
                val userModel = UserModel(
                    loginResponse.user.userId,
                    email,
                    loginResponse.accessToken,
                    loginResponse.refreshToken,
                    true,
                    loginResponse.user.isVerified == 1,
                    loginResponse.user.isDetailFilled == 1,
                    loginResponse.user.name,
                    loginResponse.user.phoneNumber
                )
                userPreference.saveSession(userModel)
                emit(Result.Success(loginResponse))
            } else {
                emit(Result.Error("Signup failed"))
            }
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

            val userModel = UserModel(id, email, accessToken, refreshToken, true, isVerifiedBoolean,
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

    //login google part 1
    fun googleSigninToken(uid: String): LiveData<Result<GoogleSigninStep1Response>> = liveData {
        emit(Result.Loading)
        try {
            val requestBody = GoogleSigninTokenRequest(uid)
            val response = apiService.googleSigninToken(requestBody)

            delay(2000)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    // login part 3
    fun loginWithGoogle(
        email: String,
        idToken: String
    ): LiveData<Result<GoogleSigninStep3Response>> = liveData {
        emit(Result.Loading)
        try {
            val requestBody = SendGoogleSigninVerificationRequest(idToken)
            val response = apiService.sendGoogleSigninVerification(requestBody)
            val id = response.user.userId
            val isVerified = response.user.isVerified
            val isVerifiedBoolean = isVerified == 1
            val isDetailFilled = response.user.isDetailFilled
            val isDetailFilledBoolean = isDetailFilled == 1
            val accessToken = response.accessToken
            val refreshToken = response.refreshToken
            val name = "Nourimate User" //response.user.name
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
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    fun performGoogleSignIn(token: String): LiveData<Result<GoogleSigninStep3Response>> = liveData(
        Dispatchers.IO
    ) {
        emit(Result.Loading)
        try {
            coroutineScope {
                // First API call
                val step1Response =
                    async { apiService.googleSigninToken(GoogleSigninTokenRequest(token)) }.await()
                val customToken = step1Response.customToken

                // Second API call
                val apiKey = "AIzaSyBzel3r3JpwL4ghphSEVZr35Nm2Ok_z7qc"
                val step2Response = async {
                    googleApiService.signInWithCustomToken(
                        apiKey,
                        CustomTokenRequest(customToken, true)
                    )
                }.await()
                val idToken = step2Response.idToken

                // Third API call
                val response = async {
                    apiService.sendGoogleSigninVerification(
                        SendGoogleSigninVerificationRequest(
                            idToken
                        )
                    )
                }.await()
                val userModel = UserModel(
                    response.user.userId,
                    token,  // Assuming token is still needed here
                    response.accessToken,
                    response.refreshToken,
                    true,
                    response.user.isVerified == 1,
                    response.user.isDetailFilled == 1,
                    "Nourimate User",  // or response.user.name if available
                    response.user.phoneNumber
                )

                // Save the user model to your DataStore or preference
                userPreference.saveSession(userModel)
                Log.d("LoginProcess", "UserModel: $userModel")

                // Emitting success result
                emit(Result.Success(response))
            }
        } catch (e: Exception) {
            Log.e("LoginProcess", "Error in Google sign-in", e)
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
            val requestBody = ChangePhoneNumberRequest(userId, phoneNumber, confirmPhoneNumber)
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

    fun createNewProgram(
        ongoingProgram: Int,
        startDate: String,
        endDate: String,
        startWeight: Int,
        endWeight: Int,
        editCurrentWeightDate: String
    ): LiveData<Result<SendEmailVerificationResponse>> = liveData {
        emit(Result.Loading)
        try {
            val userId = getUserId().first()
            val requestBody = CreateNewProgramRequest(
                ongoingProgram,
                startDate,
                endDate,
                startWeight,
                endWeight,
                editCurrentWeightDate,
                userId
            )
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

    fun getUsername(): Flow<String> {
        return userPreference.getUsername()
    }

    fun getEmailAndPhoneNumber(): Flow<Pair<String, String>> {
        return userPreference.getEmailAndPhoneNumber()
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


    suspend fun updateSelectedRecommendationsPerMealType(mealType: Int) {
        foodDao.updateSelectedRecommendationsPerMealType(mealType)
    }

//    suspend fun updateAccountState(userId: Int, loginState: Int) {
//        userDao.updateAccountState(userId, loginState)
//    }

    //=== QUERY FOOD ===

    //query weekly


    fun getAllRecipesByMealType(mealId: Int): LiveData<List<Recipe>> {
        return foodDao.getAllRecipesByMealType(mealId)
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

    suspend fun getWeightTrackByIds(id: Int): WeightTrack {
        return userDao.getWeightTrackByIds(id)
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

    fun getRecipeHistorySortedAscending(userId: Int): LiveData<List<RecipeHistory>> {
        return foodDao.getRecipeHistorySortedAscending(userId)
    }

    suspend fun getTotalCaloriesByMealTypeHistory(mealType: Int, userId: Int): Int {
        return foodDao.getTotalCaloriesByMealTypeHistory(mealType, userId)
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


    suspend fun checkUserDetailExists(userId: Int): Boolean {
        return userDao.checkUserDetailExists(userId) > 0
    }

    suspend fun getRecipesByNameAndMealType(query: String, mealType: Int): List<Recipe> {
        return foodDao.getRecipesByNameAndMealType(query, mealType)
    }

    suspend fun getRecommendationsByMealId(mealType: Int): List<Recommendation> {
        return foodDao.getRecommendationsByMealId(mealType)
    }

    suspend fun getAllSelectedRecipeIds(): List<Int> {
        return foodDao.getAllSelectedRecipeIds()
    }

    suspend fun insertMealHistories(histories: List<RecipeHistory>) {
        return foodDao.insertRecipeHistories(histories)
    }

    suspend fun deselectSelectedRecipes() {
        foodDao.deselectSelectedRecipes()
    }

    suspend fun checkIfRecipeDatabaseIsFilled(): Int {
        return foodDao.checkIfRecipeDatabaseIsFilled()
    }

    suspend fun checkIfRecommendationDatabaseIsFilled(): Int {
        return foodDao.checkIfRecommendationDatabaseIsFilled()
    }

    // ========== Query untuk menampilkan resep di RecipeFragment ==========

    //Daily
    fun getAllRecipes(): LiveData<List<Recipe>> {
        return foodDao.getAllRecipes()
    }

    fun getRecommendationsByUserId(userId: Int): LiveData<List<Recommendation>> {
        return foodDao.getRecommendationsByUserId(userId)
    }

    //Weekly
    fun getRecipesByMealType(mealId: Int): LiveData<List<Recipe>> {
        return foodDao.getRecipesByMealType(mealId)
    }

    fun getRecommendationsByMealIdSortedAscending(
        mealId: Int,
        userId: Int
    ): LiveData<List<Recommendation>> {
        return foodDao.getRecommendationsByMealIdSortedAscending(mealId, userId)
    }

    //Pilih Resep
    //Dapatkan Rekomendasinya berdasarkan id resep dan mealType
    suspend fun getRecommendationByRecipeIdAndMealType(
        recipeId: Int,
        mealType: Int
    ): Recommendation? {
        return foodDao.getRecommendationByRecipeIdAndMealType(recipeId, mealType)
    }

    //Berdasarkan id nya, dapetin keseluruhan row rekomendasinya
    suspend fun getRecommendationById(id: Int): Recommendation? {
        return foodDao.getRecommendationById(id)
    }

    //Update rekomendasi yang didapat berdasarkan query sebelumnya
    suspend fun updateRecommendation(recommendation: Recommendation) {
        foodDao.updateRecommendation(recommendation)
    }



    companion object {
        @Volatile
        private var instance: NourimateRepository? = null
        fun getInstance(
            apiService: ApiService,
            apiService2: ApiService2,
            googleApiService: GoogleApiService,
            pref: UserPreference,
            pref2: SettingsPreference,
            userDao: UserDao,
            foodDao: FoodDao,
            context: Context
        ): NourimateRepository = instance ?: synchronized(this) {
            instance ?: NourimateRepository(
                apiService,
                apiService2,
                googleApiService,
                pref,
                pref2,
                userDao,
                foodDao,
                context
            )
        }.also { instance = it }
    }

}