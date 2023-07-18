package com.space.haram_android.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.data.model.LoginModel
import com.space.domain.usecase.login.AuthRepository
import com.space.haram_android.adapter.KeyEventListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: AuthRepository,
) : ViewModel() {
    private val _loginEvent = MutableLiveData<Boolean>()
    val loginEvent: LiveData<Boolean> = _loginEvent

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    val bindingListener = object : KeyEventListener {
        override fun keyEvent() {
            _loginEvent.value = true
        }

        override fun keyEventEnd() {
            _loginEvent.value = false
        }
    }

    fun makeLoginModel(username: String, password: String): LoginModel =
        LoginModel(username, password)


    init {
        if (loginUseCase.getToken() != null) {
            Log.d("LoginViewModel", "Access token valid!!")
            _loginForm.value = LoginFormState(isTokenValid = true)
        } else {
            Log.d("LoginViewModel", "Access token null!!")
            _loginForm.value = LoginFormState(isTokenValid = false)
        }
    }

    fun biblemonLogin(loginModel: LoginModel) {
        viewModelScope.launch {
            loginUseCase.getSpaceAuthToken(loginModel).let {
                when (it.code) {
                    "PA01" -> {
                        loginUseCase.setToken(it.data)
                        loginUseCase.saveLogin(loginModel)
                        _loginForm.value = LoginFormState(statusLogin = true, loginFail = true)
                    }

                    "USER01" -> {
                        Log.d("LoginViewModel", "User id fail!!")
                        _loginForm.value = LoginFormState(loginFail = false)
                    }

                    "USER02" -> {
                        Log.d("LoginViewModel", "User pw fail!!")
                        _loginForm.value = LoginFormState(loginFail = false)
                    }

                    else -> {
                        Log.d("LoginViewModel", "Login Data Error!!")
                        _loginForm.value = LoginFormState(statusLogin = false)
                    }
                }
            }
        }

    }


}