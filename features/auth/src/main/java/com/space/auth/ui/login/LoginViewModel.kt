package com.space.auth.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.core_ui.NonParamsItemHandler
import com.space.domain.usecase.auth.AuthStateUseCase
import com.space.shared.model.LoginModel
import com.space.domain.usecase.auth.LoginUseCase
import com.space.navigator.view.NavigatorMain
import com.space.shared.result.successOr
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authStateUseCase: AuthStateUseCase,
    private val loginUseCase: LoginUseCase
) : ViewModel() {


    @Inject
    lateinit var navigatorMain: NavigatorMain

    private val _loginState = MutableLiveData(LoginStatus.EMPTY)
    val loginState: LiveData<LoginStatus> = _loginState

    val username = MutableLiveData<String>()
    val passwd = MutableLiveData<String>()

    val onClick = NonParamsItemHandler {
        if (username.value.isNullOrBlank() || passwd.value.isNullOrBlank()) {
            _loginState.value = LoginStatus.FAIL
        } else {
            login(LoginModel(username.value, passwd.value))
        }
    }

    init {
        viewModelScope.launch {
            val state = authStateUseCase().successOr(LoginStatus.EMPTY)
            if (state == LoginStatus.Success) {
                Timber.d("Access token valid!!, But why did I end up here?")
                _loginState.value = LoginStatus.Success
            }
        }

    }

    private fun login(loginModel: LoginModel) {
        viewModelScope.launch {
            val result = async { loginUseCase(loginModel) }.await()
            if (result.successOr(false)) {
                _loginState.value = LoginStatus.Success
            } else {
                _loginState.value = LoginStatus.FAIL
            }
        }
    }

}