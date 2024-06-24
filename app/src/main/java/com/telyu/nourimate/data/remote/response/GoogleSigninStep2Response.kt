package com.telyu.nourimate.data.remote.response

import com.google.gson.annotations.SerializedName

data class GoogleSigninStep2Response(

	@field:SerializedName("expiresIn")
	val expiresIn: String,

	@field:SerializedName("kind")
	val kind: String,

	@field:SerializedName("idToken")
	val idToken: String,

	@field:SerializedName("isNewUser")
	val isNewUser: Boolean,

	@field:SerializedName("refreshToken")
	val refreshToken: String
)
