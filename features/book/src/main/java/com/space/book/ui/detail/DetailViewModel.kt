package com.space.book.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.domain.usecase.book.BookDetailUseCase
import com.space.domain.usecase.book.BookRentalUseCase
import com.space.shared.data.book.BookDetailInfo
import com.space.shared.data.book.BookKeep
import com.space.shared.data.book.Category
import com.space.shared.result.succeeded
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val bookDetailUseCase: BookDetailUseCase,
    private val bookRentalUseCase: BookRentalUseCase
) : ViewModel() {

    private val _detail: MutableLiveData<BookDetailInfo> = MutableLiveData<BookDetailInfo>()
    val detail: LiveData<BookDetailInfo> = _detail

    private val _rental: MutableLiveData<BookKeep> = MutableLiveData<BookKeep>()
    val rental: LiveData<BookKeep> = _rental


    fun getDetail(category: Category) {
        viewModelScope.launch {
            val detailResult = async { bookDetailUseCase(category) }
            _detail.value = detailResult.await().succeeded()
        }
    }

    fun getRental(category: Category) {
        viewModelScope.launch {
            val rentalResult = async { bookRentalUseCase(category) }
            _rental.value = rentalResult.await().succeeded()
        }
    }

}