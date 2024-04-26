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
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.telyu.nourimate.R
import com.telyu.nourimate.adapter.DialogRecipeAdapter
import com.telyu.nourimate.adapter.RecipeAdapter
import com.telyu.nourimate.data.local.models.Recipe
import com.telyu.nourimate.databinding.PopupLayoutMealBinding
import com.telyu.nourimate.viewmodels.RecipeViewModel
import com.telyu.nourimate.viewmodels.ViewModelFactory
import com.telyu.nourimate.views.custom.RecipeDialogMealTutorial
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Suppress("DEPRECATION")
class RecipeDialogMeal : DialogFragment(), DialogRecipeAdapter.DialogOnAddClickListener {

    private lateinit var binding: PopupLayoutMealBinding
    private val recipeAdapter = DialogRecipeAdapter(this)
    var selectedMeal: Int = -1

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

        val args = arguments

        val recyclerView = view.findViewById<RecyclerView>(R.id.recipeRecyclerView)
        if (recyclerView != null) {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())

            if (args != null) {
                selectedMeal = args.getInt("selectedMeal")
                recyclerView.adapter = recipeAdapter

                viewModel.getAllSelectedRecommendationIdsByMealId(selectedMeal)
                    .observe(viewLifecycleOwner) { recommendationIds ->
                        viewModel.getSelectedRecipesByRecommendationIds(recommendationIds)
                            .observe(viewLifecycleOwner) { recipes ->
                                recipeAdapter.submitList(recipes)
                            }
                    }

                setMealType(selectedMeal)
                getRecipeCountByMealType(selectedMeal)
            }
        }

        val mealTutorialButton = view.findViewById<View>(R.id.selectMealBreakfastButton)
        mealTutorialButton?.setOnClickListener {
            showThirdDialog(selectedMeal)
        }

        //observeSelectedRecommendation()
    }

    override fun dialogOnAddClick(recipe: Recipe) {
        CoroutineScope(Dispatchers.Main).launch {
            val recommendation =
                viewModel.getRecommendationByRecipeIdAndMealType(recipe.recipeId, selectedMeal)
            recommendation?.let { rec ->
                rec.isSelected = !rec.isSelected
                viewModel.selectRecommendation(rec)
                Toast.makeText(requireContext(), "Recommendation updated", Toast.LENGTH_SHORT).show()
            }
        }
    }

//    private fun observeSelectedRecommendation() {
//        viewModel.recommendation.observe(viewLifecycleOwner) { recommendation ->
//            if (recommendation != null) {
//                recommendation.isSelected = false
//                viewModel.selectRecommendation(recommendation)
//                Toast.makeText(requireContext(), "Recommendation Removed", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }

    private fun getRecipeCountByMealType(mealType: Int) {
        viewModel.getSelectedRecipeCountByMealType(mealType).observe(viewLifecycleOwner) { count ->
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

