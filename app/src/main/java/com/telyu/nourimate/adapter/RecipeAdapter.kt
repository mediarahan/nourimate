package com.telyu.nourimate.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.telyu.nourimate.data.local.models.Recipe
import com.telyu.nourimate.databinding.ItemFoodBinding

class RecipeAdapter : ListAdapter<Recipe, RecipeAdapter.RecipeViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding = ItemFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = getItem(position)
        holder.bind(recipe)
    }

    inner class RecipeViewHolder(private val binding: ItemFoodBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(recipe: Recipe) {
            binding.apply {
                tvName.text = recipe.name
                tvIngredients.text = recipe.ingredients
                tvCalories.text = "Calories: ${recipe.calories.toInt()}"
                tvProtein.text = "Protein: ${recipe.protein.toInt()}"
                tvFat.text = "Fat: ${recipe.fat.toInt()}"
                tvCarbs.text = "Carbs: ${recipe.carbs.toInt()}"
                val resourceId = itemView.context.resources.getIdentifier(
                    recipe.recipePictures,
                    "drawable",
                    itemView.context.packageName
                )
                ivRecipe.setImageResource(resourceId)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Recipe>() {
            override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
                return oldItem == newItem
            }
        }
    }

}