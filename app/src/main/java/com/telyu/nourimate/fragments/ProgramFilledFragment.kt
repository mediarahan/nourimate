package com.telyu.nourimate.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.telyu.nourimate.R
import com.telyu.nourimate.data.local.models.WeightEntry
import com.telyu.nourimate.data.local.models.WeightTrack
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

        setupMealHistory()                          // binding buat buka fragment meal history
        setupMealtimeCalories()                  // binding total kalori yang dikonsumsi
        setupInputCurrentWeight()             // setup input weight, gak ngapa2in kalau gak di klik
        setupCurrentAndStartWeight()

        setupWeightEntryObservation()
        setupInputCurrentWeightButton()

        return binding.root
    }

    //========== Untuk setting tampilan history makanan ==========

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
    private var isStartingWeightSet = false

    private fun setupCurrentAndStartWeight() {
        viewModel.latestWeightEntry.observe(viewLifecycleOwner) { latestWeightEntryWeight ->
            binding.textViewCurrentWeightMW.text = latestWeightEntryWeight.toString()
            if (!isStartingWeightSet) {
                binding.textViewStartingWeightMW.text = latestWeightEntryWeight.toString()
                isStartingWeightSet = true
            }
        }
    }


    private fun setupInputCurrentWeight() {
        binding.iconeditweightMWImageView.setOnClickListener {
            val editText = EditText(context).apply {
                inputType = InputType.TYPE_CLASS_NUMBER
                setText(binding.textViewCurrentWeightMW.text.toString())
            }

            AlertDialog.Builder(requireContext()).apply {
                setTitle("Update Current Weight")
                setMessage("Enter the new weight:")
                setView(editText)
                setPositiveButton("OK") { dialog, _ ->
                    val newWeight = editText.text.toString()
                    if (newWeight.isNotEmpty()) {
                        binding.textViewCurrentWeightMW.text = newWeight
                    }
                    dialog.dismiss()
                }
                setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }
                show()
            }
        }
    }

    private fun setupInputCurrentWeightButton() {
        binding.buttonInputWeight.setOnClickListener {
            val weightText = binding.textViewCurrentWeightMW.text.toString()
            val weight = weightText.toInt()

            //date next week
         //   val midnightNextWeek = Converters().dateFromTimestamp(GeneralUtil.calculateNextWeekMidnight())
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
                            it.startWeight, weight, midnightNextWeek, it.userId)
                        viewModel.insertWeightTrack(weightTrack)

                        //insert to weight entry for graph view
                        val weightEntry  = WeightEntry(0, weight, today, it.userId)
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
                dateList.add(Converters().formatDateToString(entry.date))
            }
            binding.weightChart.setWeights(weightList)
            binding.weightChart.setDates(dateList)
        }
    }

//    private fun setupChangeRecipe() {
//        binding.changeRecipeButton.setOnClickListener {
//            viewModel.changeRecommendationFromConsumedToExpired()
//            Toast.makeText(context, "Recipes Modified", Toast.LENGTH_SHORT).show()
//        }
//    }


}