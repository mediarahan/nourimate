package com.telyu.nourimate.viewmodels

import android.app.PendingIntent
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
import com.telyu.nourimate.data.local.models.Recipe
import com.telyu.nourimate.data.local.models.SleepSegmentEventEntity
import com.telyu.nourimate.data.repository.NourimateRepository
import com.telyu.nourimate.utils.GeneralUtil
import com.telyu.nourimate.utils.GeneralUtil.calculateAKEi
import com.telyu.nourimate.utils.GeneralUtil.calculateAge
import com.telyu.nourimate.utils.GeneralUtil.calculateBreakfastNutrition
import com.telyu.nourimate.utils.GeneralUtil.calculateDinnerNutrition
import com.telyu.nourimate.utils.GeneralUtil.calculateLunchNutrition
import com.telyu.nourimate.utils.GeneralUtil.convertConditionToCode
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import java.util.Calendar

class HomeViewModel(private val repository: NourimateRepository) : ViewModel() {
    private val _greetingMessage = MutableLiveData<String>()
    val greetingMessage: LiveData<String> get() = _greetingMessage

    private val _ongoingProgram = MutableLiveData<String>()
    val ongoingProgram: LiveData<String> get() = _ongoingProgram

    val sleepTime = MutableLiveData<String>()
    val wakeUpTime = MutableLiveData<String>()

    private val _waterIntake = MutableLiveData(0)
    val waterIntake: LiveData<Int> get() = _waterIntake

    private val _currentGlass = MutableLiveData(0)
    val currentGlass: LiveData<Int> get() = _currentGlass

    init {
        viewModelScope.launch {
            repository.getWaterIntake().collect { amount ->
                _waterIntake.value = amount
                _currentGlass.value = amount / 250 // Each glass is assumed to be 250ml
            }
        }
        updateGreetingMessage()
    }

    fun addWater(amount: Int) {
        if ((_currentGlass.value ?: 0) < 8) { // Assuming there are 8 glasses
            val newAmount = (_waterIntake.value ?: 0) + amount
            _waterIntake.value = newAmount
            viewModelScope.launch {
                repository.saveWaterIntake(newAmount)
            }
        }
    }

    fun setCurrentGlass(glass: Int) {
        _currentGlass.value = glass
    }

    init {
        // Contoh untuk mengatur waktu tidur dan bangun
        // Dalam aplikasi nyata, ini mungkin akan diambil dari database atau API
        sleepTime.value = "7hr 52m" // Contoh waktu tidur
        wakeUpTime.value = "06:00 AM" // Contoh waktu bangun
    }

    private fun updateGreetingMessage() {
        val calendar = Calendar.getInstance()
        val hourOfDay = calendar.get(Calendar.HOUR_OF_DAY)

        val greeting = when {
            hourOfDay < 12 -> "Good morning,"
            hourOfDay < 18 -> "Good afternoon,"
            else -> "Good evening,"
        }

        _greetingMessage.value = greeting
    }

    private val usersId = repository.getUserId().asLiveData()

    val userOngoingProgramAndMessage: LiveData<Pair<String, String>> = usersId.switchMap { id ->
        liveData {
            val weightTrack = repository.getWeightTrackById(id)
            val programStatus = weightTrack?.ongoingProgram ?: 0
            val programText = when (programStatus) {
                1 -> "Maintain Weight"
                2 -> "Loss Weight"
                3 -> "Gain Weight"
                else -> "No Program"
            }

            //kasus khusus maintain
            val weightDifference = weightTrack?.let {
                val difference = it.endWeight - it.startWeight
                when {
                    difference < 0 -> "You've lost ${-difference} kg from last week"
                    difference > 0 -> "You've gained ${difference} kg from last week"
                    else -> "You've successfully maintained your weight"
                }
            } ?: "Weight data unavailable."

            // Kasus untuk lost / gain weight
            val messageText = when (programStatus) {
                1 -> weightDifference
                2 -> if (weightTrack != null) "You've lost ${-(weightTrack.endWeight - weightTrack.startWeight)} kg since last week." else "Weight data unavailable."
                3 -> if (weightTrack != null) "You've gained ${weightTrack.endWeight - weightTrack.startWeight} kg since last week." else "Weight data unavailable."
                else -> "Please choose a program."
            }

            emit(Pair(programText, messageText))
        }
    }

