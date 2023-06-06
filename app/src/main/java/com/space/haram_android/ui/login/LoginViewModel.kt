package com.space.haram_android.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.haram_android.R
import com.space.haram_android.common.data.model.LoginModel
import com.space.haram_android.repository.login.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {
    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    init {
        if (authRepository.getToken() != null) {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    fun login(loginModel: LoginModel) {
        viewModelScope.launch {
            val res = authRepository.getSpaceAuthToken(loginModel)
            if (res.code == "PA01") {
                authRepository.setToken(res.data)
                _loginForm.value = LoginFormState(statusLogin = true)
            }else{
                _loginForm.value = LoginFormState(statusLogin = false)
            }
        }
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isInputValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
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