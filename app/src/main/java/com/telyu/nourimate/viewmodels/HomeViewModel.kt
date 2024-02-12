package com.telyu.nourimate.viewmodels

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class HomeViewModel : ViewModel() {
    private val _greetingMessage = MutableLiveData<String>()
    val greetingMessage: LiveData<String>
        get() = _greetingMessage

    private val _weightMessage = MutableLiveData<String>()
    val weightMessage: LiveData<String>
        get() = _weightMessage

    private val _userProfilePhoto = MutableLiveData<Bitmap>()
    val userProfilePhoto: LiveData<Bitmap>
        get() = _userProfilePhoto

    val sleepTime = MutableLiveData<String>()
    val wakeUpTime = MutableLiveData<String>()

    // aktivitas olahraga
    val runningSpeed = MutableLiveData<String>().apply { value = "8 mph" }
    val runningGraphData = MutableLiveData<List<Int>>().apply {
        value = listOf(5, 10, 8, 15, 14, 10, 7, 9) // Contoh data lari
    }

    // LiveData for total calories
    val totalCalories = MutableLiveData<Int>().apply { value = 100 }
    val caloriesProgress = MutableLiveData<Int>().apply { value = 30 }

    // Initialize or update the totalCalories LiveData as needed
    fun updateTotalCalories(calories: Int, progress: Int) {
        totalCalories.value = calories
        caloriesProgress.value = progress
    }

    // Fungsi untuk update data (dalam aplikasi nyata ini akan lebih kompleks)
    fun updateRunningData(speed: String, graphData: List<Int>) {
        runningSpeed.value = speed
        runningGraphData.value = graphData
    }

    init {
        // Contoh untuk mengatur waktu tidur dan bangun
        // Dalam aplikasi nyata, ini mungkin akan diambil dari database atau API
        sleepTime.value = "22:00" // Contoh waktu tidur
        wakeUpTime.value = "06:00" // Contoh waktu bangun
    }

    init {
        updateGreetingMessage()
        // Default weight message
        _weightMessage.value = "You've gained 0 kg today"
    }

    fun setWeightMessage(weightGain: Int) {
        _weightMessage.value = "You've gained $weightGain kg today"
    }

    fun setUserProfilePhoto(photo: Bitmap) {
        _userProfilePhoto.value = photo
    }

    private fun updateGreetingMessage() {
        val calendar = Calendar.getInstance()
        val hourOfDay = calendar.get(Calendar.HOUR_OF_DAY)

        val greeting = when {
            hourOfDay < 12 -> "Good morning"
            hourOfDay < 18 -> "Good afternoon"
            else -> "Good evening"
        }

        _greetingMessage.value = greeting
    }
}
