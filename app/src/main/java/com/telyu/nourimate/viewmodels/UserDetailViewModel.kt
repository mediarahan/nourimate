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

    val userBMI: LiveData<Float> = userId.switchMap { id ->
        liveData {
            val detail = repository.getUserDetailsById(id)
            val bmi = GeneralUtil.calculateBMI(detail?.height, detail?.weight)
            Log.d("VERIFIKASI BMI", "BMI: $bmi")
            if (bmi != null) {
                emit(bmi)
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

    val userProgramStatus: LiveData<Boolean> = userId.switchMap { id ->
        liveData {
            val weightTrack = repository.getWeightTrackById(id)
            val status = weightTrack?.let {
                it.ongoingProgram != 0
            }
            if (status != null) {
                emit(status)
            }
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

    fun insertDetailToBackend(
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
            repository.postUserDetails(
                userId,
                dob,
                height,
                waistSize,
                weight,
                gender,
                allergen,
                disease
            )
        }
    }

    private val recommendationData: LiveData<List<Recommendation>> =
        userDetails.switchMap { detail ->
            liveData {
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
                try {
                    val response = repository.fetchRecommendationData(recommendationRequest) //API call
                    Log.d("Debug", "Recommendation response received: $response")
                    val userId = repository.getUserId().firstOrNull() ?: -1
                    val recommendations = mapFetchedIdsToRecommendationEntity(
                        response.recipeIdsSarapan,
                        response.recipeIdsMakanSiang,
                        response.recipeIdsMakanMalam,
                        userId
                    )
                    Log.d("Debug", "Mapped recommendations: $recommendations")
                    emit(recommendations)
                } catch (e: Exception) {
                    Log.e("Debug", "Error fetching recommendation data: ${e.message}")
                    emit(listOf())
                }
            }
        }

    val recommendationsLiveData: LiveData<List<Recommendation>> = recommendationData


    private fun mapFetchedIdsToRecommendationEntity(
        idSarapan: List<Int>,
        idMakanSiang: List<Int>,
        idMakanMalam: List<Int>,
        userId: Int
    ): List<Recommendation> {
        val recommendations = mutableListOf<Recommendation>()
        val startDate = Date()

        // Calculate the maximum number of days based on the longest list
        val numDays = maxOf(idSarapan.size, idMakanSiang.size, idMakanMalam.size) / 3

        for (i in 0 until numDays) {
            val date = Date(startDate.time + i * 86400000)
            val dateFormat = SimpleDateFormat("yyyy/MM/dd")
            val dateString = dateFormat.format(date)

            // Function to add recommendations
            fun addRecommendations(ids: List<Int>, startIdx: Int) {
                for (j in 0 until 3) {
                    val index = startIdx * 3 + j
                    if (index < ids.size) {
                        recommendations.add(
                            Recommendation(
                                recommendationId = recommendations.size + 1,
                                date = dateString,
                                isSelected = 0,
                                recipeId = ids[index],
                                userId = userId
                            )
                        )
                    }
                }
            }

            // Assign breakfast, lunch, and dinner recommendations
            addRecommendations(idSarapan, i)
            addRecommendations(idMakanSiang, i)
            addRecommendations(idMakanMalam, i)
        }

        // Handle any remaining recipes that didn't fit into full days
        val maxDaysHandled = numDays * 3
        fun handleLeftovers(ids: List<Int>, mealType: String) {
            for (index in maxDaysHandled until ids.size) {
                val date =
                    Date(startDate.time + (numDays * 86400000)) // Additional days beyond numDays
                val dateString = SimpleDateFormat("yyyy/MM/dd").format(date)
                recommendations.add(
                    Recommendation(
                        recommendationId = recommendations.size + 1,
                        date = dateString,
                        isSelected = 0,
                        recipeId = ids[index],
                        userId = userId
                    )
                )
            }
        }

        handleLeftovers(idSarapan, "Breakfast")
        handleLeftovers(idMakanSiang, "Lunch")
        handleLeftovers(idMakanMalam, "Dinner")

        return recommendations
    }


//    val recommendationsLiveData: LiveData<List<Recommendation>> =
//        recommendationData.switchMap { result ->
//            liveData {
//                Log.d("Debug", "recommendationData changed: $result")
//                val userId = repository.getUserId().firstOrNull() ?: -1  // Fetch the user ID here
//                emit(
//                    when (result) {
//                        is Result.Success -> {
//                            Log.d("Debug", "Result.Success received")
//                            val recommendations = mapFetchedIdsToRecommendationEntity(
//                                result.data.recipeIdsSarapan,
//                                result.data.recipeIdsMakanSiang,
//                                result.data.recipeIdsMakanMalam,
//                                userId
//                            )
//                            Log.d("Debug", "Mapped recommendations: $recommendations")
//                            recommendations
//                        }
//
//                        is Result.Loading -> listOf()
//                        is Result.Error -> {
//                            Log.e("Error", "Error in recommendation result.")
//                            listOf()
//                        }
//                    }
//                )
//            }
//        }

    fun insertRecommendations(recommendations: List<Recommendation>) {
        viewModelScope.launch {
            repository.insertRecommendations(recommendations)
        }
    }

    fun deleteCurrentRecommendations() {
        viewModelScope.launch {
            val userId = repository.getUserId().first()
            repository.deleteCurrentRecommendations(userId)
        }
    }
}