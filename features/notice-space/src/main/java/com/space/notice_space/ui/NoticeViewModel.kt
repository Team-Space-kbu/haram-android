package com.space.notice_space.ui

import androidx.lifecycle.viewModelScope
import com.space.core_ui.base.BaseViewModel
import com.space.domain.ntocie_space.NoticeSpaceUseCase
import com.space.navigator.view.NavigatorImage
import com.space.shared.UiStatus
import com.space.shared.UiStatusType
import com.space.shared.data.notice_space.NoticeSpace
import com.space.shared.mapCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class NoticeViewModel @Inject constructor(
    private val noticeSpaceUseCase: NoticeSpaceUseCase,
) : BaseViewModel<NoticeSpace>() {

    @Inject
    lateinit var navigatorImage: NavigatorImage

    fun getNotice(seq: String) {
        viewModelScope.launch {
            val result = async { noticeSpaceUseCase(seq) }.await()
            result.mapCatching(
                onSuccess = {
                    _view.value =
                        UiStatus(
                            UiStatusType.SUCCESS,
                            it
                        )
                },
                onError = { throwable ->
                    Timber.i(throwable.message)
                }
            )
        }
    }
}

