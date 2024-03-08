package com.space.rothem.ui.reservation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.domain.usecase.rothem.RothemReservationsDetail
import com.space.shared.data.rothem.ReservationCalendar
import com.space.shared.data.rothem.RoomReservation
import com.space.shared.data.rothem.RothemTime
import com.space.shared.mapCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class ReservationViewModel @Inject constructor(
    private val rothemReservationsDetail: RothemReservationsDetail
) : ViewModel() {


    private val _rothem = MutableLiveData<RoomReservation>()
    val rothem: LiveData<RoomReservation> = _rothem

    private val _dataList = MutableLiveData<MutableMap<Int, RothemTime>>()
    val dataList: LiveData<MutableMap<Int, RothemTime>> = _dataList

    val selectCalender = MutableLiveData<ReservationCalendar>()

    init {
        _dataList.value = mutableMapOf()
    }

    fun removeData(id: Int) {
        _dataList.value?.remove(id)
        _dataList.postValue(_dataList.value)
    }

    fun updateData(newValue: RothemTime) {
        _dataList.value?.put(newValue.timeSeq, newValue)
        _dataList.postValue(_dataList.value)
    }

    fun getReservationInfo(seq: String) {
        viewModelScope.launch {
            val result = async { rothemReservationsDetail(seq) }.await()
            result.mapCatching(
                onSuccess = { reservation ->
                    _rothem.value = reservation
                    val current =
                        LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd"))
                    reservation.calendarResponses.forEach {
                        if (current.toInt().toString() == it.date) {
                            selectCalender.value = it
                        }
                    }
                },
                onError = { throwable ->
                    Timber.d(throwable.message)
                }
            )
        }
    }

}