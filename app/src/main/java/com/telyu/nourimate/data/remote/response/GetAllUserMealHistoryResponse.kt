package com.telyu.nourimate.data.remote.response

import com.google.gson.annotations.SerializedName

data class GetAllUserMealHistoryResponse(

	@field:SerializedName("mealHistories")
	val mealHistories: List<MealHistoriesItem>,

	@field:SerializedName("message")
	val message: String
)

data class MealHistoriesItem(

	@field:SerializedName("consumedDate")
	val consumedDate: String,

	@field:SerializedName("consumedTime")
	val consumedTime: String,

	@field:SerializedName("mealHistoryId")
	val id: Int,

	@field:SerializedName("user_id")
	val userId: Int,

	@field:SerializedName("recipeId")
	val recipeId: Int
)
