package com.telyu.nourimate.data.remote.retrofit

import android.content.Context
import com.telyu.nourimate.utils.UserPreference
import com.telyu.nourimate.utils.dataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    companion object {
        fun getApiService(context: Context): ApiService {
            val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

            // Authorization Interceptor
            val authInterceptor = Interceptor { chain ->
                val accessToken = runBlocking {
                    UserPreference.getInstance(context.dataStore).getSession().first().accessToken
                }

                val originalRequest = chain.request()
                val requestWithAuthorization = originalRequest.newBuilder()
                    .header("Authorization", "Bearer $accessToken")
                    .build()

                chain.proceed(requestWithAuthorization)
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(authInterceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl("http://34.128.84.209/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}
