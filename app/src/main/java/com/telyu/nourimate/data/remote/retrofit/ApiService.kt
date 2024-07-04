package com.telyu.nourimate.data.remote.retrofit

import com.google.gson.annotations.SerializedName
import com.telyu.nourimate.data.remote.response.GetAllHistoryResponse
import com.telyu.nourimate.data.remote.response.GetAllUserMealHistoryResponse
import com.telyu.nourimate.data.remote.response.GetAllUserProgramResponse
import com.telyu.nourimate.data.remote.response.GetUserDetailResponse
import com.telyu.nourimate.data.remote.response.GoogleSigninStep1Response
import com.telyu.nourimate.data.remote.response.GoogleSigninStep3Response
import com.telyu.nourimate.data.remote.response.InsertUserDetailResponse
import com.telyu.nourimate.data.remote.response.SendEmailVerificationResponse
import com.telyu.nourimate.data.remote.response.SigninResponse
import com.telyu.nourimate.data.remote.response.SignupResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    //SendEmailVerificationResponse bisa dipake untuk semua response yang message doang

    @POST("api/auth/signup")
    suspend fun signup(
        @Body requestBody: SignupRequest
    ): SignupResponse

    @POST("api/auth/signin")
    suspend fun signin(
        @Body requestBody: SigninRequest
    ): SigninResponse

    //google sign in step 1
    @POST("api/auth/token")
    suspend fun googleSigninToken(
        @Body requestBody: GoogleSigninTokenRequest
    ): GoogleSigninStep1Response

    //google sign in step 2
    //di apiservice sebelah

    //step 3
    @POST("api/auth/google-verify-token")
    suspend fun sendGoogleSigninVerification(
        @Body requestBody: SendGoogleSigninVerificationRequest
    ): GoogleSigninStep3Response

    @POST("api/auth/send-email-verification")
    suspend fun sendEmailVerification(
        @Body requestBody: SendEmailVerificationRequest
    ): SendEmailVerificationResponse

    @POST("api/auth/verify-email")
    suspend fun verifyEmail(
        @Body requestBody: EmailVerificationRequest
    )

    @POST("api/auth/send-sms-verification")
    suspend fun sendPhoneVerification(
        @Body requestBody: SendPhoneVerificationRequest
    ): SendEmailVerificationResponse

    @POST("api/auth/verify-sms")
    suspend fun verifyPhone(
        @Body requestBody: PhoneVerificationRequest
    )

    //ganti password part 1
    @POST("api/auth/forgot-password")
    suspend fun sendForgotPassword(
        @Body requestBody: SendForgotPasswordRequest
    ): SendEmailVerificationResponse

    //ganti password part 2
    @POST("api/auth/reset-password/{token}")
    suspend fun resetPassword(
        @Path("token") token: String,
        @Body requestBody: ResetPasswordRequest
    ): SendEmailVerificationResponse

    //ganti password di profile
    @POST("api/auth/change-password")
    suspend fun changePassword(
        @Body requestBody: ChangePasswordRequest
    ): SendEmailVerificationResponse

    //ganti nomor telepon di profile
    @POST("api/auth/change-phone-number")
    suspend fun changePhoneNumber(
        @Body requestBody: ChangePhoneNumberRequest
    ): SendEmailVerificationResponse

    @GET("api/users/{user_id}/details")
    suspend fun getDetailUser(@Path("user_id") userId: Int): GetUserDetailResponse

    @POST("api/users/{user_id}/details")
    suspend fun insertDetailUser(
        @Path("user_id") userId: Int,
        @Body requestBody: InsertDetailUserRequest
    ): InsertUserDetailResponse

    //POST, GET and DELETE Program

    @POST("api/userprogram")
    suspend fun createNewProgram (
        @Body requestBody: CreateNewProgramRequest
    ): SendEmailVerificationResponse

    @GET("api/userprogram")
    suspend fun getAllUserProgram(): List<GetAllUserProgramResponse>

    //POST and GET MealHistory
    @POST("api/mealhistory")
    suspend fun createNewMealHistory (
        @Body requestBody: CreateNewMealHistoryRequest
    ): Response<SendEmailVerificationResponse>

    @GET("api/mealhistory")
    suspend fun getAllUserMealHistory(): GetAllUserMealHistoryResponse


    //POST and GET History
    @POST("api/history")
    suspend fun createNewHistory (
        @Body requestBody: CreateNewHistoryRequest
    ): SendEmailVerificationResponse

    @GET("api/history")
    suspend fun getAllUserHistory(): GetAllHistoryResponse
}

data class SignupRequest(
    val name: String,
    val email: String,
    val password: String,
    val phoneNumber: String
)

data class SigninRequest(
    val email: String,
    val password: String
)

data class SendEmailVerificationRequest(
    val userId: Int,
    val email: String
)

data class EmailVerificationRequest(
    val userId: Int,
    val emailToken: String,
)

data class SendPhoneVerificationRequest(
    val userId: Int,
    val phoneNumber: Int
)

data class PhoneVerificationRequest(
    val userId: Int,
    val smsToken: String
)

data class InsertDetailUserRequest(
    val dob: String,
    val height: Int,
    val waistSize: Int,
    val weight: Int,
    val gender: String,
    val allergen: String,
    val disease: String,
)

data class SendForgotPasswordRequest(
    val email: String,
)

data class ResetPasswordRequest(
    val email: String,
    val password: String,
    @SerializedName("confirm_password") val confirmPassword: String
)

data class ChangePasswordRequest(
    @SerializedName("user_id") val userId: Int,
    @SerializedName("old_password") val oldPassword: String,
    @SerializedName("password") val password: String,
    @SerializedName("confirm_password") val confirmPassword: String
)

data class GoogleSigninTokenRequest(
    val uid: String, //email
)

data class SendGoogleSigninVerificationRequest(
    @SerializedName("id_token") val idToken: String
)

data class ChangePhoneNumberRequest(
    @SerializedName("user_id") val userId: Int,
    @SerializedName("phone_number") val phoneNumber: String,
    @SerializedName("confim_phone_number") val confirmPhoneNumber: String
)

data class CreateNewProgramRequest(
    val ongoingProgram: Int,
    val startDate:  String,
    val endDate: String,
    val startWeight: Int,
    val endWeight: Int,
    val editCurrentWeightDate: String,
    @SerializedName("user_id") val userId: Int,
)

data class CreateNewMealHistoryRequest(
    @SerializedName("recipeId") val recipeId: Int,
    @SerializedName("consumedTime") val consumedTime: String,
    @SerializedName("consumedDate") val consumedDate: String,
    @SerializedName("user_id") val userId: Int
)

data class CreateNewHistoryRequest(
    @SerializedName("programName") val programName: String,
    @SerializedName("startDate") val startDate: String,
    @SerializedName("endDate") val endDate: String,
    @SerializedName("calories") val calories: Int,
    @SerializedName("protein") val protein: Int,
    @SerializedName("fat") val fat: Int,
    @SerializedName("carbs") val carbs: Int,
    @SerializedName("startWeight") val startWeight: Int,
    @SerializedName("endWeight") val endWeight: Int,
    @SerializedName("userId") val userId: Int,
    @SerializedName("createdAt") val createdAt: Long
)
