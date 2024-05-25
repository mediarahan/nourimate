package com.telyu.nourimate.adapter.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.telyu.nourimate.data.local.models.History
import com.telyu.nourimate.databinding.EachHistoryItemBinding

class HistoryItemAdapter : ListAdapter<History, HistoryItemAdapter.HistoryItemViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryItemViewHolder {
        val binding = EachHistoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryItemViewHolder, position: Int) {
        val model = getItem(position)
        holder.bind(model)
    }

    inner class HistoryItemViewHolder(private val binding: EachHistoryItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: History) {
            binding.itemTv.text = item.programName
            binding.startDateTv.text = item.startDate
            binding.endDateTv.text = item.endDate
            binding.TotalCalories.text = item.calories.toString()
            binding.TotalProtein.text = item.protein.toString()
            binding.TotalFat.text = item.fat.toString()
            binding.TotalCarbs.text = item.carbs.toString()
            binding.StartWeight.text = item.startWeight.toString()
            binding.EndWeight.text = item.endWeight.toString()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<History>() {
        override fun areItemsTheSame(oldItem: History, newItem: History): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: History, newItem: History): Boolean {
            return oldItem == newItem
        }
    }
}
