package com.telyu.nourimate.data.remote.response

import com.google.gson.annotations.SerializedName

data class SignupResponse(

	@field:SerializedName("firebaseUid")
	val firebaseUid: String,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("userId")
	val userId: Int
)
