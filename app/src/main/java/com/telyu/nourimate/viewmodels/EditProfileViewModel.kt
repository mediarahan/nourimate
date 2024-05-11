package com.telyu.nourimate.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.telyu.nourimate.data.local.models.Detail
import com.telyu.nourimate.data.local.models.Recommendation
import com.telyu.nourimate.data.remote.Result
import com.telyu.nourimate.data.remote.retrofit.RecommendationRequest
import com.telyu.nourimate.data.repository.NourimateRepository
import com.telyu.nourimate.utils.GeneralUtil
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.Date

class EditProfileViewModel (private val repository: NourimateRepository): ViewModel() {

    fun insertDetail(detail: Detail) {
        viewModelScope.launch {
            repository.insertDetail(detail)
        }
    }



    //BISMILLAH API ----------------------------------------------------------------------------------------

    private val userEmail: LiveData<String> = repository.getUserEmail().asLiveData()
    private val userId: LiveData<Int> = repository.getUserId().asLiveData()

    private val userDetails: LiveData<Detail> = userEmail.switchMap { email ->
        liveData {
            Log.d("Debug", "Fetching user details for email: $email")
            val detail = repository.getUserDetailsByEmail(email)
            Log.d("Debug", "User details fetched: $detail")
            emit(detail)
        }
    }

    private val recommendationData: LiveData<Result<NourimateRepository.ListOfIds>> = userDetails.switchMap { detail ->
        val age = GeneralUtil.calculateAge(detail.dob)

        val recommendationRequest = RecommendationRequest(
            tinggi_badan = detail.height?.toInt() ?: 9999,
            jenis_kelamin = detail.gender,
            umur = age,
            penyakit = detail.disease,
            alergi = detail.allergen
        )
        Log.d("Debug", "Sending recommendation request: $recommendationRequest")
        liveData {
            try {
                val response = repository.fetchRecommendationData(recommendationRequest)
                Log.d("Debug", "Recommendation response received: $response")
                emit(Result.Success(response))
            } catch (e: Exception) {
                Log.e("Debug", "Error fetching recommendation data: ${e.message}")
                emit(Result.Error(e.message.toString()))
            }
        }
    }

    private fun mapFetchedIdsToRecommendationEntity(idSarapan: List<Int>, idMakanSiang: List<Int>, idMakanMalam: List<Int>): List<Recommendation> {
        val recommendations = mutableListOf<Recommendation>()
        val startDate = Date()

        val numDays = minOf(idSarapan.size, idMakanSiang.size, idMakanMalam.size)

        for (i in 0 until numDays) {
            val date = Date(startDate.time + i * 86400000)

            recommendations.add(Recommendation(
                recommendationId = recommendations.size + 1,
                date = date,
                isSelected = 0,
                recipeId = idSarapan[i],
            ))

            recommendations.add(Recommendation(
                recommendationId = recommendations.size + 1,
                date = date,
                isSelected = 0,
                recipeId = idMakanSiang[i],
            ))

            recommendations.add(Recommendation(
                recommendationId = recommendations.size + 1,
                date = date,
                isSelected = 0,
                recipeId = idMakanMalam[i],
            ))
        }
        return recommendations
    }

    val recommendationsLiveData: LiveData<List<Recommendation>> = recommendationData.switchMap { result ->
        MutableLiveData<List<Recommendation>>().apply {
            value = when (result) {
                is Result.Success -> {
                    val recommendations = mapFetchedIdsToRecommendationEntity(
                        result.data.recipeIdsSarapan,
                        result.data.recipeIdsMakanSiang,
                        result.data.recipeIdsMakanMalam
                    )
                    Log.d("Debug", "Mapped recommendations: $recommendations")
                    recommendations
                }
                is Result.Loading -> listOf()
                is Result.Error -> {
                    Log.e("Error", "Error in recommendation result: ")
                    listOf()
                }
            }
        }
    }


    fun insertRecommendations(recommendations: List<Recommendation>) {
        viewModelScope.launch {
            repository.insertRecommendations(recommendations)
        }
    }
    //BISMILLAH API ----------------------------------------------------------------------------------------

    fun setAccountStateAsCompleted() {
        viewModelScope.launch {
            val email = repository.getUserEmail().firstOrNull() ?: ""
            val userId = repository.getUserId().firstOrNull() ?: -1

            if (userId != -1 && email.isNotEmpty()) {
                repository.changeAccountState(userId, email, 3)
                repository.updateAccountState(userId, 3)
            } else {
                Log.e("ViewModel", "Invalid userId or email: userId=$userId, email=$email")
            }
        }
    }

}