package com.space.core_ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.space.navigator.view.NavigatorLogin
import com.space.shared.UiStatus
import com.space.shared.UiStatusType
import com.space.shared.exception.NotFoundStudentIdException
import com.space.shared.exception.user.LogoutProcessed
import timber.log.Timber
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

abstract class BaseViewModel<T> : ViewModel() {

    protected val _view: MutableLiveData<UiStatus<T>> = MutableLiveData<UiStatus<T>>()
    val view: LiveData<UiStatus<T>> = _view


    @Inject
    lateinit var navigatorLogin: NavigatorLogin

    init {
        _view.value = UiStatus(UiStatusType.LOADING)
    }

    fun setIntranetData(throwable: Throwable) {
        Timber.i(throwable.message)
        when (throwable) {
            is NotFoundStudentIdException ->
                _view.value = UiStatus(UiStatusType.REJECT)

            is LogoutProcessed ->
                _view.value = UiStatus(UiStatusType.LOGOUT)


            is UnknownHostException, is SocketTimeoutException ->
                _view.value = UiStatus(UiStatusType.NO_CONNECTION)


            is Exception ->
                _view.value = UiStatus(UiStatusType.ERROR)

        }
    }
}