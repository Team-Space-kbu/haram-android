package com.space.book.ui.home

import androidx.lifecycle.viewModelScope
import com.space.core_ui.base.BaseViewModel
import com.space.domain.book.BookHomeUseCase
import com.space.navigator.view.NavigatorImage
import com.space.navigator.view.NavigatorLogin
import com.space.shared.UiStatus
import com.space.shared.UiStatusType
import com.space.shared.data.book.BookHome
import com.space.shared.mapCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookHomeViewModel @Inject constructor(
    private val homeUseCase: BookHomeUseCase,
) : BaseViewModel<BookHome>() {


    @Inject
    lateinit var navigatorImage: NavigatorImage


    init {
        viewModelScope.launch {
            val result = async { homeUseCase() }.await()
            result.mapCatching(
                onSuccess = { bookHome ->
                    _view.value = UiStatus(UiStatusType.SUCCESS, bookHome)
                },
                onError = ::setIntranetData
            )
        }
    }

}