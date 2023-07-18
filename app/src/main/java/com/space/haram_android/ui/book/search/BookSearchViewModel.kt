package com.space.haram_android.ui.book.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.data.ResultData
import com.space.data.response.book.data.SearchResultModel
import com.space.domain.usecase.function.book.BookRepository
import com.space.haram_android.adapter.BookViewListener
import com.space.haram_android.ui.book.home.BookHomeFormState
import com.space.shared.annotation.IoDispatcher
import com.space.shared.annotation.MainImmediateDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BookSearchViewModel @Inject constructor(
    private val bookRepository: BookRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @MainImmediateDispatcher private val mainDispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _searchForm: MutableLiveData<List<SearchResultModel>?> =
        MutableLiveData<List<SearchResultModel>?>()
    val searchForm: LiveData<List<SearchResultModel>?> = _searchForm

    private val _serverStatus = MutableLiveData<Boolean>(true)
    val serverStatus: LiveData<Boolean> = _serverStatus

    private val _viewListener = MutableLiveData<BookHomeFormState>()
    val viewListener: LiveData<BookHomeFormState> = _viewListener

    val bindingViewListener = object : BookViewListener {
        override fun setViewType(path: Int) {
            _viewListener.value = BookHomeFormState(viewPath = path, viewStatus = true)
        }

        override fun clearViewType() {
            _viewListener.value = BookHomeFormState(viewStatus = false)
        }
    }

    fun getSearchList(searchText: String) {
        viewModelScope.launch(ioDispatcher) {
            bookRepository.getBookSearchList(searchText).let {
                withContext(mainDispatcher) {
                    when (it) {
                        is ResultData.Success<List<SearchResultModel>> ->
                            _searchForm.value = it.body

                        is ResultData.Error -> _serverStatus.value = false
                        else -> _serverStatus.value = false
                    }
                }

            }
        }
    }
}