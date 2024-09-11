package com.space.class_room.ui.home

import androidx.lifecycle.viewModelScope
import com.space.core_ui.base.BaseViewModel
import com.space.domain.class_room.ClassRoomUseCase
import com.space.shared.UiStatus
import com.space.shared.UiStatusType
import com.space.shared.mapCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ClassRoomViewModel @Inject constructor(
    private val classRoomUseCase: ClassRoomUseCase
) : BaseViewModel<List<String>>() {

    init {
        viewModelScope.launch {
            val result = async { classRoomUseCase() }.await()
            result.mapCatching(
                onSuccess = {
                    _view.value = UiStatus(UiStatusType.SUCCESS, it)
                },
                onError = ::setIntranetData
            )
        }
    }
}