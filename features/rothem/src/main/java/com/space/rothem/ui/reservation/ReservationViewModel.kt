package com.space.rothem.ui.reservation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.domain.usecase.rothem.RothemReservationsDetail
import com.space.shared.data.rothem.ReservationCalendar
import com.space.shared.data.rothem.RoomReservation
import com.space.shared.data.rothem.RothemPolicy
import com.space.shared.data.rothem.RothemTime
import com.space.shared.mapCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ReservationViewModel @Inject constructor(
    private val rothemReservationsDetail: RothemReservationsDetail
) : ViewModel() {

    private val policyData: MutableMap<Int, Boolean> = mutableMapOf()
    private val _dataList = MutableLiveData<MutableMap<Int, RothemTime>>()
    private val _rothem = MutableLiveData<RoomReservation>()

    val rothem: LiveData<RoomReservation> = _rothem
    val dataList: LiveData<MutableMap<Int, RothemTime>> = _dataList
    val selectCalender = MutableLiveData<ReservationCalendar>()
    val nameLive = MutableLiveData<String>()
    val cellPhone = MutableLiveData<String>()


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

    fun setPolicy(rothemPolicy: RothemPolicy, isChecked: Boolean) {
        policyData[rothemPolicy.policySeq] = isChecked
    }

    fun getReservationInfo(seq: String) {
        viewModelScope.launch {
            val result = async { rothemReservationsDetail(seq) }.await()
            result.mapCatching(
                onSuccess = { reservation ->
                    _rothem.value = reservation
                    selectCalender.value =
                        reservation.calendarResponses.filter { it.isAvailable }.toList().first()
                },
                onError = { throwable ->
                    Timber.d(throwable.message)
                }
            )
        }
    }

}