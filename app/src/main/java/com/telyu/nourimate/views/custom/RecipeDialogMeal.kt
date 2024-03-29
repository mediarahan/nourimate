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
import com.telyu.nourimate.adapter.DialogRecipeAdapter
import com.telyu.nourimate.databinding.PopupLayoutMealBinding
import com.telyu.nourimate.viewmodels.RecipeViewModel
import com.telyu.nourimate.viewmodels.ViewModelFactory
import com.telyu.nourimate.views.custom.RecipeDialogMealTutorial

@Suppress("DEPRECATION")
class RecipeDialogMeal : DialogFragment() {

    private lateinit var binding: PopupLayoutMealBinding

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
        binding = PopupLayoutMealBinding.inflate(inflater, container, false)

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //set dialog width
        setWidthPercent(85)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var selectedMeal: Int? = null

        val args = arguments
        if (args != null) {
            selectedMeal = args.getInt("selectedMeal")
            viewModel.getRecipeByMealTypeAndSelectedRecommendation(selectedMeal)
            Log.d("RecipeDialogMeal", "Selected Meal: $selectedMeal") // Logging selectedMeal
        }

        if (selectedMeal != null) {
            setMealType(selectedMeal)
            getRecipeCountByMealType(selectedMeal)
        }

        val recipeAdapter = DialogRecipeAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.recipeRecyclerView)
        if (recyclerView != null) {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = recipeAdapter

            viewModel.recipeListSelected.observe(viewLifecycleOwner) { recipes ->
                recipeAdapter.submitList(recipes)
                Log.d(
                    "RecipeDialogMeal",
                    "Recipe List Selected: $recipes"
                )
            }
        }

        val mealTutorialButton = view.findViewById<View>(R.id.selectMealBreakfastButton)
        mealTutorialButton?.setOnClickListener {
            if (selectedMeal != null) {
                showThirdDialog(selectedMeal)
            }
        }
    }

    private fun getRecipeCountByMealType(mealType: Int) {
        viewModel.getSelectedRecipeCountByMealType(mealType)

        viewModel.selectedRecipeCountByMealType.observe(viewLifecycleOwner) { count ->
            binding.textViewBasketMeal.text = count.toString()
        }
    }

    private fun setMealType(mealType: Int) {
        if (mealType == 1) {
            binding.popupTitleMeal.text = "Breakfast"
        } else if (mealType == 2) {
            binding.popupTitleMeal.text = "Lunch"
        } else if (mealType == 3) {
            binding.popupTitleMeal.text = "Dinner"
        }
    }

    private fun showThirdDialog(selectedMeal: Int) {
        val bundle = Bundle()
        bundle.putInt("selectedMeal", selectedMeal)

        val recipeDialogMeal = RecipeDialogMealTutorial(R.layout.popup_layout_meal_tutorial)
        recipeDialogMeal.arguments = bundle
        recipeDialogMeal.show(parentFragmentManager, "RecipeDialogMeal")
    }
}
// Example function to modify dialog views
//    private  fun deleteMeal() {
//        val recipeDialogAdapter = DialogRecipeAdapter {recipe ->
//            viewModel.updateRecommendationSelection()
//        }
//    }

