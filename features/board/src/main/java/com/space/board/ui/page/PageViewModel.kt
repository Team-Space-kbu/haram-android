package com.space.board.ui.page


import androidx.lifecycle.viewModelScope
import com.space.core_ui.base.BaseViewModel
import com.space.domain.board.BoardPageUseCase
import com.space.navigator.view.NavigatorLogin
import com.space.shared.UiStatus
import com.space.shared.UiStatusType
import com.space.shared.data.board.BoardPage
import com.space.shared.mapCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PageViewModel @Inject constructor(
    private val boardPageUseCase: BoardPageUseCase
) : BaseViewModel<BoardPage>() {


    fun getPages(
        type: Int,
        page: Int = 1
    ) {
        viewModelScope.launch {
            val result = async { boardPageUseCase(Pair(type, page)) }.await()
            result.mapCatching(
                onSuccess = { boardPage ->
                    _view.value = UiStatus(UiStatusType.SUCCESS, boardPage)
                },
                onError = ::setIntranetData
            )
        }
    }
}