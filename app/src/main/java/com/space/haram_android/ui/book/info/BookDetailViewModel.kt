package com.space.haram_android.ui.book.info

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.data.ResultData
import com.space.data.response.book.BookDetailReq
import com.space.domain.usecase.function.book.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BookDetailViewModel @Inject constructor(
    private val bookRepository: BookRepository
) : ViewModel() {

    private val _detailForm: MutableLiveData<BookDetailReq?> = MutableLiveData<BookDetailReq?>()
    val detailForm: LiveData<BookDetailReq?> = _detailForm

    private val _serverStatus = MutableLiveData<Boolean>(true)
    val serverStatus: LiveData<Boolean> = _serverStatus

    fun getBookDetail(url: String) {
        viewModelScope.launch{
            bookRepository.getBookDetailInfo(url).let {
                when (it) {
                    is ResultData.Success<BookDetailReq> -> _detailForm.value = it.body

                    is ResultData.Error -> {
                        Log.d("BookDetailInfo", it.throwable.message.toString())
                        _serverStatus.value = false
                    }

                    else -> _serverStatus.value = false
                }

            }
        }
    }
}