package com.space.chapel.ui

import androidx.lifecycle.viewModelScope
import com.space.core_ui.base.BaseIntranetViewModel
import com.space.domain.usecase.chapel.ChapelUseCase
import com.space.navigator.view.NavigatorLogin
import com.space.shared.UiStatusType
import com.space.shared.UiStatus
import com.space.shared.data.chapel.Chapel
import com.space.shared.mapCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ChapelViewModel @Inject constructor(
    private val chapelUseCase: ChapelUseCase
) : BaseIntranetViewModel<Chapel>() {

    init {
        viewModelScope.launch {
            val info = async { chapelUseCase() }.await()
            info.mapCatching(
                onSuccess = { chapel ->
                    _liveData.value = UiStatus(UiStatusType.SUCCESS, chapel)
                },
                onError = { error ->
                    Timber.d(error.message)
                    setIntranetData(error)
                }
            )
        }
    }
}