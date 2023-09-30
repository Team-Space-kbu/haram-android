package com.space.biblemon.ui.book.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.space.data.ResultData
import com.space.data.res.book.BookHomeReq
import com.space.domain.usecase.book.BookUsecase
import com.space.biblemon.base.view.BaseViewModel
import com.space.shared.annotation.IoDispatcher
import com.space.shared.annotation.MainDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BookHomeViewModel @Inject constructor(
    private val bookUsecase: BookUsecase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher
) : BaseViewModel() {

    private val _homeForm: MutableLiveData<BookHomeReq?> = MutableLiveData<BookHomeReq?>()
    val homeInfo: LiveData<BookHomeReq?> = _homeForm

    private val _serverStatus = MutableLiveData<Boolean>(true)
    val serverStatus: LiveData<Boolean> = _serverStatus

    init {
        viewModelScope.launch(ioDispatcher) {
            bookUsecase.getBookHomeInfo().let {
                withContext(mainDispatcher) {
                    when (it) {
                        is ResultData.Success<BookHomeReq> -> _homeForm.value = it.body
                        is ResultData.Error -> _serverStatus.value = false
                        else -> _serverStatus.value = false
                    }
                }
            }
        }
    }


}