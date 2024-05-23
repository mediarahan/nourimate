package com.telyu.nourimate.fragments

import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.telyu.nourimate.R
import com.telyu.nourimate.data.local.models.NutritionSum
import com.telyu.nourimate.data.local.models.SleepSegmentEventEntity
import com.telyu.nourimate.databinding.FragmentHomeBinding
import com.telyu.nourimate.utils.GeneralUtil.calculateDuration
import com.telyu.nourimate.utils.SleepReceiver
import com.telyu.nourimate.viewmodels.HomeViewModel
import com.telyu.nourimate.viewmodels.ViewModelFactory
import android.Manifest
import android.net.Uri
import android.util.Log
import androidx.fragment.app.activityViewModels
import com.telyu.nourimate.utils.GeneralUtil


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
        setupClickListeners()
        subscribeUi()
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

        viewModel.sleepTime.observe(viewLifecycleOwner) { sleepTime ->
            binding.timeInSleep.text = getString(R.string.sleep_time_format, sleepTime)
        }

        viewModel.wakeUpTime.observe(viewLifecycleOwner) { wakeUpTime ->
            binding.wakeUpTime.text = getString(R.string.wake_up_time_format, wakeUpTime)
        }

        viewModel.currentGlass.observe(viewLifecycleOwner) { glass ->
            updateGlassesUI(glass)
        }
        // Call updateGlassesUI initially to set the '+' on the first glass
        updateGlassesUI(viewModel.currentGlass.value ?: 0)

        bindBMI()
        bindIdealWeightAndSize()
        observeMealCalories()
        setupMealCalories()
        setupNutritionNeeds()
        setupMealCount()
        viewModel.getNutritionSums()
        displayProfpic()
        setupMealHistory()
        //observeSleep()
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
                in 0.0..18.5 -> {
                    binding.speedTextView.text = "Underweight"
                    binding.speedTextView.setBackgroundResource(R.drawable.rectangle_light_green)
                }
                in 18.6..24.9 -> {
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
            }
        }

    }

    private fun bindIdealWeightAndSize() {
        viewModel.weightValues.observe(viewLifecycleOwner) {values ->
            binding.ageTextView.text = values.first.toString() + " kg" + "\nIdeal Weight"
            binding.heightTextView.text = values.second.toString() + "\nWaist Size"
        }
    }


    private fun calculatePercentage(consumed: Int, total: Int) = (consumed * 100) / total

    private fun updateUI(calories: Int) {
        binding.textViewTotalCalories.text = getString(R.string.consumed_calories, calories)
        binding.linearProgressCaloriesIndicator.progress =
            calculatePercentage(calories, 2000) // Assuming 2000 is the total calories goal
        binding.tvCaloriesPercentage.text = getString(
            R.string.calories_percentage,
            binding.linearProgressCaloriesIndicator.progress
        )
    }

    //Today's Meal Related Functions
    //Part 1

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



    //========== Sleep API Related ==========
//    private fun observeSleep() {
//        viewModel.sleepSegments.observe(viewLifecycleOwner) { segments ->
//            displaySleepSegments(segments)
//        }
//
//        viewModel.isSubscribed.observe(viewLifecycleOwner) { isSubscribed ->
//            // Update button text or UI elements based on subscription status
//            updateSubscriptionUI(isSubscribed)
//        }
//
//        binding.sleepSubscribeButton.setOnClickListener {
//            if (ContextCompat.checkSelfPermission(
//                    requireContext(),
//                    Manifest.permission.ACTIVITY_RECOGNITION
//                ) != PackageManager.PERMISSION_GRANTED
//            ) {
//                requestActivityRecognitionPermission()
//            } else {
//                viewModel.toggleSleepDataSubscription(createPendingIntent())
//            }
//        }
//    }

    private fun requestActivityRecognitionPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.ACTIVITY_RECOGNITION),
            REQUEST_CODE_ACTIVITY_RECOGNITION // Make sure this constant is defined in your fragment
        )
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_ACTIVITY_RECOGNITION) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                viewModel.toggleSleepDataSubscription(createPendingIntent())
            } else {
                Toast.makeText(context, "Permission denied. Cannot subscribe to sleep data.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun createPendingIntent(): PendingIntent {
        val intent = Intent(context, SleepReceiver::class.java)
        return PendingIntent.getBroadcast(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    private fun displaySleepSegments(segments: List<SleepSegmentEventEntity>) {
        val formattedSegments = segments.joinToString("\n\n") {
            val duration = calculateDuration(it.startTimeMillis, it.endTimeMillis)
            duration
        }

        binding.timeInSleep.text = formattedSegments
    }

//    private fun updateSubscriptionUI(isSubscribed: Boolean) {
//        binding.sleepSubscribeButton.text = if (isSubscribed) "Unsubscribe" else "Subscribe"
//    }


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

    private fun setupClickListeners() {
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
                    viewModel.addWater(250)  // amount of water in one glass
                    viewModel.setCurrentGlass(index + 1)
                }
            }
        }
    }

    private fun subscribeUi() {
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
            binding.water1,
            binding.water2,
            binding.water3,
            binding.water4,
            binding.water5,
            binding.water6,
            binding.water7,
            binding.water8,
        )

        for (i in glassList.indices) {
            // The first glass should not be blue or selected until clicked
            if (i < currentGlass) {
                // User has clicked this glass, it should be filled (blue)
                glassList[i].isSelected = true
            } else {
                // Glass has not been clicked yet or is the first to be clicked
                glassList[i].isSelected = false
            }

            // Set the '+' icon on the first unclicked glass
            if (i == currentGlass) {
                glassList[i].setImageResource(R.drawable.addwater)
            } else {
                glassList[i].setImageResource(0)  // Remove any drawable
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

    companion object {
        private const val REQUEST_CODE_ACTIVITY_RECOGNITION = 1
    }
}
