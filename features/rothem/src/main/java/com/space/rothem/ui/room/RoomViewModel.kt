package com.space.rothem.ui.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.domain.rothem.RothemDetail
import com.space.shared.data.rothem.Room
import com.space.shared.data.rothem.RoomDetail
import com.space.shared.mapCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RoomViewModel @Inject constructor(
    private val rothemDetail: RothemDetail
) : ViewModel() {

    private val _rothem: MutableLiveData<RoomDetail> = MutableLiveData<RoomDetail>()
    val rothem: LiveData<RoomDetail> = _rothem

    fun onRothemDetail(room: Room) {
        viewModelScope.launch {
            val result = async { rothemDetail(room.roomSeq.toString()) }.await()
            result.mapCatching(
                onSuccess = { roomDetail ->
                    _rothem.value = roomDetail
                },
                onError = { throwable ->
                    Timber.d(throwable.message)
                }
            )
        }
    }
}