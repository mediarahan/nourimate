package com.telyu.nourimate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import android.graphics.Rect
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.telyu.nourimate.databinding.FragmentRecipeBinding
import com.telyu.nourimate.databinding.PopupLayoutBinding


class RecipeFragment : Fragment() {

    data class Food(
        val name: String,
        val imageResId: Int,
        val ingredients: String,
        val nutrition: String,
        val date: String? = null,
        val isAdded: Boolean = false,  // Add this property
        val showAddButton: Boolean = false
    )


    private lateinit var viewModel: RecipeViewModel
    private var _binding: FragmentRecipeBinding? = null
    private val binding get() = _binding!!

    private var dailyOrWeeklyClicked = false
    private var isButtonsEnabled = false



    private val breakfastData =
        Food(
            "Vegetable Omelette",
            R.drawable.splash,
            "2 telur\n" +
                    "1/2 cangkir tomat, potong dadu kecil\n" +
                    "1/4 cangkir paprika, potong dadu kecil\n" +
                    "1/4 cangkir bayam, cincang halus\n" +
                    "1 sendok teh minyak zaitun\n" +
                    "Garam dan merica secukupnya",
            "200 Calories\n" +
                    "8g Carbs\n" +
                    "15g Fat\n" +
                    "12g Protein",
            isAdded = false
        )
    private val lunchData =
        Food("Fruit Smoothies", R.drawable.splash, "1 pisang matang\n" +
                "1/2 cangkir stroberi beku\n" +
                "1/2 cangkir yogurt rendah lemak\n" +
                "1/4 cangkir susu almond\n" +
                "1 sendok makan madu", "150 Calories\n" +
                "30g Carbs\n" +
                "3g Fat\n" +
                "8g Protein",
            isAdded = false )
    private val dinnerData =
        Food("Mango Chicken Salad", R.drawable.splash, "Dada ayam, panggang dan potong dadu\n" +
                "Mangga, potong dadu\n" +
                "Selada hijau\n" +
                "Tomat cherry, belah dua\n" +
                "Bawang merah, iris tipis\n" +
                "Kacang mete, sangrai\n" +
                "Dressing balsamic", "250 Calories\n" +
                "30g Carbs\n" +
                "12g Fat\n" +
                "15g Protein",
            isAdded = false )


    private val allFoodData = mutableListOf<Food>().apply {
        repeat(12) {
            add(breakfastData)
            add(lunchData)
            add(dinnerData)
        }

    }


    private var dailyBreakfastClicked = false
    private var dailyLunchClicked = false
    private var dailyDinnerClicked = false

    private var weeklyBreakfastClicked = false
    private var weeklyLunchClicked = false
    private var weeklyDinnerClicked = false

    private var isBreakfastAdded = false
    private var isLunchAdded = false
    private var isDinnerAdded = false

    private var addedBreakfast: Food? = null
    private var addedLunch: Food? = null
    private var addedDinner: Food? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecipeBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.selectedMealButton.visibility = if (dailyOrWeeklyClicked) View.VISIBLE else View.GONE

        binding.btnBreakfast.isEnabled = false
        binding.btnLunch.isEnabled = false
        binding.btnDinner.isEnabled = false


        binding.btnDaily.setOnClickListener {
            dailyOrWeeklyClicked = true
            binding.selectedMealButton.visibility = View.VISIBLE
            updateButtonState(binding.btnDaily)
            updateButtonState(binding.btnWeekly)
            enableMealButtons()
            updateRecommendations(emptyList())

            // Aktifkan tombol-tombol setelah tombol "Daily" diklik
            isButtonsEnabled = true
            binding.btnBreakfast.isEnabled = true
            binding.btnLunch.isEnabled = true
            binding.btnDinner.isEnabled = true
        }


        binding.btnWeekly.setOnClickListener {
            dailyOrWeeklyClicked = false
            binding.selectedMealButton.visibility = View.GONE
            updateButtonState(binding.btnWeekly)
            updateButtonState(binding.btnDaily)
            enableMealButtons()
            weeklyBreakfastClicked = false
            weeklyLunchClicked = false
            weeklyDinnerClicked = false
            updateRecommendations(emptyList())

            // Aktifkan tombol-tombol setelah tombol "Weekly" diklik
            isButtonsEnabled = true
            binding.btnBreakfast.isEnabled = true
            binding.btnLunch.isEnabled = true
            binding.btnDinner.isEnabled = true
        }


        binding.recommendationRecyclerView.layoutManager = LinearLayoutManager(context)

