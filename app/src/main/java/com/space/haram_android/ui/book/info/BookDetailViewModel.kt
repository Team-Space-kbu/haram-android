package com.space.haram_android.ui.book.info

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.data.ResultData
import com.space.data.response.book.data.BookDetailInfo
import com.space.data.response.book.data.BookKeepInfo
import com.space.domain.usecase.function.book.BookRepository
import com.space.shared.annotation.IoDispatcher
import com.space.shared.annotation.MainDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BookDetailViewModel @Inject constructor(
    private val bookRepository: BookRepository,
    @MainDispatcher private val MainDispatcher: CoroutineDispatcher,
    @IoDispatcher private val IODispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _detailForm: MutableLiveData<BookDetailInfo?> = MutableLiveData<BookDetailInfo?>()
    val detailForm: LiveData<BookDetailInfo?> = _detailForm

    private val _keepForm: MutableLiveData<List<BookKeepInfo>?> =
        MutableLiveData<List<BookKeepInfo>?>()
    val keepForm: LiveData<List<BookKeepInfo>?> = _keepForm

    private val _serverStatus = MutableLiveData<Boolean>(true)
    val serverStatus: LiveData<Boolean> = _serverStatus

    fun getBookDetail(path: Int) {
        viewModelScope.launch(IODispatcher) {
            bookRepository.getBookDetailInfo(path).let {
                withContext(MainDispatcher) {
                    when (it) {
                        is ResultData.Success<BookDetailInfo> -> _detailForm.value = it.body

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

    fun getBookKeep(path: Int) {
        viewModelScope.launch(IODispatcher) {
            bookRepository.getBookDetailKeep(path).let {
                withContext(MainDispatcher) {
                    when (it) {
                        is ResultData.Success<List<BookKeepInfo>> -> _keepForm.value = it.body

                        is ResultData.Error -> {
                            Log.d("BookDetailKeep", it.throwable.message.toString())
                            _serverStatus.value = false
                        }

                        else -> _serverStatus.value = false
                    }
                }
            }
        }
    }
}