package com.space.haram_android.ui.book.info

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.haram_android.common.data.ResultData
import com.space.haram_android.common.data.response.book.BookDetailReq
import com.space.haram_android.usecase.function.book.BookRepository
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
        viewModelScope.launch(Dispatchers.IO) {
            bookRepository.getBookDetailInfo(url).let {
                withContext(Dispatchers.Main) {
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
}