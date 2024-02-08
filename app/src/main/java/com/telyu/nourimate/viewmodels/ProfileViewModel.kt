package com.telyu.nourimate.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.telyu.nourimate.data.repository.NourimateRepository
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: NourimateRepository) : ViewModel() {
    fun onSignOutButtonClick() {
        // Implement your sign-out logic here
        // For example, if you're using Firebase Authentication:
        FirebaseAuth.getInstance().signOut()
    }

    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }

}
