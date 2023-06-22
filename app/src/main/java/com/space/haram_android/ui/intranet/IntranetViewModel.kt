package com.space.haram_android.ui.intranet

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.stream.MalformedJsonException
import com.space.haram_android.common.data.ResultData
import com.space.haram_android.common.data.model.LoginIntranetModel
import com.space.haram_android.common.data.response.intranet.IntranetTokenRes
import com.space.haram_android.repository.ResponseBody
import com.space.haram_android.repository.intranet.IntranetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class IntranetViewModel @Inject constructor(
    private val intranetRepository: IntranetRepository,
) : ViewModel() {
    val onKeyboardEnterActionEvent = MutableLiveData<Unit>()
    val onKeyboardDoneActionEvent = MutableLiveData<Unit>()

    private val _loginStatus = MutableLiveData(false)
    val loginStatus: LiveData<Boolean> = _loginStatus

    private val _loginDataStatus = MutableLiveData<Boolean>()
    val loginDataStatus: LiveData<Boolean> = _loginDataStatus

    private var classType: Fragment? = null

    init {
        intranetRepository.getIntranetTokenData().run {
            if (this.token == null || this.xsrf_token == null || this.laravel_session == null) {
                _loginDataStatus.value = false
            } else {
                val res = runBlocking {
                    intranetRepository.isInvalidToken(this@run)
                }
                res.run {
                    when (this) {
                        is ResultData.Success<Boolean> -> _loginDataStatus.value = true
                        else -> {
                            login(intranetRepository.getIntranetIdModel())
                        }
                    }
                }
            }

        }
    }

    fun login(
        intranetModel: LoginIntranetModel
    ) {
        viewModelScope.launch {
            intranetRepository.getIntranetToken().run {
                when (this) {
                    is ResultData.Success<ResponseBody<IntranetTokenRes>> -> {
                        this.body.data.token?.let { intranetModel.setToken(it) }
                        return@run this
                    }

                    else -> {
                        _loginStatus.value = false
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
                                _loginStatus.value = true
                            }

                            is ResultData.Error -> {
                                Log.d("IntranetService", result.throwable.message.toString())
                                _loginStatus.value = false
                            }

                            else -> {}
                        }
                    }
                } catch (e: MalformedJsonException) {
                    Log.d("[Intranet]", "변환코드 오류 발생  = ${e.message}")
                } catch (e: Exception) {
                    Log.d("[Intranet]", "인트라넷 관련 서비스 에러 발생  = ${e.message}")
                }

            }
        }
    }

    fun getClassType() = classType

    fun setClassType(fragment: Fragment) {
        classType = fragment
    }
}