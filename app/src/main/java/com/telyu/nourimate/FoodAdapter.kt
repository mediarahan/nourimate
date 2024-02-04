package com.telyu.nourimate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import com.telyu.nourimate.databinding.ItemDateBinding
import com.telyu.nourimate.databinding.ItemFoodBinding
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class FoodAdapter(
    private val foodList: List<RecipeFragment.Food>,
    private val showAddButton: Boolean = true,
    private val onAddButtonClick: (RecipeFragment.Food) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val FOOD_TYPE = 0
    private val DATE_TYPE = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            FOOD_TYPE -> {
                FoodViewHolder(ItemFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
            DATE_TYPE -> {
                DateViewHolder(ItemDateBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is FoodViewHolder -> holder.bind(foodList[position], showAddButton)
            is DateViewHolder -> holder.bind(foodList[position])
            else -> throw IllegalArgumentException("Invalid ViewHolder type")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (foodList[position].date != null) DATE_TYPE else FOOD_TYPE
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    inner class FoodViewHolder(private val binding: ItemFoodBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(food: RecipeFragment.Food, showAddButton: Boolean) {
            with(binding) {
                textViewFoodName.text = food.name
                imageViewFood.setImageResource(food.imageResId)
                textViewIngredients.text = food.ingredients
                textViewNutrition.text = food.nutrition

                if (showAddButton) {
                    btnAdd.visibility = View.VISIBLE
                    btnAdd.setOnClickListener {
                        onAddButtonClick(food)
                    }
                } else {
                    btnAdd.visibility = View.GONE
                }
            }
        }
    }

    inner class DateViewHolder(private val binding: ItemDateBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(food: RecipeFragment.Food) {
            binding.textViewDate.text = getFormattedDate(food.date)
        }

        private fun getFormattedDate(date: String?): String {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val outputFormat = SimpleDateFormat("EEEE, d MMMM yyyy", Locale.getDefault())

            return try {
                val parsedDate = inputFormat.parse(date ?: "")
                outputFormat.format(parsedDate ?: Date())
            } catch (e: ParseException) {
                "--Invalid Date--"
            }
        }
    }
}
