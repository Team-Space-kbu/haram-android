package com.space.notice.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.domain.usecase.notice.NoticeHomeUseCase
import com.space.shared.data.notice.NoticeHome
import com.space.shared.mapCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class NoticeHomeViewModel @Inject constructor(
    private val noticeHomeUseCase: NoticeHomeUseCase

) : ViewModel() {

    private val _homeInfo = MutableLiveData<NoticeHome>()
    val homeInfo: LiveData<NoticeHome> = _homeInfo

    init {
        viewModelScope.launch {
            val result = async { noticeHomeUseCase() }.await()
            result.mapCatching(
                onSuccess = { noticeHome ->
                    _homeInfo.value = noticeHome
                }, onError = { throwable ->
                    Timber.d(throwable.message)
                }
            )
        }
    }

}