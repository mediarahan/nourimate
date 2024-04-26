package com.telyu.nourimate.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.telyu.nourimate.R
import com.telyu.nourimate.databinding.FragmentHomeBinding
import com.telyu.nourimate.databinding.FragmentRecipeBinding
import com.telyu.nourimate.viewmodels.HomeViewModel
import com.telyu.nourimate.viewmodels.RecipeViewModel
import com.telyu.nourimate.viewmodels.ViewModelFactory

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel by viewModels<HomeViewModel> {
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

        viewModel.weightMessage.observe(viewLifecycleOwner) { weightMessage ->
            binding.weightMessageTextView.text = weightMessage
        }

        viewModel.userProfilePhoto.observe(viewLifecycleOwner) { userProfilePhoto ->
            binding.profileImageView.setImageBitmap(userProfilePhoto)
        }

        viewModel.sleepTime.observe(viewLifecycleOwner) { sleepTime ->
            binding.timeInSleep.text = getString(R.string.sleep_time_format, sleepTime)
        }

        viewModel.wakeUpTime.observe(viewLifecycleOwner) { wakeUpTime ->
            binding.wakeUpTime.text = getString(R.string.wake_up_time_format, wakeUpTime)
        }

        viewModel.consumedCalories.observe(viewLifecycleOwner) { calories ->
            updateUI(calories)
        }
        viewModel.currentGlass.observe(viewLifecycleOwner) { glass ->
            updateGlassesUI(glass)
        }
        // Call updateGlassesUI initially to set the '+' on the first glass
        updateGlassesUI(viewModel.currentGlass.value ?: 0)

        observeMealCalories()
        setupMealCalories()
        setupNutritionNeeds()
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

    private fun updateProgressBar(consumedCalories: Int, maxCalories: Int) {
        binding.linearProgressCaloriesIndicator.max = maxCalories
        binding.linearProgressCaloriesIndicator.progress = consumedCalories
    }

    //Today's Meal Related Functions
    //Part 1

    private fun observeMealCalories() {
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

    //Today's Meal Related Functions
    //Part 2
    private fun setupNutritionNeeds() {


        viewModel.getNutritionSums()
        //viewModel.calculateNutritionPercentage()
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
}
