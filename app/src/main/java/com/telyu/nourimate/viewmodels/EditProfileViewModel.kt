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
            val detail = repository.getUserDetailsByEmail(email)
            emit(detail)
        }
    }

    val recommendationData: LiveData<Result<NourimateRepository.ListOfIds>> = userDetails.switchMap { detail ->
        val age = GeneralUtil.calculateAge(detail.dob)

        val recommendationRequest = RecommendationRequest(
            tinggi_badan = detail.height?.toInt() ?: 9999,
            jenis_kelamin = detail.gender ?: "Unknown",
            umur = age,
            penyakit = detail.disease ?: "None",
            alergi = detail.allergen ?: "None"
        )

        liveData {
            emit(Result.Loading)
            //pura2 loading
            delay(2000)
            try {
                val response = repository.fetchRecommendationData(recommendationRequest)
                Log.d("Age", age.toString())
                emit(Result.Success(response))
            } catch (e: Exception) {
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
                is Result.Success -> mapFetchedIdsToRecommendationEntity(
                    result.data.recipeIdsSarapan,
                    result.data.recipeIdsMakanSiang,
                    result.data.recipeIdsMakanMalam
                )
                is Result.Loading -> listOf()  // Or handle loading differently
                is Result.Error -> listOf()    // Or handle errors differently
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
            repository.changeAccountState(userId.toString().toInt(), userEmail.toString(), 3)
            repository.updateAccountState(userId.toString().toInt(), 3)
        }
    }

}