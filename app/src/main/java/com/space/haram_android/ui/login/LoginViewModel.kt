package com.space.haram_android.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.haram_android.data.response.LoginToken
import com.space.haram_android.repository.login.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : ViewModel() {
    private val _users: MutableLiveData<LoginToken?> = MutableLiveData<LoginToken?>();
    private val userModel : MutableLiveData<LoginToken?> = _users

    init {
        login()
    }

    fun login() {
        viewModelScope.launch {
            _users.value = loginRepository.getSpaceAuthToken().data
        }
    }

    fun getUsers() = userModel

}