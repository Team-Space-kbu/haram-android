package com.space.notice.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.domain.usecase.notice.NoticeDetailUseCase
import com.space.shared.data.notice.Notice
import com.space.shared.data.notice.NoticeDetail
import com.space.shared.data.notice.NoticeType
import com.space.shared.mapCatching
import com.space.shared.model.NoticeDetailModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class NoticeDetailViewModel @Inject constructor(
    private val noticeDetailUseCase: NoticeDetailUseCase
) : ViewModel() {

    private val _detail = MutableLiveData<NoticeDetail>()
    val detail: LiveData<NoticeDetail> = _detail

    fun getNoticeDetail(
        notice: Notice,
        noticeType: NoticeType
    ) {
        viewModelScope.launch {
            val model = NoticeDetailModel(noticeType, notice)
            val result = async { noticeDetailUseCase(model) }.await()
            result.mapCatching(
                onSuccess = { noticeDetail ->
                    _detail.value = noticeDetail
                },
                onError = { throwable ->
                    Timber.d(throwable.message)
                }
            )
        }
    }
}