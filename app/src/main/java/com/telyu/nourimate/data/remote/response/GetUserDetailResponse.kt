package com.telyu.nourimate.data.remote.response

import com.google.gson.annotations.SerializedName

data class GetUserDetailResponse(

	@field:SerializedName("allergen")
	val allergen: String,

	@field:SerializedName("disease")
	val disease: String,

	@field:SerializedName("gender")
	val gender: String,

	@field:SerializedName("user_id")
	val userId: Int,

	@field:SerializedName("dob")
	val dob: String,

	@field:SerializedName("waistSize")
	val waistSize: Int,

	@field:SerializedName("weight")
	val weight: Int,

	@field:SerializedName("detail_id")
	val detailId: Int,

	@field:SerializedName("height")
	val height: Int
)
