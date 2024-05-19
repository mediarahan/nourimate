package com.telyu.nourimate.adapter.recipe

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.telyu.nourimate.data.local.models.Recipe
import com.telyu.nourimate.databinding.ItemRecipeDialogBinding

class DialogRecipeAdapter(private val listener: DialogOnAddClickListener) : ListAdapter<Recipe, DialogRecipeAdapter.DialogRecipeViewHolder>(
    DIFF_CALLBACK
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DialogRecipeViewHolder {
        val binding = ItemRecipeDialogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DialogRecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DialogRecipeViewHolder, position: Int) {
        val recipe = getItem(position)
        holder.bind(recipe)
    }

    interface DialogOnAddClickListener {
        fun dialogOnAddClick(recipe: Recipe)
    }

    inner class DialogRecipeViewHolder(val binding: ItemRecipeDialogBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind (recipe: Recipe) {
            binding.apply {
                tvRecipenameDialog.text = recipe.name
                tvCaloriesDialog.text = "${recipe.calories.toInt()}g Calories"
                tvProteinDialog.text = "${recipe.protein.toInt()}g Protein"
                tvFatDialog.text = "${recipe.fat.toInt()}g Fat "
                tvCarbsDialog.text = "${recipe.carbs.toInt()}g Carbs"

                ivDeleteMeal.setOnClickListener {
                    listener.dialogOnAddClick(recipe)
                }
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