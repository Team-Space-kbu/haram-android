package com.space.auth.ui

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.auth.base.listener.KeyEventListener
import com.space.shared.model.LoginModel
import com.space.domain.usecase.auth.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: AuthUseCase,
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

    fun makeLoginModel(username: Editable, password: Editable): LoginModel =
        LoginModel(username.toString(), password.toString())


    init {
        if (loginUseCase.getToken() != null) {
            Timber.d("Access token valid!!")
            _loginForm.value = LoginFormState(isTokenValid = true)
        } else {
            Timber.d("Access token null!!")
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
                        Timber.d("User id fail!!")
                        _loginForm.value = LoginFormState(loginFail = false)
                    }

                    "USER02" -> {
                        Timber.d("User pw fail!!")
                        _loginForm.value = LoginFormState(loginFail = false)
                    }

                    else -> {
                        Timber.d("Login Data Error!!")
                        _loginForm.value = LoginFormState(statusLogin = false)
                    }
                }
            }
        }

    }


}