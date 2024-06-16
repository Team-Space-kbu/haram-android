package com.space.other

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.base.BaseViewModel
import com.space.domain.auth.LogoutUseCase
import com.space.domain.user.UserInfoUseCase
import com.space.navigator.view.NavigatorNotice
import com.space.navigator.view.NavigatorUser
import com.space.shared.UiStatus
import com.space.shared.UiStatusType
import com.space.shared.data.auth.User
import com.space.shared.mapCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OtherViewModel @Inject constructor(
    private val userInfoUseCase: UserInfoUseCase,
    private val logoutUseCase: LogoutUseCase,
) : BaseViewModel<User>() {

    @Inject
    lateinit var navigatorNotice: NavigatorNotice

    @Inject
    lateinit var navigatorUser: NavigatorUser


    private val _logout = MutableLiveData<Boolean>()
    val logout: LiveData<Boolean> = _logout

    init {
        viewModelScope.launch {
            val result = async { userInfoUseCase() }.await()
            result.mapCatching(
                onSuccess = { user ->
                    _view.value = UiStatus(UiStatusType.SUCCESS, user)
                },
                onError = ::setIntranetData
            )
        }
    }

    fun logout() {
        viewModelScope.launch {
            val result = async { logoutUseCase() }.await()
            result.mapCatching(
                onSuccess = {
                    _logout.value = it
                },
                onError = ::setIntranetData
            )
        }
    }
}