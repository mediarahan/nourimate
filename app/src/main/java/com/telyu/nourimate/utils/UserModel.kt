package com.telyu.nourimate.utils

data class UserModel (
    val id: Int?,
    val email: String?,
    val accessToken: String?,
    val refreshToken: String?,
    val isLoggedIn: Boolean = false,
    val isVerified: Boolean = false,
    val isDetailFilled: Boolean = false,
)