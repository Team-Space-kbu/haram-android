package com.space.rothem.ui.reserved

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.core_ui.base.BaseViewModel
import com.space.domain.rothem.RothemIsDeleteReserved
import com.space.domain.rothem.RothemIsReserved
import com.space.shared.UiStatus
import com.space.shared.UiStatusType
import com.space.shared.data.rothem.Reservation
import com.space.shared.mapCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ReservedDetailViewModel @Inject constructor(
    private val rothemIsReserved: RothemIsReserved,
    private val rothemIsDeleteReserve: RothemIsDeleteReserved
) : BaseViewModel<Reservation>() {


    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status

    init {
        viewModelScope.launch {
            val result = async { rothemIsReserved() }.await()
            result.mapCatching(
                onSuccess = {
                    _view.value = UiStatus(UiStatusType.SUCCESS, it)
                },
                onError = ::setIntranetData
            )
        }
    }


    fun deleteReserved() {
        viewModelScope.launch {
            val seq = view.value!!.data?.reservationSeq ?: return@launch
            val result = async { rothemIsDeleteReserve(seq) }.await()
            result.mapCatching(
                onSuccess = { _ ->
                    _status.value = true
                },
                onError = { throwable ->
                    Timber.d(throwable)
                    _status.value = false
                }
            )
        }
    }
}