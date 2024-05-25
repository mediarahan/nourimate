package com.telyu.nourimate.adapter.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.telyu.nourimate.R
import com.telyu.nourimate.data.local.models.RecipeHistoryData
import com.telyu.nourimate.databinding.ItemDateBinding
import com.telyu.nourimate.databinding.ItemFoodHistoryBinding
import com.telyu.nourimate.utils.Converters

class RecipeHistoryAdapter : ListAdapter<RecipeHistoryData, RecipeHistoryAdapter.BaseViewHolder>(DiffUtilCallback()) {

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is RecipeHistoryData.RecipeItem -> viewTypeChildren
        is RecipeHistoryData.RecipeHistoryItem -> viewTypeParent
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        when (viewType) {
            viewTypeChildren -> ChildViewHolder(
                ItemFoodHistoryBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            viewTypeParent -> ParentViewHolder(
                ItemDateBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            else -> throw IllegalArgumentException("Unknown viewType $viewType")
        }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (val item = getItem(position)) {
            is RecipeHistoryData.RecipeItem -> (holder as ChildViewHolder).bind(item)
            is RecipeHistoryData.RecipeHistoryItem -> (holder as ParentViewHolder).bind(item)
        }
    }

    abstract class BaseViewHolder(binding: ViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        abstract fun bind(item: RecipeHistoryData)
    }

    inner class ParentViewHolder(private val binding: ItemDateBinding) : BaseViewHolder(binding) {
        override fun bind(item: RecipeHistoryData) {
            item as RecipeHistoryData.RecipeHistoryItem
            binding.textViewDate.text = Converters().formatDayDateToString(item.recipeHistory.consumedDate)
        }
    }

    inner class ChildViewHolder(private val binding: ItemFoodHistoryBinding) : BaseViewHolder(binding) {
        override fun bind(item: RecipeHistoryData) {
            item as RecipeHistoryData.RecipeItem
            with(binding) {
                tvName.text = item.recipe.name
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
            }
        }
    }

    companion object {
        private const val viewTypeParent = 0
        private const val viewTypeChildren = 1
    }
}

internal class DiffUtilCallback : DiffUtil.ItemCallback<RecipeHistoryData>() {
    override fun areItemsTheSame(
        oldItem: RecipeHistoryData,
        newItem: RecipeHistoryData
    ): Boolean =
        when {
            oldItem is RecipeHistoryData.RecipeItem && newItem is RecipeHistoryData.RecipeItem -> oldItem.recipe.recipeId == newItem.recipe.recipeId
            oldItem is RecipeHistoryData.RecipeHistoryItem && newItem is RecipeHistoryData.RecipeHistoryItem -> oldItem.recipeHistory.id == newItem.recipeHistory.id
            else -> false
        }

    override fun areContentsTheSame(
        oldItem: RecipeHistoryData,
        newItem: RecipeHistoryData
    ): Boolean = oldItem == newItem
}
