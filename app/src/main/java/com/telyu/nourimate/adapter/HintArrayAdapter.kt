package com.telyu.nourimate.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.telyu.nourimate.R

class HintArrayAdapter(context: Context, resource: Int, objects: Array<String>) : ArrayAdapter<String>(context, resource, objects) {
    private var hintResource = resource

    override fun isEnabled(position: Int): Boolean {
        // Make the first item (hint) disabled
        return position != 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getView(position, convertView, parent) as TextView
        if (position == 0) {
            // Set the hint color from colors.xml for the first item
            view.setTextColor(ContextCompat.getColor(parent.context, R.color.color41))
        } else {
            // Optional: Set regular text color for other items if necessary
            view.setTextColor(ContextCompat.getColor(parent.context, R.color.color41))
        }
        // Set font and size for all items
        val typeface: Typeface? = ResourcesCompat.getFont(context, R.font.abel)
        view.typeface = typeface
        view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)

        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getDropDownView(position, convertView, parent) as TextView
        if (position == 0) {
            // Set hint color and font for the first item
            view.setTextColor(Color.GRAY)
            view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
        } else {
            // Set regular text color and font for other items
            view.setTextColor(Color.BLACK)
            view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
        }
        // Custom font for dropdown view
        val typeface: Typeface? = ResourcesCompat.getFont(context, R.font.abel)
        view.typeface = typeface

        return view
    }
}
