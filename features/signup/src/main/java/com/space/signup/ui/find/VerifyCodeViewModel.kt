package com.space.signup.ui.find

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.domain.singup.FindPwVerifyUseCase
import com.space.shared.UiStatus
import com.space.shared.UiStatusType
import com.space.shared.common.exception.NotFoundUserException
import com.space.shared.common.exception.signup.EmailCodeFormatException
import com.space.shared.common.exception.signup.ExpirationCodeException
import com.space.shared.common.exception.signup.IncorrectCodeException
import com.space.shared.mapCatching
import com.space.shared.model.EmailModel
import com.space.shared.model.FindEmailModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class VerifyCodeViewModel @Inject constructor(
    private val findPwVerifyUseCase: FindPwVerifyUseCase
) : ViewModel() {

    var email: String = ""
    val emailCode = MutableLiveData<String>()

    val toastMessage = MutableLiveData<String>()
    private val _uiStatus = MutableLiveData<UiStatus<EmailModel>>()
    val uiStatus: LiveData<UiStatus<EmailModel>> = _uiStatus


    fun findPw() {
        viewModelScope.launch {
            val pair = Pair("$email@bible.ac.kr", FindEmailModel(emailCode.value.toString()))
            val result = async { findPwVerifyUseCase(pair) }.await()
            result.mapCatching(
                onSuccess = {
                    _uiStatus.value = UiStatus(
                        UiStatusType.SUCCESS,
                        EmailModel(email, it)
                    )
                },
                onError = ::handleError
            )
        }
    }


    private fun handleError(throwable: Throwable) {
        Timber.i(throwable.message)
        when (throwable) {
            is NotFoundUserException ->
                toastMessage.value = "사용자 정보를 찾을 수 없습니다."

            is EmailCodeFormatException ->
                toastMessage.value = "이메일 코드가 맞지않습니다."

            is ExpirationCodeException, is IncorrectCodeException ->
                toastMessage.value = "만료된 코드이거나 코드가 올바르지 않습니다."

            is UnknownHostException, is SocketTimeoutException ->
                toastMessage.value = "네트워크를 연결할 수 없습니다."

            else -> toastMessage.value = "알 수 없는 오류가 발생했습니다."
        }
    }
}