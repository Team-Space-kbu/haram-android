package com.space.book.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.domain.usecase.book.BookHomeUseCase
import com.space.shared.common.annotation.IoDispatcher
import com.space.shared.common.annotation.MainDispatcher
import com.space.shared.data.book.BookHome
import com.space.shared.result.succeeded
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookHomeViewModel @Inject constructor(
    private val homeUseCase: BookHomeUseCase,
) : ViewModel() {

    private val _bookHome: MutableLiveData<BookHome> = MutableLiveData<BookHome>()
    val bookHome: LiveData<BookHome> = _bookHome

    init {
        viewModelScope.launch {
            val result = async { homeUseCase() }
            _bookHome.value = result.await().succeeded()
        }
    }

}