        binding.btnBreakfast.setOnClickListener {
            if (dailyOrWeeklyClicked) {
                dailyBreakfastClicked = true
                dailyLunchClicked = false
                dailyDinnerClicked = false
                displayRecommendations(allFoodData.filterIndexed { index, _ -> index % 3 == 0 })
            } else {
                weeklyBreakfastClicked = true
                weeklyLunchClicked = false
                weeklyDinnerClicked = false
                displayWeeklyRecommendations(allFoodData.filterIndexed { index, _ -> index % 3 == 0 })
            }
            updateButtonState(binding.btnBreakfast)
            updateButtonState(binding.btnLunch)
            updateButtonState(binding.btnDinner)
        }

        binding.btnLunch.setOnClickListener {
            if (dailyOrWeeklyClicked) {
                dailyBreakfastClicked = false
                dailyLunchClicked = true
                dailyDinnerClicked = false
                displayRecommendations(allFoodData.filterIndexed { index, _ -> index % 3 == 1 })
            } else {
                weeklyBreakfastClicked = false
                weeklyLunchClicked = true
                weeklyDinnerClicked = false
                displayWeeklyRecommendations(allFoodData.filterIndexed { index, _ -> index % 3 == 1 })
            }
            updateButtonState(binding.btnBreakfast)
            updateButtonState(binding.btnLunch)
            updateButtonState(binding.btnDinner)
        }

        binding.btnDinner.setOnClickListener {
            if (dailyOrWeeklyClicked) {
                dailyBreakfastClicked = false
                dailyLunchClicked = false
                dailyDinnerClicked = true
                displayRecommendations(allFoodData.filterIndexed { index, _ -> index % 3 == 2 })
            } else {
                weeklyBreakfastClicked = false
                weeklyLunchClicked = false
                weeklyDinnerClicked = true
                displayWeeklyRecommendations(allFoodData.filterIndexed { index, _ -> index % 3 == 2 })
            }
            updateButtonState(binding.btnBreakfast)
            updateButtonState(binding.btnLunch)
            updateButtonState(binding.btnDinner)
        }

        binding.selectedMealButton.setOnClickListener {
            showPopup()
        }


