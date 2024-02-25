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
