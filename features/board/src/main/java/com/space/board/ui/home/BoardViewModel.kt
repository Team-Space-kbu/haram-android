package com.space.board.ui.home

import androidx.lifecycle.viewModelScope
import com.space.core_ui.base.BaseViewModel
import com.space.domain.board.BoardCategoryUseCase
import com.space.navigator.view.NavigatorBoard
import com.space.shared.UiStatus
import com.space.shared.UiStatusType
import com.space.shared.data.board.BoardCategory
import com.space.shared.mapCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BoardViewModel @Inject constructor(
    private val boardCategoryUseCase: BoardCategoryUseCase
) : BaseViewModel<List<BoardCategory>>() {

    @Inject
    lateinit var boardNavigatorBoard: NavigatorBoard

    init {
        viewModelScope.launch {
            val info = async { boardCategoryUseCase() }.await()
            info.mapCatching(
                onSuccess = { category ->
                    _view.value = UiStatus(UiStatusType.SUCCESS, category)
                },
                onError = ::setIntranetData
            )
        }
    }
}