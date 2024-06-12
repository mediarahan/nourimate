package com.telyu.nourimate.fragments

import android.os.Bundle
import android.util.Log
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
import com.telyu.nourimate.adapter.recipe.DialogRecipeTutorialAdapter
import com.telyu.nourimate.adapter.recipe.RecipeAdapter
import com.telyu.nourimate.databinding.FragmentMealHistoryBinding
import com.telyu.nourimate.databinding.LayoutRecipeHistoryHomeBinding
import com.telyu.nourimate.utils.Converters
import com.telyu.nourimate.viewmodels.HomeViewModel
import com.telyu.nourimate.viewmodels.ProgramViewModel
import com.telyu.nourimate.viewmodels.ViewModelFactory

class HomeMealHistoryFragment : Fragment() {

    private lateinit var binding: LayoutRecipeHistoryHomeBinding
    private val adapter = DialogRecipeTutorialAdapter()

    private val viewModel by activityViewModels<HomeViewModel> {
        ViewModelFactory.getInstance(requireActivity().applicationContext)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = LayoutRecipeHistoryHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setStatusBarColor(ContextCompat.getColor(requireContext(), R.color.color19))

        setupRecyclerView()

        binding.backIcon.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    private fun setupRecyclerView() {
        Log.d("HomeMealHistoryFragment", "HUHAHHH")
        binding.recommendationRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recommendationRecyclerView.adapter = adapter

        viewModel.selectedMealTime.observe(viewLifecycleOwner) { mealTime ->
            Log.d("HomeMealHistoryFragment", "mealTime: $mealTime")
            viewModel.getSelectedRecipesByMealType(mealTime)
                .observe(viewLifecycleOwner) { recipes ->
                    adapter.submitList(recipes)
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