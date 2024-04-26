package com.telyu.nourimate.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.core.content.ContextCompat
import com.telyu.nourimate.databinding.GridItemDateBinding
import com.telyu.nourimate.R
import com.telyu.nourimate.fragments.DateItem // Make sure this import points to the correct location of DateItem

class DateAdapter(
    private val context: Context,
    private val datesOfMonth: List<DateItem>,
    private var selectedPosition: Int // Add selectedPosition variable to store the position of the selected date
) : BaseAdapter() {

    override fun getCount(): Int = datesOfMonth.size

    override fun getItem(position: Int): Any = datesOfMonth[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val holder: ViewHolder
        val view: View

        if (convertView == null) {
            val inflater = LayoutInflater.from(context)
            val binding = GridItemDateBinding.inflate(inflater, parent, false)
            view = binding.root
            holder = ViewHolder(binding)
            view.tag = holder
        } else {
            view = convertView
            holder = convertView.tag as ViewHolder
        }

        val dateItem = getItem(position) as DateItem
        holder.binding.dateText.text = dateItem.day

        // Set the color based on whether it's the current month
        val textColorId = if (dateItem.isCurrentMonth) {
            android.R.color.black // Use android's default black
        } else {
            android.R.color.darker_gray // Use android's default dark gray
        }

        holder.binding.dateText.setTextColor(ContextCompat.getColor(context, textColorId))

        // Determine background color based on selection state
        if (dateItem.isSelected) {
            holder.root.setBackgroundResource(R.drawable.circle_background_orange)
        } else {
            holder.root.background = null // Clear background if not selected
        }

        return view
    }

    fun setSelectedPosition(position: Int) {
        selectedPosition = position
        notifyDataSetChanged() // Update the view after changing the selected position
    }

    // ViewHolder pattern with ViewBinding
    private class ViewHolder(val binding: GridItemDateBinding) {
        val root: View = binding.root // Access the root view of the layout
    }
}
