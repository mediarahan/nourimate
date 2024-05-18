package com.telyu.nourimate.custom

data class DateItem(
    val day: String,
    val isCurrentMonth: Boolean,
    var isSelected: Boolean = false,  // Declare as var to allow reassignment
    var isEnabled: Boolean = true,  // Ensure isEnabled is also mutable if needed
    var isSelectable: Boolean = true,
    var isUnderAge: Boolean = false,
    var isPreviousMonth: Boolean = false,
    var isNextMonth: Boolean = false
)