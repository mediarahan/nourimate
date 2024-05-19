package com.telyu.nourimate.custom

import DialogUtils.setWidthPercent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.telyu.nourimate.databinding.PopupLayoutBinding
import com.telyu.nourimate.viewmodels.RecipeViewModel
import com.telyu.nourimate.viewmodels.ViewModelFactory

@Suppress("DEPRECATION")
class RecipeDialog : DialogFragment() {

    private lateinit var binding: PopupLayoutBinding

    private val viewModel by viewModels<RecipeViewModel> {
        ViewModelFactory.getInstance(
            requireContext().applicationContext
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PopupLayoutBinding.inflate(inflater, container, false)

        // Adjust dialog properties
        val layoutParams = dialog?.window?.attributes
        layoutParams?.apply {
            width = WindowManager.LayoutParams.MATCH_PARENT
            height = WindowManager.LayoutParams.MATCH_PARENT
            gravity = Gravity.CENTER
        }

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.attributes = layoutParams

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getRecipeCount()
        setupMealButtons()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //set dialog width
        setWidthPercent(85)
    }

    private fun getRecipeCount() {
        viewModel.getSelectedRecipeCount().observe(viewLifecycleOwner) { count ->
            binding.textViewBasket.text = count.toString()
        }
    }

    // Example function to modify dialog views
    private fun setupMealButtons() {
        var selectedMeal: Int

        binding.breakfastTextView.setOnClickListener {
            selectedMeal = 1
            showSecondDialog(selectedMeal)
        }
        binding.lunchTextView.setOnClickListener {
            selectedMeal = 2
            showSecondDialog(selectedMeal)
        }
        binding.dinnerTextView.setOnClickListener {
            selectedMeal = 3
            showSecondDialog(selectedMeal)
        }
    }

    private fun showSecondDialog(selectedMeal: Int) {
        val bundle = Bundle()
        bundle.putInt("selectedMeal", selectedMeal)

        val recipeDialogMeal = RecipeDialogMeal()
        recipeDialogMeal.arguments = bundle
        recipeDialogMeal.show(parentFragmentManager, "com.telyu.nourimate.custom.RecipeDialogMeal")
    }

}
