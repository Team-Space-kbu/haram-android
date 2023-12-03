package com.space.biblemon.ui.book.info

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.space.shared.result.ResultData
import com.space.shared.data.book.BookDetailInfo
import com.space.shared.data.book.BookKeep
import com.space.domain.usecase.BookUsecase
import com.space.biblemon.base.view.BaseViewModel
import com.space.shared.annotation.IoDispatcher
import com.space.shared.annotation.MainDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class BookDetailViewModel @Inject internal constructor(
    private val bookUsecase: BookUsecase,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : BaseViewModel() {

    val isLoading: ObservableBoolean = ObservableBoolean(false)

    private val _detailForm: MutableLiveData<BookDetailInfo?> = MutableLiveData<BookDetailInfo?>()
    val detailForm: LiveData<BookDetailInfo?> = _detailForm

    private val _keepForm: MutableLiveData<BookKeep?> = MutableLiveData<BookKeep?>()
    val keepForm: LiveData<BookKeep?> = _keepForm

    private val _serverStatus = MutableLiveData<Boolean>(true)
    val serverStatus: LiveData<Boolean> = _serverStatus

    fun getBookDetail(path: Int) {
        viewModelScope.launch(ioDispatcher) {
            bookUsecase.getBookDetailInfo(path).let {
                withContext(mainDispatcher) {
                    if (it is ResultData.Success<BookDetailInfo>) {
                        _detailForm.value = it.body
                    } else {
                        _serverStatus.value = false
                    }

                }
            }
        }
    }

    fun getBookKeep(path: Int) {
        viewModelScope.launch(ioDispatcher) {
            bookUsecase.getBookDetailKeep(path).let {
                withContext(mainDispatcher) {
                    when (it) {
                        is ResultData.Success<BookKeep> -> {
                            _keepForm.value = it.body
                            if (it.body.keepBooks.display == 0){
                                isLoading.set(false)
                            }
                            else{
                                isLoading.set(true)
                            }
                        }

                        is ResultData.Error -> {
                            Timber.d(it.throwable.message.toString())
                            isLoading.set(false)

//                            _serverStatus.value = false
                        }

                        is ResultData.Unauthorized -> {
                            Timber.d(it.throwable.message.toString())
                            isLoading.set(false)
                        }
                    }
                }
            }
        }
    }
}
