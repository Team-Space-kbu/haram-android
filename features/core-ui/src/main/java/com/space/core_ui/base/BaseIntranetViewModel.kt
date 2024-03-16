package com.space.core_ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.space.navigator.view.NavigatorLogin
import com.space.shared.UiStatus
import com.space.shared.UiStatusType
import com.space.shared.common.exception.NotFoundStudentIdException
import javax.inject.Inject

abstract class BaseIntranetViewModel<T> : ViewModel(){

    protected val _liveData: MutableLiveData<UiStatus<T>> = MutableLiveData<UiStatus<T>>()
    val data: LiveData<UiStatus<T>> = _liveData
    @Inject
    lateinit var navigatorLogin: NavigatorLogin
    fun setIntranetData(e :Throwable){
        when (e) {
            is NotFoundStudentIdException -> {
                _liveData.value = UiStatus(UiStatusType.REJECT)
            }
            is Exception -> {

            }
        }
    }
}