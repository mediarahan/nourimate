package com.telyu.nourimate.data.remote.retrofit

import com.telyu.nourimate.data.remote.response.GoogleSigninStep2Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface GoogleApiService {
    @POST("v1/accounts:signInWithCustomToken")
    suspend fun signInWithCustomToken(
        @Query("key") apiKey: String,
        @Body request: CustomTokenRequest
    ): GoogleSigninStep2Response
}

data class CustomTokenRequest (
    val token: String,
    val returnSecureToken: Boolean = true
)
