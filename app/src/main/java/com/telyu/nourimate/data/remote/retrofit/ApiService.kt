package com.telyu.nourimate.data.remote.retrofit

import com.telyu.nourimate.data.remote.response.GetAllUserProgramResponse
import com.telyu.nourimate.data.remote.response.GetUserDetailResponse
import com.telyu.nourimate.data.remote.response.InsertUserDetailResponse
import com.telyu.nourimate.data.remote.response.SendEmailVerificationResponse
import com.telyu.nourimate.data.remote.response.SigninResponse
import com.telyu.nourimate.data.remote.response.SignupResponse
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
        @Body requestBody: ChangePhoneRequest
    ): SendEmailVerificationResponse

    @GET("api/users/{user_id}/details")
    suspend fun getDetailUser(@Path("user_id") userId: Int): GetUserDetailResponse

    @POST("api/users/{user_id}/details")
    suspend fun insertDetailUser(
        @Path("user_id") userId: Int,
        @Body requestBody: InsertDetailUserRequest
    ): InsertUserDetailResponse

    @PUT("api/users/{user_id}/details")
    suspend fun updateDetailUser(
        @Path("user_id") userId: Int,
        @Body requestBody: UpdateDetailUserRequest
    ): InsertUserDetailResponse

    //google sign in step 1
    @POST("api/auth/token")
    suspend fun googleSigninToken(
        @Body requestBody: GoogleSigninTokenRequest
    )

    //google sign in step 2
    @POST("api/auth/signin")
    suspend fun googleSignin(
        @Body requestBody: GoogleSigninTokenRequest
    )

    @POST("api/auth/send-email-verification")
    suspend fun sendGoogleSigninVerification(
        @Body requestBody: SendGoogleSigninVerificationRequest
    )

    @GET("api/userprogram")
    suspend fun getAllUserProgram(): GetAllUserProgramResponse

//    @GET("api/userprogram/{programId}")
//    suspend fun getOneProgram(): GetOneProgramResponse

    @POST("api/userprogram")
    suspend fun createNewProgram (
        @Body requestBody: CreateNewProgramRequest
    ): SendEmailVerificationResponse


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
)

data class ChangePasswordRequest(
    val userId: Int,
    val oldPassword: String,
    val password: String,
    val currentPassword: String,
)

data class GoogleSigninTokenRequest(
    val uid: String, //email
)

data class SendGoogleSigninVerificationRequest(
    val idToken: String
)

data class UpdateDetailUserRequest(
    val dob: String,
    val height: Int,
    val waistSize: Int,
    val weight: Int,
    val gender: String,
    val allergen: String,
    val disease: String,
)

data class ChangePhoneRequest(
    val userId: Int,
    val phoneNumber: String,
    val confirmPhoneNumber: String,
)

data class CreateNewProgramRequest(
    val ongoingProgram: Int,
    val startDate:  String,
    val endDate: String,
    val startWeight: Int,
    val endWeight: Int,
    val editCurrentWeightDate: String,
    val userId: Int,
)