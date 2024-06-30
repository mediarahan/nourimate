package com.telyu.nourimate.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.telyu.nourimate.R
import com.telyu.nourimate.custom.WeightRulerView
import com.telyu.nourimate.data.local.models.WeightEntry
import com.telyu.nourimate.data.local.models.WeightTrack
import com.telyu.nourimate.databinding.DialogWeightPickerBinding
import com.telyu.nourimate.databinding.FragmentProgramFilledBinding
import com.telyu.nourimate.utils.Converters
import com.telyu.nourimate.utils.GeneralUtil
import com.telyu.nourimate.viewmodels.ProgramViewModel
import com.telyu.nourimate.viewmodels.ViewModelFactory

class ProgramFilledFragment : Fragment() {

    private lateinit var binding: FragmentProgramFilledBinding
    private val viewModel by activityViewModels<ProgramViewModel> {
        ViewModelFactory.getInstance(requireActivity().applicationContext)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProgramFilledBinding.inflate(inflater, container, false)

        return binding.root
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

    //========== Untuk setting tampilan history makanan ==========

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setStatusBarColor(ContextCompat.getColor(requireContext(), R.color.color55))
        viewModel.fetchCooldownTime()
        setupWeeklyWeightInput()

        setupMealHistory()                          // binding buat buka fragment meal history
        setupMealtimeCalories()                  // binding total kalori yang dikonsumsi
        setupInputCurrentWeight()             // setup input weight, gak ngapa2in kalau gak di klik
        setupCurrentAndStartWeight()

        setupWeightEntryObservation()
        setupInputCurrentWeightButton()

        setupMealHistoryObservationForBackend(requireContext())
    }

    private fun setupMealHistoryObservationForBackend(context: Context) {
        viewModel.userMealHistory.observe(viewLifecycleOwner) { recipeHistories ->
            Log.d("ProgramFilledFragment", "setupMealHistoryObservationForBackend: $recipeHistories")
            for (recipeHistory in recipeHistories) {
                viewModel.scheduleMealHistoryUpdate(recipeHistory.recipeId, recipeHistory.consumedDate, context)
            }
        }
    }

    private fun setupMealHistory() {
        binding.apply {
            setMealClickListener(rectanglemealprogrambreakfast, 1)
            setMealClickListener(rectanglemealprogramlunch, 2)
            setMealClickListener(rectanglemealprogramdinner, 3)
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
            replace(R.id.fragmentContainer, MealHistoryFragment())
            addToBackStack(null)
            commit()
        }
    }

    //=============== Untuk setup kalori per mealtime ===============
    private fun setupMealtimeCalories() {
        viewModel.getTotalCaloriesByMealTypeHistory(1)
        viewModel.getTotalCaloriesByMealTypeHistory(2)
        viewModel.getTotalCaloriesByMealTypeHistory(3)

        viewModel.breakfastCalories.observe(viewLifecycleOwner) { calories ->
            binding.TextViewCaloriesbreakfast.text = "Calories: " + calories.toString()
        }
        viewModel.lunchCalories.observe(viewLifecycleOwner) { calories ->
            binding.TextViewCalorieslunch.text = "Calories: " + calories.toString()
        }
        viewModel.dinnerCalories.observe(viewLifecycleOwner) { calories ->
            binding.TextViewCaloriesdinner.text = "Calories: " + calories.toString()
        }
    }

    //===== Setting initial StartWeight dan CurrentWeight =====

    private fun setupCurrentAndStartWeight() {
        setupStartingWeight()

        viewModel.userId.observe(viewLifecycleOwner) { id ->
            viewModel.setLatestWeightEntryWeight(id)
        }

        viewModel.latestWeightEntryWeight.observe(viewLifecycleOwner) { weight ->
            binding.textViewCurrentWeightMW.text = weight.toString()
        }
    }

    private fun setupStartingWeight() {
        viewModel.userWeightDetail.observe(viewLifecycleOwner) { currentWeight ->
            binding.textViewStartingWeightMW.text = currentWeight?.toInt().toString()
        }
    }

    private fun setupInputCurrentWeight() {
        binding.iconeditweightMWImageView.setOnClickListener {
            showWeightRulerPickerDialog(0f)
        }
    }

