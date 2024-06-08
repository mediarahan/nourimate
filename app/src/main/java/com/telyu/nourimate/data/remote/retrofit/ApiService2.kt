package com.telyu.nourimate.data.remote.retrofit

import com.telyu.nourimate.data.remote.response.RecommendationResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService2 {

    @POST("auth/signup")
    suspend fun signup(@Body requestBody: SignUpBody)

    @POST("auth/signin")
    suspend fun signin(@Body requestBody: SignInBody)

    @POST("auth/send-email-verification")
    suspend fun sendEmailVerification(@Body requestBody: EmailVerificationBody)

    @POST("auth/verify-email")
    suspend fun verifyEmail(@Body requestBody: EmailVerificationBody)

    @POST("auth/send-phone-verification")
    suspend fun sendPhoneVerification(@Body requestBody: PhoneVerificationBody)

    @POST("auth/verify-phone")
    suspend fun verifyPhone(@Body requestBody: PhoneVerificationBody)

    @POST("auth/google-update-user")
    suspend fun googleUpdateLogin(@Body requestBody: GoogleUpdateBody)

    @POST("auth/token")
    suspend fun token(@Body requestBody: TokenBody)

    @POST("auth/generate-id_token")
    suspend fun generateIdToken(@Body requestBody: IdTokenBody)

    @POST("rekomendasi")
    suspend fun getRecommendedRecipes(
        @Body requestData: RecommendationRequest
    ): RecommendationResponse


}

data class SignUpBody(val email: String, val password: String, val name: String, val phoneNumber: String)
data class SignInBody(val email: String, val password: String)
data class EmailVerificationBody(val userId: Int, val email: String)
data class SendPhoneVerificationBody(val userId: Int, val phoneNumber: Long?)
data class PhoneVerificationBody(val userId: Int, val smsToken: String)

data class GoogleUpdateBody(val uid: String, val name: String, val phoneNumber: String)
data class TokenBody(val uid: String)
data class IdTokenBody(val token: String, val returnSecureToken: Boolean)

//data class SignUpResponse(val idToken: String)