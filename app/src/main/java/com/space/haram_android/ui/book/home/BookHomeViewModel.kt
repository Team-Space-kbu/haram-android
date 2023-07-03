package com.space.haram_android.ui.book.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.data.ResultData
import com.space.data.response.book.BookHomeReq
import com.space.domain.usecase.function.book.BookRepository
import com.space.haram_android.adapter.KeyEventListener
import com.space.haram_android.ui.book.adapter.BookViewListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookHomeViewModel @Inject constructor(
    private val bookRepository: BookRepository
) : ViewModel() {

    private val _homeForm: MutableLiveData<BookHomeReq?> = MutableLiveData<BookHomeReq?>()
    val homeInfo: LiveData<BookHomeReq?> = _homeForm

    private val _serverStatus = MutableLiveData<Boolean>(true)
    val serverStatus: LiveData<Boolean> = _serverStatus

    private val _searchKeyEvent = MutableLiveData(false)
    val searchKeyEvent: LiveData<Boolean> = _searchKeyEvent

    private val _viewListener = MutableLiveData<BookHomeFormState>()
    val viewListener: LiveData<BookHomeFormState> = _viewListener

    val bindingViewListener = object : BookViewListener {
        override fun setViewType(path: String) {
            _viewListener.value = BookHomeFormState(viewPath = path, viewStatus = true)
        }

        override fun clearViewType() {
            _viewListener.value = BookHomeFormState(viewStatus = false)
        }
    }

    val bindingListener = object : KeyEventListener {
        override fun keyEvent() {
            _searchKeyEvent.value = true
        }

        override fun keyEventEnd() {
            _searchKeyEvent.value = false
        }
    }

    init {
        viewModelScope.launch {
            bookRepository.getBookHomeInfo().let {
                when (it) {
                    is ResultData.Success<BookHomeReq> -> _homeForm.value = it.body
                    is ResultData.Error -> _serverStatus.value = false
                    else -> _serverStatus.value = false
                }
            }
        }
    }


}