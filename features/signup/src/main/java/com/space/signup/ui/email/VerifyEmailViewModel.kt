package com.space.signup.ui.email

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.domain.singup.SendEmailUseCase
import com.space.domain.singup.VerifyEmailUseCase
import com.space.shared.UiStatus
import com.space.shared.UiStatusType
import com.space.shared.exception.signup.ExpirationCodeException
import com.space.shared.exception.signup.FormatIncorrectException
import com.space.shared.exception.signup.IncorrectCodeException
import com.space.shared.exception.signup.ToMuchRequestException
import com.space.shared.mapCatching
import com.space.shared.model.EmailModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class VerifyEmailViewModel @Inject constructor(
    private val verifyEmailUseCase: VerifyEmailUseCase
) : ViewModel() {

    private val statusVerifyModel = Pair("이메일 확인 코드가 다릅니다.", "#E82722")

    val email = MutableLiveData<String>()
    val emailVerify = MutableLiveData<String>()

    val verifyStatus = MutableLiveData(false)
    val verifyModel = MutableLiveData<Pair<String, String>>()
    val toastMessage = MutableLiveData<String>()
    val uiStatus = MutableLiveData<UiStatus<EmailModel>>()

    fun verifyCode() {
        val inputCode = emailVerify.value
        if (inputCode.isNullOrEmpty()) {
            displayError("인증코드를 입력해주세요")
            return
        }
        val emailModel = EmailModel(email.value.toString(), emailVerify.value.toString())
        viewModelScope.launch {
            val result = async { verifyEmailUseCase(emailModel) }.await()
            result.mapCatching(
                onSuccess = {
                    uiStatus.value = UiStatus(
                        UiStatusType.SUCCESS,
                        emailModel
                    )
                    verifyStatus.value = false
                },
                onError = ::handleError
            )
        }
    }

    private fun handleError(throwable: Throwable) {
        Timber.i(throwable.message)
        when (throwable) {
            is ExpirationCodeException, is IncorrectCodeException -> {
                verifyModel.value = statusVerifyModel
                verifyStatus.value = true
            }

            is UnknownHostException, is SocketTimeoutException -> {
                displayError("네트워크를 연결할 수 없습니다.")
            }

            else -> {
                verifyStatus.value = false
                displayError("알 수 없는 오류가 발생했습니다.")
            }
        }
    }

    private fun displayError(message: String) {
        verifyStatus.value = true
        toastMessage.value = message
    }

}
