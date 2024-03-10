package com.space.rothem.ui.reservation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.domain.usecase.rothem.RothemReservations
import com.space.domain.usecase.rothem.RothemReservationsDetail
import com.space.shared.common.exception.ExistReservation
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
class ReservationViewModel @Inject constructor(
    private val detail: RothemReservationsDetail,
    private val reservations: RothemReservations,
) : ViewModel() {

    private val policyData: MutableMap<Int, Boolean> = mutableMapOf()
    private val _dataList = MutableLiveData<MutableMap<Int, RothemTime>>()
    private val _rothem = MutableLiveData<RoomReservation>()
    private val _request = MutableLiveData<ReservationStatus>()

    val rothem: LiveData<RoomReservation> = _rothem
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
        _dataList.postValue(_dataList.value)
    }

    fun updateData(newValue: RothemTime) {
        _dataList.value?.put(newValue.timeSeq, newValue)
        _dataList.postValue(_dataList.value)
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
            if (!isTimeSlot()) {
                _request.value = ReservationStatus.TIME
                return@launch
            }
            if (!isPolicyChecked()) {
                _request.value = ReservationStatus.POLICY
                return@launch
            }
            if (!isValidCellphone()) {
                _request.value = ReservationStatus.PHONE
                return@launch
            }
            if (!isValidName()) {
                _request.value = ReservationStatus.NAME
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
                    if (it) {
                        _request.value = ReservationStatus.PASS
                    } else {
                        _request.value = ReservationStatus.ERROR
                    }
                },
                onError = {
                    Timber.d(it.message)
                    when (it) {
                        is ExistReservation -> {
                            _request.value = ReservationStatus.EXIST
                        }

                        is UnknownHostException -> {
                            _request.value = ReservationStatus.HOST

                        }

                        is SocketTimeoutException -> {
                            _request.value = ReservationStatus.HOST

                        }

                        is Exception -> {
                            _request.value = ReservationStatus.ERROR

                        }
                    }
                }
            )
        }
    }

    fun getReservationInfo(seq: String) {
        viewModelScope.launch {
            val result = async { detail(seq) }.await()
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

    private fun isTimeSlot(): Boolean {
        return dataList.value?.isNotEmpty() == true
    }

    private fun isPolicyChecked(): Boolean {
        val policy = rothem.value ?: return false
        Timber.d(policyData.values.toString())
        return policy.policyResponses.all { response ->
            response.isRequired && policyData.containsKey(response.policySeq) && policyData[response.policySeq] == true
        }
    }
}

