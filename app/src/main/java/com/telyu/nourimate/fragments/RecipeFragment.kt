package com.telyu.nourimate.fragments

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.MotionEvent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.telyu.nourimate.R
import com.telyu.nourimate.adapter.RecipeAdapter
import com.telyu.nourimate.data.local.FakeFoodData
import com.telyu.nourimate.data.local.db.FoodDatabase
import com.telyu.nourimate.data.local.models.Meal
import com.telyu.nourimate.data.local.models.Recipe
import com.telyu.nourimate.data.local.models.Recommendation
import com.telyu.nourimate.data.local.relations.MealsRecipesCrossRef
import com.telyu.nourimate.data.local.relations.RecipesRecommendationCrossRef
import com.telyu.nourimate.databinding.FragmentRecipeBinding
import com.telyu.nourimate.databinding.PopupLayoutBinding
import com.telyu.nourimate.databinding.PopupLayoutBreakfastBinding
import com.telyu.nourimate.databinding.PopupLayoutBreakfastTutorialBinding
import com.telyu.nourimate.databinding.PopupLayoutLunchBinding
import com.telyu.nourimate.databinding.PopupLayoutLunchTutorialBinding
import com.telyu.nourimate.databinding.PopupLayoutDinnerBinding
import com.telyu.nourimate.databinding.PopupLayoutDinnerTutorialBinding
import com.telyu.nourimate.viewmodels.RecipeViewModel
import com.telyu.nourimate.viewmodels.ViewModelFactory
import kotlinx.coroutines.launch

class RecipeFragment : Fragment() {

    private lateinit var binding: FragmentRecipeBinding
    private lateinit var bindingFirstPopup: PopupLayoutBinding
    private lateinit var bindingBreakfastPopup: PopupLayoutBreakfastBinding
    private lateinit var bindingLunchPopup: PopupLayoutLunchBinding
    private lateinit var bindingDinnerPopup: PopupLayoutDinnerBinding
    private lateinit var bindingBreakfastTutorialPopup: PopupLayoutBreakfastTutorialBinding
    private lateinit var bindingLunchTutorialPopup: PopupLayoutLunchTutorialBinding
    private lateinit var bindingDinnerTutorialPopup: PopupLayoutDinnerTutorialBinding

