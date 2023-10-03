package com.space.biblemon.ui.book.search

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.space.data.ResultData
import com.space.domain.usecase.book.BookUsecase
import com.space.data.res.book.BookSearchReq
import com.space.biblemon.base.view.BaseViewModel
import com.space.shared.annotation.IoDispatcher
import com.space.shared.annotation.MainImmediateDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BookSearchViewModel @Inject constructor(
    private val bookUsecase: BookUsecase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @MainImmediateDispatcher private val mainDispatcher: CoroutineDispatcher
) : BaseViewModel() {

    val isLoading: ObservableBoolean = ObservableBoolean(false)

    private val _searchForm: MutableLiveData<SearchFormData?> = MutableLiveData<SearchFormData?>()
    val searchForm: LiveData<SearchFormData?> = _searchForm

    private var _searchInputText: String = ""

    fun getSearchList(index: Int? = 1) {
        viewModelScope.launch(ioDispatcher) {
            bookUsecase.getBookSearchList(_searchInputText, index).let {
                withContext(mainDispatcher) {
                    when (it) {
                        is ResultData.Success<BookSearchReq> -> {
                            _searchForm.value =
                                SearchFormData(searchReq = it.body, searchData = true)
                            isLoading.set(true)
                        }

                        is ResultData.Unauthorized -> {

                        }

                        is ResultData.Error -> {


                        }
                    }
                }

            }
        }
    }

    fun setInputText(text: String) {
        _searchInputText = text
    }

}