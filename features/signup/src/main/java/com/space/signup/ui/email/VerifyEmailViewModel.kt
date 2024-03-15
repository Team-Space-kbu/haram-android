package com.space.signup.ui.email

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VerifyEmailViewModel @Inject constructor(

) : ViewModel() {

    val email = MutableLiveData<String>()
    val emailVerify = MutableLiveData<String>()

}