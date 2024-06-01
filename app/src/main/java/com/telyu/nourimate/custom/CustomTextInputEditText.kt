package com.telyu.nourimate.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.telyu.nourimate.R

class CustomTextInputEditText : TextInputEditText, View.OnTouchListener {

    private lateinit var clearButtonImage: Drawable

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
        clearButtonImage =
            ContextCompat.getDrawable(context, R.drawable.delete) as Drawable

        setOnTouchListener(this)

        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.isNotEmpty()) showClearButton() else hideClearButton()
                handleValidation(s)
            }

            override fun afterTextChanged(s: Editable) {}
        })
    }

    private fun handleValidation(s: CharSequence) {
        if (id == R.id.passwordEditText || id == R.id.editTextPassword || id == R.id.editTextConfirmPassword) {
            if (s.isEmpty()){
                error = context.getString(R.string.errorEmptyPassword)
            } else if (s.length < 8) {
                error = context.getString(R.string.errorLengthPassword)
            } else if (!s.matches(".*\\d.*".toRegex())) {
                error = context.getString(R.string.errorNumberPassword)
            }else {
                error = null
            }
        }
        if (id == R.id.emailEditText || id == R.id.editTextEmail || id == R.id.verifyEmailEditText) {
            val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
            if (s.isEmpty()){
                error = context.getString(R.string.errorEmptyEmail)
            } else if (!s.matches(emailPattern.toRegex())) {
                error = context.getString(R.string.errorInvalidEmail)
            }else {
                error = null
            }
        }
        if (id == R.id.verifyEditText) {
            if (s.isEmpty()){
                error = context.getString(R.string.errorEmptyCode)
            } else if (s.length < 6) {
                error = context.getString(R.string.errorLengthCode)
            }else {
                error = null
            }
        }
        if (id == R.id.editTextFullName) {
            if (s.isEmpty()){
                error = context.getString(R.string.errorEmptyName)
            } else {
                error = null
            }
        }
        if (id == R.id.editTextPhone || id == R.id.verifyPhoneNumberEditText) {
            if (s.isEmpty()){
                error = context.getString(R.string.errorEmptyPhoneNumber)
            } else if (s.length < 10) {
                error = context.getString(R.string.errorLengthPhoneNumber)
            }else {
                error = null
            }
        }
        if (id == R.id.editTextDateOfProgram) {
            val dopPattern = "\\d{4}/\\d{2}/\\d{2} to \\d{4}/\\d{2}/\\d{2}"
            if (text.isNullOrEmpty()) {
                (parent.parent as? TextInputLayout)?.error = context.getString(R.string.emptyDop)
            } else if (!(text?.matches(dopPattern.toRegex()) ?: false)) {
                (parent.parent as? TextInputLayout)?.error = context.getString(R.string.errorDop)
            } else {
                (parent.parent as? TextInputLayout)?.error = null
            }
        }


    }

    private fun showClearButton() {
        setButtonDrawables(endOfTheText = clearButtonImage)
    }

    private fun hideClearButton() {
        setButtonDrawables()
    }

    private fun setButtonDrawables(
        startOfTheText: Drawable? = null,
        topOfTheText: Drawable? = null,
        endOfTheText: Drawable? = null,
        bottomOfTheText: Drawable? = null
    ) {
        setCompoundDrawablesWithIntrinsicBounds(
            startOfTheText,
            topOfTheText,
            endOfTheText,
            bottomOfTheText
        )
    }

    override fun onTouch(v: View?, event: MotionEvent): Boolean {
        if (compoundDrawables[2] != null) {
            val clearButtonStart: Float
            val clearButtonEnd: Float
            var isClearButtonClicked = false

            if (layoutDirection == View.LAYOUT_DIRECTION_RTL) {
                clearButtonEnd = (clearButtonImage.intrinsicWidth + paddingStart).toFloat()
                when {
                    event.x < clearButtonEnd -> isClearButtonClicked = true
                }
            } else {
                clearButtonStart = (width - paddingEnd - clearButtonImage.intrinsicWidth).toFloat()
                when {
                    event.x > clearButtonStart -> isClearButtonClicked = true
                }
            }
            if (isClearButtonClicked) {
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        clearButtonImage = ContextCompat.getDrawable(
                            context,
                            R.drawable.delete
                        ) as Drawable
                        showClearButton()
                        return true
                    }

                    MotionEvent.ACTION_UP -> {
                        clearButtonImage = ContextCompat.getDrawable(
                            context,
                            R.drawable.delete
                        ) as Drawable
                        when {
                            text != null -> text?.clear()
                        }
                        hideClearButton()
                        return true
                    }

                    else -> return false
                }
            } else return false
        }
        return false
    }
}
