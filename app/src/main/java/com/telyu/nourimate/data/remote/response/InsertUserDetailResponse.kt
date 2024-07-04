package com.telyu.nourimate.data.remote.response

import com.google.gson.annotations.SerializedName

data class InsertUserDetailResponse(

	@field:SerializedName("message")
	val message: String
)
