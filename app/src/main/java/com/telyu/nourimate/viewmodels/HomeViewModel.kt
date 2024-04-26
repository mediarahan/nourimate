package com.telyu.nourimate.viewmodels

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.telyu.nourimate.data.local.models.Detail
import com.telyu.nourimate.data.local.models.NutritionSum
import com.telyu.nourimate.data.repository.NourimateRepository
import com.telyu.nourimate.data.remote.Result
import com.telyu.nourimate.utils.GeneralUtil
import kotlinx.coroutines.launch
import java.util.Calendar

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

    // Step 1: Fetch user's email
    val userEmail: LiveData<String> = repository.getUserEmail().asLiveData()

    // Step 2: Fetch user's details based on email
    private val userDetails: LiveData<Detail> = userEmail.switchMap { email ->
        liveData {
            val detail = repository.getUserDetailsByEmail(email)
            emit(detail)
        }
    }

    private val _nutritionSums = MutableLiveData<NutritionSum>()
    val nutritionSums: LiveData<NutritionSum> = _nutritionSums

    fun getNutritionSums() {
        viewModelScope.launch {
            val nutritionSums = repository.getNutritionSums()
            Log.d("NutritionSums", "Fetched Nutrition Sums: $nutritionSums")
            _nutritionSums.value = nutritionSums
        }
    }

    //Step 3: Extract user's health details
    private val maxNuritionsLiveData: LiveData<List<Int>> = userDetails.switchMap { detail ->
        val gender =
            if (detail.gender == "Laki-laki") true else if (detail.gender == "Perempuan") false else null
        val age = GeneralUtil.calculateAge(detail.dob)
        liveData {
            val dailyCalorieNeeds =
                calculateDailyCalorieNeeds(detail.height?.toInt() ?: 420, gender!!, age)
            val proteinNeeds = calculateDailyNutritionNeeds(dailyCalorieNeeds, 0.2)
            val fatNeeds = calculateDailyNutritionNeeds(dailyCalorieNeeds, 0.025)
            val carbNeeds = calculateDailyNutritionNeeds(dailyCalorieNeeds, 0.1375)

            Log.d("MaxNutritionValues", "Calorie: $dailyCalorieNeeds, Protein: $proteinNeeds, Fat: $fatNeeds, Carb: $carbNeeds")
            emit(listOf(dailyCalorieNeeds, proteinNeeds, fatNeeds, carbNeeds))

        }
    }

    val nutritionPercentage = MediatorLiveData<List<Int>>().apply {
        var maxNutritions: List<Int>? = null
        var nutritionSum: NutritionSum? = null

        fun update() {
            val localMaxNutritions = maxNutritions
            val localNutritionSum = nutritionSum
            if (localMaxNutritions != null && localNutritionSum != null) {
                val dailyCalorieNeeds = localMaxNutritions[0]
                val proteinNeeds = localMaxNutritions[1]
                val fatNeeds = localMaxNutritions[2]
                val carbNeeds = localMaxNutritions[3]

                val calorieSum = localNutritionSum.totalCalories
                val proteinSum = localNutritionSum.totalProtein
                val fatSum = localNutritionSum.totalFat
                val carbSum = localNutritionSum.totalCarbs

                val caloriePercentage = ((calorieSum / dailyCalorieNeeds.toDouble()) * 100).toInt()
                val proteinPercentage = ((proteinSum / proteinNeeds.toDouble()) * 100).toInt()
                val fatPercentage = ((fatSum / fatNeeds.toDouble()) * 100).toInt()
                val carbPercentage = ((carbSum / carbNeeds.toDouble()) * 100).toInt()

                Log.d("NutritionPercentages", "Calorie: $dailyCalorieNeeds / $calorieSum = $caloriePercentage%, ")
                Log.d("NutritionPercentages", "Protein: $proteinNeeds / $proteinSum = $proteinPercentage%, ")
                Log.d("NutritionPercentages", "Fat: $fatNeeds / $fatSum = $fatPercentage%, ")
                Log.d("NutritionPercentages", "Carb: $carbNeeds / $carbSum = $carbPercentage%, ")
                value = listOf(caloriePercentage, proteinPercentage, fatPercentage, carbPercentage)
            }
        }

        addSource(maxNuritionsLiveData) { maxNutritions = it; update() }
        addSource(_nutritionSums) { nutritionSum = it; update() }
    }


    private fun calculateDailyCalorieNeeds(
        userHeight: Int,
        userGender: Boolean,
        userAge: Int
    ): Int {
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

    //Untuk nampilin nama dan profpic
    private val _userId = MutableLiveData<Int?>()
    val userId: LiveData<Int?> = _userId

    private val _profilePicture = MutableLiveData<String?>()
    val profilePicture: LiveData<String?> = _profilePicture

    fun getUserIdByEmail(email: String) {
        viewModelScope.launch {
            val id = repository.getUserIdByEmail(email)
            _userId.value = id
        }
    }

    fun getProfpicById(id: Int) {
        viewModelScope.launch {
            val profpic = repository.getProfpicById(id)
            _profilePicture.value = profpic
        }
    }

}
