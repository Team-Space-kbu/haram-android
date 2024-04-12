package com.space.notice.ui.detail

import androidx.lifecycle.viewModelScope
import com.space.core_ui.base.BaseViewModel
import com.space.domain.notice.NoticeDetailUseCase
import com.space.shared.UiStatus
import com.space.shared.UiStatusType
import com.space.shared.data.notice.Notice
import com.space.shared.data.notice.NoticeDetail
import com.space.shared.data.notice.NoticeType
import com.space.shared.mapCatching
import com.space.shared.model.NoticeDetailModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoticeDetailViewModel @Inject constructor(
    private val noticeDetailUseCase: NoticeDetailUseCase
) : BaseViewModel<NoticeDetail>() {

    fun getNoticeDetail(
        notice: Notice,
        noticeType: NoticeType
    ) {
        viewModelScope.launch {
            val model = NoticeDetailModel(noticeType, notice)
            val result = async { noticeDetailUseCase(model) }.await()
            result.mapCatching(
                onSuccess = { noticeDetail ->
                    _view.value = UiStatus(UiStatusType.SUCCESS, noticeDetail)
                },
                onError = ::setIntranetData
            )
        }
    }
}