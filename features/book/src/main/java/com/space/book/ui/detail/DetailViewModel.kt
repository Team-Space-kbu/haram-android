package com.space.book.ui.detail


import androidx.lifecycle.viewModelScope
import com.space.core_ui.base.BaseViewModel
import com.space.domain.book.BookDetailUseCase
import com.space.shared.UiStatus
import com.space.shared.UiStatusType
import com.space.shared.data.book.BookDetailInfo
import com.space.shared.data.book.BookEtc
import com.space.shared.data.book.Category
import com.space.shared.mapCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val bookDetailUseCase: BookDetailUseCase,
) : BaseViewModel<Pair<BookDetailInfo, BookEtc>>() {


    fun getDetail(category: Category) {
        viewModelScope.launch {
            val detailResult = async { bookDetailUseCase(category) }
            detailResult.await().mapCatching(
                onError = { throwable ->
                    Timber.d("${throwable.message}")
                    _view.value = UiStatus(UiStatusType.EMPTY)
                },
                onSuccess = { detail ->
                    _view.value = UiStatus(UiStatusType.SUCCESS, detail)
                }
            )
        }
    }
}