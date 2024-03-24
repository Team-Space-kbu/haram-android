package com.space.book.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.domain.usecase.book.BookHomeUseCase
import com.space.navigator.view.NavigatorImage
import com.space.shared.data.book.BookHome
import com.space.shared.mapCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class BookHomeViewModel @Inject constructor(
    private val homeUseCase: BookHomeUseCase,
) : ViewModel() {

    private val _bookHome: MutableLiveData<BookHome> = MutableLiveData<BookHome>()
    val bookHome: LiveData<BookHome> = _bookHome

    @Inject
    lateinit var navigatorImage: NavigatorImage
    init {
        viewModelScope.launch {
            val result = async { homeUseCase() }.await()
            result.mapCatching(
                onSuccess = { bookHome ->
                    _bookHome.value = bookHome
                },
                onError = {
                    Timber.d(it.message)
                }
            )
        }
    }

}