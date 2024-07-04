package com.telyu.nourimate.data.remote.response

import com.google.gson.annotations.SerializedName

data class GetAllHistoryResponse(

	@field:SerializedName("histories")
	val histories: List<HistoriesItem>,

	@field:SerializedName("message")
	val message: String
)

data class HistoriesItem(
	@SerializedName("id")
	val id: Int,

	@SerializedName("program_name")
	val programName: String,

	@SerializedName("start_date")
	val startDate: String,

	@SerializedName("end_date")
	val endDate: String,

	@SerializedName("calories")
	val calories: Int,

	@SerializedName("protein")
	val protein: Int,

	@SerializedName("fat")
	val fat: Int,

	@SerializedName("carbs")
	val carbs: Int,

	@SerializedName("start_weight")
	val startWeight: Int,

	@SerializedName("end_weight")
	val endWeight: Int,

	@SerializedName("user_id")
	val userId: Int,

	@SerializedName("created_at")
	val createdAt: Long
)
