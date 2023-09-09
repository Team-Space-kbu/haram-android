package com.space.haram_android.ui.intranet

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.stream.MalformedJsonException
import com.space.data.ResponseBody
import com.space.data.ResultData
import com.space.data.model.LoginIntranetModel
import com.space.data.model.LoginModel
import com.space.data.response.intranet.IntranetTokenRes
import com.space.domain.usecase.intranet.IntranetRepository
import com.space.haram_android.adapter.KeyEventListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class IntranetViewModel @Inject constructor(
    private val intranetRepository: IntranetRepository,
) : ViewModel() {

    private val _loginForm = MutableLiveData<IntranetFormState>()
    val loginForm: LiveData<IntranetFormState> = _loginForm

    private val _loginKeyEvent = MutableLiveData(false)
    val loginEvent: LiveData<Boolean> = _loginKeyEvent

    private val _loginBackEvent = MutableLiveData<Boolean>()
    val loginBackEvent: LiveData<Boolean> = _loginBackEvent

    val bindingListener = object : KeyEventListener {
        override fun keyEvent() {
            _loginKeyEvent.value = true
        }

        override fun keyEventEnd() {
            _loginKeyEvent.value = false
        }
    }

    fun backEvent() {
        _loginBackEvent.value = true
    }

    fun makeLoginModel(username: String, password: String): LoginIntranetModel =
        LoginIntranetModel(null, username, password)

    init {
        intranetRepository.getIntranetTokenData().run {
            if (token == null || xsrf_token == null || laravel_session == null) {
                _loginForm.value = IntranetFormState(loginDataStatus = false)
            } else {
                val res = runBlocking {
                    intranetRepository.isInvalidToken(this@run)
                }
                res.run {
                    when (this) {
                        is ResultData.Success<Boolean> ->
                            _loginForm.value = IntranetFormState(loginDataStatus = true)

                        else -> {
                            intranetLogin(intranetRepository.getIntranetIdModel())
                        }
                    }
                }
            }
        }
    }

    fun intranetLogin(
        intranetModel: LoginIntranetModel
    ) {
        viewModelScope.launch {
            intranetRepository.getIntranetToken().run {
                when (this) {
                    is ResultData.Success<ResponseBody<IntranetTokenRes>> -> {
                        body.data.token?.let { intranetModel.setToken(it) }
                        return@run this
                    }

                    else -> {
                        _loginForm.value = IntranetFormState(loginStatus = false, loginFail = false)
                        return@launch
                    }
                }
            }.let {
                try {
                    intranetRepository.getIntranetLogin(intranetModel, it.body.data).let { result ->
                        when (result) {
                            is ResultData.Success<Boolean> -> {
                                intranetRepository.saveIntranetModel(intranetModel)
                                intranetRepository.saveIntranetToken(it.body.data)
                                _loginForm.value =
                                    IntranetFormState(loginStatus = true, loginFail = true)
                            }

                            is ResultData.Error -> {
                                Log.d("IntranetServiceError", result.throwable.message.toString())
                                _loginForm.value =
                                    IntranetFormState(loginStatus = false, loginFail = false)
                            }

                            is ResultData.Unauthorized -> {
                                Log.d(
                                    "IntranetServiceUnauthorized",
                                    result.throwable.message.toString()
                                )
                                _loginForm.value =
                                    IntranetFormState(loginStatus = false, loginFail = false)
                            }
                        }
                    }
                } catch (e: MalformedJsonException) {
                    Log.d("[Intranet]", "변환 코드 오류 발생  = ${e.message}")
                } catch (e: Exception) {
                    Log.d("[Intranet]", "인트라넷 관련 서비스 에러 발생  = ${e.message}")
                }

            }
        }
    }

}