    val userBMI: LiveData<Float> = usersId.switchMap { id ->
        Log.d("UserBMI", "User ID: $id")
        liveData {
            val detail = repository.getUserDetailsById(id)
            val bmi = detail?.bmi
            Log.d("UserBMI", "BMI: $bmi")
            emit(bmi ?: -999f)
        }
    }

    val currentValues: LiveData<Pair<Int?, Int?>> = usersId.switchMap { id ->
        liveData {
            val detail = repository.getUserDetailsById(id)
            val currentWeight = detail?.weight?.toInt()?: 0
            val currentWaist = detail?.waistSize?.toInt()?: 0
            emit(Pair(currentWeight, currentWaist))
        }
    }

    val idealValues: LiveData<Pair<Int?, Int?>> = usersId.switchMap { id ->
        liveData {
            val detail = repository.getUserDetailsById(id)
            val idealWeight: Int? =
                GeneralUtil.calculateIdealWeight(detail?.height)?.toInt()  // Allow null values
            val idealWaist: Int? = when(detail?.gender) {
                "Laki-laki" -> 90
                else -> 80
            }
            emit(Pair(idealWeight, idealWaist))
        }
    }


    private val _selectedMealTime = MutableLiveData<Int>()
    val selectedMealTime: LiveData<Int> get() = _selectedMealTime

    fun selectMealTime(mealTime: Int) {
        _selectedMealTime.value = mealTime
        Log.d("SelectedMealTime", _selectedMealTime.value.toString())
    }

    fun getSelectedRecipesByMealType(mealType: Int): LiveData<List<Recipe>> {
        return repository.getSelectedRecipesByMealType(mealType)
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
            val usersId = repository.getUserId().firstOrNull() ?: -1
            Log.d("Debug", "Fetching user details for id: $usersId")
            val detail = repository.getUserDetailsById(usersId)
            Log.d("Debug", "User details fetched: $detail")
            if (detail != null) {
                emit(detail)
            }
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
    val maxNutritionLiveData: LiveData<List<Int>> = userDetails.switchMap { detail ->
        liveData {
            val gender =
                if (detail.gender == "Laki-laki") true else if (detail.gender == "Perempuan") false else null
            val age = calculateAge(detail.dob)

            val akei = calculateAKEi(detail.height?.toInt() ?: 9999, gender, age)
            val conditionCode = convertConditionToCode(detail.disease)

            val breakfastNutrition = calculateBreakfastNutrition(akei, conditionCode)
            val lunchNutrition = calculateLunchNutrition(akei, conditionCode)
            val dinnerNutrition = calculateDinnerNutrition(akei, conditionCode)

            val totalCalorieNeeds =
                (breakfastNutrition.calories + lunchNutrition.calories + dinnerNutrition.calories).toInt()
            val totalProteinNeeds =
                (breakfastNutrition.protein + lunchNutrition.protein + dinnerNutrition.protein).toInt()
            val totalFatNeeds =
                (breakfastNutrition.fat + lunchNutrition.fat + dinnerNutrition.fat).toInt()
            val totalCarbNeeds =
                (breakfastNutrition.carbohydrates + lunchNutrition.carbohydrates + dinnerNutrition.carbohydrates).toInt()

            Log.d(
                "MaxNutritionValues",
                "Calorie: $totalCalorieNeeds, Protein: $totalProteinNeeds, Fat: $totalFatNeeds, Carb: $totalCarbNeeds"
            )
            emit(listOf(totalCalorieNeeds, totalProteinNeeds, totalFatNeeds, totalCarbNeeds))

        }
    }

    //daftar kalori per meal time untuk di expose ke view
    val caloriesPerMealtime: LiveData<List<Int>> = userDetails.switchMap { detail ->
        liveData {
            val gender =
                if (detail.gender == "Laki-laki") true else if (detail.gender == "Perempuan") false else null
            val age = calculateAge(detail.dob)

            val akei = calculateAKEi(detail.height?.toInt() ?: 9999, gender, age)
            val conditionCode = convertConditionToCode(detail.disease)

            val breakfastNutrition = calculateBreakfastNutrition(akei, conditionCode)
            val lunchNutrition = calculateLunchNutrition(akei, conditionCode)
            val dinnerNutrition = calculateDinnerNutrition(akei, conditionCode)

            val breakfastCalorieNeeds = (breakfastNutrition.calories).toInt()
            val lunchCalorieNeeds = (lunchNutrition.calories).toInt()
            val dinnerCalorieNeeds = (dinnerNutrition.calories).toInt()
            emit(listOf(breakfastCalorieNeeds, lunchCalorieNeeds, dinnerCalorieNeeds))
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

                Log.d(
                    "NutritionPercentages",
                    "Calorie: $dailyCalorieNeeds / $calorieSum = $caloriePercentage%, "
                )
                Log.d(
                    "NutritionPercentages",
                    "Protein: $proteinNeeds / $proteinSum = $proteinPercentage%, "
                )
                Log.d("NutritionPercentages", "Fat: $fatNeeds / $fatSum = $fatPercentage%, ")
                Log.d("NutritionPercentages", "Carb: $carbNeeds / $carbSum = $carbPercentage%, ")
                value = listOf(caloriePercentage, proteinPercentage, fatPercentage, carbPercentage)
            }
        }

        addSource(maxNutritionLiveData) { maxNutritions = it; update() }
        addSource(_nutritionSums) { nutritionSum = it; update() }
    }

