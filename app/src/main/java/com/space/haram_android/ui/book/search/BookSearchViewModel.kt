package com.space.haram_android.ui.book.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.haram_android.common.data.ResultData
import com.space.haram_android.common.data.response.book.data.SearchResultModel
import com.space.haram_android.usecase.function.book.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BookSearchViewModel @Inject constructor(
    private val bookRepository: BookRepository

) : ViewModel() {
    private val _searchForm: MutableLiveData<List<SearchResultModel>?> =
        MutableLiveData<List<SearchResultModel>?>()
    val searchForm: LiveData<List<SearchResultModel>?> = _searchForm

    private val _serverStatus = MutableLiveData<Boolean>(true)
    val serverStatus: LiveData<Boolean> = _serverStatus

    fun getSearchList(searchText: String) {
        viewModelScope.launch(Dispatchers.IO) {
            bookRepository.getBookSearchList(searchText).let {
                withContext(Dispatchers.Main) {
                    when (it) {
                        is ResultData.Success<List<SearchResultModel>> -> _searchForm.value =
                            it.body

                        is ResultData.Error -> _serverStatus.value = false
                        else -> _serverStatus.value = false
                    }
                }

            }
        }
    }
}