package com.space.haram_android.ui.book.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.haram_android.adapter.KeyEventInf
import com.space.haram_android.common.data.ResultData
import com.space.haram_android.common.data.response.book.BookHomeReq
import com.space.haram_android.usecase.function.book.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BookHomeViewModel @Inject constructor(
    private val bookRepository: BookRepository
) : ViewModel(), KeyEventInf {

    private val _homeForm: MutableLiveData<BookHomeReq?> = MutableLiveData<BookHomeReq?>()
    val homeInfo: LiveData<BookHomeReq?> = _homeForm

    private val _serverStatus = MutableLiveData<Boolean>(true)
    val serverStatus: LiveData<Boolean> = _serverStatus

    private val _searchKeyEvent = MutableLiveData(false)
    val searchKeyEvent: LiveData<Boolean> = _searchKeyEvent

    init {
        viewModelScope.launch(Dispatchers.IO) {
            bookRepository.getBookHomeInfo().let {
                withContext(Dispatchers.Main) {
                    when (it) {
                        is ResultData.Success<BookHomeReq> -> _homeForm.value = it.body
                        is ResultData.Error -> _serverStatus.value = false
                        else -> _serverStatus.value = false
                    }
                }
            }
        }
    }

    override fun keyEvent() {
        _searchKeyEvent.value = true
    }

    override fun keyEventEnd() {
        _searchKeyEvent.value = false
    }
}