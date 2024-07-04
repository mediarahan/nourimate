package com.telyu.nourimate.data.remote.response

import com.google.gson.annotations.SerializedName

data class GoogleSigninStep3Response(

	@field:SerializedName("accessToken")
	val accessToken: String,

	@field:SerializedName("user")
	val user: Userr,

	@field:SerializedName("refreshToken")
	val refreshToken: String
)

data class Userr(

	@field:SerializedName("isDetailFilled")
	val isDetailFilled: Int,

	@field:SerializedName("uid")
	val uid: String,

	@field:SerializedName("phoneNumber")
	val phoneNumber: String,

	@field:SerializedName("user_id")
	val userId: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("is_verified")
	val isVerified: Int,

	@field:SerializedName("email")
	val email: String
)
