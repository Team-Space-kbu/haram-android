package com.space.signup.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.domain.singup.SignupUseCase
import com.space.shared.common.exception.signup.EmailCodeFormatException
import com.space.shared.common.exception.signup.EmailFormatException
import com.space.shared.common.exception.signup.EmailInUseException
import com.space.shared.common.exception.signup.ExpirationCodeException
import com.space.shared.common.exception.signup.IncorrectCodeException
import com.space.shared.common.exception.signup.NicknameFormatException
import com.space.shared.common.exception.signup.NicknameInUseException
import com.space.shared.common.exception.signup.PasswordFormatException
import com.space.shared.common.exception.signup.UserAlreadyExistsException
import com.space.shared.common.exception.signup.UserIdFormatException
import com.space.shared.mapCatching
import com.space.shared.model.EmailModel
import com.space.shared.model.SignupModel
import com.space.shared.model.UserTerms
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class SignupVerifyViewModel @Inject constructor(
    private val signupUseCase: SignupUseCase
) : ViewModel() {
    val userId = MutableLiveData<String>()
    val userStatus = MutableLiveData<Boolean>()
    val nickName = MutableLiveData<String>()
    val nickNameStatus = MutableLiveData<Boolean>()
    val password = MutableLiveData<String>()
    val passwordVerify = MutableLiveData<String>()
    val passwordStatus = MutableLiveData<Boolean>()
    val passwordVerifyStatus = MutableLiveData<Boolean>()


    val email = MutableLiveData<String>()
    val emailStatus = MutableLiveData<Boolean>()
    val toastMessage = MutableLiveData<String>()

    private val _signUpStatus = MutableLiveData<Boolean>()
    val signUpStatus: LiveData<Boolean> = _signUpStatus

    fun signup(emailModel: EmailModel, policy: List<UserTerms>) {
        viewModelScope.launch {
            val model = SignupModel(
                userId.value.toString().lowercase(),
                "${emailModel.email}@bible.ac.kr",
                password.value.toString(),
                nickName.value.toString(),
                emailModel.code,
                policy
            )

            updateUserStatus(model)
            updatePasswordStatus(model)
            updateEmailStatus(model)

            if (!userStatus.value!! && !passwordStatus.value!! && !passwordVerifyStatus.value!! && !emailStatus.value!!) {
                val result = async { signupUseCase(model) }.await()
                result.mapCatching(
                    onSuccess = {
                        _signUpStatus.value = it
                    },
                    onError = ::handleError
                )
            } else {
                return@launch
            }
        }

    }


    private fun updateUserStatus(model: SignupModel) {
        userStatus.value = !model.isValidUserId()
    }

    private fun updatePasswordStatus(model: SignupModel) {
        passwordStatus.value = !model.isValidUserNickname()
        passwordStatus.value = !model.isValidUserPassword()
        passwordVerifyStatus.value = password.value != passwordVerify.value
    }

    private fun updateEmailStatus(model: SignupModel) {
        emailStatus.value = !model.isValidUserEmail()
        emailStatus.value = !model.isValidEmailAuthCode()
    }


    private fun handleError(throwable: Throwable) {
        Timber.i(throwable.message)
        when (throwable) {
            is UserIdFormatException -> userStatus.value = true
            is EmailFormatException -> emailStatus.value = true
            is NicknameFormatException -> {
                nickNameStatus.value = true
                toastMessage.value = "해당 닉네임은 사용할 수 없는닉네임 입니다."
            }

            is PasswordFormatException -> passwordStatus.value = true
            is EmailCodeFormatException -> {
                emailStatus.value = true
                toastMessage.value = "이메일 코드는 6자리여야 합니다."
            }

            is UserAlreadyExistsException -> {
                userStatus.value = true
                toastMessage.value = "사용자가 이미 존재합니다."
            }

            is EmailInUseException -> {
                emailStatus.value = true
                toastMessage.value = "이메일이 사용중입니다."
            }

            is NicknameInUseException -> toastMessage.value = "해당 닉네임은 사용중입니다."
            is ExpirationCodeException, is IncorrectCodeException -> toastMessage.value =
                "이메일 인증을 다시해주시기 바랍니다."

            is UnknownHostException, is SocketTimeoutException -> toastMessage.value =
                "네트워크를 연결할 수 없습니다."

            else -> toastMessage.value = "알 수 없는 오류가 발생했습니다."
        }
    }

}