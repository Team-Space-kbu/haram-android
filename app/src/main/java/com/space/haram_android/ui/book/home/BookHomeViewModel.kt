package com.space.haram_android.ui.book.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.data.ResultData
import com.space.data.model.BookHomeView
import com.space.data.res.book.BookHomeReq
import com.space.domain.usecase.book.BookRepository
import com.space.haram_android.adapter.KeyEventListener
import com.space.haram_android.adapter.BookViewListener
import com.space.shared.annotation.IoDispatcher
import com.space.shared.annotation.MainDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BookHomeViewModel @Inject constructor(
    private val bookRepository: BookRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _homeForm: MutableLiveData<BookHomeReq?> = MutableLiveData<BookHomeReq?>()
    val homeInfo: LiveData<BookHomeReq?> = _homeForm

    private val _serverStatus = MutableLiveData<Boolean>(true)
    val serverStatus: LiveData<Boolean> = _serverStatus

    private val _viewListener = MutableLiveData<BookHomeView>()
    val viewListener: LiveData<BookHomeView> = _viewListener

    val bindingViewListener = object : BookViewListener {
        override fun setViewType(path: Int) {
            _viewListener.value = BookHomeView(viewPath = path, viewStatus = true)
        }

        override fun clearViewType() {
            _viewListener.value = BookHomeView(viewStatus = false)
        }
    }

    val bindingListener = object : KeyEventListener {
        override fun keyEvent() {
            _viewListener.value = BookHomeView(keyEvent = true)
        }

        override fun keyEventEnd() {
            _viewListener.value = BookHomeView(keyEvent = false)
        }
    }

    init {
        viewModelScope.launch(ioDispatcher) {
            bookRepository.getBookHomeInfo().let {
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