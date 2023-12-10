package com.space.bible.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.shared.data.book.BookHome
import com.space.shared.result.succeeded
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class BibleViewModel @Inject constructor(

) : ViewModel() {

    private val _bookHome: MutableLiveData<BookHome> = MutableLiveData<BookHome>()
    val bookHome: LiveData<BookHome> = _bookHome

    init {
//        viewModelScope.launch {
//            val result = async { homeUseCase() }
//            _bookHome.value = result.await().succeeded()
//        }
    }
}