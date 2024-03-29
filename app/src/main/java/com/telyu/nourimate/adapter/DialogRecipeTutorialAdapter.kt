package com.telyu.nourimate.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.telyu.nourimate.data.local.models.Recipe
import com.telyu.nourimate.databinding.ItemFoodBinding
import com.telyu.nourimate.databinding.ItemRecipeDialogTutorialBinding

class DialogRecipeTutorialAdapter : ListAdapter<Recipe, DialogRecipeTutorialAdapter.DialogTutorialRecipeViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DialogTutorialRecipeViewHolder {
        val binding = ItemRecipeDialogTutorialBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DialogTutorialRecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DialogTutorialRecipeViewHolder, position: Int) {
        val recipe = getItem(position)
        holder.bind(recipe)
    }

    inner class DialogTutorialRecipeViewHolder(private val binding: ItemRecipeDialogTutorialBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(recipe: Recipe) {
            binding.apply {
                tvName.text = recipe.name
                tvIngredients.text = recipe.ingredients
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