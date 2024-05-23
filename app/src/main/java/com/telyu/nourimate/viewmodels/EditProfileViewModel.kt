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

class EditProfileViewModel(private val repository: NourimateRepository) : ViewModel() {

    fun insertDetail(detail: Detail) {
        viewModelScope.launch {
            repository.insertDetail(detail)
        }
    }


    //BISMILLAH API ----------------------------------------------------------------------------------------

    private val userEmail: LiveData<String> = repository.getUserEmail().asLiveData()

    private val userDetails: LiveData<Detail> = userEmail.switchMap { email ->
        liveData {
            val usersId = repository.getUserId().firstOrNull() ?: -1
            Log.d("Debug", "Fetching user details for id: $usersId")
            val detail = repository.getUserDetailsById(usersId)
            Log.d("Debug", "User details fetched: $detail")
            if (detail != null) {
                emit(detail)
            }
        }
    }

    private val recommendationData: LiveData<Result<NourimateRepository.ListOfIds>> =
        userDetails.switchMap { detail ->
            val age = GeneralUtil.calculateAge(detail.dob)

            val recommendationRequest = RecommendationRequest(
                tinggi_badan = detail.height?.toInt() ?: 9999,
                berat_badan = detail.weight?.toInt() ?: 9999,
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

    private fun mapFetchedIdsToRecommendationEntity(
        idSarapan: List<Int>,
        idMakanSiang: List<Int>,
        idMakanMalam: List<Int>
    ): List<Recommendation> {
        val recommendations = mutableListOf<Recommendation>()
        val startDate = Date()

        val numDays = minOf(idSarapan.size, idMakanSiang.size, idMakanMalam.size)

        for (i in 0 until numDays) {
            val date = Date(startDate.time + i * 86400000)

            recommendations.add(
                Recommendation(
                    recommendationId = recommendations.size + 1,
                    date = date,
                    isSelected = 0,
                    recipeId = idSarapan[i],
                )
            )

            recommendations.add(
                Recommendation(
                    recommendationId = recommendations.size + 1,
                    date = date,
                    isSelected = 0,
                    recipeId = idMakanSiang[i],
                )
            )

            recommendations.add(
                Recommendation(
                    recommendationId = recommendations.size + 1,
                    date = date,
                    isSelected = 0,
                    recipeId = idMakanMalam[i],
                )
            )
        }
        return recommendations
    }

    val recommendationsLiveData: LiveData<List<Recommendation>> =
        recommendationData.switchMap { result ->
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

}