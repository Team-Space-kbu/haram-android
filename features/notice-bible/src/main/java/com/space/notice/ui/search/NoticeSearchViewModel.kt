package com.space.notice.ui.search

import androidx.lifecycle.viewModelScope
import com.space.core_ui.base.BaseViewModel
import com.space.domain.notice.NoticeSearchUseCase
import com.space.shared.UiStatus
import com.space.shared.UiStatusType
import com.space.shared.data.notice.NoticeSearch
import com.space.shared.data.notice.NoticeType
import com.space.shared.mapCatching
import com.space.shared.model.NoticeSearchModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoticeSearchViewModel @Inject constructor(
    private val noticeSearchUseCase: NoticeSearchUseCase
) : BaseViewModel<NoticeSearch>() {

    fun getNoticeSearch(search: NoticeType, page: String? = "1") {
        viewModelScope.launch {
            val result = async {
                val model = NoticeSearchModel(search, page)
                noticeSearchUseCase(model)
            }.await()
            result.mapCatching(
                onSuccess = { noticeSearch ->
                    _view.value = UiStatus(UiStatusType.SUCCESS, noticeSearch)
                },
                onError = ::setIntranetData
            )
        }
    }
}
