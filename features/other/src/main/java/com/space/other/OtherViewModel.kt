package com.space.other

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.domain.usecase.auth.DeleteAuthUseCase
import com.space.domain.usecase.auth.LogoutUseCase
import com.space.domain.usecase.auth.UserInfoUseCase
import com.space.navigator.view.NavigatorLogin
import com.space.navigator.view.NavigatorNotice
import com.space.shared.UiStatus
import com.space.shared.UiStatusType
import com.space.shared.data.auth.User
import com.space.shared.data.home.Home
import com.space.shared.mapCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class OtherViewModel @Inject constructor(
    private val userInfoUseCase: UserInfoUseCase,
    private val logoutUseCase: LogoutUseCase,
) : ViewModel() {
    @Inject
    lateinit var navigatorNotice: NavigatorNotice

    @Inject
    lateinit var navigatorLogin: NavigatorLogin

    private val _user = MutableLiveData<UiStatus<User>>()
    val user: LiveData<UiStatus<User>> = _user

    private val _logout = MutableLiveData<UiStatus<Boolean>>()
    val logout: LiveData<UiStatus<Boolean>> = _logout

    init {
        viewModelScope.launch {
            val result = async { userInfoUseCase() }.await()
            result.mapCatching(
                onSuccess = { user ->
                    _user.value = UiStatus(UiStatusType.SUCCESS, user)
                },
                onError = { throwable ->
                    Timber.d(throwable.message)
                    _user.value = UiStatus(UiStatusType.NO_CONNECTION)
                }
            )
        }
    }

    fun logout() {
        viewModelScope.launch {
            val result = async { logoutUseCase() }.await()
            result.mapCatching(
                onSuccess = {
                    _logout.value = UiStatus(UiStatusType.SUCCESS)
                },
                onError = { throwable ->
                    Timber.d(throwable.message)
                    _logout.value = UiStatus(UiStatusType.ERROR)
                }
            )
        }
    }
}