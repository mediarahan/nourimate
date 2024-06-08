package com.telyu.nourimate.adapter.recipe

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.telyu.nourimate.R
import com.telyu.nourimate.data.local.models.Recipe
import com.telyu.nourimate.data.local.models.Recommendation
import com.telyu.nourimate.databinding.ItemFoodBinding

class RecipeAdapter2(private val onClickListener: (CombinedRecipe) -> Unit) :
    ListAdapter<CombinedRecipe, RecipeAdapter2.Recipe2ViewHolder>(Recipe2DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Recipe2ViewHolder {
        val binding = ItemFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Recipe2ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeAdapter2.Recipe2ViewHolder, position: Int) {
        val recipe = getItem(position)
        holder.bind(recipe)

    }

    inner class Recipe2ViewHolder(private val binding: ItemFoodBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CombinedRecipe) {
            binding.apply {
                tvName.text = item.recipe.name
                tvIngredients.text = item.recipe.ingredients
                tvCalories.text = "Calories: ${item.recipe.calories.toInt()}"
                tvProtein.text = "Protein: ${item.recipe.protein.toInt()}"
                tvFat.text = "Fat: ${item.recipe.fat.toInt()}"
                tvCarbs.text = "Carbs: ${item.recipe.carbs.toInt()}"
                Glide.with(ivRecipe.context)
                    .load(
                        itemView.context.resources.getIdentifier(
                            item.recipe.recipePictures,
                            "drawable",
                            itemView.context.packageName
                        )
                    )
                    .placeholder(R.drawable.capcay)
                    .into(ivRecipe)

                fabaddmeal.setOnClickListener {
                }
            }
        }
    }
}

internal class Recipe2DiffCallback : DiffUtil.ItemCallback<CombinedRecipe>() {
    override fun areItemsTheSame(oldItem: CombinedRecipe, newItem: CombinedRecipe): Boolean =
        oldItem.recipe.recipeId == newItem.recipe.recipeId

    override fun areContentsTheSame(oldItem: CombinedRecipe, newItem: CombinedRecipe): Boolean =
        oldItem == newItem
}


data class CombinedRecipe(
    val recipe: Recipe,
    var recommendation: Recommendation
)