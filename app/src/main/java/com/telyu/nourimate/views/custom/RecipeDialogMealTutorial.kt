package com.telyu.nourimate.views.custom

import DialogUtils.setWidthPercent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.telyu.nourimate.R
import com.telyu.nourimate.adapter.DialogRecipeTutorialAdapter
import com.telyu.nourimate.databinding.PopupLayoutMealBinding
import com.telyu.nourimate.databinding.PopupLayoutMealTutorialBinding
import com.telyu.nourimate.viewmodels.RecipeViewModel
import com.telyu.nourimate.viewmodels.ViewModelFactory

@Suppress("DEPRECATION")
class RecipeDialogMealTutorial(val layoutResId: Int) : DialogFragment() {

    private lateinit var binding: PopupLayoutMealTutorialBinding

    private val viewModel by viewModels<RecipeViewModel> {
        ViewModelFactory.getInstance(
            requireContext().applicationContext
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PopupLayoutMealTutorialBinding.inflate(inflater, container, false)

        // Adjust dialog properties
        val layoutParams = dialog?.window?.attributes
        layoutParams?.apply {
            width = WindowManager.LayoutParams.WRAP_CONTENT
            height = WindowManager.LayoutParams.WRAP_CONTENT
            gravity = Gravity.CENTER
        }

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.attributes = layoutParams

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Retrieve 'selectedMeal' from arguments
        val selectedMeal = arguments?.getInt("selectedMeal") ?: return  // Return early if null

        setupRecyclerView(selectedMeal)
        setMealType(selectedMeal)
        getRecipeCountByMealType(selectedMeal)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //set dialog width
        setWidthPercent(85)
    }


    private fun setupRecyclerView(selectedMeal: Int) {
        val recipeAdapter = DialogRecipeTutorialAdapter()
        binding.recipeTutorialRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = recipeAdapter
        }

        // Observing selected recommendation IDs and fetching recipes based on them
        viewModel.getAllConfirmedRecommendationIdsByMealId(selectedMeal).observe(viewLifecycleOwner) { recommendationIds ->
            viewModel.getSelectedRecipesByRecommendationIds(recommendationIds).observe(viewLifecycleOwner) { recipes ->
                recipeAdapter.submitList(recipes)
            }
        }
    }

    private fun getRecipeCountByMealType(mealType: Int) {
        viewModel.getSelectedRecipeCountByMealType(mealType).observe(viewLifecycleOwner) { count ->
            binding.textViewBasketMealTutorial.text = count.toString()
        }
    }

    private fun setMealType(mealType: Int) {
        if (mealType == 1) {
            binding.popupTitleMealTutorial.text = "Breakfast"
        } else if (mealType == 2) {
            binding.popupTitleMealTutorial.text = "Lunch"
        } else if (mealType == 3) {
            binding.popupTitleMealTutorial.text = "Dinner"
        }
    }
}