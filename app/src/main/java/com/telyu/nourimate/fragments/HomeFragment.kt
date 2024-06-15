package com.telyu.nourimate.fragments

import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.telyu.nourimate.R
import com.telyu.nourimate.data.local.models.NutritionSum
import com.telyu.nourimate.databinding.FragmentHomeBinding
import com.telyu.nourimate.viewmodels.HomeViewModel
import com.telyu.nourimate.viewmodels.ViewModelFactory
import java.util.Calendar


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel by activityViewModels<HomeViewModel> {
        ViewModelFactory.getInstance(
            requireContext().applicationContext
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        setupGlassUI()
        subscribeGlassUI()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set the color of the status bar
        setStatusBarColor(ContextCompat.getColor(requireContext(), R.color.color30))

        binding.semiCircleProgressView.animateProgress(500f)

        // Set observers on ViewModel LiveData
        viewModel.greetingMessage.observe(viewLifecycleOwner) { greeting ->
            binding.greetingTextView.text = greeting
        }

        viewModel.currentGlass.observe(viewLifecycleOwner) { glass ->
            updateGlassesUI(glass)
        }
        // Call updateGlassesUI initially to set the '+' on the first glass
        updateGlassesUI(viewModel.currentGlass.value ?: 0)

        bindIdealWeightAndSize()
        setupBMIListener()
        bindBMI()
        observeMealCalories()
        setupInfoButtonListeners()
        setupMealCalories()
        setupNutritionNeeds()
        setupMealCount()
        viewModel.getNutritionSums()
        displayProfpic()
        setupMealHistory()


    }

    //=============== Kotak UI Pertama ===============
    private fun bindBMI() {
        viewModel.userBMI.observe(viewLifecycleOwner) { bmi ->
            val minBMI = 15f
            val maxBMI = 40f

            val progress = ((bmi - minBMI) / (maxBMI - minBMI)) * 3000f
            binding.semiCircleProgressView.animateProgress(progress)

            val formattedBMI = String.format("%.1f", bmi)
            binding.caloriesBurnedTextView.text = formattedBMI

            when(bmi) {
                in 0.0..18.4 -> {
                    binding.speedTextView.text = "Underweight"
                    binding.speedTextView.setBackgroundResource(R.drawable.rectangle_light_green)
                }
                in 18.5..24.9 -> {
                    binding.speedTextView.text = "Normal"
                    binding.speedTextView.setBackgroundResource(R.drawable.rectangle_green)
                }
                in 25.0..29.9 -> {
                    binding.speedTextView.text = "Overweight"
                    binding.speedTextView.setBackgroundResource(R.drawable.rectangle_orange)
                }
                in 30.0..40.0 -> {
                    binding.speedTextView.text = "Obese"
                    binding.speedTextView.setBackgroundResource(R.drawable.rectangle_red)
                }
                else -> {
                    binding.speedTextView.text = "Error"
                    binding.speedTextView.setBackgroundResource(R.drawable.rectangle_red)
                }
            }
        }

    }

    private fun bindIdealWeightAndSize() {
        viewModel.currentValues.observe(viewLifecycleOwner) {values ->
            binding.weightCurrentTextView.text = "Current Weight\n" + values.first.toString() + "kg"
            binding.waistCurrentTextView.text = "Current Waist\n" + values.second.toString() + "cm"
        }

        viewModel.idealValues.observe(viewLifecycleOwner) { values ->
            binding.weightIdealTextView.text = "Ideal Weight\n" + values.first.toString() + "kg"
            binding.waistIdealTextView.text = "Max Waist\n"+ values.second.toString() + "cm"

        }
    }

    private fun setupBMIListener() {
        binding.speedTextView.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainer, UserDetailFragment())
                addToBackStack(null)
                commit()
            }
        }
    }

    //=============== Kotak UI Kedua (Nutrition) ===============

    private fun observeMealCalories() {
        //bind calories of all confirmed meals (breakfast, lunch, dinner) for each mealtime
        viewModel.breakfastCalories.observe(viewLifecycleOwner) { calories ->
            binding.TextViewKcalbreakfast.text = calories.toString() + " / "
        }
        viewModel.lunchCalories.observe(viewLifecycleOwner) { calories ->
            binding.TextViewKcallunch.text = calories.toString() + " / "
        }
        viewModel.dinnerCalories.observe(viewLifecycleOwner) { calories ->
            binding.TextViewKcaldinner.text = calories.toString() + " / "
        }
    }

    private fun setupMealCalories() {
        viewModel.getCaloriesByMealType(1)  // For breakfast
        viewModel.getCaloriesByMealType(2)  // For lunch
        viewModel.getCaloriesByMealType(3)  // For dinner
    }

    private fun setupMealCount() {
        viewModel.getSelectedRecipeCountUsingMealType(1)  // For breakfast
        viewModel.getSelectedRecipeCountUsingMealType(2)  // For lunch
        viewModel.getSelectedRecipeCountUsingMealType(3)  // For dinner
    }

    //Today's Meal Related Functions
    //Part 2
    private fun setupNutritionNeeds() {
        viewModel.nutritionSums.observe(viewLifecycleOwner) { nutritions ->
            updateNutritionDisplay(nutritions)
            setupInfoButtonState(nutritions)
        }

        viewModel.maxNutritionLiveData.observe(viewLifecycleOwner) { maxNutritions ->
            binding.apply {
                textViewGoalCalories.text = "/" + maxNutritions[0].toString()
                textViewGoalProtein.text = "/" + maxNutritions[1].toString()
                textViewGoalFat.text = "/" + maxNutritions[2].toString()
                textViewGoalCarbs.text = "/" + maxNutritions[3].toString()
            }
        }

        //bind each nutrition percentage
        viewModel.nutritionPercentage.observe(viewLifecycleOwner) { percentages ->
            binding.apply {
                tvCaloriesPercentage.text = percentages[0].toString() + "%"
                tvProteinPercentage.text = percentages[1].toString() + "%"
                tvFatPercentage.text = percentages[2].toString() + "%"
                tvCarbsPercentage.text = percentages[3].toString() + "%"

                //bind each nutrition progress bar
                linearProgressCaloriesIndicator.progress = percentages[0]
                linearProgressProteinIndicator.progress = percentages[1]
                linearProgressFatIndicator.progress = percentages[2]
                linearProgressCarbsIndicator.progress = percentages[3]
            }

        }
    }

    private fun updateNutritionDisplay(nutritionSum: NutritionSum) {

        //bind calorie needs (max calorie) per mealtime
        viewModel.caloriesPerMealtime.observe(viewLifecycleOwner) { mealtimeCalories ->
            binding.apply {
                TextViewKcalbreakfastNeeds.text = mealtimeCalories[0].toString() + " kcal"
                TextViewKcallunchNeeds.text = mealtimeCalories[1].toString() + " kcal"
                TextViewKcaldinnerNeeds.text = mealtimeCalories[2].toString() + " kcal"
            }
        }

        //bind nutrition needs
        binding.apply {
            textViewTotalCalories.text = nutritionSum.totalCalories.toInt().toString()
            textViewTotalProtein.text = nutritionSum.totalProtein.toInt().toString()
            textViewTotalFat.text = nutritionSum.totalFat.toInt().toString()
            textViewTotalCarbs.text = nutritionSum.totalCarbs.toInt().toString()
        }

        //display how many recipes is Selected
        viewModel.breakfastCount.observe(viewLifecycleOwner) { selectedRecipeCount ->
            binding.TextViewQuantitybreakfast.text = selectedRecipeCount.toString() + " Foods"
        }
        viewModel.lunchCount.observe(viewLifecycleOwner) { selectedRecipeCount ->
            binding.TextViewQuantitylunch.text = selectedRecipeCount.toString() + " Foods"
        }
        viewModel.dinnerCount.observe(viewLifecycleOwner) { selectedRecipeCount ->
            binding.TextViewQuantitydinner.text = selectedRecipeCount.toString() + " Foods"
        }
    }

    //========== Dialog Informasi Nutrisi ==========
    enum class UserNutritionStatus {
        EXCESS, DEFICIT
    }

    enum class WaterIntakeStatus {
        DEFICIT, EXCESS
    }

    private fun getCurrentMealTime(): String {
        val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        return when (hour) {
            in 6..9 -> "breakfast"
            in 12..14 -> "lunch"
            in 18..20 -> "dinner"
            else -> "invalid"
        }
    }

    private fun checkNutrientStatus(currentNutrientValue: Int, requiredNutrientValue: Int): UserNutritionStatus {
        return when {
            currentNutrientValue < requiredNutrientValue -> UserNutritionStatus.DEFICIT
            else -> UserNutritionStatus.EXCESS
        }
    }

    private fun checkWaterIntakeStatus(consumedWater: Int): WaterIntakeStatus {
        return if (consumedWater < 1500) WaterIntakeStatus.DEFICIT else WaterIntakeStatus.EXCESS
    }


    private var nutrientStatusMap = mutableMapOf<String, UserNutritionStatus>()

    private fun updateButton(imageView: ImageView, nutrientType: String, consumed: Int, required: Int) {
        val status = checkNutrientStatus(consumed, required)
        nutrientStatusMap[nutrientType] = status

        val currentTime = getCurrentMealTime()
        val isCriticalTime = currentTime in listOf("Breakfast", "Lunch", "Dinner")

        val drawableResId = when {
            status == UserNutritionStatus.DEFICIT && isCriticalTime -> R.drawable.infored
            status == UserNutritionStatus.EXCESS -> R.drawable.infored
            status == UserNutritionStatus.DEFICIT -> R.drawable.info
            else -> R.drawable.infogreen
        }

        imageView.setImageResource(drawableResId)
    }


    private fun setupInfoButtonListeners() {
        binding.apply {
            imageInfoCalories.setOnClickListener {
                showNutritionDialog("Calories")
            }
            imageInfoProtein.setOnClickListener {
                showNutritionDialog("Protein")
            }
            imageInfoFat.setOnClickListener {
                showNutritionDialog("Fat")
            }
            imageInfoCarbs.setOnClickListener {
                showNutritionDialog("Carbs")
            }
            infowaterImageView.setOnClickListener {
                showNutritionDialog("Water")
            }
        }
    }

    private fun setupInfoButtonState(nutritionSum: NutritionSum) {
        viewModel.maxNutritionLiveData.observe(viewLifecycleOwner) { maxNutritions ->
            binding.apply {
                updateButton(imageInfoCalories, "Calories", nutritionSum.totalCalories.toInt(), maxNutritions[0])
                updateButton(imageInfoProtein, "Protein", nutritionSum.totalProtein.toInt(), maxNutritions[1])
                updateButton(imageInfoFat, "Fat", nutritionSum.totalFat.toInt(), maxNutritions[2])
                updateButton(imageInfoCarbs, "Carbs", nutritionSum.totalCarbs.toInt(), maxNutritions[3])
            }
        }

        viewModel.waterIntake.observe(viewLifecycleOwner) { waterIntake ->
            val waterStatus = checkWaterIntakeStatus(waterIntake)
            binding.infowaterImageView.setImageResource(
                when (waterStatus) {
                    WaterIntakeStatus.DEFICIT -> R.drawable.infored
                    WaterIntakeStatus.EXCESS -> R.drawable.info
                } )
        }
    }

    private fun showNutritionDialog(nutrientType: String) {
        val status = nutrientStatusMap[nutrientType] ?: UserNutritionStatus.DEFICIT
        val dialog = InfoNutritionDialogFragment.newInstance(nutrientType, status)
        dialog.show(requireActivity().supportFragmentManager, "nutritionInfo")
    }


    //=============== Kotak UI Ketiga (Today's Meal) ===============
    private fun setupMealHistory() {
        binding.apply {
            setMealClickListener(rectanglehomemealbreakfast, 1)
            setMealClickListener(rectanglehomemeallunch, 2)
            setMealClickListener(rectanglehomemealdinner, 3)
        }
    }

    private fun setMealClickListener(view: View, mealTime: Int) {
        view.setOnClickListener {
            viewModel.selectMealTime(mealTime)
            Log.d("ProgramFilledFragment", "setMealClickListener: $mealTime")
            displayMealHistoryFragment()
        }
    }

    private fun displayMealHistoryFragment() {
        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainer, HomeMealHistoryFragment())
            addToBackStack(null)
            commit()
        }
    }

    //Water Related Functions

    private fun setupGlassUI() {
        val glassList = listOf(
            binding.water1,
            binding.water2,
            binding.water3,
            binding.water4,
            binding.water5,
            binding.water6,
            binding.water7,
            binding.water8,
        )

        for ((index, imageView) in glassList.withIndex()) {
            imageView.setOnClickListener {
                if (index == viewModel.currentGlass.value) {
                    viewModel.addWater(250)
                    viewModel.setCurrentGlass(index + 1)
                }
            }
        }
    }

    private fun subscribeGlassUI() {
        viewModel.waterIntake.observe(viewLifecycleOwner) { amount ->
            binding.textWaterIntake.text = "$amount / 2000ml"
            val percentage = (amount.toFloat() / 2000 * 100).toInt()
            binding.textWaterPersentage.text = "$percentage%"
        }
        viewModel.currentGlass.observe(viewLifecycleOwner) { glass ->
            updateGlassesUI(glass)
        }
    }


    private fun updateGlassesUI(currentGlass: Int) {
        val glassList = listOf(
            binding.water1, binding.water2, binding.water3, binding.water4,
            binding.water5, binding.water6, binding.water7, binding.water8,
        )

        for (i in glassList.indices) {
            if (i < currentGlass) {
                glassList[i].isSelected = true
            } else {
                glassList[i].isSelected = false
            }

            if (i == currentGlass && currentGlass < 8) {
                glassList[i].setImageResource(R.drawable.addwater)
            } else {
                glassList[i].setImageResource(0)  // Remove any drawable
            }
        }
    }

    //=============== Display User Related Interfaces ===============

    //Untuk nampilin nama dan profpic
    private fun displayProfpic() {
        viewModel.userEmail.observe(viewLifecycleOwner) { userEmail ->
            userEmail.let {
                viewModel.getUserIdByEmail(it)
            }
        }

        viewModel.userId.observe(viewLifecycleOwner) { userId ->
            if (userId != null) {
                viewModel.getProfpicById(userId)
                viewModel.getUserNameById(userId)
            }
        }

        viewModel.profilePicture.observe(viewLifecycleOwner) { uriString ->
            uriString?.let { uriStr ->
                val uri = Uri.parse(uriStr)
                binding.profileImageView.setImageURI(uri)
            }

            viewModel.userName.observe(viewLifecycleOwner) { userName ->
                binding.usernameTextView.text = userName + "!"
            }

            viewModel.userOngoingProgramAndMessage.observe(viewLifecycleOwner) { programMessages ->
                binding.programMessageTextView.text = programMessages.first
                binding.weightMessageTextView.text = programMessages.second
            }
        }
    }

    private fun setStatusBarColor(color: Int) {
        val window = requireActivity().window
        val insetsController = WindowInsetsControllerCompat(window, window.decorView)

        insetsController.isAppearanceLightStatusBars =
            true // Set true or false depending on the status bar icons' color
        insetsController.isAppearanceLightNavigationBars =
            true // Set true or false depending on the navigation bar icons' color

        window.statusBarColor = color
    }
}
