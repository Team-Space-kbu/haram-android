package com.space.signup.ui.email

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.domain.singup.SendEmailUseCase
import com.space.shared.exception.signup.ExpirationCodeException
import com.space.shared.exception.signup.FormatIncorrectException
import com.space.shared.exception.signup.IncorrectCodeException
import com.space.shared.exception.signup.ToMuchRequestException
import com.space.shared.mapCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class SendEmailViewModel @Inject constructor(
    private val sendEmailUseCase: SendEmailUseCase,
) : ViewModel() {

    private val statusEmailModel = Pair("이메일 형식이 맞지 않습니다.\n@bible.ac.kr 이메일로 입력해주세요", "#E82722")


    val email = MutableLiveData<String>()

    val verifyStatus = MutableLiveData(false)
    val verifyModel = MutableLiveData<Pair<String, String>>()
    val toastMessage = MutableLiveData<String>()
    val sendStatus = MutableLiveData(false)

    fun sendEmail() {
        val inputEmail = email.value
        if (inputEmail.isNullOrEmpty() || inputEmail.trim() == "") {
            displayError("이메일 주소를 입력해주세요")
            verifyModel.value = statusEmailModel
            return
        }

        val emailModel = "$inputEmail@bible.ac.kr"
        if (!isValidEmail(emailModel)) {
            displayError("잘못된 이메일 형식입니다.")
            verifyModel.value = statusEmailModel
            return
        }

        viewModelScope.launch {
            val result = runCatching { sendEmailUseCase(emailModel) }
            result.onSuccess {
                verifyStatus.value = false
                sendStatus.value = true
            }.onFailure {
                handleError(it)
            }
        }
    }

    private fun displayError(message: String) {
        verifyStatus.value = true
        toastMessage.value = message
    }

    private fun handleInvalidEmail() {
        Timber.i("The email format is strange or incorrect.")
        verifyStatus.value = true
        verifyModel.value = statusEmailModel
    }

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("^[^\\s@]+@bible\\.ac\\.kr\$")
        return emailRegex.matches(email)
    }

    private fun handleError(throwable: Throwable) {
        Timber.i(throwable.message)
        when (throwable) {
            is FormatIncorrectException -> handleInvalidEmail()
            is ToMuchRequestException -> {
                displayError("이메일이 이미 발송되었습니다. 1분뒤 다시 시도해주세요")
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

}
