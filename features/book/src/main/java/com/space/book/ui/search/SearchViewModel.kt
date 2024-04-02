package com.space.book.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.core_ui.base.BaseViewModel
import com.space.domain.book.BookSearchUseCase
import com.space.shared.UiStatus
import com.space.shared.UiStatusType
import com.space.shared.data.book.BookSearch
import com.space.shared.mapCatching
import com.space.shared.succeeded
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val bookSearchUseCase: BookSearchUseCase
) : BaseViewModel<BookSearch>() {


    fun getSearch(string: String, int: Int? = 1) {
        search(BookSearchUseCase.SearchParam(string, int))
    }

    private fun search(searchParam: BookSearchUseCase.SearchParam) {
        viewModelScope.launch {
            val result = async { bookSearchUseCase(searchParam) }.await()
            result.mapCatching(
                onSuccess = { bookSearch ->
                    _view.value = UiStatus(UiStatusType.SUCCESS, bookSearch)
                },
                onError = { throwable ->
                    Timber.d(throwable.message)
                    _view.value = UiStatus(UiStatusType.SUCCESS,
                        BookSearch(
                        start = 0,
                        end = 0,
                        emptyList()
                    ))
                }
            )

        }
    }

}