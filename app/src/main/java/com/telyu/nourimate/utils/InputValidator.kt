package com.telyu.nourimate.utils

import android.util.Patterns

class InputValidator {
    //TODO: Tambahkan fungsi lain kalau butuh validasi untuk input edittext lain
    companion object {
        fun isValidEmail(email: String): Boolean {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        fun isValidPassword(password: String): Boolean {
            return password.length >= 8
        }
    }
}