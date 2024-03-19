package com.telyu.nourimate.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.telyu.nourimate.R
import com.telyu.nourimate.databinding.FragmentHomeBinding
import com.telyu.nourimate.viewmodels.HomeViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
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

    }

    private fun updateUI(calories: Int) {
        binding.textViewTotalCalories.text = getString(R.string.consumed_calories, calories)
        binding.linearProgressCaloriesIndicator.progress = calculatePercentage(calories, 2000) // Assuming 2000 is the total calories goal
        binding.tvCaloriesPercentage.text = getString(R.string.calories_percentage, binding.linearProgressCaloriesIndicator.progress)
    }

    private fun calculatePercentage(consumed: Int, total: Int) = (consumed * 100) / total

    private fun updateProgressBar(consumedCalories: Int, maxCalories: Int) {
        binding.linearProgressCaloriesIndicator.max = maxCalories
        binding.linearProgressCaloriesIndicator.progress = consumedCalories
    }

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




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setStatusBarColor(color: Int) {
        val window = requireActivity().window
        val insetsController = WindowInsetsControllerCompat(window, window.decorView)

        insetsController.isAppearanceLightStatusBars = true // Set true or false depending on the status bar icons' color
        insetsController.isAppearanceLightNavigationBars = true // Set true or false depending on the navigation bar icons' color

        window.statusBarColor = color
    }
}
