package com.telyu.nourimate.viewmodels

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class ProfileViewModel : ViewModel() {
    fun onSignOutButtonClick() {
        // Implement your sign-out logic here
        // For example, if you're using Firebase Authentication:
        FirebaseAuth.getInstance().signOut()
    }}