    //Dapetin hitungan jumlah resep
    private val _breakfastCount = MutableLiveData<Int>()
    val breakfastCount: LiveData<Int> = _breakfastCount

    private val _lunchCount = MutableLiveData<Int>()
    val lunchCount: LiveData<Int> = _lunchCount

    private val _dinnerCount = MutableLiveData<Int>()
    val dinnerCount: LiveData<Int> = _dinnerCount

    fun getSelectedRecipeCountUsingMealType(mealType: Int) {
        viewModelScope.launch {
            val selectedRecipeCount = repository.getSelectedRecipeCountUsingMealType(mealType)
            when (mealType) {
                1 -> _breakfastCount.value = selectedRecipeCount
                2 -> _lunchCount.value = selectedRecipeCount
                3 -> _dinnerCount.value = selectedRecipeCount
            }
        }
    }

    //Implementasi Sleep API
    val sleepSegments: LiveData<List<SleepSegmentEventEntity>> = repository.getAllSleepSegments()
    private val _isSubscribed = MutableLiveData<Boolean>()
    val isSubscribed: LiveData<Boolean> get() = _isSubscribed

    fun toggleSleepDataSubscription(pendingIntent: PendingIntent) {
        if (_isSubscribed.value == true) {
            repository.unsubscribeToSleepData(pendingIntent)
            Log.d("SleepDataSubscription", "Unsubscribed.")
            _isSubscribed.value = false
        } else {
            repository.subscribeToSleepData(pendingIntent)
            Log.d("SleepDataSubscription", "Subscribed.")
            _isSubscribed.value = true
        }
    }

    //Untuk nampilin nama dan profpic
    private val _userId = MutableLiveData<Int?>()
    val userId: LiveData<Int?> = _userId

    private val _profilePicture = MutableLiveData<String?>()
    val profilePicture: LiveData<String?> = _profilePicture

    private val _userName = MutableLiveData<String?>()
    val userName: LiveData<String?> = _userName

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

    fun getUserNameById(userId: Int) {
        viewModelScope.launch {
            val userName = repository.getUserNameById(userId)
            _userName.value = userName
        }

    }


}