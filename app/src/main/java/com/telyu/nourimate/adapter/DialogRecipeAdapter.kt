package com.telyu.nourimate.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.telyu.nourimate.data.local.models.Recipe
import com.telyu.nourimate.databinding.ItemRecipeDialogBinding

class DialogRecipeAdapter : ListAdapter<Recipe, DialogRecipeAdapter.DialogRecipeViewHolder>(DIFF_CALLBACK) {
    //(private val onItemClicked: (Recipe) -> Unit)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DialogRecipeViewHolder {
        val binding = ItemRecipeDialogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DialogRecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DialogRecipeViewHolder, position: Int) {
        val recipe = getItem(position)
        holder.bind(recipe)

//        holder.binding.ivDeleteMeal.setOnClickListener {
//            val recommendationId = recipe.recommendationId
//        }
    }

    inner class DialogRecipeViewHolder(val binding: ItemRecipeDialogBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind (recipe: Recipe) {
            binding.apply {
                tvRecipename.text = recipe.name
                tvCalories.text = "${recipe.calories.toInt()}g Calories"
                tvProtein.text = "${recipe.protein.toInt()}g Protein"
                tvFat.text = "${recipe.fat.toInt()}g Fat "
                tvCarbs.text = "${recipe.carbs.toInt()}g Carbs"
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