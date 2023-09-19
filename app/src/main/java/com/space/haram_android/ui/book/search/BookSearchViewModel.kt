package com.space.haram_android.ui.book.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.data.ResultData
import com.space.domain.usecase.book.BookUsecase
import com.space.haram_android.adapter.BookViewListener
import com.space.data.model.BookCategoryView
import com.space.data.res.book.BookSearchReq
import com.space.haram_android.base.BaseViewModel
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

    private val _searchForm: MutableLiveData<SearchFormData?> = MutableLiveData<SearchFormData?>()
    val searchForm: LiveData<SearchFormData?> = _searchForm


    fun getSearchList(searchText: String, index: Int? = 1) {
        viewModelScope.launch(ioDispatcher) {
            bookUsecase.getBookSearchList(searchText, index).let {
                withContext(mainDispatcher) {
                    when (it) {
                        is ResultData.Success<BookSearchReq> -> {
                            _searchForm.value = SearchFormData(it.body, it.body.result.isEmpty())
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

}