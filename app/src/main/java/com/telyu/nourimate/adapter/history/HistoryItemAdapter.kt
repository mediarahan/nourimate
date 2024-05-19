package com.telyu.nourimate.adapter.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.telyu.nourimate.R
import com.telyu.nourimate.data.local.models.History
import com.telyu.nourimate.databinding.EachHistoryItemBinding

class HistoryItemAdapter(private var mList: List<History>) : RecyclerView.Adapter<HistoryItemAdapter.HistoryItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryItemViewHolder {
        val binding = EachHistoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryItemViewHolder, position: Int) {
        val model = mList[position]
        holder.bind(model)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    inner class HistoryItemViewHolder(private val binding: EachHistoryItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: History) {
            binding.itemTv.text = model.programName
            binding.startDateTv.text = "${model.startDate}"
            binding.endDateTv.text = "${model.endDate}"

            val isExpandable = model.isExpandable
            binding.expandableLayout.visibility = if (isExpandable) View.VISIBLE else View.GONE

            binding.arroImageview.setImageResource(if (isExpandable) R.drawable.arrow else R.drawable.arrow_drop)

            val adapter = NestedHistoryAdapter(model.nestedItems)
            binding.childRv.layoutManager = LinearLayoutManager(binding.root.context)
            binding.childRv.setHasFixedSize(true)
            binding.childRv.adapter = adapter

            binding.linearLayout.setOnClickListener {
                model.isExpandable = !model.isExpandable
                notifyItemChanged(adapterPosition)
            }
        }
    }
}
