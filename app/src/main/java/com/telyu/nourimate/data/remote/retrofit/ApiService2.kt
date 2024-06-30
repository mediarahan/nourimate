package com.telyu.nourimate.data.remote.retrofit

import com.telyu.nourimate.data.remote.response.RecommendationResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService2 {
    @POST("rekomendasi")
    suspend fun getRecommendedRecipes(
        @Body requestData: RecommendationRequest
    ): RecommendationResponse
}

data class RecommendationRequest(
    val tinggi_badan: Int,
    val berat_badan: Int,
    val jenis_kelamin: String,
    val umur: Int,
    val penyakit: String,
    val alergi: String
)
