package com.space.signup.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.space.shared.common.exception.NotFoundUserException
import com.space.shared.common.exception.signup.ExpirationCodeException
import com.space.shared.common.exception.signup.IncorrectCodeException
import com.space.shared.common.exception.signup.PasswordFormatException
import com.space.shared.common.exception.signup.PasswordIncorrectCode
import com.space.shared.common.exception.user.PasswordBefore
import com.space.shared.common.exception.user.PasswordDifferent
import com.space.signup.util.isValidUserPassword
import timber.log.Timber
import java.net.SocketTimeoutException
import java.net.UnknownHostException

open class BasePermute : ViewModel() {

    val password = MutableLiveData<String>()
    val passwordVerify = MutableLiveData<String>()
    val passwordStatus = MutableLiveData<Boolean>()
    val passwordVerifyStatus = MutableLiveData<Boolean>()
    val toastMessage = MutableLiveData<String>()
    val uiStatus = MutableLiveData(false)

    fun handleAction(action: () -> Unit) {
        if (!isValidUserPassword()) {
            passwordStatus.value = true
            return
        }
        if (password.value != passwordVerify.value) {
            passwordVerifyStatus.value = true
            passwordStatus.value = false
            return
        }
        action()
    }

    fun handleError(throwable: Throwable) {
        Timber.i(throwable.message)
        when (throwable) {

            is PasswordDifferent ->
                toastMessage.value = "비밀번호가 다릅니다."

            is PasswordBefore ->
                toastMessage.value = "기존 비밀번호와 같습니다."

            is PasswordFormatException ->
                passwordStatus.value = true

            is PasswordIncorrectCode ->
                toastMessage.value = "인증코드가 만료되거나 잘못되었습니다."

            is NotFoundUserException ->
                toastMessage.value = "사용자 정보를 찾을 수 없습니다."

            is ExpirationCodeException, is IncorrectCodeException -> toastMessage.value =
                "이메일 인증을 다시해주시기 바랍니다."

            is UnknownHostException, is SocketTimeoutException -> toastMessage.value =
                "네트워크를 연결할 수 없습니다."

            else -> toastMessage.value = "알 수 없는 오류가 발생했습니다."
        }
    }
}