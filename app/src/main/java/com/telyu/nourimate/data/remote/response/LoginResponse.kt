package com.telyu.nourimate.data.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("accessToken")
	val accessToken: String,

	@field:SerializedName("user")
	val user: User,

	@field:SerializedName("refreshToken")
	val refreshToken: String
)

data class User(

	@field:SerializedName("password")
	val password: String,

	@field:SerializedName("phoneNumber")
	val phoneNumber: String,

	@field:SerializedName("email_verified")
	val emailVerified: Int,

	@field:SerializedName("phone_verified")
	val phoneVerified: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("userId")
	val userId: Int,

	@field:SerializedName("email")
	val email: String
)
