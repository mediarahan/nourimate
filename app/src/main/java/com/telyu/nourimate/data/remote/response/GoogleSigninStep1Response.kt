package com.telyu.nourimate.data.remote.response

import com.google.gson.annotations.SerializedName

data class GoogleSigninStep1Response(

	@field:SerializedName("customToken")
	val customToken: String
)
