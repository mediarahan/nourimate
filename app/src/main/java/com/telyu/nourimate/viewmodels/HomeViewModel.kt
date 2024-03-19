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


    private val _consumedCalories = MutableLiveData<Int>()
    val consumedCalories: LiveData<Int> = _consumedCalories

    private val _waterIntake = MutableLiveData(0)
    val waterIntake: LiveData<Int> get() = _waterIntake

    private val _currentGlass = MutableLiveData(0)
    val currentGlass: LiveData<Int> get() = _currentGlass

    fun updateConsumedCalories(calories: Int) {
        _consumedCalories.value = calories
    }


    init {
        // Contoh untuk mengatur waktu tidur dan bangun
        // Dalam aplikasi nyata, ini mungkin akan diambil dari database atau API
        sleepTime.value = "7hr 52m" // Contoh waktu tidur
        wakeUpTime.value = "06:00 AM" // Contoh waktu bangun
    }

    init {
        updateGreetingMessage()
        // Default weight message
        _weightMessage.value = "You've gained 0 kg today!"
    }

    fun setWeightMessage(weightGain: Int) {
        _weightMessage.value = "You've gained $weightGain kg today!"
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

    fun addWater(amount: Int) {
        if (_currentGlass.value ?: 0 < 8) { // Assuming there are 8 glasses
            _waterIntake.value = (_waterIntake.value ?: 0) + amount
            _currentGlass.value = (_currentGlass.value ?: 0) + 1
        }
    }

    fun setCurrentGlass(glass: Int) {
        _currentGlass.value = glass
    }


}
