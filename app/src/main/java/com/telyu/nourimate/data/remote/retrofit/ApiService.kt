package com.telyu.nourimate.data.remote.retrofit

import com.telyu.nourimate.data.remote.response.LoginResponse
import com.telyu.nourimate.data.remote.response.RecommendationResponse
import com.telyu.nourimate.data.remote.response.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("recommend")
    suspend fun getRecommendedRecipes(
        @Body requestData: RecommendationRequest
    ): RecommendationResponse

    @POST("api/register")
    suspend fun register(
        @Body requestData: RegisterRequest
    ): RegisterResponse

    @POST("api/login")
    suspend fun login(
        @Body requestData: LoginRequest
    ): LoginResponse
}

data class RecommendationRequest(
    val tinggi_badan: Int,
    val jenis_kelamin: String,
    val umur: Int,
    val penyakit: String,
    val alergi: String
)

data class RegisterRequest(
    val name: String,
    val phoneNumber: String,
    val email: String,
    val password: String,
)

data class LoginRequest(
    val email: String,
    val password: String,
)