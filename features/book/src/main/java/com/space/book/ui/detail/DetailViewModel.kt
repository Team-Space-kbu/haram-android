package com.space.book.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.domain.usecase.book.BookDetailUseCase
import com.space.domain.usecase.book.BookRentalUseCase
import com.space.shared.data.book.BookDetailInfo
import com.space.shared.data.book.BookEtc
import com.space.shared.data.book.Category
import com.space.shared.result.mapCatching
import com.space.shared.result.succeeded
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val bookDetailUseCase: BookDetailUseCase,
    private val bookRentalUseCase: BookRentalUseCase
) : ViewModel() {

    private val _status: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status


    private val _detail: MutableLiveData<BookDetailInfo> = MutableLiveData<BookDetailInfo>()
    val detail: LiveData<BookDetailInfo> = _detail

    private val _rental: MutableLiveData<BookEtc> = MutableLiveData<BookEtc>()
    val rental: LiveData<BookEtc> = _rental


    fun getDetail(category: Category) {
        viewModelScope.launch {
            val detailResult = async { bookDetailUseCase(category) }.await().mapCatching(
                onError = { throwable ->
                    Timber.d("TEST : ${throwable.message}")
                    _status.value = false
                },
                onSuccess = { detail ->
                    _status.value = true
                    _detail.value = detail
                }
            )
        }
    }

    fun getRental(category: Category) {
        viewModelScope.launch {
            val rentalResult = async { bookRentalUseCase(category) }.await().mapCatching(
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