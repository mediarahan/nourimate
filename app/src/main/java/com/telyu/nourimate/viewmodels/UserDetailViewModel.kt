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
import com.telyu.nourimate.utils.UserPreference
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.Date

class UserDetailViewModel(private val repository: NourimateRepository) : ViewModel() {

    private val userId = repository.getUserId().asLiveData()

    val userDetails: LiveData<Detail> = userId.switchMap {
        liveData {
            val detail = repository.getUserDetailsById(it)
            if (detail != null) {
                emit(detail)
            }
        }
    }

    private var _username = MutableLiveData<String>()
    val username: LiveData<String> = _username

    fun getUsername() {
        viewModelScope.launch {
            val username = repository.getUsername().first()
            _username.value = username
        }
    }

    fun updateUserProfile(
        detailId: Int,
        dob: Date?,
        height: Int,
        weight: Int,
        waistSize: Int,
        gender: String,
        allergen: String,
        disease: String,
        bmi: Float
    ) {
        viewModelScope.launch {
            val userId = repository.getUserId().first()
            val detail = Detail(
                detailId,
                dob,
                height,
                weight,
                waistSize,
                gender,
                allergen,
                disease,
                bmi,
                userId
            )
            repository.updateUserProfile(detail)
        }
    }

    fun updateUserProfileToBackend(
        dob: String,
        height: Int,
        waistSize: Int,
        weight: Int,
        gender: String,
        allergen: String,
        disease: String,
    ) {
        viewModelScope.launch {
            val userId = repository.getUserId().first()
            repository.updateUserProfileToBackend(userId, dob, height, waistSize, weight, gender, allergen, disease)
        }
    }

    private val recommendationData: LiveData<Result<NourimateRepository.ListOfIds>> =
        userDetails.switchMap { detail ->
            val age = GeneralUtil.calculateAge(detail.dob)

            val recommendationRequest = RecommendationRequest(
                tinggi_badan = detail.height,
                berat_badan = detail.weight,
                jenis_kelamin = detail.gender,
                umur = age,
                penyakit = detail.disease,
                alergi = detail.allergen
            )
            Log.d("Debug", "Sending recommendation request: $recommendationRequest")
            liveData {
                try {
                    val response = repository.fetchRecommendationData(recommendationRequest) //API call from repository
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
        idMakanMalam: List<Int>,
        userId: Int
    ): List<Recommendation> {
        val recommendations = mutableListOf<Recommendation>()
        val startDate = Date()

        val numDays = maxOf(idSarapan.size, idMakanSiang.size, idMakanMalam.size) / 3  // Assuming each list has 3 times the IDs needed per day

        for (i in 0 until numDays) {
            val date = Date(startDate.time + i * 86400000)
            val dateFormat = SimpleDateFormat("yyyy/MM/dd")
            val dateString = dateFormat.format(date)

            // Assigning three breakfast recommendations
            for (j in 0 until 3) {
                val index = i * 3 + j
                if (index < idSarapan.size) {
                    recommendations.add(
                        Recommendation(
                        recommendationId = recommendations.size + 1,
                        date = dateString,
                        isSelected = 0,
                        recipeId = idSarapan[index],
                        userId = userId
                    )
                    )
                }
            }

            // Assigning three lunch recommendations
            for (j in 0 until 3) {
                val index = i * 3 + j
                if (index < idMakanSiang.size) {
                    recommendations.add(
                        Recommendation(
                        recommendationId = recommendations.size + 1,
                        date = dateString,
                        isSelected = 0,
                        recipeId = idMakanSiang[index],
                        userId = userId
                    )
                    )
                }
            }

            // Assigning three dinner recommendations
            for (j in 0 until 3) {
                val index = i * 3 + j
                if (index < idMakanMalam.size) {
                    recommendations.add(
                        Recommendation(
                        recommendationId = recommendations.size + 1,
                        date = dateString,
                        isSelected = 0,
                        recipeId = idMakanMalam[index],
                        userId = userId
                    )
                    )
                }
            }
        }
        return recommendations
    }


    val recommendationsLiveData: LiveData<List<Recommendation>> =
        recommendationData.switchMap { result ->
            liveData {
                val userId = repository.getUserId().firstOrNull() ?: -1  // Fetch the user ID here
                emit(when (result) {
                    is Result.Success -> {
                        val recommendations = mapFetchedIdsToRecommendationEntity(
                            result.data.recipeIdsSarapan,
                            result.data.recipeIdsMakanSiang,
                            result.data.recipeIdsMakanMalam,
                            userId
                        )
                        Log.d("Debug", "Mapped recommendations: $recommendations")
                        recommendations
                    }
                    is Result.Loading -> listOf()
                    is Result.Error -> {
                        Log.e("Error", "Error in recommendation result.")
                        listOf()
                    }
                })
            }
        }

    fun insertRecommendations(recommendations: List<Recommendation>) {
        viewModelScope.launch {
            repository.insertRecommendations(recommendations)
        }
    }




























}