    private val viewModel by viewModels<RecipeViewModel> {
        ViewModelFactory.getInstance(
            requireContext().applicationContext
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecipeBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fillDatabaseWithFakeData()

        displayRecipes()

        displayUserNameAndProfpic()

        setupSearchBarAndSearchView()

        setupDraggableSelectedItem()

    }

    private fun selectMealType(onMealTypeSelected: (Int) -> Unit) {
        // Sembunyikan button pada awalnya
        binding.selecteditem.visibility = View.GONE

        binding.radioGroupMealtype.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.button_breakfast -> {
                    onMealTypeSelected(1)
                    showPopupMenuButton()
                }
                R.id.button_lunch -> {
                    onMealTypeSelected(2)
                    showPopupMenuButton()
                }
                R.id.button_dinner -> {
                    onMealTypeSelected(3)
                    showPopupMenuButton()
                }
            }
        }
    }

    //Menampilkan button selected meal
    private fun showPopupMenuButton() {
        //setelah memilih daily kemudian memilih lunch/dinner/breakfast akan muncul tombol selected meal
        binding.selecteditem.visibility = View.VISIBLE
        binding.selecteditem.setOnClickListener {
            showPopupMenu()
        }
        binding.fab.setOnClickListener {
            showPopupMenu()
        }
    }



    //Menampilkan Pop Up Selected Meal
    private fun showPopupMenu() {
        val dialog = Dialog(requireContext())

        // Inisialisasi binding dengan layout popup pertama
        bindingFirstPopup = PopupLayoutBinding.inflate(layoutInflater)
        dialog.setContentView(bindingFirstPopup.root)

        dialog.window?.setBackgroundDrawableResource(R.drawable.rectangle_popup_selected_item)

        // Sesuaikan properti dialog
        dialog.window?.let { window ->
            val layoutParams = window.attributes
            layoutParams.gravity = Gravity.CENTER
            // Atur lebar dari dialog menjadi 90% dari lebar layar
            val displayMetrics = resources.displayMetrics
            layoutParams.width = (displayMetrics.widthPixels * 0.9).toInt()
            layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
            window.attributes = layoutParams
        }

        bindingFirstPopup.breakfastImageView.setOnClickListener {
            // Tutup dialog pertama
            dialog.dismiss()
            // Tampilkan dialog kedua
            showBreakfastPopupMenu()
        }

        bindingFirstPopup.breakfastTextView.setOnClickListener {
            // Tutup dialog pertama
            dialog.dismiss()
            // Tampilkan dialog kedua
            showBreakfastPopupMenu()
        }

        bindingFirstPopup.lunchImageView.setOnClickListener {
            // Tutup dialog pertama
            dialog.dismiss()
            // Tampilkan dialog kedua
            showLunchPopupMenu()
        }

        bindingFirstPopup.lunchTextView.setOnClickListener {
            // Tutup dialog pertama
            dialog.dismiss()
            // Tampilkan dialog kedua
            showLunchPopupMenu()
        }

        bindingFirstPopup.dinnerImageView.setOnClickListener {
            // Tutup dialog pertama
            dialog.dismiss()
            // Tampilkan dialog kedua
            showDinnerPopupMenu()
        }

        bindingFirstPopup.dinnerTextView.setOnClickListener {
            // Tutup dialog pertama
            dialog.dismiss()
            // Tampilkan dialog kedua
            showDinnerPopupMenu()
        }

        dialog.show()
    }


    //Menampilkan Lanjutan Pop Up untuk pemilihan breakfast
    private fun showBreakfastPopupMenu() {
        val dialog = Dialog(requireContext())

        // Inisialisasi binding dengan layout popup breakfast
        bindingBreakfastPopup = PopupLayoutBreakfastBinding.inflate(layoutInflater)
        dialog.setContentView(bindingBreakfastPopup.root)

        dialog.window?.setBackgroundDrawableResource(R.drawable.rectangle_popup_selected_item)

        // Sesuaikan properti dialog
        dialog.window?.let { window ->
            val layoutParams = window.attributes
            layoutParams.gravity = Gravity.CENTER
            // Atur lebar dari dialog menjadi 90% dari lebar layar
            val displayMetrics = resources.displayMetrics
            layoutParams.width = (displayMetrics.widthPixels * 0.9).toInt()
            layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
            window.attributes = layoutParams
        }

        bindingBreakfastPopup.selectMealBreakfastButton.setOnClickListener {
            // Tutup dialog pertama
            dialog.dismiss()
            // Tampilkan dialog kedua
            showBreakfastTutorialPopupMenu()
        }

        dialog.show()
    }

    //Menampilkan Lanjutan Pop Up untuk tutorial breakfast
    private fun showBreakfastTutorialPopupMenu() {
        val dialog = Dialog(requireContext())

        bindingBreakfastTutorialPopup = PopupLayoutBreakfastTutorialBinding.inflate(layoutInflater)
        dialog.setContentView(bindingBreakfastTutorialPopup.root)

        dialog.window?.setBackgroundDrawableResource(R.drawable.rectangle_popup_selected_item)

        // Sesuaikan properti dialog
        dialog.window?.let { window ->
            val layoutParams = window.attributes
            layoutParams.gravity = Gravity.CENTER
            // Atur lebar dari dialog menjadi 90% dari lebar layar
            val displayMetrics = resources.displayMetrics
            layoutParams.width = (displayMetrics.widthPixels * 0.9).toInt()
            layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
            window.attributes = layoutParams
        }

        dialog.show()
    }

    //Menampilkan Lanjutan Pop Up untuk pemilihan lunch
    private fun showLunchPopupMenu() {
        val dialog = Dialog(requireContext())

        // Inisialisasi binding dengan layout popup lunch
        bindingLunchPopup = PopupLayoutLunchBinding.inflate(layoutInflater)
        dialog.setContentView(bindingLunchPopup.root)

        dialog.window?.setBackgroundDrawableResource(R.drawable.rectangle_popup_selected_item)

        // Sesuaikan properti dialog
        dialog.window?.let { window ->
            val layoutParams = window.attributes
            layoutParams.gravity = Gravity.CENTER
            // Atur lebar dari dialog menjadi 90% dari lebar layar
            val displayMetrics = resources.displayMetrics
            layoutParams.width = (displayMetrics.widthPixels * 0.9).toInt()
            layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
            window.attributes = layoutParams
        }

        bindingLunchPopup.selectMealLunchButton.setOnClickListener {
            // Tutup dialog pertama
            dialog.dismiss()
            // Tampilkan dialog kedua
            showLunchTutorialPopupMenu()
        }

        dialog.show()
    }

    //Menampilkan Lanjutan Pop Up untuk tutorial lunch
    private fun showLunchTutorialPopupMenu() {
        val dialog = Dialog(requireContext())

        bindingLunchTutorialPopup = PopupLayoutLunchTutorialBinding.inflate(layoutInflater)
        dialog.setContentView(bindingLunchTutorialPopup.root)

        dialog.window?.setBackgroundDrawableResource(R.drawable.rectangle_popup_selected_item)

        // Sesuaikan properti dialog
        dialog.window?.let { window ->
            val layoutParams = window.attributes
            layoutParams.gravity = Gravity.CENTER
            // Atur lebar dari dialog menjadi 90% dari lebar layar
            val displayMetrics = resources.displayMetrics
            layoutParams.width = (displayMetrics.widthPixels * 0.9).toInt()
            layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
            window.attributes = layoutParams
        }

        dialog.show()
    }

    //Menampilkan Lanjutan Pop Up untuk pemilihan dinner
    private fun showDinnerPopupMenu() {
        val dialog = Dialog(requireContext())

        // Inisialisasi binding dengan layout popup dinner
        bindingDinnerPopup = PopupLayoutDinnerBinding.inflate(layoutInflater)
        dialog.setContentView(bindingDinnerPopup.root)

        dialog.window?.setBackgroundDrawableResource(R.drawable.rectangle_popup_selected_item)

        // Sesuaikan properti dialog
        dialog.window?.let { window ->
            val layoutParams = window.attributes
            layoutParams.gravity = Gravity.CENTER
            // Atur lebar dari dialog menjadi 90% dari lebar layar
            val displayMetrics = resources.displayMetrics
            layoutParams.width = (displayMetrics.widthPixels * 0.9).toInt()
            layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
            window.attributes = layoutParams
        }

        bindingDinnerPopup.selectMealDinnerButton.setOnClickListener {
            // Tutup dialog pertama
            dialog.dismiss()
            // Tampilkan dialog kedua
            showDinnerTutorialPopupMenu()
        }

        dialog.show()
    }

    //Menampilkan Lanjutan Pop Up untuk tutorial dinner
    private fun showDinnerTutorialPopupMenu() {
        val dialog = Dialog(requireContext())

        bindingDinnerTutorialPopup = PopupLayoutDinnerTutorialBinding.inflate(layoutInflater)
        dialog.setContentView(bindingDinnerTutorialPopup.root)

        dialog.window?.setBackgroundDrawableResource(R.drawable.rectangle_popup_selected_item)

        // Sesuaikan properti dialog
        dialog.window?.let { window ->
            val layoutParams = window.attributes
            layoutParams.gravity = Gravity.CENTER
            // Atur lebar dari dialog menjadi 90% dari lebar layar
            val displayMetrics = resources.displayMetrics
            layoutParams.width = (displayMetrics.widthPixels * 0.9).toInt()
            layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
            window.attributes = layoutParams
        }

        dialog.show()
    }


    //FAB selected meal
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



    private fun displayRecipes() {
        //rv adapter stuff
        val recipeAdapter = RecipeAdapter()
        binding.recommendationRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recommendationRecyclerView.adapter = recipeAdapter

        selectMealType { selectedMealType ->
            viewModel.getRecipeByMeal(selectedMealType)
                .observe(viewLifecycleOwner) { recipes ->
                    recipeAdapter.submitList(recipes)
                }
        }

        viewModel.searchResult.observe(viewLifecycleOwner) { recipes ->
            recipeAdapter.submitList(recipes)
        }
    }

    private fun setupSearchBarAndSearchView() {
        with(binding) {
            searchView.setupWithSearchBar(searchBar)

            searchView.editText.setOnEditorActionListener { _, _, _ ->
                searchBar.text = searchView.text
                searchView.hide()

                val query = searchBar.text.toString()
                viewModel.getRecipeByName(query)
                true // Return true to indicate that the action has been handled
            }
        }
    }

    private fun displayUserNameAndProfpic() {
        viewModel.userEmail.observe(viewLifecycleOwner) { userEmail ->
            userEmail.let {
                viewModel.getUserIdByEmail(it)
                viewModel.getUserNameByEmail(it)
            }
        }

        viewModel.userName.observe(viewLifecycleOwner) {userName ->
            binding.usernameTextView.text = userName
        }

        viewModel.userId.observe(viewLifecycleOwner) {userId ->
            if (userId != null) {
                viewModel.getProfpicById(userId)
            }
        }

        viewModel.profilePicture.observe(viewLifecycleOwner) {uriString ->
            uriString?.let { uriStr ->
                val uri = Uri.parse(uriStr)
                binding.recipeProfileImageView.setImageURI(uri)
            }

        }

    }

    private fun fillDatabaseWithFakeData(){

        // Untuk isi database dengan fake data
        val dao = FoodDatabase.getInstance(requireContext()).foodDao()
        val fakeFoodData = FakeFoodData()

        val mappedRecipes = fakeFoodData.recipes.map { recipe ->
            Recipe(
                recipeId = recipe.recipeId,
                name = recipe.name,
                calories = recipe.calories,
                carbs = recipe.carbs,
                fat = recipe.fat,
                protein = recipe.protein,
                ingredients = recipe.ingredients,
                cookingSteps = recipe.cookingSteps,
                recipePictures = recipe.recipePictures
            )
        }
        val mappedMeals = fakeFoodData.meals.map { meal ->
            Meal(
                mealId = meal.mealId,
                name = meal.name
            )
        }

        val mappedRecommendations = fakeFoodData.recommendations.map { recommendation ->
            Recommendation(
                recommendationId = recommendation.recommendationId,
                date = recommendation.date
            )
        }

        val mappedRelationRR = fakeFoodData.recipeRecommendationCrossRef.map { crossRef ->
            RecipesRecommendationCrossRef(
                recipeId = crossRef.recipeId,
                recommendationId = crossRef.recommendationId,
            )
        }

        val mappedRelationMR = fakeFoodData.mealRecipeCrossRef.map { crossRef ->
            MealsRecipesCrossRef(
                mealId = crossRef.mealId,
                recipeId = crossRef.recipeId,
            )
        }

        // Insert data into database within coroutine scope
        lifecycleScope.launch {
            // Insert each recipe individually
            mappedRecipes.forEach { recipe ->
                dao.insertRecipe(recipe)
            }
            mappedMeals.forEach { meals ->
                dao.insertMeal(meals)
            }
            mappedRecommendations.forEach { recommendation ->
                dao.insertRecommendation(recommendation)
            }
            mappedRelationRR.forEach { crossRef ->
                dao.insertRecipeRecommendationCrossRef(crossRef)
            }
            mappedRelationMR.forEach { crossRef ->
                dao.insertMealRecipeCrossRef(crossRef)
            }
        }
    }
}