package com.telyu.nourimate.viewmodels

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.telyu.nourimate.data.local.models.Detail
import com.telyu.nourimate.data.local.models.NutritionSum
import com.telyu.nourimate.data.repository.NourimateRepository
import com.telyu.nourimate.utils.GeneralUtil
import kotlinx.coroutines.launch
import java.util.*

class HomeViewModel(private val repository: NourimateRepository) : ViewModel() {
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


    //Today's Meal Related Functions

    //Part 1 of getting each nutrition percentage Values
    //Fetching each sum of selected recipes
        private val _breakfastCalories = MutableLiveData<Int>()
        val breakfastCalories: LiveData<Int> = _breakfastCalories

        private val _lunchCalories = MutableLiveData<Int>()
        val lunchCalories: LiveData<Int> = _lunchCalories

        private val _dinnerCalories = MutableLiveData<Int>()
        val dinnerCalories: LiveData<Int> = _dinnerCalories

        fun getCaloriesByMealType(mealType: Int) {
            viewModelScope.launch {
                val calories = repository.getCaloriesByMealType(mealType)
                when (mealType) {
                    1 -> _breakfastCalories.value = calories
                    2 -> _lunchCalories.value = calories
                    3 -> _dinnerCalories.value = calories
                }
            }
        }

    //Part 2 of getting each nutrition percentage Values
    //Fetching the user's personal health details


    private val _userDetails = MutableLiveData<Detail>()
    val userDetails: LiveData<Detail> = _userDetails

    // Step 1: Fetch user's email
    fun getUserDetailsByEmail(email: String) {
        viewModelScope.launch {
            val detail = repository.getUserDetailsByEmail(email)
            _userDetails.postValue(detail)

            val dailyCalories = detail.height?.let { calculateDailyCalorieNeeds(it.toInt(), detail.gender == "Laki-laki", GeneralUtil.calculateAge(detail.dob)) }
        }
    }

    // Step 2: Fetch userId based on email
//    fun getUserIdByEmail(email: String) {
//        viewModelScope.launch {
//            val id = repository.getUserIdByEmail(email)
//            _userId.value = id
//        }
//    }

    // Step 3: Fetch user's details based on userId

//    private val _userDetails = MutableLiveData<Detail?>()

    //Step 4: get User Detail Values
//    fun getUserDetailsById (id: Int) {
//        viewModelScope.launch {
//            val detail = repository.getUserDetailsById(id)
//            _userDetails.value = detail
//            Log.d("HomeViewModel", "User Details: $detail")
//            setUserDetailsByIdValues(detail)
//        }
//    }

    private var height: Int? = null
    private var age: Int? = null
    private var gender: Boolean? = false

    //Step 5: set User Detail Values
    private fun setUserDetailsByIdValues (detail: Detail?) {
        height = detail?.height?.toInt()
        age = GeneralUtil.calculateAge(detail?.dob)
        gender = if (detail?.gender == "Laki-laki")
            true // Male
        else {
            false //Female
        }
    }


    private fun calculateDailyCalorieNeeds(userHeight: Int, userGender: Boolean, userAge: Int): Int {
           val idealWeight = (userHeight - 100) - (0.1 * (userHeight - 100))

            val dailyCalorieNeeds = when {
                userAge in 20..29 -> if (userGender) ((15.3 * idealWeight + 679) * 1.78).toInt()
                else ((14.7 * idealWeight + 496) * 1.64).toInt()

                userAge in 30..59 -> if (userGender) ((11.6 * idealWeight + 879) * 1.78).toInt()
                else ((8.7 * idealWeight + 829) * 1.64).toInt()

                userAge >= 60 -> if (userGender) ((13.5 * idealWeight + 487) * 1.78).toInt()
                else ((13.5 * idealWeight + 596) * 1.64).toInt()

                else -> -999
            }

        return (dailyCalorieNeeds * 0.2).toInt()
    }

    private fun calculateDailyNutritionNeeds(dailyCalories: Int, nutritionMultiplier: Double): Int {
        return (dailyCalories * nutritionMultiplier).toInt()
    }

    private val _nutritionSums = MutableLiveData<NutritionSum?>()
    fun getNutritionSums() {
        viewModelScope.launch {
            val sums = repository.getNutritionSums()
            _nutritionSums.value = sums
            setNutritionSumsByMealTypeValues(sums)
        }
    }

    private var calories: Int? = null
    private var protein: Int? = null
    private var fat: Int? = null
    private var carbs: Int? = null

    private fun setNutritionSumsByMealTypeValues (nutritionSum: NutritionSum) {
        calories = nutritionSum.totalCalories.toInt()
        protein = nutritionSum.totalProtein.toInt()
        fat = nutritionSum.totalFat.toInt()
        carbs = nutritionSum.totalCarbs.toInt()
    }

    fun calculateNutritionPercentage(): List<Int> {

        val dailyCalorieNeeds = calculateDailyCalorieNeeds(height!!, gender!!, age!!)
        val proteinNeeds = calculateDailyNutritionNeeds(dailyCalorieNeeds, 0.2)
        val fatNeeds = calculateDailyNutritionNeeds(dailyCalorieNeeds, 0.025)
        val carbNeeds = calculateDailyNutritionNeeds(dailyCalorieNeeds, 0.1375)

        val calorieSum = calories
        val proteinSum = protein
        val fatSum = fat
        val carbSum = carbs

        val caloriePercentage = ((dailyCalorieNeeds / calorieSum!!) *100)
        val proteinPercentage = ((proteinNeeds / proteinSum!!) *100)
        val fatPercentage = ((fatNeeds / fatSum!!) *100)
        val carbPercentage = ((carbNeeds / carbSum!!) *100)

        val percentages = listOf(caloriePercentage, proteinPercentage, fatPercentage, carbPercentage)

        return percentages
    }

}
