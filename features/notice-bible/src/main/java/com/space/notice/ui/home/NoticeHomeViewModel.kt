package com.space.notice.ui.home

import androidx.lifecycle.viewModelScope
import com.space.core_ui.base.BaseViewModel
import com.space.domain.notice_bible.NoticeHomeUseCase
import com.space.shared.UiStatus
import com.space.shared.UiStatusType
import com.space.shared.data.notice.NoticeHome
import com.space.shared.mapCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoticeHomeViewModel @Inject constructor(
    private val noticeHomeUseCase: NoticeHomeUseCase
) : BaseViewModel<NoticeHome>() {

    init {
        viewModelScope.launch {
            val result = async { noticeHomeUseCase() }.await()
            result.mapCatching(
                onSuccess = { noticeHome ->
                    _view.value = UiStatus(UiStatusType.SUCCESS, noticeHome)
                },
                onError = ::setIntranetData
            )
        }
    }

}