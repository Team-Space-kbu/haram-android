package com.space.rothem.ui.room

import androidx.lifecycle.viewModelScope
import com.space.core_ui.base.BaseViewModel
import com.space.domain.rothem.RothemDetail
import com.space.shared.UiStatus
import com.space.shared.UiStatusType
import com.space.shared.data.rothem.Room
import com.space.shared.data.rothem.RoomDetail
import com.space.shared.mapCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomViewModel @Inject constructor(
    private val rothemDetail: RothemDetail
) : BaseViewModel<RoomDetail>() {

    fun onRothemDetail(room: Room) {
        viewModelScope.launch {
            val result = async { rothemDetail(room.roomSeq.toString()) }.await()
            result.mapCatching(
                onSuccess = { roomDetail ->
                    _view.value = UiStatus(UiStatusType.SUCCESS, roomDetail)
                },
                onError = ::setIntranetData
            )
        }
    }
}