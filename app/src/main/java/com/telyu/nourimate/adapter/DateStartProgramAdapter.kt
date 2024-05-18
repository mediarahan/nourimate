package com.telyu.nourimate.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.telyu.nourimate.databinding.GridItemDateBinding
import com.telyu.nourimate.R
import com.telyu.nourimate.custom.DateItem

class DateStartProgramAdapter(
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
        holder.binding.dateText.textSize = 14f
        holder.binding.dateText.typeface = ResourcesCompat.getFont(context, R.font.robotomedium)

        // Set text color based on whether the day is selectable
        if (!dateItem.isCurrentMonth) {
            // Set the text color to grey for dates not in the current month
            holder.binding.dateText.setTextColor(ContextCompat.getColor(context, android.R.color.darker_gray))
        } else if (!dateItem.isSelectable) {
            // Set the text color to grey for dates that are not selectable
            holder.binding.dateText.setTextColor(ContextCompat.getColor(context, android.R.color.darker_gray))
        } else {
            // Set the text color to black for dates that are selectable and in the current month
            holder.binding.dateText.setTextColor(ContextCompat.getColor(context, android.R.color.black))
        }


        // Determine background color based on selection state
        if (dateItem.isSelected) {
            holder.root.setBackgroundResource(R.drawable.circle_background_orange)
        } else {
            holder.root.background = null
        }

        // Disable click on past dates
        holder.root.isEnabled = dateItem.isSelectable

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