package com.space.book.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.domain.usecase.book.BookSearchUseCase
import com.space.shared.data.book.BookSearch
import com.space.shared.result.succeeded
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val bookSearchUseCase: BookSearchUseCase
) : ViewModel() {


    private val _searchInfo: MutableLiveData<BookSearch> = MutableLiveData<BookSearch>()
    val searchInfo: LiveData<BookSearch> = _searchInfo


    fun getSearch(string: String) {
        search(BookSearchUseCase.SearchParam(string))
    }

    fun getSearch(string: String, int: Int) {
        search(BookSearchUseCase.SearchParam(string, int))
    }

    private fun search(searchParam: BookSearchUseCase.SearchParam) {
        viewModelScope.launch {
            val result = async { bookSearchUseCase(searchParam) }
            _searchInfo.value = result.await().succeeded()
        }
    }

}