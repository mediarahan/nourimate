package com.telyu.nourimate.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.telyu.nourimate.R
import com.telyu.nourimate.adapter.history.RecipeHistoryAdapter
import com.telyu.nourimate.databinding.FragmentMealHistoryBinding
import com.telyu.nourimate.utils.Converters
import com.telyu.nourimate.viewmodels.ProgramViewModel
import com.telyu.nourimate.viewmodels.ViewModelFactory

class MealHistoryFragment : Fragment() {

    private lateinit var binding: FragmentMealHistoryBinding
    private val adapter = RecipeHistoryAdapter()

    private val viewModel by activityViewModels<ProgramViewModel> {
        ViewModelFactory.getInstance(requireActivity().applicationContext)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMealHistoryBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setStatusBarColor(ContextCompat.getColor(requireContext(), R.color.color16))

        setupProgramNameAndDate()
        setupRecyclerView()

        binding.backButton.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
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

    private fun setupRecyclerView() {
        binding.recommendationRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recommendationRecyclerView.adapter = adapter

        viewModel.recipeHistoryData.observe(viewLifecycleOwner) { recipeHistory ->
            adapter.submitList(recipeHistory)
        }
    }

    private fun setupProgramNameAndDate() {
        viewModel.selectedMealTime.observe(viewLifecycleOwner) { selectedMealtime ->
            binding.ProgramTitle.text = when (selectedMealtime) {
                1 -> "Your Breakfast"
                2 -> "Your Lunch"
                3 -> "Your Dinner"
                else -> "invalid"
            }
        }

        viewModel.userWeightTrack.observe(viewLifecycleOwner) { userProgramDetail ->
            val startDate = Converters().formatDate(userProgramDetail.startDate)
            val endDate = Converters().formatDate(userProgramDetail.endDate)
            binding.StartDateTextView.text = "$startDate"
            binding.EndDateTextView.text = "$endDate"
        }
    }


}