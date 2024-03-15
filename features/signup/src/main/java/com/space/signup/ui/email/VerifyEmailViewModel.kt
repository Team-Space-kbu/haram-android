package com.space.signup.ui.email

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.domain.usecase.singup.SendEmailUseCase
import com.space.domain.usecase.singup.VerifyEmailUseCase
import com.space.shared.common.exception.signup.ExpirationCode
import com.space.shared.common.exception.signup.FormatIncorrect
import com.space.shared.common.exception.signup.IncorrectCode
import com.space.shared.mapCatching
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

    val email = MutableLiveData<String>()
    val emailVerify = MutableLiveData<String>()

    val verifyStatus = MutableLiveData(false)
    val verifyModel = MutableLiveData<Pair<String, String>>()
    val toastMessage = MutableLiveData<String>()
    val uiStatus = MutableLiveData(false)

    fun verifyCode() {
        handleAction {
            val code = emailVerify.value.toString()
            val emailModel = getEmailModel()
            if (!isValidEmail(emailModel)) {
                handleInvalidEmail()
                return@handleAction
            }
            viewModelScope.launch {
                val result = async { verifyEmailUseCase(Pair(emailModel, code)) }.await()
                result.mapCatching(
                    onSuccess = {
                        uiStatus.value = it
                        verifyStatus.value = false
                    },
                    onError = ::handleError
                )
            }
        }
    }

    fun sendEmail() {
        handleAction {
            val emailModel = getEmailModel()
            if (!isValidEmail(emailModel)) {
                handleInvalidEmail()
                return@handleAction
            }
            viewModelScope.launch {
                val result = async { sendEmailUseCase(emailModel) }.await()
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

    private fun getEmailModel(): String {
        val email = email.value.toString().replace("@bible.ac.kr", "")
        return "$email@bible.ac.kr"
    }

    private fun handleInvalidEmail() {
        verifyStatus.value = false
        toastMessage.value = "이메일 형식이 맞지 않습니다."
    }

    private fun handleError(throwable: Throwable) {
        Timber.i(throwable.message)
        when (throwable) {
            is FormatIncorrect -> handleInvalidEmail()
            is ExpirationCode, is IncorrectCode -> {
                verifyModel.value = statusVerifyModel
                verifyStatus.value = true
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

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$")
        return emailRegex.matches(email)
    }
}
