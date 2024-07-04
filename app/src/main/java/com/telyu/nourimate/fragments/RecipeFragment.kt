package com.telyu.nourimate.fragments

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.telyu.nourimate.R
import com.telyu.nourimate.adapter.recipe.CombinedRecipe
import com.telyu.nourimate.adapter.recipe.RecipeAdapter2
import com.telyu.nourimate.adapter.recipe.RecommendationRecipeAdapter
import com.telyu.nourimate.custom.BottomMarginItemDecoration
import com.telyu.nourimate.custom.RecipeDialog
import com.telyu.nourimate.databinding.FragmentRecipeBinding
import com.telyu.nourimate.viewmodels.RecipeViewModel
import com.telyu.nourimate.viewmodels.ViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//this code needs lots of fixes, but it'll have to do for now.
class RecipeFragment : Fragment() {

    private lateinit var binding: FragmentRecipeBinding
    private val weeklyRecipeAdapter = RecommendationRecipeAdapter()
    private lateinit var breakfastRecipeAdapter: RecipeAdapter2
    private lateinit var lunchRecipeAdapter: RecipeAdapter2
    private lateinit var dinnerRecipeAdapter: RecipeAdapter2

    private var isRecipesForSearchInserted = false

    private val viewModel by viewModels<RecipeViewModel> {
        ViewModelFactory.getInstance(
            requireContext().applicationContext
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecipeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setStatusBarColor(ContextCompat.getColor(requireContext(), R.color.color16))
        createRecommendationsForAllRecipes()

        //supaya ilang dulu
        binding.radioGroupMealtype.visibility = View.GONE
        binding.searchBar.visibility = View.GONE

        setupRecyclerViewAdapter()
        setupRecyclerView()
        displayUserNameAndProfpic()
        setupSearchBarAndSearchView()
        setupDraggableSelectedItem()

        selectMealType()
        selectMealTime()

        //setup choose the food message and selected meal button
        binding.chooseFoodTextView.visibility = View.GONE
        binding.noteTextView.visibility = View.GONE
        binding.selected.visibility = View.GONE

        viewModel.weeklyRecipes.observe(viewLifecycleOwner) {weeklyRecipes ->
            setupFoodMessageVisibility()
            weeklyRecipeAdapter.submitList(weeklyRecipes)
        }

        viewModel.searchResult.observe(viewLifecycleOwner) { combinedRecipes ->
            when (viewModel.mealType.value) {
                1 -> breakfastRecipeAdapter.submitList(combinedRecipes)
                2 -> lunchRecipeAdapter.submitList(combinedRecipes)
                3 -> dinnerRecipeAdapter.submitList(combinedRecipes)
                else -> {  }
            }
        }

        binding.selected.setOnClickListener {
            showPopupMenu()
        }

        val bottomMargin = resources.getDimensionPixelSize(R.dimen.recycler_view_bottom_margin)
        binding.breakfastRecyclerView.addItemDecoration(BottomMarginItemDecoration(bottomMargin))
        binding.lunchRecyclerView.addItemDecoration(BottomMarginItemDecoration(bottomMargin))
        binding.dinnerRecyclerView.addItemDecoration(BottomMarginItemDecoration(bottomMargin))
    }

    private fun setupFoodMessageVisibility() {
        binding.chooseFoodTextView.visibility = View.VISIBLE
        binding.noteTextView.visibility = View.VISIBLE
    }

    private fun animateAddToCart(view: View, photo: String) {
        val cartLocation = IntArray(2)
        binding.selected.getLocationOnScreen(cartLocation)

        val drawableResId = view.context.resources.getIdentifier(photo, "drawable", view.context.packageName)

        val flyView = ImageView(context).apply {
            setImageResource(drawableResId) // Ganti dengan drawable item Anda
            layoutParams = ViewGroup.LayoutParams(view.width, view.height)
        }
        (activity?.window?.decorView as ViewGroup).addView(flyView)

        val startPos = IntArray(2)
        view.getLocationOnScreen(startPos)
        flyView.x = startPos[0].toFloat()
        flyView.y = startPos[1].toFloat()

        val endX = cartLocation[0].toFloat() + binding.selected.width / 2 - view.width / 2
        val endY = cartLocation[1].toFloat() + binding.selected.height / 2 - view.height / 2

        ObjectAnimator.ofFloat(flyView, "x", endX).apply {
            duration = 700
        }.start()

        ObjectAnimator.ofFloat(flyView, "y", endY).apply {
            duration = 700
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    (activity?.window?.decorView as ViewGroup).removeView(flyView)
                }
            })
        }.start()
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

    private fun setupRecyclerViewAdapter() {
        breakfastRecipeAdapter = RecipeAdapter2 { combinedRecipe, view ->
            toggleSelection(combinedRecipe)
            if(combinedRecipe.recommendation.isSelected == 0){
                animateAddToCart(view, combinedRecipe.recipe.recipePictures)
            }
        }
        lunchRecipeAdapter = RecipeAdapter2 { combinedRecipe, view ->
            toggleSelection(combinedRecipe)
            if(combinedRecipe.recommendation.isSelected == 0){
                animateAddToCart(view, combinedRecipe.recipe.recipePictures)
            }
        }
        dinnerRecipeAdapter = RecipeAdapter2 { combinedRecipe, view ->
            toggleSelection(combinedRecipe)
            if(combinedRecipe.recommendation.isSelected == 0){
                animateAddToCart(view, combinedRecipe.recipe.recipePictures)
            }
        }
    }

    private fun toggleSelection(combinedRecipe: CombinedRecipe) {
        CoroutineScope(Dispatchers.Main).launch {
            val recommendation = viewModel.getRecommendationById(combinedRecipe.recommendation.recommendationId)
            recommendation?.let { rec ->
                rec.isSelected = if (combinedRecipe.recommendation.isSelected == 0) 1 else 0
                viewModel.selectRecommendation(rec)
            }

        }
    }

    private fun setupRecyclerView() {
        binding.breakfastRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.lunchRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.dinnerRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.weeklyRecommendationRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        binding.breakfastRecyclerView.adapter = breakfastRecipeAdapter
        binding.lunchRecyclerView.adapter = lunchRecipeAdapter
        binding.dinnerRecyclerView.adapter = dinnerRecipeAdapter
        binding.weeklyRecommendationRecyclerView.adapter = weeklyRecipeAdapter
    }

    private fun selectMealType() {
        binding.radioGroupMealtype.setOnCheckedChangeListener { _, checkedId ->
            hideAllDailyRecyclerViews()
            setupFoodMessageVisibility()
            val mealId = when (checkedId) {
                R.id.button_breakfast -> {
                    if (!binding.buttonWeekly.isChecked) {
                        viewModel.breakfastRecipes.observe(viewLifecycleOwner) {
                            breakfastRecipeAdapter.submitList(it)
                        }
                        binding.breakfastRecyclerView.visibility = View.VISIBLE
                    }
                    1
                }
                R.id.button_lunch -> {
                    if (!binding.buttonWeekly.isChecked) {
                        viewModel.lunchRecipes.observe(viewLifecycleOwner) {
                            lunchRecipeAdapter.submitList(it)
                        }
                        binding.lunchRecyclerView.visibility = View.VISIBLE
                    }
                    2
                }
                R.id.button_dinner -> {
                    if (!binding.buttonWeekly.isChecked) {
                        viewModel.dinnerRecipes.observe(viewLifecycleOwner) {
                            dinnerRecipeAdapter.submitList(it)
                        }
                        binding.dinnerRecyclerView.visibility = View.VISIBLE
                    }
                    3
                }
                else -> 0
            }
            viewModel.setSelectedMealType(mealId)
        }
    }

    private fun selectMealTime() {
        binding.radioGroupMealtime.setOnCheckedChangeListener { _, checkedId ->
            val mealTime = when (checkedId) {
                R.id.button_daily -> "Daily"
                R.id.button_weekly -> "Weekly"
                else -> "Invalid"
            }
            viewModel.setSelectedMealTime(mealTime)
            binding.radioGroupMealtype.visibility = View.VISIBLE
            updateRecyclerViewVisibility(mealTime)
        }
    }

    private fun updateRecyclerViewVisibility(mealTime: String) {
        if (mealTime == "Daily") {
            binding.weeklyRecommendationRecyclerView.visibility = View.GONE
            showDailyRecyclerViewsBasedOnMealType()
            binding.searchBar.visibility = View.VISIBLE
            binding.selected.visibility = View.VISIBLE
        } else if (mealTime == "Weekly")  {
            hideAllDailyRecyclerViews()
            binding.weeklyRecommendationRecyclerView.visibility = View.VISIBLE
            binding.searchBar.visibility = View.GONE
            binding.selected.visibility = View.GONE
        }
    }
    private fun showDailyRecyclerViewsBasedOnMealType() {
        hideAllDailyRecyclerViews()  // Ensure all are hidden
        val checkedId = binding.radioGroupMealtype.checkedRadioButtonId
        when (checkedId) {
            R.id.button_breakfast -> binding.breakfastRecyclerView.visibility = View.VISIBLE
            R.id.button_lunch -> binding.lunchRecyclerView.visibility = View.VISIBLE
            R.id.button_dinner -> binding.dinnerRecyclerView.visibility = View.VISIBLE
        }
    }

    private fun hideAllDailyRecyclerViews() {
        binding.breakfastRecyclerView.visibility = View.GONE
        binding.lunchRecyclerView.visibility = View.GONE
        binding.dinnerRecyclerView.visibility = View.GONE
    }

    //popup. Mulai untuk profile feature branch
    private fun showPopupMenu() {
        val dialogFragment = RecipeDialog()
        dialogFragment.show(
            parentFragmentManager,
            "com.telyu.nourimate.custom.RecipeDialog"
        )
    }

    private fun setupSearchBarAndSearchView() {
        with(binding) {
            searchView.setupWithSearchBar(searchBar)

            searchView.editText.setOnEditorActionListener { _, _, _ ->
                searchBar.text = searchView.text
                searchView.hide()

                val query = searchBar.text.toString()
                val mealType = viewModel.mealType.value ?: 0
                viewModel.searchRecipes(query, mealType)
                true // Return true to indicate that the action has been handled
            }
        }
    }

    //FAB selected meal
    @SuppressLint("ClickableViewAccessibility")
    private fun setupDraggableSelectedItem() {
        var dX = 0f
        var dY = 0f
        var lastAction = 0

        binding.selecteditem.setOnTouchListener { view, event ->
            when (event.actionMasked) {
                MotionEvent.ACTION_DOWN -> {
                    dX = view.x - event.rawX
                    dY = view.y - event.rawY
                    lastAction = MotionEvent.ACTION_DOWN
                    true
                }

                MotionEvent.ACTION_MOVE -> {
                    view.y = event.rawY + dY
                    view.x = event.rawX + dX
                    lastAction = MotionEvent.ACTION_MOVE
                    true
                }

                MotionEvent.ACTION_UP -> {
                    if (lastAction == MotionEvent.ACTION_DOWN) {
                        // Logika untuk menampilkan dialog pop-up
                        showPopupMenu()
                    }
                    true
                }

                else -> false
            }
        }
    }

    private fun displayUserNameAndProfpic() {
        viewModel.getUsername()
        viewModel.username.observe(viewLifecycleOwner) { name ->
            name?.let {
                val truncatedUserName = truncateUserName(it, wordLimit = 1, maxChars = 8)
                binding.usernameTextView.text = truncatedUserName
            }
        }
        displayImage()
    }

    private fun truncateUserName(userName: String, wordLimit: Int = 1, maxChars: Int = 8): String {
        val words = userName.split(" ").take(wordLimit).joinToString(" ")
        return if (words.length > maxChars) words.substring(0, maxChars) else words
    }

    private fun displayImage() {
        viewModel.profpic.observe(viewLifecycleOwner) { uriString ->
            uriString?.let { uriStr ->
                val uri = Uri.parse(uriStr)
                binding.recipeProfileImageView.setImageURI(uri)
            }
        }
    }


    //buat search
    private fun createRecommendationsForAllRecipes() {
        Log.d("WADUH", "createRecommendationsForAllRecipes: ")
        viewModel.createRecommendationsForAllRecipes()
        isRecipesForSearchInserted = true
    }

}