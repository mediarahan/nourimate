package com.telyu.nourimate.views.custom

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import androidx.databinding.DataBindingUtil.setContentView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.telyu.nourimate.R

class RecipeDialog(context: Context, layoutResId: Int) : Dialog(context) {


    init {
        setContentView(layoutResId)

        // Adjust dialog properties
        val layoutParams = window?.attributes
        layoutParams?.apply {
            width = WindowManager.LayoutParams.MATCH_PARENT
            height = WindowManager.LayoutParams.WRAP_CONTENT
            gravity = Gravity.CENTER
        }

        window?.attributes = layoutParams
    }

    // Example function to modify dialog views
    fun setDialogTitle(title: String) {
    }

}


//    init {
//        setContentView(R.layout.popup_layout)
//
//        val breakfastTextView = findViewById<TextView>(R.id.breakfastTextView)
//        val lunchTextView = findViewById<TextView>(R.id.lunchTextView)
//        val dinnerTextView = findViewById<TextView>(R.id.dinnerTextView)
//
//        breakfastTextView.setOnClickListener {
//            // Handle breakfast click
//        }
//
//        lunchTextView.setOnClickListener {
//            // Handle lunch click
//        }
//
//        dinnerTextView.setOnClickListener {
//
//        }
//    }
//
//    fun showDialog() {
//        show()
//    }
