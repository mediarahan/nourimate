package com.telyu.nourimate.data.remote.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientFirebase {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://identitytoolkit.googleapis.com/") // Base URL for Firebase
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <S> createService(serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }
}