        return view
    }

    private fun updateRecommendations(foodList: List<Food>) {
        val adapter = FoodAdapter(foodList, showAddButton = false) { /* Empty lambda */ }
        binding.recommendationRecyclerView.adapter = adapter
        updateButtonState(binding.btnBreakfast)
        updateButtonState(binding.btnLunch)
        updateButtonState(binding.btnDinner)
    }



    private fun displayRecommendations(foodList: List<Food>) {
        val adapter = FoodAdapter(foodList.take(2), showAddButton = dailyOrWeeklyClicked) { food: Food ->
            onAddButtonClick(food)
        }
        binding.recommendationRecyclerView.adapter = adapter
        updateButtonState(binding.btnBreakfast)
        updateButtonState(binding.btnLunch)
        updateButtonState(binding.btnDinner)
    }

    private fun displayWeeklyRecommendations(foodList: List<Food>) {
        val filteredList = mutableListOf<Food>()

        for ((index, food) in foodList.withIndex()) {
            // Menambahkan elemen tanggal
            if (index % 2 == 0) {
                val dateFood = Food("Date ${index / 2 + 1}", 0, "", "", food.date ?: "")
                filteredList.add(dateFood)
            }

            // Menambahkan dua elemen makanan setelah setiap tanggal
            if (index % 2 == 1) {
                filteredList.add(food.copy(isAdded = isFoodAdded(food)))
                if (index < foodList.size - 1) {
                    filteredList.add(foodList[index + 1].copy(isAdded = isFoodAdded(foodList[index + 1])))
                }
            }
        }

        val adapter = FoodAdapter(filteredList, dailyOrWeeklyClicked) { food: Food ->
            onAddButtonClick(food)
        }
        binding.recommendationRecyclerView.adapter = adapter
        updateButtonState(binding.btnBreakfast)
        updateButtonState(binding.btnLunch)
        updateButtonState(binding.btnDinner)
    }

    private fun isFoodAdded(food: Food): Boolean {
        return when {
            food == breakfastData && isBreakfastAdded -> true
            food == lunchData && isLunchAdded -> true
            food == dinnerData && isDinnerAdded -> true
            else -> false
        }
    }


    private fun enableMealButtons() {
        binding.btnBreakfast.apply {
            setBackgroundColor(resources.getColor(android.R.color.darker_gray))
            isClickable =
                dailyOrWeeklyClicked && !dailyBreakfastClicked || !dailyOrWeeklyClicked && !weeklyBreakfastClicked
            dailyBreakfastClicked = false
            weeklyBreakfastClicked = false
        }

        binding.btnLunch.apply {
            setBackgroundColor(resources.getColor(android.R.color.darker_gray))
            isClickable =
                dailyOrWeeklyClicked && !dailyLunchClicked || !dailyOrWeeklyClicked && !weeklyLunchClicked
            dailyLunchClicked = false
            weeklyLunchClicked = false
        }

        binding.btnDinner.apply {
            setBackgroundColor(resources.getColor(android.R.color.darker_gray))
            isClickable =
                dailyOrWeeklyClicked && !dailyDinnerClicked || !dailyOrWeeklyClicked && !weeklyDinnerClicked
            dailyDinnerClicked = false
            weeklyDinnerClicked = false
        }
    }


    private fun updateButtonState(button: View) {
        val selectedColor = "#FFC745"
        val unselectedColor = "#F1EFEF"

        if (button == binding.btnDaily || button == binding.btnWeekly) {
            binding.btnDaily.setBackgroundColor(
                if (dailyOrWeeklyClicked) android.graphics.Color.parseColor(selectedColor)
                else android.graphics.Color.parseColor(unselectedColor)
            )
            binding.btnWeekly.setBackgroundColor(
                if (!dailyOrWeeklyClicked) android.graphics.Color.parseColor(selectedColor)
                else android.graphics.Color.parseColor(unselectedColor)
            )
        }

        if (button == binding.btnBreakfast) {
            binding.btnBreakfast.setBackgroundColor(
                if (dailyOrWeeklyClicked && dailyBreakfastClicked || !dailyOrWeeklyClicked && weeklyBreakfastClicked)
                    android.graphics.Color.parseColor(selectedColor)
                else
                    android.graphics.Color.parseColor(unselectedColor)
            )
        }

        if (button == binding.btnLunch) {
            binding.btnLunch.setBackgroundColor(
                if (dailyOrWeeklyClicked && dailyLunchClicked || !dailyOrWeeklyClicked && weeklyLunchClicked)
                    android.graphics.Color.parseColor(selectedColor)
                else
                    android.graphics.Color.parseColor(unselectedColor)
            )
        }

        if (button == binding.btnDinner) {
            binding.btnDinner.setBackgroundColor(
                if (dailyOrWeeklyClicked && dailyDinnerClicked || !dailyOrWeeklyClicked && weeklyDinnerClicked)
                    android.graphics.Color.parseColor(selectedColor)
                else
                    android.graphics.Color.parseColor(unselectedColor)
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(RecipeViewModel::class.java)

        viewModel.greetingMessage.observe(viewLifecycleOwner, Observer {
            binding.recipeGreetingTextView.text = "Hello, $it"
        })

        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.spacing) // Adjust the dimension according to your needs
        binding.recommendationRecyclerView.addItemDecoration(SpacingItemDecoration(spacingInPixels))
    }

    class SpacingItemDecoration(private val spacingInPixels: Int) : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            outRect.left = spacingInPixels
            outRect.right = spacingInPixels
            outRect.bottom = spacingInPixels
            outRect.top = spacingInPixels
        }
    }


    private fun showPopup() {
        if (dailyOrWeeklyClicked) {
            val inflater = LayoutInflater.from(requireContext())
            val dialogView = inflater.inflate(R.layout.popup_layout, null)
            val binding = PopupLayoutBinding.bind(dialogView)

            val dialogBuilder = AlertDialog.Builder(requireContext())
                .setView(binding.root)
                .setTitle("Selected Meal")

            val alertDialog = dialogBuilder.create()
            alertDialog.show()

            // Set text and visibility based on added meals
            binding.breakfastTextView.text = addedBreakfast?.name ?: "No breakfast added"
            binding.lunchTextView.text = addedLunch?.name ?: "No lunch added"
            binding.dinnerTextView.text = addedDinner?.name ?: "No dinner added"

            // Set click listeners for each button
            binding.breakfastImageView.setOnClickListener {
                showToast("Breakfast selected")
                alertDialog.dismiss()
            }

            binding.lunchImageView.setOnClickListener {
                showToast("Lunch selected")
                alertDialog.dismiss()
            }

            binding.dinnerImageView.setOnClickListener {
                showToast("Dinner selected")
                alertDialog.dismiss()
            }
        }
    }


    private fun onAddButtonClick(food: Food) {
        when {
            food == breakfastData && addedBreakfast == null -> {
                addedBreakfast = food
                showToast("Breakfast added")
            }
            food == lunchData && addedLunch == null -> {
                addedLunch = food
                showToast("Lunch added")
            }
            food == dinnerData && addedDinner == null -> {
                addedDinner = food
                showToast("Dinner added")
            }
        }
    }





    private fun showToast(message: String) {
        // Implementasi tampilan pesan toast di sini
        // Misalnya, menggunakan Toast.makeText
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}