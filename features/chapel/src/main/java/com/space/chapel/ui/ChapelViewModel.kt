package com.space.chapel.ui

import androidx.lifecycle.viewModelScope
import com.space.core_ui.base.BaseViewModel
import com.space.domain.chapel.ChapelUseCase
import com.space.shared.UiStatusType
import com.space.shared.UiStatus
import com.space.shared.data.chapel.Chapel
import com.space.shared.mapCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChapelViewModel @Inject constructor(
    private val chapelUseCase: ChapelUseCase
) : BaseViewModel<Chapel>() {

    init {
        viewModelScope.launch {
            val info = async { chapelUseCase() }.await()
            info.mapCatching(
                onSuccess = { chapel ->
                    _view.value = UiStatus(UiStatusType.SUCCESS, chapel)
                },
                onError = ::setIntranetData
            )
        }
    }
}