package com.telyu.nourimate.data.remote.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
companion object {
    fun getApiService(): ApiService {
val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
val client = OkHttpClient.Builder()
    .addInterceptor(loggingInterceptor)
    .build()
        val retrofit = Retrofit.Builder()
            .baseUrl("http://34.128.81.224:8050/")  // Added 'http://' to the base URL
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(ApiService::class.java)
    }
}

}