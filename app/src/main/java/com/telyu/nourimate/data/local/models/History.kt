package com.telyu.nourimate.data.local.models

data class History(
    val programName: String,
    val startDate: String,
    val endDate: String,
    val nestedItems: List<NestedHistory>,
    var isExpandable: Boolean = false
)