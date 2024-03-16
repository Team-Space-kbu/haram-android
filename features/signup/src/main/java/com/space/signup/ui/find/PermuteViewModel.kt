package com.space.signup.ui.find

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.domain.usecase.singup.FindSetPasswordUseCase
import com.space.shared.common.exception.NotFoundUserException
import com.space.shared.common.exception.signup.ExpirationCodeException
import com.space.shared.common.exception.signup.IncorrectCodeException
import com.space.shared.common.exception.signup.PasswordFormatException
import com.space.shared.common.exception.signup.PasswordIncorrectCode
import com.space.shared.mapCatching
import com.space.shared.model.EmailModel
import com.space.shared.model.FindEmailModel
import com.space.shared.model.FindPassword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class PermuteViewModel @Inject constructor(
    private val findSetPasswordUseCase: FindSetPasswordUseCase
) : ViewModel() {
    lateinit var emailModel: EmailModel

    val password = MutableLiveData<String>()
    val passwordVerify = MutableLiveData<String>()
    val passwordStatus = MutableLiveData<Boolean>()
    val passwordVerifyStatus = MutableLiveData<Boolean>()
    val toastMessage = MutableLiveData<String>()

    val uiStatus = MutableLiveData(false)
    fun setNewPw() {
        handleAction {
            val model =
                Pair(
                    "${emailModel.email}@bible.ac.kr",
                    FindPassword(password.value.toString(), emailModel.code)
                )
            viewModelScope.launch {
                val result = async { findSetPasswordUseCase(model) }.await()
                result.mapCatching(
                    onSuccess = {
                        if (it) {
                            toastMessage.value = "성공적으로 변경되었습니다."
                            uiStatus.value = it
                        }
                    },
                    onError = ::handleError
                )
            }
        }
    }

    private fun handleAction(action: () -> Unit) {
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

    private fun isValidUserPassword(): Boolean {
//        val pattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@\$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,255}$")
//        return pattern.matcher(userPassword).matches()
        return true
    }

    private fun handleError(throwable: Throwable) {
        Timber.i(throwable.message)
        when (throwable) {

            is PasswordFormatException -> passwordStatus.value = true

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