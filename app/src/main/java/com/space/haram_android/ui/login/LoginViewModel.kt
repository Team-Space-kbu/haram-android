package com.space.haram_android.ui.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.haram_android.R
import com.space.haram_android.data.model.LoginModel
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
    val userModel: MutableLiveData<LoginToken?> = _users

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    fun login(loginModel: LoginModel) {
        viewModelScope.launch {
            _users.value = loginRepository.getSpaceAuthToken(loginModel).data
        }
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isInputValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        }else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    private fun isInputValid(username: String): Boolean {
        return if (username.contains("[^A-Za-z0-9]")) {
            true
        } else {
            username.isNotBlank()
        }
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }

}