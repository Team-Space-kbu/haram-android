package com.space.notice.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.domain.usecase.notice.NoticeSearchUseCase
import com.space.shared.data.notice.NoticeSearch
import com.space.shared.data.notice.NoticeType
import com.space.shared.mapCatching
import com.space.shared.model.NoticeSearchModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class NoticeSearchViewModel @Inject constructor(
    private val noticeSearchUseCase: NoticeSearchUseCase
) : ViewModel() {

    private val _search = MutableLiveData<NoticeSearch>()
    val search: LiveData<NoticeSearch> = _search

    fun getNoticeSearch(search: NoticeType, page: String? = "1") {
        viewModelScope.launch {
            val result = async {
                val model = NoticeSearchModel(search, page)
                noticeSearchUseCase(model)
            }.await()

            result.mapCatching(
                onSuccess = { noticeSearch ->
                    _search.value = noticeSearch
                },
                onError = { throwable ->
                    Timber.d(throwable.message)
                }
            )
        }
    }
}
