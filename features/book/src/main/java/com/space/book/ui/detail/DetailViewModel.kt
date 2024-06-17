package com.space.book.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.core_ui.base.BaseFragment
import com.space.core_ui.base.BaseViewModel
import com.space.domain.book.BookDetailUseCase
import com.space.domain.book.BookRentalUseCase
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
    private val bookRentalUseCase: BookRentalUseCase
) : BaseViewModel<BookDetailInfo>() {



    private val _rental: MutableLiveData<BookEtc> = MutableLiveData<BookEtc>()
    val rental: LiveData<BookEtc> = _rental


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

    fun getRental(category: Category) {
        viewModelScope.launch {
            val rentalResult = async { bookRentalUseCase(category) }
            rentalResult.await().mapCatching(
                onSuccess = { bookKeep ->
                    _rental.value = bookKeep
                },
                onError = { throwable ->
                    Timber.d(throwable.message)
                }
            )

        }
    }

}