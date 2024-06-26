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
    private val sendEmailUseCase: SendEmailUseCase,
    private val verifyEmailUseCase: VerifyEmailUseCase
) : ViewModel() {

    private val statusSendModel = Pair("이메일이 성공적으로 발송되었습니다.", "#2F80ED")
    private val statusVerifyModel = Pair("이메일 확인 코드가 다릅니다.", "#E82722")
    private val statusEmailModel = Pair("이메일 형식이 맞지 않습니다.\n@bible.ac.kr 이메일로 입력해주세요", "#E82722")

    val email = MutableLiveData<String>()
    val emailVerify = MutableLiveData<String>()

    val verifyStatus = MutableLiveData(false)
    val verifyModel = MutableLiveData<Pair<String, String>>()
    val toastMessage = MutableLiveData<String>()
    val uiStatus = MutableLiveData<UiStatus<EmailModel>>()

    fun verifyCode() {
        handleAction {
            val emailModel = EmailModel(email.value.toString(), emailVerify.value.toString())
            if (emailModel.isValidEmail()) {
                handleInvalidEmail()
                return@handleAction
            }
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
    }

    fun sendEmail() {
        handleAction {
            val emailModel = EmailModel(email.value.toString(), emailVerify.value.toString())
            if (emailModel.isValidEmail()) {
                handleInvalidEmail()
                return@handleAction
            }
            viewModelScope.launch {
                val result = async { sendEmailUseCase(emailModel.getEmailModel()) }.await()
                result.mapCatching(
                    onSuccess = {
                        verifyModel.value = statusSendModel
                        verifyStatus.value = true
                    },
                    onError = ::handleError
                )
            }

        }
    }

    private fun handleAction(action: () -> Unit) {
        if (email.value == null) {
            verifyStatus.value = false
            toastMessage.value = "이메일 주소를 입력해주세요"
            return
        }
        action()
    }


    private fun handleInvalidEmail() {
        Timber.i("The email format is strange or incorrect.")
        verifyStatus.value = true
        verifyModel.value = statusEmailModel
    }

    private fun handleError(throwable: Throwable) {
        Timber.i(throwable.message)
        when (throwable) {
            is FormatIncorrectException -> handleInvalidEmail()
            is ExpirationCodeException, is IncorrectCodeException -> {
                verifyModel.value = statusVerifyModel
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
