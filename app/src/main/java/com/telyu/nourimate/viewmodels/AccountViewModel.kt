package com.telyu.nourimate.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.telyu.nourimate.data.repository.NourimateRepository

class AccountViewModel(private val repository: NourimateRepository): ViewModel() {

    val emailAndPhoneNumber: LiveData<Pair<String, String>> = repository.getEmailAndPhoneNumber().asLiveData()


}