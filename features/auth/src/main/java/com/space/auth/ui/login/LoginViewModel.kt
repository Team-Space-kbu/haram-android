package com.space.auth.ui.login

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.domain.usecase.auth.AuthStateUseCase
import com.space.shared.model.LoginModel
import com.space.domain.usecase.auth.LoginUseCase
import com.space.navigator.NavigatorMain
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

    private val _loginState = MutableLiveData<Boolean>()
    val loginState: LiveData<Boolean> = _loginState

    init {
        viewModelScope.launch {
            val state = authStateUseCase().successOr(false)
            if (state) {
                Timber.d("Access token valid!!")
                _loginState.value = true
            } else {
                Timber.d("Token not found!")
                _loginState.value = false
            }
        }

    }

    fun login(loginModel: LoginModel) {
        viewModelScope.launch {
            val result = async { loginUseCase(loginModel) }
            _loginState.value = result.await().successOr(false)
        }
    }

    fun makeLoginModel(username: Editable, password: Editable): LoginModel =
        LoginModel(username.toString(), password.toString())


}