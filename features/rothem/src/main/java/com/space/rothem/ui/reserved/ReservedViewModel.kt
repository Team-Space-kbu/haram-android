package com.space.rothem.ui.reserved

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.core_ui.base.BaseViewModel
import com.space.domain.rothem.RothemRoomReserved
import com.space.domain.rothem.RothemReservedDetail
import com.space.shared.UiStatus
import com.space.shared.UiStatusType
import com.space.shared.exception.ExistReservationException
import com.space.shared.data.rothem.ReservationCalendar
import com.space.shared.data.rothem.ReservationStatus
import com.space.shared.data.rothem.RoomReservation
import com.space.shared.data.rothem.RothemPolicy
import com.space.shared.data.rothem.RothemTime
import com.space.shared.mapCatching
import com.space.shared.model.PolicyReqModel
import com.space.shared.model.ReservationsModel
import com.space.shared.model.TimeReqModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class ReservedViewModel @Inject constructor(
    private val detail: RothemReservedDetail,
    private val reservations: RothemRoomReserved,
) : BaseViewModel<RoomReservation>() {

    private val policyData: MutableMap<Int, Boolean> = mutableMapOf()
    private val _dataList = MutableLiveData<MutableMap<Int, RothemTime>>()
    private val _request = MutableLiveData<ReservationStatus>()


    val dataList: LiveData<MutableMap<Int, RothemTime>> = _dataList
    val selectCalender = MutableLiveData<ReservationCalendar>()
    val nameLive = MutableLiveData<String>()
    val cellPhone = MutableLiveData<String>()
    val request: LiveData<ReservationStatus> = _request


    init {
        _dataList.value = mutableMapOf()
    }

    fun removeData(id: Int) {
        _dataList.value?.remove(id)
        _dataList.notifyObserver()
    }

    fun updateData(newValue: RothemTime) {
        _dataList.value?.put(newValue.timeSeq, newValue)
        _dataList.notifyObserver()
    }

    fun setPolicy(rothemPolicy: RothemPolicy, isChecked: Boolean) {
        policyData[rothemPolicy.policySeq] = isChecked
    }

    fun isValidTimeSlot(selectKey: Int, time: RothemTime): Boolean {
        val times = dataList.value?.get(selectKey)?.meridiem ?: return false
        return ((selectKey - 1) == time.timeSeq || (selectKey + 1) == time.timeSeq) && time.meridiem == times
    }

    private fun isValidName(): Boolean {
        return nameLive.value?.isNotEmpty() == true
    }

    fun requestReservation(seq: String) {
        viewModelScope.launch {
            if (!isPolicyChecked()) {
                _request.value = ReservationStatus.POLICY
                return@launch
            }
            if (!isTimeSlot()) {
                _request.value = ReservationStatus.TIME
                return@launch
            }
            if (!isValidName()) {
                _request.value = ReservationStatus.NAME
                return@launch
            }
            if (!isValidCellphone()) {
                _request.value = ReservationStatus.PHONE
                return@launch
            }
            val model = ReservationsModel(
                nameLive.value.toString(),
                cellPhone.value.toString(),
                selectCalender.value!!.calendarSeq,
                policyData.map { PolicyReqModel(it.key, if (it.value) "Y" else "N") }.toList(),
                dataList.value!!.map { TimeReqModel(it.key) }.toList()
            )
            val result = async { reservations(Pair(seq, model)) }.await()
            result.mapCatching(
                onSuccess = {
                    _request.value = if (it) ReservationStatus.PASS else ReservationStatus.ERROR
                },
                onError = ::handleError
            )
        }
    }

    private fun handleError(throwable: Throwable) {
        Timber.i(throwable.message)
        _request.value = when (throwable) {
            is ExistReservationException -> ReservationStatus.EXIST
            is UnknownHostException, is SocketTimeoutException -> ReservationStatus.HOST
            else -> ReservationStatus.ERROR
        }
    }

    fun getReservationInfo(seq: String) {
        viewModelScope.launch {
            val result = async { detail(seq) }.await()
            result.mapCatching(
                onSuccess = { reservation ->
                    _view.value = UiStatus(UiStatusType.SUCCESS, reservation)
                    selectCalender.value =
                        reservation.calendarResponses.filter { it.isAvailable }.toList().first()
                },
                onError = { throwable ->
                    Timber.d(throwable.message)
                }
            )
        }
    }

    private fun isValidCellphone(): Boolean {
        val phoneNumber = cellPhone.value.toString()
        val pattern1 = Regex("^010-?([0-9]{4})-?([0-9]{4})$")
        val pattern2 = Regex("^010-[0-9]{4}-[0-9]{4}\$")
        return when {
            pattern1.matches(phoneNumber) -> {
                val pattern = Regex("(\\d{3})(\\d{4})(\\d{4})")
                cellPhone.value = phoneNumber.replace(pattern, "$1-$2-$3")
                true
            }

            pattern2.matches(phoneNumber) -> {
                true
            }

            else -> false
        }
    }

    private fun isTimeSlot(): Boolean = !dataList.value.isNullOrEmpty()

    private fun isPolicyChecked(): Boolean {
        val policy = view.value?.data ?: return false
        return policy.policyResponses.all { response ->
            !response.isRequired || policyData[response.policySeq] == true
        }
    }

    private fun <T> MutableLiveData<T>.notifyObserver() {
        this.postValue(this.value)
    }
}

