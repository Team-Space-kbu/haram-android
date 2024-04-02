package com.space.signup.ui.find

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.domain.singup.FindSetPasswordUseCase
import com.space.shared.common.exception.NotFoundUserException
import com.space.shared.common.exception.signup.ExpirationCodeException
import com.space.shared.common.exception.signup.IncorrectCodeException
import com.space.shared.common.exception.signup.PasswordFormatException
import com.space.shared.common.exception.signup.PasswordIncorrectCode
import com.space.shared.mapCatching
import com.space.shared.model.EmailModel
import com.space.shared.model.FindEmailModel
import com.space.shared.model.FindPassword
import com.space.signup.ui.base.BasePermute
import com.space.signup.util.isValidUserPassword
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
) : BasePermute() {
    lateinit var emailModel: EmailModel

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
}