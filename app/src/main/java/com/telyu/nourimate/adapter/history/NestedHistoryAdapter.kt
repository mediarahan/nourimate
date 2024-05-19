package com.telyu.nourimate.adapter.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.telyu.nourimate.data.local.models.NestedHistory
import com.telyu.nourimate.databinding.NestedHistoryItemBinding

class NestedHistoryAdapter(private var mList: List<NestedHistory>) : RecyclerView.Adapter<NestedHistoryAdapter.NestedHistoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NestedHistoryViewHolder {
        val binding = NestedHistoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NestedHistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NestedHistoryViewHolder, position: Int) {
        holder.bind(mList[position])
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    inner class NestedHistoryViewHolder(private val binding: NestedHistoryItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: NestedHistory) {
            binding.TotalCaloriesTitle.text = "Total Calories :"
            binding.TotalCalories.text = model.calories
            binding.TotalCaloriesQuantifiers.text = "kkal"

            binding.TotalProteinTitle.text = "Total Protein :"
            binding.TotalProtein.text = model.protein
            binding.TotalProteinQuantifiers.text = "g"

            binding.TotalCarbsTitle.text = "Total Carbs :"
            binding.TotalCarbs.text = model.carbs
            binding.TotalCarbsQuantifiers.text = "g"

            binding.TotalFatTitle.text = "Total Fat :"
            binding.TotalFat.text = model.fat
            binding.TotalFatQuantifiers.text = "g"

            binding.WeightTransformationTitle.text = "Weight Change :"
            binding.StartWeight.text = model.startWeight
            binding.StartWeightQuantifiers.text = "kg"
            binding.EndWeight.text = model.endWeight
            binding.EndWeightQuantifiers.text = "kg"
        }
    }
}