    private fun setupInputCurrentWeightButton() {
        binding.buttonInputWeight.setOnClickListener {
            val weightText = binding.textViewCurrentWeightMW.text.toString()
            val weight = weightText.toInt()

            //date next week
//               val midnightNextWeek = Converters().dateFromTimestamp(GeneralUtil.calculateNextWeekMidnight())
            val midnightNextWeek = Converters().dateFromTimestamp(GeneralUtil.calculateOneMinuteLaterTimestamp())
            val today = Converters().dateFromTimestamp(System.currentTimeMillis())

            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Confirm")
            builder.setMessage("Are you sure you want to add this weight?")

            builder.setPositiveButton("Yes") { _, _ ->
                if (weight >= 300) {
                    showWeightLimitDialog()
                } else {
                    viewModel.userWeightTrack.observe(viewLifecycleOwner) {
                        val weightTrack = WeightTrack(
                            it.id, it.ongoingProgram, it.startDate, it.endDate,
                            it.startWeight, weight, midnightNextWeek, it.userId
                        )
                        viewModel.insertWeightTrack(weightTrack)
                        Log.d("Step 1", "WeightTrack: $weightTrack")
                        //TODO: insert to detail (update weight)
                        viewModel.userDetails.observe(viewLifecycleOwner) { detail ->
                            viewModel.insertDetail(
                                detail.dob,
                                detail.height,
                                weightTrack.endWeight, //new endweight
                                detail.waistSize,
                                detail.gender,
                                detail.allergen,
                                detail.disease,
                                detail.bmi,
                            )

                            //TODO: insert to backend detail
                            viewModel.insertDetailBackend(
                                Converters().formatDateToString(detail.dob),
                                detail.height,
                                weightTrack.endWeight,
                                detail.waistSize,
                                detail.gender,
                                detail.allergen,
                                detail.disease,
                            )
                        }



                        //insert to weight entry for graph view
                        val weightEntry = WeightEntry(0, weight, today, it.userId)
                        viewModel.insertWeightEntry(weightEntry)
                    }
                }
            }

            builder.setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }

            val dialog = builder.create()
            dialog.show()
        }
    }

    private fun showWeightLimitDialog() {
        val limitBuilder = AlertDialog.Builder(requireContext())
        limitBuilder.setTitle("Error")
        limitBuilder.setMessage("Weight must be less than 300.")
        limitBuilder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        val limitDialog = limitBuilder.create()
        limitDialog.show()
    }

    //===== For Graph =====
    private fun setupWeightEntryObservation() {
        viewModel.weightEntries.observe(viewLifecycleOwner) { weightEntries ->
            val weightList = mutableListOf<Int>()
            val dateList = mutableListOf<String>()

            weightEntries.forEach { entry ->
                weightList.add(entry.weight)
                dateList.add(Converters().formatDateToStringShort(entry.date))
            }
            binding.weightChart.setWeights(weightList)
            binding.weightChart.setDates(dateList)
        }
    }

    //=============== Setup Input Weight 1 minggu sekali ===============
    private fun setupWeeklyWeightInput() {

        viewModel.remainingTime.observe(viewLifecycleOwner) { remaining ->
            if (remaining > 0) {
                startCountdown(remaining)
            } else {
                binding.apply {
                    buttonInputWeight.isEnabled = true
                    buttonInputWeight.alpha = 1f
                    timerRemainingInputCurrentWeight.text = "*Your weekly weight input is available."
                }
            }
        }
    }

    private fun startCountdown(timeInMillis: Long) {
        binding.apply {
            val timer = object : CountDownTimer(timeInMillis, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    buttonInputWeight.isEnabled = false
                    buttonInputWeight.alpha = 0.3f
                    timerRemainingInputCurrentWeight.text = "*Next weight input happens in: ${GeneralUtil.formatTime(millisUntilFinished)}"
                }

                override fun onFinish() {
                    buttonInputWeight.isEnabled = true
                    buttonInputWeight.alpha = 1f
                    timerRemainingInputCurrentWeight.text = "*Your weekly weight input is available."
                }
            }
            timer.start()
        }

    }

    //========== Setup WeightPicker untuk Input CurrentWeight ==========

    private fun showWeightRulerPickerDialog(initialSelectedValue: Float) {
        val dialog = Dialog(requireContext())
        val dialogBinding = DialogWeightPickerBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)

        // Mengatur agar latar dialog transparan
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        // Mendapatkan ukuran layar
        val displayMetrics = resources.displayMetrics
        val screenWidth = displayMetrics.widthPixels
        // Mengatur lebar dialog menjadi 85% dari lebar layar
        val dialogWidth = (screenWidth * 0.85).toInt()
        dialog.window?.setLayout(dialogWidth, ViewGroup.LayoutParams.WRAP_CONTENT)

        // Set initial selected value
        dialogBinding.weightRulerView.selectedValue = initialSelectedValue
        dialogBinding.textViewNumber2.text = initialSelectedValue.toInt().toString()

        // Set listener untuk CurvedRulerView
        dialogBinding.weightRulerView.listener = object : WeightRulerView.OnValueChangeListener {
            override fun onValueChanged(value: Float) {
                // Update selected value
                dialogBinding.textViewNumber2.text = value.toInt().toString()
                // Update EditText in real-time
                binding.textViewCurrentWeightMW.text = String.format("%d", value.toInt())
            }
        }

        // Set action for 'Done' button
        dialogBinding.buttonDone.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

//    private fun setupChangeRecipe() {
//        binding.changeRecipeButton.setOnClickListener {
//            viewModel.changeRecommendationFromConsumedToExpired()
//            Toast.makeText(context, "Recipes Modified", Toast.LENGTH_SHORT).show()
//        }
//    }


}