package com.space.signup.ui.user

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.domain.user.UserPasswordUseCase

import com.space.shared.mapCatching
import com.space.shared.model.FindPassword
import com.space.shared.model.PermutePasswordModel
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
class UserViewModel @Inject constructor(
    private val userPasswordUseCase: UserPasswordUseCase
) : BasePermute() {

    val oldPassword = MutableLiveData<String>()


    fun setNewPw() {
        handleAction {
            val old = oldPassword.value ?: return@handleAction
            val new = password.value ?: return@handleAction
            viewModelScope.launch {
                val result = async {
                    userPasswordUseCase(
                        PermutePasswordModel(old, new)
                    )
                }.await()
                result.mapCatching(
                    onSuccess = {
                        if (it) {
                            toastMessage.value = "성공적으로 변경되었습니다."
                        }
                        uiStatus.value = it
                    },
                    onError = ::handleError
                )
            }
        }
    }


}