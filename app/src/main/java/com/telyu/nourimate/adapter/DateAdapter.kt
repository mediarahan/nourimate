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
import com.telyu.nourimate.utils.DateItem

class DateAdapter(
    private val context: Context,
    private val datesOfMonth: List<DateItem>,
    private var selectedPosition: Int
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
        val typeface = ResourcesCompat.getFont(context, R.font.robotomedium)
        holder.binding.dateText.typeface = typeface

        val textColorId = if (dateItem.isUnderAge) {
            android.R.color.darker_gray
        } else if (dateItem.isCurrentMonth) {
            android.R.color.black
        } else {
            android.R.color.darker_gray
        }

        holder.binding.dateText.setTextColor(ContextCompat.getColor(context, textColorId))

        if (dateItem.isSelected) {
            holder.root.setBackgroundResource(R.drawable.circle_background_orange)
        } else {
            holder.root.background = null
        }

        return view
    }

    fun setSelectedPosition(position: Int) {
        selectedPosition = position
        notifyDataSetChanged()
    }

    private class ViewHolder(val binding: GridItemDateBinding) {
        val root: View = binding.root
    }
}