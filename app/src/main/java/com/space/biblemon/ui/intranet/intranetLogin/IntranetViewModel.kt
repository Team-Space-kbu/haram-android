package com.space.biblemon.ui.intranet.intranetLogin

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.space.data.ResponseBody
import com.space.data.ResultData
import com.space.data.model.LoginIntranetModel
import com.space.data.res.intranet.IntranetTokenRes
import com.space.domain.usecase.intranet.IntranetRepository
import com.space.biblemon.base.view.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class IntranetViewModel @Inject constructor(
    private val intranetRepository: IntranetRepository,
) : BaseViewModel() {

    private val _loginForm = MutableLiveData<IntranetFormState>()
    val loginForm: LiveData<IntranetFormState> = _loginForm

    fun makeLoginModel(username: Editable, password: Editable): LoginIntranetModel =
        LoginIntranetModel(null, username.toString(), password.toString())

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
                                Timber.d(result.throwable.message.toString())
                                _loginForm.value =
                                    IntranetFormState(loginStatus = false, loginFail = false)
                            }

                            is ResultData.Unauthorized -> {
                                Timber.d(result.throwable.message.toString())
                                _loginForm.value =
                                    IntranetFormState(loginStatus = false, loginFail = false)
                            }
                        }
                    }
                } catch (e: Exception) {
                    Timber.d("인트라넷 관련 서비스 에러 발생  = " + e.message)
                }

            }
        }
    }

}