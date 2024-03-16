package com.space.signup.ui.find

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.domain.usecase.singup.SendEmailUseCase
import com.space.shared.common.exception.signup.ExpirationCodeException
import com.space.shared.common.exception.signup.FormatIncorrectException
import com.space.shared.common.exception.signup.IncorrectCodeException
import com.space.shared.common.exception.signup.ToMuchRequestException
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
class FindPwEmailViewModel @Inject constructor(
    private val sendEmailUseCase: SendEmailUseCase
) : ViewModel() {

    val email = MutableLiveData<String>()
    val verifyStatus = MutableLiveData(false)
    val toastMessage = MutableLiveData<String>()

    val uiStatus = MutableLiveData(false)

    fun sendEmail() {
        handleAction {
            val emailModel = EmailModel(email.value.toString(), "")
            if (emailModel.isValidEmail()) {
                handleInvalidEmail()
                return@handleAction
            }
            viewModelScope.launch {
                val result = async { sendEmailUseCase(emailModel.getEmailModel()) }.await()
                result.mapCatching(
                    onSuccess = {
                        verifyStatus.value = false
                        uiStatus.value = it
                    },
                    onError = ::handleError
                )
            }

        }
    }

    private fun handleInvalidEmail() {
        Timber.i("The email format is strange or incorrect.")
        verifyStatus.value = true
    }

    private fun handleAction(action: () -> Unit) {
        if (email.value == null) {
            verifyStatus.value = false
            toastMessage.value = "이메일 주소를 입력해주세요"
            return
        }
        action()
    }

    private fun handleError(throwable: Throwable) {
        Timber.i(throwable.message)
        when (throwable) {
            is FormatIncorrectException -> handleInvalidEmail()
            is ExpirationCodeException, is IncorrectCodeException -> {
                verifyStatus.value = true
            }

            is ToMuchRequestException -> {
                toastMessage.value = "이메일이 이미 발송되었습니다. 나중에 다시 시도해주세요"
            }

            is UnknownHostException, is SocketTimeoutException -> {
                toastMessage.value = "네트워크를 연결할 수 없습니다."
            }

            else -> {
                verifyStatus.value = false
                toastMessage.value = "알 수 없는 오류가 발생했습니다."
            }
        }
    }
}