package com.telyu.nourimate.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.telyu.nourimate.R
import com.telyu.nourimate.data.local.models.Recipe
import com.telyu.nourimate.databinding.ItemFoodBinding

class RecipeAdapter(private val listener: OnAddClickListener) :
    ListAdapter<Recipe, RecipeAdapter.RecipeViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding = ItemFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = getItem(position)
        holder.bind(recipe)
    }


    interface OnAddClickListener {
        fun onAddClick(recipe: Recipe)
    }

    inner class RecipeViewHolder(val binding: ItemFoodBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(recipe: Recipe) {
            binding.apply {
                tvName.text = recipe.name
                tvIngredients.text = recipe.ingredients
                tvCalories.text = "Calories: ${recipe.calories.toInt()}"
                tvProtein.text = "Protein: ${recipe.protein.toInt()}"
                tvFat.text = "Fat: ${recipe.fat.toInt()}"
                tvCarbs.text = "Carbs: ${recipe.carbs.toInt()}"
                Glide.with(ivRecipe.context)
                    .load(
                        itemView.context.resources.getIdentifier(
                            recipe.recipePictures,
                            "drawable",
                            itemView.context.packageName
                        )
                    )
                    .placeholder(R.drawable.capcay)
                    .into(ivRecipe)

                fabaddmeal.setOnClickListener {
                    listener.onAddClick(recipe)
                }
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Recipe>() {
            override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
                return oldItem.recipeId == newItem.recipeId
            }

            override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
                return oldItem == newItem
            }
        }
    }

}