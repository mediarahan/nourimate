package com.telyu.nourimate.data.remote.response

import com.google.gson.annotations.SerializedName

data class SigninResponse(

	@field:SerializedName("accessToken")
	val accessToken: String,

	@field:SerializedName("user")
	val user: User,

	@field:SerializedName("refreshToken")
	val refreshToken: String
)

data class User(

	@field:SerializedName("isDetailFilled")
	val isDetailFilled: Int,

	@field:SerializedName("phoneNumber")
	val phoneNumber: String,

	@field:SerializedName("user_id")
	val userId: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("is_verified")
	val isVerified: Int
)
