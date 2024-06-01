package com.telyu.nourimate.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.SpinnerAdapter
import com.google.android.material.textfield.TextInputLayout
import androidx.core.content.ContextCompat
import com.telyu.nourimate.R

class CustomSpinner : androidx.appcompat.widget.AppCompatSpinner, View.OnTouchListener, AdapterView.OnItemSelectedListener {

    private lateinit var clearButtonImage: Drawable
    private var adapter: ArrayAdapter<*>? = null
    private var isTouched = false // To track whether the spinner was touched

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
        clearButtonImage = ContextCompat.getDrawable(context, R.drawable.delete) as Drawable
        setOnTouchListener(this)
        onItemSelectedListener = this
    }

    override fun setAdapter(adapter: SpinnerAdapter?) {
        super.setAdapter(adapter)
        this.adapter = adapter as ArrayAdapter<*>?
    }

    override fun onTouch(v: View?, event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> isTouched = true
        }
        return false
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        clearButtonImage.setBounds(
            width - paddingRight - clearButtonImage.intrinsicWidth,
            (height - clearButtonImage.intrinsicHeight) / 2,
            width - paddingRight,
            (height + clearButtonImage.intrinsicHeight) / 2
        )
        clearButtonImage.draw(canvas)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (isTouched) {
            isTouched = false // Reset the touch flag after a selection is made
            validateSelection(position)
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        if (isTouched) {
            // User did not select any item after touching the spinner
            val textInputLayout = parent?.parent as? TextInputLayout
            textInputLayout?.error = "You have to choose a program"
            isTouched = false // Reset the touch flag
        }
    }

    private fun validateSelection(position: Int) {
        val item = adapter?.getItem(position).toString()
        val textInputLayout = parent as? TextInputLayout
        if (item == "Select item") {
            textInputLayout?.error = "Please select a valid item."
        } else {
            textInputLayout?.error = null
        }
    }
}
