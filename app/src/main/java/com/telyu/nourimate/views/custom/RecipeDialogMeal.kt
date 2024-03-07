package com.telyu.nourimate.views.custom

import android.app.Dialog
import android.content.Context
import android.widget.TextView
import com.telyu.nourimate.R

class RecipeDialogMeal (context: Context): Dialog(context) {


    init {
        setContentView(R.layout.popup_layout)

        val breakfastTextView = findViewById<TextView>(R.id.breakfastTextView)
        val lunchTextView = findViewById<TextView>(R.id.lunchTextView)
        val dinnerTextView = findViewById<TextView>(R.id.dinnerTextView)

        breakfastTextView.setOnClickListener {
            // Handle breakfast click
        }

        lunchTextView.setOnClickListener {
            // Handle lunch click
        }

        dinnerTextView.setOnClickListener {

        }
    }

    fun showDialog() {
        show()
    }


}