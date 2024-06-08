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
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.concurrent.TimeUnit

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
        if (id == R.id.passwordEditText || id == R.id.editTextPassword || id == R.id.editTextConfirmPassword || id == R.id.editTextCurrentPassword || id == R.id.editTextNewPassword) {
            error = if (s.isEmpty()){
                context.getString(R.string.errorEmptyPassword)
            } else if (s.length < 8) {
                context.getString(R.string.errorLengthPassword)
            } else if (!s.matches(".*\\d.*".toRegex())) {
                context.getString(R.string.errorNumberPassword)
            }else {
                null
            }
        }
        if (id == R.id.emailEditText || id == R.id.editTextEmail || id == R.id.verifyEmailEditText) {
            val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
            error = if (s.isEmpty()){
                context.getString(R.string.errorEmptyEmail)
            } else if (!s.matches(emailPattern.toRegex())) {
                context.getString(R.string.errorInvalidEmail)
            }else {
                null
            }
        }
        if (id == R.id.verifyEditText) {
            error = if (s.isEmpty()){
                context.getString(R.string.errorEmptyCode)
            } else if (s.length < 6) {
                context.getString(R.string.errorLengthCode)
            }else {
                null
            }
        }
        if (id == R.id.editTextFullName) {
            error = if (s.isEmpty()){
                context.getString(R.string.errorEmptyName)
            } else {
                null
            }
        }
        if (id == R.id.editTextPhone || id == R.id.verifyPhoneNumberEditText || id == R.id.editTextCurrentNumber || id == R.id.editTextConfirmNewNumber) {
            error = if (s.isEmpty()){
                context.getString(R.string.errorEmptyPhoneNumber)
            } else if (s.length < 10) {
                context.getString(R.string.errorLengthPhoneNumber)
            }else {
                null
            }
        }
//        if (id == R.id.editTextDateOfProgram) {
//            if (s.isEmpty()) {
//                error = context.getString(R.string.emptyDop)
//            }  else {
//                error = null
//            }
//        }
//        when (id) {
//            R.id.editTextHeight, R.id.editTextWeight, R.id.editTextWaist -> {
//                val isValid = s.toString().toFloatOrNull()?.let { it > 0 } ?: false
//                val errorResId = when (id) {
//                    R.id.editTextHeight -> R.string.errorEmptyName
//                    R.id.editTextWeight -> R.string.errorEmptyName
//                    R.id.editTextWaist -> R.string.errorEmptyName
//                    else -> null
//                }
//                errorResId?.let {
//                    (parent.parent as? TextInputLayout)?.error = if (isValid) null else context.getString(it)
//                }
//            }
//            R.id.editTextBirth -> {
//                val isValid = validateDate(s.toString(), false)  // Assuming you have a validateDate function
//                (parent.parent as? TextInputLayout)?.error = if (isValid) null else context.getString(R.string.errorEmptyName)
//            }
//            R.id.editTextDateOfProgram -> {
//                val isValid = validateDateRange(s.toString())  // Assuming you have a validateDateRange function
//                (parent.parent as? TextInputLayout)?.error = if (isValid) null else context.getString(R.string.errorEmptyName)
//            }
        // Continue adding other cases as necessary
//        }


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

    companion object {
        fun validateDate(dateStr: String, allowFuture: Boolean): Boolean {
            val sdf = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
            sdf.isLenient = false  // Set to false to prevent dates like February 30th from being parsed.
            try {
                val date = sdf.parse(dateStr)
                return if (date != null) {
                    if (!allowFuture) {
                        // Check if the date is in the past or today.
                        val today = Calendar.getInstance()
                        today.set(Calendar.HOUR_OF_DAY, 0)
                        today.set(Calendar.MINUTE, 0)
                        today.set(Calendar.SECOND, 0)
                        today.set(Calendar.MILLISECOND, 0)
                        !date.after(today.time)  // Returns true if the date is not after today.
                    } else {
                        true  // If future dates are allowed, just the existence of a valid date is enough.
                    }
                } else {
                    false
                }
            } catch (e: ParseException) {
                // If the date couldn't be parsed, return false.
                return false
            }
        }

        fun validateDateRange(dates: String): Boolean {
            val dateRange = dates.split(" to ")
            return if (dateRange.size == 2) {
                val sdf = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
                sdf.isLenient = false
                try {
                    val startDate = sdf.parse(dateRange[0])
                    val endDate = sdf.parse(dateRange[1])
                    startDate != null && endDate != null && !endDate.before(startDate) &&
                            TimeUnit.MILLISECONDS.toDays(endDate.time - startDate.time) >= 28
                } catch (e: ParseException) {
                    false
                }
            } else {
                false
            }
        }
    }

}