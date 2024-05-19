package com.telyu.nourimate.adapter.recipe

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.telyu.nourimate.R
import com.telyu.nourimate.data.local.models.Recipe
import com.telyu.nourimate.data.local.models.RecommendationRecipe
import com.telyu.nourimate.databinding.ItemFoodBinding
import com.telyu.nourimate.databinding.ItemDateBinding
import com.telyu.nourimate.utils.Converters

class RecommendationRecipeAdapter(private val listener: OnAddClickListener) :
    ListAdapter<RecommendationRecipe, RecommendationRecipeAdapter.BaseViewHolder>(DiffUtilCallback()) {

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is RecommendationRecipe.RecipeItem -> viewTypeChildren
        is RecommendationRecipe.RecommendationItem -> viewTypeParent
    }

    interface OnAddClickListener {
        fun onAddClick(recipe: Recipe)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        when (viewType) {
            viewTypeChildren -> ChildViewHolder(
                ItemFoodBinding.inflate(
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
            is RecommendationRecipe.RecipeItem -> (holder as ChildViewHolder).bind(item)
            is RecommendationRecipe.RecommendationItem -> (holder as ParentViewHolder).bind(item)
        }
    }

    abstract class BaseViewHolder(binding: ViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        abstract fun bind(item: RecommendationRecipe)
    }

    inner class ParentViewHolder(private val binding: ItemDateBinding) : BaseViewHolder(binding) {
        override fun bind(item: RecommendationRecipe) {
            item as RecommendationRecipe.RecommendationItem
            binding.textViewDate.text = Converters().formatDayDateToString(item.recommendation.date)
        }
    }

    inner class ChildViewHolder(private val binding: ItemFoodBinding) : BaseViewHolder(binding) {
        override fun bind(item: RecommendationRecipe) {
            item as RecommendationRecipe.RecipeItem
            with(binding) {
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
                    listener.onAddClick(item.recipe)
                }
            }
        }
    }

    companion object {
        private const val viewTypeParent = 0
        private const val viewTypeChildren = 1
    }
}

internal class DiffUtilCallback : DiffUtil.ItemCallback<RecommendationRecipe>() {
    override fun areItemsTheSame(
        oldItem: RecommendationRecipe,
        newItem: RecommendationRecipe
    ): Boolean =
        when {
            oldItem is RecommendationRecipe.RecipeItem && newItem is RecommendationRecipe.RecipeItem -> oldItem.recipe.recipeId == newItem.recipe.recipeId
            oldItem is RecommendationRecipe.RecommendationItem && newItem is RecommendationRecipe.RecommendationItem -> oldItem.recommendation.recommendationId == newItem.recommendation.recommendationId
            else -> false
        }

    override fun areContentsTheSame(
        oldItem: RecommendationRecipe,
        newItem: RecommendationRecipe
    ): Boolean = oldItem == newItem
}
